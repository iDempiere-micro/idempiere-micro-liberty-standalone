package gcc.kt.rest

import com.coxautodev.graphql.tools.GraphQLQueryResolver

class QueryResolver : GraphQLQueryResolver {
    companion object {
        const val VER = "1.0.0"
    }

    val version = Version(VER)
}
