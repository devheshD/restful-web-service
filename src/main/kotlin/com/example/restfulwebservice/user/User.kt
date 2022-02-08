package com.example.restfulwebservice.user

import java.time.LocalDate
import javax.validation.constraints.Size

data class User(
    var id: Int?,

    @field:Size(min = 2, message = "Name은 2글자 이상 입력해주세요")
    val name: String,

    val joinDate: LocalDate,
)
