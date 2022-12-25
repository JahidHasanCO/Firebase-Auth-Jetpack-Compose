package dev.jahidhasanco.firebaseauth.common.utils.state

import dev.jahidhasanco.firebaseauth.data.model.User

data class UserState(
    val data: User? = null,
    val error: String = "",
    val isLoading: Boolean = false
)