package com.example.restfulwebservice.helloworld

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController {
    // GET
    // /hello-world (endpoint)
    @GetMapping("/hello-world")
    fun helloWorld(): String = "Hello World"

    @GetMapping("/hello-world-bean")
    fun helloWorldBean(): HelloWorldBean = HelloWorldBean("Hello World")

    @GetMapping("/hello-world-bean/path-variable/{name}")
    fun helloWorldBean(
        @PathVariable name: String
    ): HelloWorldBean = HelloWorldBean("Hello World, $name")

}
