package org.acme.infrastructure.driver.user.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.applications.shared.CustomException;
import org.acme.applications.user.port.UserCreatePort;
import org.acme.domain.models.user.UserModel;
import org.acme.infrastructure.driver.shared.CustomResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Path("/create-user")
public class UserCreateResource {
   private static final Logger log = LoggerFactory.getLogger(UserCreateResource.class);
   @Inject
    private UserCreatePort<UserModel> userModelUserCreatePort;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createUser(UserModel userModel) throws CustomException {
        Optional<UserModel> model = this.userModelUserCreatePort.createUser(userModel);
        if(model.isEmpty()){
           return CustomResponse.buildErrorResponse(Response.Status.INTERNAL_SERVER_ERROR, "User creation failed");
        }

        return CustomResponse.buildSuccessResponse(model.get(), "Success the user has been recorded");
    }
}
