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

    /**
     * APIversion
     */
    val version = Version(VER)
    /**
     * Current user
     */
    val me get() = authenticationService.currentUser()
    /**
     * Users
     */
    val users get() = usersService.getUsers()
    /**
     * Business partners
     */
    val businessPartners get() = businessPartnerService.getAll()
    /**
     * Currencies
     */
    val currencies get() = currencyService.getAll()
    /**
     * countries
     */
    val countries get() = countryService.getAll()
    /**
     * categories
     */
    val categories get() = categoryService.getAll()
    /**
     * sales Orders
     */
    val salesOrders get() = salesOrderService.getAll()
    /**
     * products
     */
    val products get() = productService.getAll()
    /**
     * Contact activities
     */
    val contactActivities get() = contactActivityService.getAll()

    /**
     * Sales order by Id
     */
    fun salesOrder(id: Int) = salesOrderService.getById(id)

    /**
     * business partner by id
     */
    fun businessPartner(id: Int) = businessPartnerService.getById(id)

    /**
     * product by id
     */
    fun product(id: Int) = productService.getById(id)
}