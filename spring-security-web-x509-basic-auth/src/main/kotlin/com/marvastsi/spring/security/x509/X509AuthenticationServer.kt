package com.marvastsi.spring.security.x509

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@SpringBootApplication
@EnableWebSecurity
class X509AuthenticationServer : WebSecurityConfigurerAdapter()

fun main(args: Array<String>) {
	runApplication<X509AuthenticationServer>(*args)
}
