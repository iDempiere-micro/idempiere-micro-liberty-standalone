package company.bigger.idempiere.resolver

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import company.bigger.idempiere.di.globalContext
import company.bigger.idempiere.dto.BusinessPartnerCategory
import company.bigger.idempiere.dto.BusinessPartnerInput
import company.bigger.idempiere.dto.ComplexInput
import org.compiere.crm.MCrmCategory
import org.compiere.model.I_C_BPartner
import org.idempiere.common.util.Env
import software.hsharp.core.util.DB
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

    fun createBusinessPartner(businessPartner: BusinessPartnerInput): I_C_BPartner {
        return businessPartnerService.createBusinessPartner(businessPartner)
    }

    fun echo(what: String): String = what

    fun echoComplex(what: ComplexInput): String = what.content
}