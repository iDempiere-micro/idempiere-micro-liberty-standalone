package company.bigger.idempiere.resolver

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import company.bigger.idempiere.di.globalContext
import company.bigger.idempiere.dto.BusinessPartnerCategory
import org.compiere.crm.MCrmCategory
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
        return DB.run {
            val result = MCrmCategory(Env.getCtx(), 0)
            result.name = name
            result.setSearchKey(value)
            result.save()
            BusinessPartnerCategory(result)
        }
    }

    fun echo(what: String): String = what
}