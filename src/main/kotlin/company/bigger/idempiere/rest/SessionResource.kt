package company.bigger.idempiere.rest

import company.bigger.dto.UserLoginModel
import company.bigger.dto.UserLoginModelResponse
import company.bigger.idempiere.db.intentionallyAssignedToCallTheInitFn
import company.bigger.idempiere.service.SessionService
import javax.enterprise.context.RequestScoped
import javax.ws.rs.*
import javax.ws.rs.core.Application
import javax.ws.rs.core.MediaType

@Path("/session")
@ApplicationPath("/")
@RequestScoped
class SessionResource : Application() {
    companion object {
        private val sessionService = SessionService()
    }

    @GET
    @Path("/{username}/login/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    fun login(
        @PathParam("username") username: String,
        @PathParam("password") password: String
    ): UserLoginModelResponse? {
        val x = intentionallyAssignedToCallTheInitFn
        return sessionService.login(UserLoginModel(username, password))
    }

}