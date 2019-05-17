package company.bigger.idempiere.it.graphql

import com.fasterxml.jackson.annotation.JsonProperty

internal class UOM(
    @JsonProperty("Name")
    val name: String
)