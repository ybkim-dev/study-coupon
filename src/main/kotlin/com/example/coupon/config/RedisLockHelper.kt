package com.example.coupon.config

import java.util.concurrent.TimeUnit
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Component

@Component
class RedisLockHelper(
	private val redisson: RedissonClient,
) {
	fun<T> withLock(
		key: String,
		waitTimeMs: Long = 3_000,
		leaseTimeMs: Long = 10_000,
		block:() -> T,
	): T {
		val lock = redisson.getLock(key)
		val acquired = lock.tryLock(waitTimeMs, leaseTimeMs, TimeUnit.MILLISECONDS)

		if(!acquired) throw IllegalStateException("Lock 획득에 실패했습니다. 다음에 다시 시도해주세요")
		return try {
			block()
		} finally {
			if(lock.isHeldByCurrentThread) lock.unlock()
		}
	}
}