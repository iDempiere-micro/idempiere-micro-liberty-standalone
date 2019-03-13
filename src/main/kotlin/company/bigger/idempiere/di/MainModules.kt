package company.bigger.idempiere.di

import company.bigger.idempiere.config.Jwt
import company.bigger.idempiere.config.Locking
import company.bigger.idempiere.config.User
import company.bigger.idempiere.service.AuthenticationService
import org.compiere.orm.UsersService
import company.bigger.service.LoginService
import company.bigger.service.UserService
import org.compiere.bo.BusinessOpportunityServiceImpl
import org.compiere.bo.SalesStageServiceImpl
import org.compiere.crm.BusinessPartnerServiceImpl
import org.compiere.crm.CategoryServiceImpl
import org.compiere.crm.ContactActivityService
import org.compiere.crm.CountryServiceImpl
import org.compiere.product.CurrencyServiceImpl
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
    private val environmentService = mainEnvironmentModule.environmentService

    override val userService = UserService(
        mainLogicModule.loginService,
        jwtSecret = Jwt.Secret,
        jwtIssuer = Jwt.Issuer
    )
    override val usersService = UsersService(environmentService)
    override val businessPartnerService =
        BusinessPartnerServiceImpl(environmentService)
    override val currencyService = CurrencyServiceImpl(environmentService)
    override val countryService = CountryServiceImpl(environmentService)
    override val categoryService = CategoryServiceImpl(environmentService)
    override val businessOpportunityService = BusinessOpportunityServiceImpl(environmentService)
    override val salesStageService = SalesStageServiceImpl(environmentService)
    override val contactActivityService =
        ContactActivityService(
            environmentService,
            businessOpportunityService,
            salesStageService,
            currencyService
        )
}

class MainEnvironmentModule : EnvironmentModule {
    override val environmentService = EnvironmentServiceImpl()
}