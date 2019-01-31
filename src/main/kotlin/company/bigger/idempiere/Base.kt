package company.bigger.idempiere

import company.bigger.idempiere.db.intentionallyAssignedToCallTheInitFn
import javax.ws.rs.core.Application

abstract class Base: Application() {
    init {
        val x = intentionallyAssignedToCallTheInitFn
    }
}