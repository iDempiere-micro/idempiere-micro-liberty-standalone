package company.bigger.idempiere.resolver

import company.bigger.idempiere.di.ModuleImpl
import company.bigger.idempiere.service.AuthenticationService
import company.bigger.service.UserService
import space.traversal.kapsule.Injects
import space.traversal.kapsule.required

abstract class BaseResolver : Injects<ModuleImpl> {
    protected val authenticationService: AuthenticationService by required { authenticationService }
    protected val usersService by required { usersService }
    protected val businessPartnerService by required { businessPartnerService }
    protected val currencyService by required { currencyService }
    protected val countryService by required { countryService }
    protected val categoryService by required { categoryService }
    protected val contactActivityService by required { contactActivityService }
    protected val salesOrderService by required { salesOrderService }
    protected val productService by required { productService }
    protected val userService: UserService by required { userService }
}