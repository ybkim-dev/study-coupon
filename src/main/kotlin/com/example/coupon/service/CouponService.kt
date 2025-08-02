package com.example.coupon.service

import com.example.coupon.config.RedisLockHelper
import com.example.coupon.domain.Coupon
import com.example.coupon.domain.UserCoupon
import com.example.coupon.repository.CouponRepository
import com.example.coupon.repository.UserCouponRepository
import com.example.coupon.repository.entity.CouponEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CouponService(
	private val couponRepository: CouponRepository,
	private val userCouponRepository: UserCouponRepository,
	private val redisLockHelper: RedisLockHelper,
) {
	@Transactional
	fun createCoupon(coupon: Coupon) = couponRepository.save(CouponEntity(coupon)).toDomain()

	@Transactional
	fun publishCoupon(userCoupon: UserCoupon): Boolean {
		return redisLockHelper.withLock(
			key = userCoupon.makeRedisLockKey(),
		) {
			val coupon = couponRepository.findByIdOrNull(userCoupon.couponId)
				?: throw NoSuchElementException("쿠폰 없음 couponId : ${userCoupon.couponId}")
			if (userCouponRepository.existsByCouponIdAndUserId(userCoupon.couponId, userCoupon.userId)) {
				throw IllegalStateException("이미 쿠폰을 발급받은 사용자입니다. userId : ${userCoupon.userId}, couponId: ${userCoupon.couponId}")
			}
			coupon.toDomain().validateQuantity()

			coupon.publishCoupon()

			true
		}
	}
}