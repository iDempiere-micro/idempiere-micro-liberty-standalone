package company.bigger.idempiere.dto

import org.compiere.model.I_C_BPartner
import java.sql.Timestamp

data class ContactActivityInput(
    val businessPartner: I_C_BPartner,
    val startDate: Timestamp,
    val description: String,
    val contactActivityType: String
)