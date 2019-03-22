package company.bigger.idempiere.resolver

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import company.bigger.idempiere.di.globalContext
import company.bigger.idempiere.dto.BusinessPartnerCategory
import org.compiere.crm.BusinessPartnerInput
import company.bigger.idempiere.dto.ComplexInput
import company.bigger.idempiere.dto.ContactActivityInput
import org.compiere.crm.MCrmCategory
import org.compiere.model.I_C_BPartner
import org.compiere.model.I_C_ContactActivity
import org.idempiere.common.util.Env
import space.traversal.kapsule.inject

class MutationResolver : BaseResolver(), GraphQLMutationResolver {
    init {
        inject(globalContext.module)
    }

    /**
     * Create a CRM Category with [name] name. The search [value] needs to be unique for the client.
     */
    fun createCategory(name: String, value: String): BusinessPartnerCategory {
        val result = MCrmCategory(Env.getCtx(), 0)
        result.name = name
        result.searchKey = value
        result.save()
        return BusinessPartnerCategory(result)
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
}