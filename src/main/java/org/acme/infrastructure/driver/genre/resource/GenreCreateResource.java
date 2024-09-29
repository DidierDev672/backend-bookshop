package org.acme.infrastructure.driver.genre.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.applications.Genre.ports.GenreCreatePort;
import org.acme.applications.shared.CustomException;
import org.acme.domain.models.Genre;
import org.acme.infrastructure.driver.shared.CustomPresenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;
import java.util.Optional;

@Path("/create-genre")
public class GenreCreateResource {
    private static final Logger log = LoggerFactory.getLogger(GenreCreateResource.class);
    @Inject
    private GenreCreatePort<Genre, Genre> genreGenreGenreCreatePort;


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(Genre genre) throws CustomException {
        try{
            Optional<Genre> genre1 = this.genreGenreGenreCreatePort.create(genre);
            if(genre1.isEmpty()){
                return this.buildErrorResponse(Response.Status.INTERNAL_SERVER_ERROR, "Error creating genre");
            }

            return this.buildSuccessResponse(genre1.get(), "Successful registration of the genre");
        }catch (CustomException e){
          return this.buildErrorResponse(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Response buildSuccessResponse(Genre genre, String message) throws JsonProcessingException {
        CustomPresenter presenter = createPresenter(Response.Status.CREATED, message, genre);
        return Response.status(Response.Status.CREATED).entity(presenter).build();
    }

    private Response buildErrorResponse(Response.Status status, String message){
        CustomPresenter presenter = CustomPresenter.init();
        presenter.setReason(status.getReasonPhrase());
        presenter.setStatusCode(status.getStatusCode());
        presenter.setMessage(message);
        return Response.status(status).entity(presenter).build();
    }

    private CustomPresenter createPresenter(Response.Status status, String message, Genre genre) throws JsonProcessingException  {
        ObjectMapper mapper = new ObjectMapper();
        CustomPresenter presenter = CustomPresenter.init();
        presenter.setReason(status.getReasonPhrase());
        presenter.setStatusCode(status.getStatusCode());
        presenter.setMessage(message);
        presenter.setData(Base64.getEncoder().encode(mapper.writeValueAsString(genre).getBytes()));
        return presenter;
    }
}
