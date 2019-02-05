package company.bigger.idempiere.api.rest

import company.bigger.dto.UserLoginModel
import company.bigger.dto.UserLoginModelResponse
import company.bigger.idempiere.api.Base
import company.bigger.idempiere.service.SessionService
import company.bigger.idempiere.service.SessionServiceImpl
import javax.enterprise.context.RequestScoped
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/session")
@ApplicationPath("/")
@RequestScoped
open class SessionResource : Base() {
    companion object {
        val sessionService : SessionService= SessionServiceImpl()
    }


    @GET
    @Path("/{username}/login/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    fun login(
        @PathParam("username") username: String,
        @PathParam("password") password: String
    ): UserLoginModelResponse? {
        return sessionService.login(UserLoginModel(username, password))
    }

}