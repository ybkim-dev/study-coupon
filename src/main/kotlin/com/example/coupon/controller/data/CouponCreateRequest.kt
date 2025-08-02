package com.example.coupon.controller.data

import com.example.coupon.domain.Coupon

data class CouponCreateRequest(
	val name: String,
	val totalQuantity: Long,
) {
	fun toDomain() = Coupon(
		name = name,
		totalQuantity = totalQuantity,
	)
}
