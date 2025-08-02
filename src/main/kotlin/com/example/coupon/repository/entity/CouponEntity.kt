package com.example.coupon.repository.entity

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
)