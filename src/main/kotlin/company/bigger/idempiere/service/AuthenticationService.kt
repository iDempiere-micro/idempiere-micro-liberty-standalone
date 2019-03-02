package company.bigger.idempiere.service

import company.bigger.dto.UserLoginModelResponse
import mu.KotlinLogging
import org.compiere.crm.MUser
import org.compiere.model.I_AD_User
import org.idempiere.common.util.Env

private const val NO_ID = "-1"
private val logger = KotlinLogging.logger {}

class AuthenticationService {
    private fun clearCurrentUser() {
        val ctx = Env.getCtx()
        ctx.setProperty(Env.AD_CLIENT_ID, NO_ID)
        Env.setContext(ctx, Env.AD_CLIENT_ID, NO_ID)
        Env.setContext(ctx, Env.AD_USER_ID, NO_ID)
        Env.setContext(ctx, "#AD_User_Name", "")
        Env.setContext(ctx, "#SalesRep_ID", NO_ID)
    }

    internal fun setCurrentUser(user: UserLoginModelResponse?) {
        if (user == null) clearCurrentUser()
        else {
            val ctx = Env.getCtx()
            val clientId = user.clientId.toString()
            val userId = user.userId.toString()
            ctx.setProperty(Env.AD_CLIENT_ID, clientId)
            Env.setContext(ctx, Env.AD_CLIENT_ID, clientId)
            Env.setContext(ctx, Env.AD_USER_ID, userId)
            Env.setContext(ctx, "#AD_User_Name", user.loginName)
            Env.setContext(ctx, "#SalesRep_ID", userId)
        }
    }

    fun currentUser(): I_AD_User? {
        logger.debug { ">>> currentUser called" }
        val ctx = Env.getCtx()
        val userId = Env.getContext(ctx, Env.AD_USER_ID)
        logger.debug { ">>> userId: $userId" }
        if (userId.isNullOrEmpty()) return null
        return MUser.get(ctx, userId.toInt())
    }
}