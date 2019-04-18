package company.bigger.idempiere.service

import org.compiere.crm.getUser
import org.compiere.model.User
import software.hsharp.core.util.Environment

class AuthenticationService {
    fun currentUser(): User? {
        val userId = Environment.current.userId
        if (userId == 0) return null
        return getUser(userId)
    }
}