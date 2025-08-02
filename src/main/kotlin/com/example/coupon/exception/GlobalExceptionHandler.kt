package com.example.coupon.exception

import com.example.coupon.util.logger
import java.time.LocalDateTime
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
	companion object {
		private const val INVALID_INPUT = "잘못된 입력입니다. 재확인해주세요."
		private const val UNKNOWN_ERROR = "알 수 없는 서버 내부 에러가 발생했습니다."
		private const val STATE_ERROR = "서버 내부 상태 검증 오류가 발생했습니다."
	}

	@ExceptionHandler(NoSuchElementException::class)
	fun handleNoSuchElementException(ex: NoSuchElementException): ResponseEntity<ErrorResponse> =
		handleCommonException(ex = ex, httpStatus = BAD_REQUEST, message = ex.message ?: INVALID_INPUT)

	@ExceptionHandler(IllegalArgumentException::class)
	fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<ErrorResponse> =
		handleCommonException(ex = ex, httpStatus = BAD_REQUEST, message = ex.message ?: INVALID_INPUT)

	@ExceptionHandler(IllegalStateException::class)
	fun handleIllegalStateException(ex: IllegalStateException): ResponseEntity<ErrorResponse> =
		handleCommonException(ex = ex, httpStatus = INTERNAL_SERVER_ERROR, message = ex.message ?: STATE_ERROR)

	@ExceptionHandler(Exception::class)
	fun handleGeneralException(ex: Exception): ResponseEntity<ErrorResponse> {
		logger().error(ex.message, ex)

		val response =
			ErrorResponse(
				message = ex.message ?: UNKNOWN_ERROR,
				timestamp = LocalDateTime.now(),
			)

		return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(response)
	}

	private fun handleCommonException(
		ex: RuntimeException,
		httpStatus: HttpStatus,
		message: String,
	): ResponseEntity<ErrorResponse> {
		logger().warn(message, ex)

		val response =
			ErrorResponse(
				message = ex.message ?: message,
				timestamp = LocalDateTime.now(),
			)

		return ResponseEntity.status(httpStatus).body(response)
	}
}