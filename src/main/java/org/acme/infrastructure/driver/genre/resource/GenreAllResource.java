package org.acme.infrastructure.driver.genre.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.applications.Genre.ports.GenreAllPort;
import org.acme.applications.shared.CustomException;
import org.acme.domain.models.Genre;
import org.acme.infrastructure.driver.shared.CustomPresenter;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;



@Path("/all-genre")
public class GenreAllResource {
    @Inject
    private GenreAllPort genreAllPort;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() throws CustomException {
        try{
            return this.genreAllPort.all()
                    .map(this::createSuccessResponse)
                    .orElse(createNotFoundResponse());
        }catch (CustomException e){
            return this.createErrorResponse(e);
        }
    }

    private Response createNotFoundResponse(){
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    private Response createSuccessResponse(List<Genre> genres) {
        CustomPresenter presenter = CustomPresenter.init();
        presenter.setReason(Response.Status.OK.getReasonPhrase());
        presenter.setStatusCode(Response.Status.OK.getStatusCode());
        presenter.setMessage("All genres have been recovered");
        presenter.setData(genres);
        return Response.ok(presenter).build();
    }

    private Response createErrorResponse(Exception e){
       System.out.println("Error occurred: " + e.getMessage());
       return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
