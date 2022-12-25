package dev.jahidhasanco.firebaseauth.data.model

data class User(
    val name: String = "",
    val image: String = "",
    val email: String = "",
    val active: Boolean = false,
    val address: String = ""
)