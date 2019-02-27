package company.bigger.idempiere.service

import org.compiere.crm.MUser
import software.hsharp.core.models.EnvironmentService

class UsersService(
    private val environmentService: EnvironmentService
) {
    fun getUsers(): List<MUser> {
        return MUser
        .getOfClient(environmentService.context, environmentService.clientId)
        .filter { it.description != null }
    }
}