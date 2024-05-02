package com.example.optivote.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class SessionDto(val sessionId: Int,
                      val sessionNumber: Int,
                      val year: LocalDate,
                      val startDate: LocalDate,
                      val endDate: LocalDate)