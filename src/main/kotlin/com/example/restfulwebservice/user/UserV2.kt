package com.example.restfulwebservice.user

import com.fasterxml.jackson.annotation.JsonFilter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDate
import javax.validation.constraints.Size

//@JsonIgnoreProperties(value = ["password"])
@JsonFilter("UserInfoV2")
data class UserV2(
    var id: Int?,
    @field:Size(min = 2, message = "Name은 2글자 이상 입력해주세요")
    val name: String,
    val joinDate: LocalDate,
    val password: String,
    val ssn: String,
    val grade: String,
)
