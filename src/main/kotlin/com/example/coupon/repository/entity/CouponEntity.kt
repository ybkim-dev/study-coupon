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
	var totalQuantity: Long,
) {
	constructor(coupon: Coupon) : this(
		id = coupon.id,
		name = coupon.name,
		totalQuantity = coupon.totalQuantity
	)

	fun toDomain() = Coupon(
		id = id,
		name = name,
		totalQuantity = totalQuantity,
	)

	fun publishCoupon() {
		check(totalQuantity > 0) { "해당 쿠폰은 잔여 수량이 없습니다." }

		totalQuantity -= 1
	}
}