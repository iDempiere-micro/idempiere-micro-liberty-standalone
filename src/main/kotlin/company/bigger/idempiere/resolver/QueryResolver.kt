package company.bigger.idempiere.resolver

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import company.bigger.idempiere.di.globalContext
import company.bigger.idempiere.dto.Version
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
    val businessPartners get() = businessPartnerService.getAll()
    val currencies get() = currencyService.getAll()
    val countries get() = countryService.getAll()
    val categories get() = categoryService.getAll()
    val salesOrders get() = salesOrderService.getAll()
    val products get() = productService.getAll()
}
