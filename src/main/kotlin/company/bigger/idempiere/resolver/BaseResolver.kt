package company.bigger.idempiere.resolver

import company.bigger.idempiere.di.Module
import space.traversal.kapsule.Injects
import space.traversal.kapsule.required

abstract class BaseResolver : Injects<Module> {
    protected val authenticationService by required { authenticationService }
    protected val usersService by required { usersService }
    protected val businessPartnerService by required { businessPartnerService }
}