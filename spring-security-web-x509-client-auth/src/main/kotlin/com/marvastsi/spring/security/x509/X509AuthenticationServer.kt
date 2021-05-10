package com.marvastsi.spring.security.x509

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

@SpringBootApplication
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class X509AuthenticationServer : WebSecurityConfigurerAdapter() {
	@Throws(Exception::class)
	override fun configure(http: HttpSecurity) {
		http.authorizeRequests().anyRequest().authenticated()
			.and()
			.x509()
			.subjectPrincipalRegex("CN=(.*?)(?:,|$)")
			.userDetailsService(userDetailsService())
	}

	@Bean
	override fun userDetailsService(): UserDetailsService? {
		return UserDetailsService { username ->
			if (username == "Bob") {
				return@UserDetailsService User(
					username,
					"qwert123",
					AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER")
				)
			}
			throw UsernameNotFoundException("User not found!")
		}
	}
}

fun main(args: Array<String>) {
	runApplication<X509AuthenticationServer>(*args)
}
