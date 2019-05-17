package company.bigger.idempiere.it.graphql

internal data class BusinessPartner(
    val IsCustomer: Boolean,
    val URL: String?,
    val categories: List<Any>?,
    val id: String,
    val name: String,
    val searchKey: String
)