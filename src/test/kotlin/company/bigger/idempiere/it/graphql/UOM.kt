package company.bigger.idempiere.it.graphql

import com.fasterxml.jackson.annotation.JsonProperty

class UOM(
    @JsonProperty("Name")
    val name: String
)