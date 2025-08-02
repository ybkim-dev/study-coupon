package com.example.coupon.repository

import com.example.coupon.repository.entity.CouponEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CouponRepository: JpaRepository<CouponEntity,  Long>

