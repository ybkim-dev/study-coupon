package com.example.coupon.domain

class UserCoupon(
	val id: Long = 0,
	val couponId: Long,
	val userId: Long,
) {
	fun makeRedisLockKey() = "lock:$couponId"
}