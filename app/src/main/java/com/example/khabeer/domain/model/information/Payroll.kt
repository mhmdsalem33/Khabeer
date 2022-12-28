package com.example.khabeer.domain.model.information

data class Payroll(
    val Allowences: List<Allowence>,
    val Date: String,
    val Deduction: List<Deduction>,
    val Employee: List<Employee>
)