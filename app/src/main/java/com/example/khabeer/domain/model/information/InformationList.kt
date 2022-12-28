package com.example.khabeer.domain.model.information

data class InformationList(
    val Activation: Boolean,
    val ArabicMessage: String,
    val EnglishMessage: String,
    val Payroll: Payroll,
    val Success: Boolean
)