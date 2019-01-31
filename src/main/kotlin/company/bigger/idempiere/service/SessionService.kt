package company.bigger.idempiere.service

import company.bigger.dto.UserLoginModel
import company.bigger.dto.UserLoginModelResponse


interface SessionService {
    fun login(userLoginModel: UserLoginModel): UserLoginModelResponse?
    fun validateToken(token: String): UserLoginModelResponse?
}