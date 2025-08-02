package com.example.coupon.service

import com.example.coupon.domain.UserCoupon
import com.example.coupon.repository.CouponFixture
import com.example.coupon.repository.CouponRepository
import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class CouponServiceTest(
	private val couponService: CouponService,
	private val couponRepository: CouponRepository,
) : FeatureSpec({
	beforeTest {
		val coupon = CouponFixture.create(
			name = "test coupon",
			totalQuantity = 100
		)

		couponService.createCoupon(coupon.toDomain())
	}

	feature("1개의 쿠폰에 동시에 100명이 접근하여 발행할 때") {
		scenario("최종으로 발행된 쿠폰의 수는 100개로 일치한다.") {
			val jobs = (1..100).map { it ->
				async {
					couponService.publishCoupon(UserCoupon(userId = it.toLong(), couponId = 1L))
				}
			}.awaitAll()

			val coupon = requireNotNull(couponRepository.findByIdOrNull(1L))

			jobs.count{it == true}  shouldBe 100L
			coupon.totalQuantity shouldBe 0L
			coupon.id shouldBe 1L
		}
	}
})