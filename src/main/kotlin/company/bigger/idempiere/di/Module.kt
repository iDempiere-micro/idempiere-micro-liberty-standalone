package company.bigger.idempiere.di

import company.bigger.idempiere.config.Jwt
import company.bigger.idempiere.config.Locking
import company.bigger.idempiere.config.User
import company.bigger.service.LoginService
import company.bigger.service.UserService

class Module {
    val loginService = LoginService(
        User.passwordHash,
        Locking.maxAccountLockMinutes,
        Locking.maxInactivePeriodDays,
        Locking.maxLoggingAttempts
    )
    val userService = UserService(
        loginService,
        jwtSecret = Jwt.Secret,
        jwtIssuer = Jwt.Issuer
    )
}