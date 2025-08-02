package com.example.coupon.controller

import com.example.coupon.controller.data.CouponCreateRequest
import com.example.coupon.controller.data.CouponCreateResponse
import com.example.coupon.controller.data.CouponPublishResponse
import com.example.coupon.domain.UserCoupon
import com.example.coupon.service.CouponService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/coupons")
class CouponController(
	private val couponService: CouponService,
) {
	@PostMapping
	fun createCoupon(@RequestBody couponCreateRequest: CouponCreateRequest): CouponCreateResponse {
		return CouponCreateResponse(couponService.createCoupon(couponCreateRequest.toDomain()))
	}

	@PostMapping("/{couponId}/users/{userId}")
	fun publishCouponToUser(
		@PathVariable couponId: Long,
		@PathVariable userId: Long,
	): CouponPublishResponse {
		return CouponPublishResponse(couponService.publishCoupon(UserCoupon(couponId = couponId, userId = userId)))
	}
}