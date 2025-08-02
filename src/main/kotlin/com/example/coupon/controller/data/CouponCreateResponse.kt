package com.example.coupon.controller.data

import com.example.coupon.domain.Coupon

data class CouponCreateResponse(
	val id: Long,
	val name: String,
) {
	constructor(coupon: Coupon) : this(coupon.id, coupon.name)
}