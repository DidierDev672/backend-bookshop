package org.acme.infrastructure.driver.editorial.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.handler.codec.base64.Base64;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.applications.Editorial.ports.EditorialCreatePort;
import org.acme.applications.shared.CustomException;
import org.acme.domain.models.Editorial;
import org.acme.infrastructure.driver.shared.CustomPresenter;

@Path("/create-editorial")
public class EditorialCreateResource {

    @Inject
    private EditorialCreatePort<Editorial, Editorial> editorialEditorialEditorialCreatePort;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(Editorial editorial) throws CustomException {
        try {
            Editorial editorial1 = this.editorialEditorialEditorialCreatePort.create(editorial).orElse(null);
            if (editorial1 == null) {
                return this.buildErrorResponse(Response.Status.INTERNAL_SERVER_ERROR, "Error creating editorial");
            }
            return this.buildSuccessResponse(editorial1, "Successful registration of the editorial");
        } catch (CustomException e) {
            return this.buildErrorResponse(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Response buildSuccessResponse(Editorial editorial, String message) throws JsonProcessingException {
        CustomPresenter presenter = createPresenter(Response.Status.CREATED, message, editorial);
        return Response.status(Response.Status.CREATED).entity(presenter).build();
    }

    private Response buildErrorResponse(Response.Status status, String message) {
        CustomPresenter presenter = CustomPresenter.init();
        presenter.setReason(status.getReasonPhrase());
        presenter.setStatusCode(status.getStatusCode());
        presenter.setMessage(message);
        return Response.status(status).entity(presenter).build();
    }

    private CustomPresenter createPresenter (Response.Status status, String message, Editorial editorial) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CustomPresenter presenter = CustomPresenter.init();
        presenter.setReason(status.getReasonPhrase());
        presenter.setStatusCode(status.getStatusCode());
        presenter.setMessage(message);
        presenter.setData(editorial);
        return null;
    }
}
