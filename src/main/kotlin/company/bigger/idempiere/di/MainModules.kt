package company.bigger.idempiere.di

import company.bigger.idempiere.config.Jwt
import company.bigger.idempiere.config.Locking
import company.bigger.idempiere.config.User
import company.bigger.idempiere.service.AuthenticationService
import company.bigger.idempiere.service.SimpleModelFactory
import org.compiere.orm.UsersServiceImpl
import company.bigger.service.LoginService
import company.bigger.service.UserService
import org.compiere.bo.BusinessOpportunityServiceImpl
import org.compiere.bo.SalesStageServiceImpl
import org.compiere.crm.BusinessPartnerServiceImpl
import org.compiere.crm.CategoryServiceImpl
import org.compiere.crm.ContactActivityServiceImpl
import org.compiere.crm.CountryServiceImpl
import org.compiere.bo.CurrencyServiceImpl
import org.compiere.order.SalesOrderServiceImpl
import org.compiere.product.ProductServiceImpl
import org.idempiere.common.util.EnvironmentServiceImpl
import software.hsharp.modules.DataModule
import software.hsharp.modules.EnvironmentModule

class MainLogicModule : LogicModule {
    override val loginService = LoginService(
        User.passwordHash,
        Locking.maxAccountLockMinutes,
        Locking.maxInactivePeriodDays,
        Locking.maxLoggingAttempts
    )
    override val authenticationService = AuthenticationService()
    override val modelFactory = SimpleModelFactory()
}

interface DataModuleWithUserService : DataModule {
    val userService: UserService
}

class MainDataModule(mainLogicModule: MainLogicModule, mainEnvironmentModule: MainEnvironmentModule) :
    DataModuleWithUserService {
    private val environmentService = mainEnvironmentModule.environmentService

    override val userService = UserService(
        mainLogicModule.loginService,
        jwtSecret = Jwt.Secret,
        jwtIssuer = Jwt.Issuer
    )
    override val usersService = UsersServiceImpl(environmentService)
    override val businessPartnerService =
        BusinessPartnerServiceImpl(environmentService)
    override val currencyService = CurrencyServiceImpl(environmentService)
    override val countryService = CountryServiceImpl(environmentService)
    override val categoryService = CategoryServiceImpl(environmentService)
    override val businessOpportunityService = BusinessOpportunityServiceImpl(environmentService)
    override val salesStageService = SalesStageServiceImpl(environmentService)
    override val contactActivityService =
        ContactActivityServiceImpl(
            environmentService,
            businessOpportunityService,
            salesStageService,
            currencyService
        )
    override val salesOrderService = SalesOrderServiceImpl(environmentService)
    override val productService = ProductServiceImpl(environmentService)
}

class MainEnvironmentModule : EnvironmentModule {
    override val environmentService = EnvironmentServiceImpl(0, 0, 0)
}