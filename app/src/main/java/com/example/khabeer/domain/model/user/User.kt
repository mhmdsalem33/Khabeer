package com.example.khabeer.domain.model.user

data class User(
    val AmbulanceProfileId: Any,
    val AspNetUsersId: Int,
    val BirthDate: String,
    val ClassArabicName: String,
    val ClassEnglishName: String,
    val ClassId: Int,
    val Email: Any,
    val EmailConfirmed: Boolean,
    val FirstNameAr: String,
    val FirstNameEn: String,
    val Gender: Int,
    val HasInsurance: Boolean,
    val Id: Int,
    val LastNameAr: String,
    val LastNameEn: String,
    val LicenseNumber: Any,
    val MobileNumber: String,
    val PatientImage: String,
    val PhoneNumberConfirmed: Boolean,
    val Source: Any,
    val UserName: Any
)