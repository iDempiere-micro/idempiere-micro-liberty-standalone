package company.bigger.idempiere.it.graphql

import io.aexp.nodes.graphql.annotations.GraphQLProperty

@GraphQLProperty(name = "version")
internal class VersionRequest(@GraphQLProperty(name = "v") var v: String? = null)