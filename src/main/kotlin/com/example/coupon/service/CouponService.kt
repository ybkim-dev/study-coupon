package com.example.coupon.service

import com.example.coupon.domain.Coupon
import com.example.coupon.repository.CouponRepository
import com.example.coupon.repository.entity.CouponEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CouponService(
	private val couponRepository: CouponRepository,
) {
	@Transactional
	fun createCoupon(coupon: Coupon) =couponRepository.save(CouponEntity(coupon)).toDomain()
}