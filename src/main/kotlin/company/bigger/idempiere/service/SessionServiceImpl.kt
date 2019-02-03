package company.bigger.idempiere.service

import company.bigger.dto.UserLoginModel
import company.bigger.dto.UserLoginModelResponse
import company.bigger.idempiere.config.Jwt
import company.bigger.idempiere.config.Locking
import company.bigger.idempiere.config.User
import company.bigger.service.LoginService
import company.bigger.service.UserService
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class SessionServiceImpl : SessionService {

    private val userService = UserService(
        LoginService(
            User.passwordHash,
            Locking.maxAccountLockMinutes,
            Locking.maxInactivePeriodDays,
            Locking.maxLoggingAttempts
        ),
        jwtSecret = Jwt.Secret,
        jwtIssuer = Jwt.Issuer
    )

    override fun login(userLoginModel: UserLoginModel): UserLoginModelResponse? {
        return userService.login(userLoginModel)
    }

    override fun validateToken(token: String): UserLoginModelResponse? {
        return userService.validateToken(token)
    }
}