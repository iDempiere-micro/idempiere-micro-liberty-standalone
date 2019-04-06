package company.bigger.idempiere.resolver

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import company.bigger.dto.UserLoginModel
import company.bigger.dto.UserLoginModelResponse
import company.bigger.idempiere.di.globalContext
import org.compiere.crm.BusinessPartnerInput
import company.bigger.idempiere.dto.ComplexInput
import company.bigger.idempiere.dto.ContactActivityInput
import kotliquery.sessionOf
import kotliquery.using
import org.compiere.crm.MCrmCategory
import org.compiere.model.I_C_BPartner
import org.compiere.model.I_C_ContactActivity
import software.hsharp.core.util.HikariCPI
import software.hsharp.models.CrmCategory
import space.traversal.kapsule.inject

class MutationResolver : BaseResolver(), GraphQLMutationResolver {
    init {
        inject(globalContext.module)
    }

    /**
     * Create a CRM Category with [name] name. The search [value] needs to be unique for the client.
     */
    fun createCategory(name: String, value: String): CrmCategory {
        val result = MCrmCategory(0)
        result.name = name
        result.searchKey = value
        result.save()
        return result
    }

    /**
     * Create a business partner based on the [businessPartner] parameters
     */
    fun createBusinessPartner(businessPartner: BusinessPartnerInput): I_C_BPartner {
        return businessPartnerService.createBusinessPartner(businessPartner)
    }

    fun createContactActivity(contactActivity: ContactActivityInput): I_C_ContactActivity {
        return with(contactActivity) {
            contactActivityService.createContactActivity(
                businessPartner, startDate, description, contactActivityType
            )
        }
    }

    /**
     * Basic echo function
     */
    fun echo(what: String): String = what

    /**
     * Slightly more complex echo function
     */
    fun echoComplex(what: ComplexInput): String = what.content

    /**
     * Login user
     */
    fun login(login: UserLoginModel): UserLoginModelResponse? {
        return using(sessionOf(HikariCPI.dataSource())) { session ->
            userService.login(session, login)
        }
    }
}