package com.example.coupon.service

import com.example.coupon.config.TestDataSourceConfig
import com.example.coupon.repository.CouponRepository
import com.example.coupon.repository.entity.CouponEntity
import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.data.repository.findByIdOrNull

@Import(TestDataSourceConfig::class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class CouponServiceTest(
	private val couponRepository: CouponRepository,
) : FeatureSpec({
	beforeEach {
		couponRepository.deleteAll()
	}

	afterEach {
		couponRepository.deleteAll()
	}

	feature("coupon repository 는 save 명령을 받으면") {
		scenario("coupon entity를 1회 저장한다") {
			val coupon = CouponFixture.create(name = "coupon 1")

			val saved = couponRepository.save(coupon)
			val found = couponRepository.findByIdOrNull(saved.id)
			val foundAll = couponRepository.findAll()

			found?.id shouldBe saved.id
			foundAll.size shouldBe 1
		}
	}
})

class CouponFixture {
	companion object {
		fun create(name: String) = CouponEntity(name = name)
	}
}