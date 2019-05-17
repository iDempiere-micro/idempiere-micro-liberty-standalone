package company.bigger.idempiere.it.graphql

internal data class Product(
    val StorageOnHand: List<Any>,
    val UOM: UOM,
    val id: String,
    val name: String
)