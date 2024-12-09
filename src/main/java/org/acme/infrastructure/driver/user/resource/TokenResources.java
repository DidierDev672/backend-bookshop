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
import org.acme.applications.user.port.TokenPort;
import org.acme.domain.models.user.Token;
import org.acme.infrastructure.driver.shared.CustomResponse;

import java.util.Optional;

@Path("/create-token")
public class TokenResources {
 @Inject
 private TokenPort<Token> tokenTokenPort;

     @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createToken(Token token) throws CustomException, JsonProcessingException {
         Optional<Token> token1 = this.tokenTokenPort.createToken(token.getUsername());
         if(token1.isEmpty()){
             return CustomResponse.buildErrorResponse(Response.Status.INTERNAL_SERVER_ERROR, "Error creating token");
         }

         return CustomResponse.buildSuccessResponse(token1.get(), "Token created successfully");
     }
 }
