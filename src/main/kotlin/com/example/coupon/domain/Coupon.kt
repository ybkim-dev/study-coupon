package com.example.coupon.domain

class Coupon(
	val id: Long = 0,
	val name: String,
	val totalQuantity: Long,
) {
	fun validateQuantity() {
		check(totalQuantity > 0) { "해당 쿠폰은 잔여 수량이 없습니다." }
	}
}