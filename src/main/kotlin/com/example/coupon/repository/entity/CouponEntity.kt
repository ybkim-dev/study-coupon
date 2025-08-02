package com.example.coupon.repository.entity

import com.example.coupon.domain.Coupon
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "coupon")
@Entity
class CouponEntity(
	@Id
	@GeneratedValue(strategy = IDENTITY)
	val id: Long = 0,
	val name: String,
) {
	constructor(coupon: Coupon) : this(
		id = coupon.id,
		name = coupon.name,
	)

	fun toDomain() = Coupon(
		id = id,
		name = name,
	)
}