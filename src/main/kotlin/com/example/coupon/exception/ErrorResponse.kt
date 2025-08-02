package com.example.coupon.exception

import java.time.LocalDateTime

data class ErrorResponse(
	val message: String,
	val timestamp: LocalDateTime,
)
