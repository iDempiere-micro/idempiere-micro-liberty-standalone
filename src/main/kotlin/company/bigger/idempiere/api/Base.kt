package company.bigger.idempiere.api

import company.bigger.idempiere.db.intentionallyAssignedToCallTheInitFn
import javax.ws.rs.core.Application

abstract class Base: Application() {
    init {
        val x = intentionallyAssignedToCallTheInitFn
    }
}