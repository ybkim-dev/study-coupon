package com.example.coupon.config

import javax.sql.DataSource
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class TestDataSourceConfig {
	@Bean
	fun dataSource(): DataSource =
		DataSourceBuilder
			.create()
			.driverClassName("org.h2.Driver")
			.url("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;MODE=MySQL;DATABASE_TO_UPPER=false")
			.username("sa")
			.password("")
			.build()

	@Bean
	fun jpaProperties(): JpaProperties =
		JpaProperties().apply {
			this.properties["hibernate.dialect"] = "org.hibernate.dialect.H2Dialect"
			this.properties["hibernate.hbm2ddl.auto"] = "create"
		}
}
