package com.example.coupon.repository

import com.example.coupon.repository.entity.UserCouponEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserCouponRepository: JpaRepository<UserCouponEntity, Long>