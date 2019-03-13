package company.bigger.idempiere.resolver

import company.bigger.idempiere.di.Module
import space.traversal.kapsule.Injects
import space.traversal.kapsule.required

abstract class BaseResolver : Injects<Module> {
    protected val authenticationService by required { authenticationService }
    protected val usersService by required { usersService }
    protected val businessPartnerService by required { businessPartnerService }
    protected val currencyService by required { currencyService }
    protected val countryService by required { countryService }
    protected val categoryService by required { categoryService }
    protected val contactActivityService by required { contactActivityService }
}