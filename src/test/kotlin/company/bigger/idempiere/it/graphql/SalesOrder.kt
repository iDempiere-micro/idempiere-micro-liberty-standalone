package company.bigger.idempiere.it.graphql

internal data class SalesOrder(
    val Customer: Customer,
    val DateOrderedISOFormat: String,
    val Description: String,
    val DocumentNo: String,
    val GrandTotal: Double,
    val Lines: List<Line>
)