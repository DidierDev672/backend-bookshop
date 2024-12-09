package org.acme.infrastructure.driver.user.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.applications.user.port.LoginPort;
import org.acme.domain.models.user.Login;
import org.acme.infrastructure.driver.shared.CustomResponse;

import java.util.Optional;

@Path("/login-book-store")
public class LoginResource {
    @Inject
    private LoginPort loginPort;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginBookStore(Login login) {
        Optional<Login> resultLogin = this.loginPort.login(login.getUsername(), login.getPassword());
        if(resultLogin.isEmpty()){
            return CustomResponse.buildErrorResponse(Response.Status.INTERNAL_SERVER_ERROR, "Error in username and password");
        }

        return CustomResponse.buildSuccessResponse(resultLogin.get(), "Login successful");
    }
}
