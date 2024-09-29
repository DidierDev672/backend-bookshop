package org.acme.infrastructure.driver.author.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.applications.author.ports.AuthorCreatePort;
import org.acme.applications.shared.CustomException;
import org.acme.domain.models.Author;
import org.acme.infrastructure.driver.shared.CustomPresenter;

import java.util.Optional;

@Path("/create-author")
public class AuthorCreateResource {

    @Inject
    private AuthorCreatePort<Author, Author> authorAuthorAuthorCreatePort;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(Author author) throws CustomException {
        try {
            Optional<Author> author1 = this.authorAuthorAuthorCreatePort.create(author);
            if(author1.isEmpty()){
                return buildErrorResponse(Response.Status.INTERNAL_SERVER_ERROR, "Error creating author");
            }

            return buildSuccessResponse(author1.get(), "Successful registration of the author");
        }catch (CustomException e){
            return buildErrorResponse(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Response buildSuccessResponse(Author author, String message) throws JsonProcessingException {
        CustomPresenter presenter = createPresenter(Response.Status.CREATED, message, author);
        return Response.status(Response.Status.CREATED).entity(presenter).build();
    }

    private Response buildErrorResponse(Response.Status status, String message) {
        CustomPresenter presenter = CustomPresenter.init();
        presenter.setReason(status.getReasonPhrase());
        presenter.setStatusCode(status.getStatusCode());
        presenter.setMessage(message);
        return Response.status(status).entity(presenter).build();
    }

    private CustomPresenter createPresenter (Response.Status status, String message, Author author) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CustomPresenter presenter = CustomPresenter.init();
        presenter.setReason(status.getReasonPhrase());
        presenter.setStatusCode(status.getStatusCode());
        presenter.setMessage(message);
        presenter.setData(objectMapper.writeValueAsString(author).getBytes());
        return presenter;
    }
}
