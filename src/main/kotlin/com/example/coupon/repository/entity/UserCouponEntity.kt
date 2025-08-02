package com.example.coupon.repository.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table

@Table(
	name = "user_coupon",
	indexes = [
		Index(name = "ux_user_coupon_01", columnList = "coupon_id,user_id", unique = true)
	]
)
@Entity
class UserCouponEntity(
	@Id @GeneratedValue(strategy = IDENTITY)
	val id: Long = 0,
	val couponId: Long,
	val userId: Long,
)