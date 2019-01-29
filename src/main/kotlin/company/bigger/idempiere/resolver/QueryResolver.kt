package company.bigger.idempiere.resolver

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import company.bigger.idempiere.dto.Version

class QueryResolver : GraphQLQueryResolver {
    companion object {
        const val VER = "1.0.0"
    }

    val version = Version(VER)
}
