package com.example.restfulwebservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import java.util.*

@SpringBootApplication
class RestfulWebServiceApplication

fun main(args: Array<String>) {
	runApplication<RestfulWebServiceApplication>(*args)
}

@Bean
fun localeResolver(): Unit = SessionLocaleResolver().setDefaultLocale(Locale.KOREA)
