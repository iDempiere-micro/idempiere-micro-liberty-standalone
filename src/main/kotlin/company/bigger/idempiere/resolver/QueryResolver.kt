package company.bigger.idempiere.resolver

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import company.bigger.idempiere.di.globalContext
import company.bigger.idempiere.dto.Version
import software.hsharp.core.util.DB
import space.traversal.kapsule.inject

class QueryResolver : BaseResolver(), GraphQLQueryResolver {
    companion object {
        const val VER = "1.0.0"
    }

    init {
        inject(globalContext.module)
    }

    val version = Version(VER)
    val me get() = authenticationService.currentUser()
    val users get() = usersService.getUsers()
    val businessPartners get() = businessPartnerService.getBusinessPartners()
    /*
    val categories get() = categoryService?.getAllCategories()
    val countries get() = countryService?.getAllCountries()
    */
}
