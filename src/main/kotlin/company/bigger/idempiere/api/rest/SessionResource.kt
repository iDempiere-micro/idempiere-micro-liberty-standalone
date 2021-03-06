package company.bigger.idempiere.api.rest

import company.bigger.dto.UserLoginModel
import company.bigger.dto.UserLoginModelResponse
import company.bigger.idempiere.di.ModuleImpl
import company.bigger.idempiere.di.globalContext
import company.bigger.idempiere.service.AuthenticationService
import company.bigger.service.UserService
import kotliquery.sessionOf
import kotliquery.using
import org.compiere.model.User
import software.hsharp.core.services.EnvironmentService
import software.hsharp.core.util.DB
import software.hsharp.core.util.Environment
import software.hsharp.core.util.HikariCPI
import space.traversal.kapsule.Injects
import space.traversal.kapsule.inject
import space.traversal.kapsule.required
import javax.ws.rs.ApplicationPath
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.PathParam
import javax.ws.rs.core.Application
import javax.ws.rs.core.MediaType

/**
 * Logged user returned through the REST
 */
data class LoggedUser(
    val id: Int,
    val name: String,
    val description: String?
) {
    constructor(user: User) : this(user.id, user.name, user.description)
}

/**
 * Session related API calls.
 */
@Path("/session")
@ApplicationPath("/")
open class SessionResource : Application(), Injects<ModuleImpl> {
    private val sessionService: UserService by required { userService }
    private val authenticationService: AuthenticationService by required { authenticationService }
    private val environmentService: EnvironmentService by required { environmentService }
    private val environment: Environment<ModuleImpl>

    init {
        inject(globalContext.module)
        environment = Environment(globalContext.module)
    }

    /**
     * Login user
     */
    @GET
    @Path("/{username}/login/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    fun login(
        @PathParam("username") username: String,
        @PathParam("password") password: String
    ): UserLoginModelResponse? {
        return environment.run {
            using(sessionOf(HikariCPI.dataSource())) { session ->
                sessionService.login(session, UserLoginModel(username, password))
            }
        }
    }

    /**
     * Information about the currently logged in user
     */
    @GET
    @Path("/me")
    @Produces(MediaType.APPLICATION_JSON)
    fun me(): LoggedUser? {
        return environment.run {
            DB.run { authenticationService.currentUser()?.let { LoggedUser(it) } }
        }
    }
}