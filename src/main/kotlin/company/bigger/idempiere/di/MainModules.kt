package company.bigger.idempiere.di

import company.bigger.idempiere.config.Jwt
import company.bigger.idempiere.config.Locking
import company.bigger.idempiere.config.User
import company.bigger.idempiere.service.AuthenticationService
import company.bigger.idempiere.service.BusinessPartnerService
import company.bigger.idempiere.service.UsersService
import company.bigger.service.LoginService
import company.bigger.service.UserService
import org.idempiere.common.util.EnvironmentServiceImpl

class MainLogicModule : LogicModule {
    override val loginService = LoginService(
        User.passwordHash,
        Locking.maxAccountLockMinutes,
        Locking.maxInactivePeriodDays,
        Locking.maxLoggingAttempts
    )
    override val authenticationService = AuthenticationService()
}

class MainDataModule(mainLogicModule: MainLogicModule, mainEnvironmentModule: MainEnvironmentModule) : DataModule {
    override val userService = UserService(
        mainLogicModule.loginService,
        jwtSecret = Jwt.Secret,
        jwtIssuer = Jwt.Issuer
    )
    override val usersService = UsersService(mainEnvironmentModule.environmentService)
    override val businessPartnerService = BusinessPartnerService(mainEnvironmentModule.environmentService)
}

class MainEnvironmentModule : EnvironmentModule {
    override val environmentService = EnvironmentServiceImpl()
}