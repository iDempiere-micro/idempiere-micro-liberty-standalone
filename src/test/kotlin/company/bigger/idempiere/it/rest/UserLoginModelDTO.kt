package company.bigger.idempiere.it.rest

data class UserLoginModelDTO(
    val clientId: Int,
    val logged: Boolean,
    val loginName: String,
    val token: String,
    val userId: Int
)