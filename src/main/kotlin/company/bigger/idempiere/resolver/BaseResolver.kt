package company.bigger.idempiere.resolver

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import company.bigger.idempiere.di.Module
import space.traversal.kapsule.Injects
import space.traversal.kapsule.required

abstract class BaseResolver : GraphQLQueryResolver, Injects<Module> {
    protected val authenticationService by required { authenticationService }
}