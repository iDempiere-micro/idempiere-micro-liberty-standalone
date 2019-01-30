package company.bigger.idempiere.service

import company.bigger.dto.UserLoginModel
import company.bigger.dto.UserLoginModelResponse
import company.bigger.idempiere.config.Database
import company.bigger.idempiere.config.Jwt
import company.bigger.idempiere.config.Locking
import company.bigger.idempiere.config.User
import company.bigger.service.LoginService
import company.bigger.service.UserService
import kotliquery.HikariCP
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class SessionService {
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

    fun login(userLoginModel: UserLoginModel): UserLoginModelResponse? {
        HikariCP.default(Database.Url, Database.Username, Database.Password)
        return userService.login(userLoginModel)
    }

}