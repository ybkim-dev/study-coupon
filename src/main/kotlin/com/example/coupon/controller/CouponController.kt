package com.example.coupon.controller

import com.example.coupon.controller.data.CouponCreateRequest
import com.example.coupon.controller.data.CouponCreateResponse
import com.example.coupon.service.CouponService
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
}