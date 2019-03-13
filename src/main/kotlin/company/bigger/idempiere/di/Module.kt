package company.bigger.idempiere.di

import company.bigger.idempiere.service.AuthenticationService
import org.compiere.orm.UsersService
import company.bigger.service.LoginService
import company.bigger.service.UserService
import org.compiere.crm.ContactActivityService
import software.hsharp.core.models.EnvironmentService
import software.hsharp.services.*
import space.traversal.kapsule.HasModules

class Module(
    environment: EnvironmentModule,
    logic: LogicModule,
    data: DataModule
) :
    EnvironmentModule by environment,
    LogicModule by logic,
    DataModule by data,
    HasModules {

    override val modules = setOf(data, logic)
}

interface DataModule {
    val userService: UserService
    val usersService: UsersService
    val businessPartnerService: BusinessPartnerService
    val currencyService: CurrencyService
    val countryService: CountryService
    val categoryService: CategoryService
    val businessOpportunityService: BusinessOpportunityService
    val salesStageService: SalesStageService
    val contactActivityService: ContactActivityService
}

interface LogicModule {
    val loginService: LoginService
    val authenticationService: AuthenticationService
}

interface EnvironmentModule {
    val environmentService: EnvironmentService
}