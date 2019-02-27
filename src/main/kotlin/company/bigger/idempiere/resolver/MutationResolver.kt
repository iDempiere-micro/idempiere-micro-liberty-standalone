package company.bigger.idempiere.resolver

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import company.bigger.idempiere.di.globalContext
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
    fun createCategory(name: String, value: String): Int {
        return DB.run {
            val result = MCrmCategory(Env.getCtx(), 0)
            result.name = name
            result.setSearchKey(value)
            result.save()
            result.id
        }
    }

    fun echo(what: String): String = what
}