package org.acme.infrastructure.driver.editorial.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.applications.Editorial.ports.EditorialAllPort;
import org.acme.applications.shared.CustomException;
import org.acme.domain.models.Editorial;
import org.acme.infrastructure.driver.shared.CustomPresenter;

import java.util.List;

@Path("/all-editorial")
public class EditorialAllResource {

    @Inject
    private EditorialAllPort editorialAllPort;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() throws CustomException {
        try{
            return this.editorialAllPort.all()
                    .map(this::createSuccessResponse)
                    .orElse(createNotFoundResponse());
        }catch (CustomException e) {
            return this.createErrorResponse(e);
        }
    }

    private Response createNotFoundResponse() {
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    private Response createSuccessResponse(List<Editorial> editorials) {
        CustomPresenter presenter = CustomPresenter.init();
        presenter.setReason(Response.Status.OK.getReasonPhrase());
        presenter.setStatusCode(Response.Status.OK.getStatusCode());
        presenter.setMessage("All editorials have been recovered");
        presenter.setData(editorials);
        return Response.ok(presenter).build();
    }

    private Response createErrorResponse(Exception e){
        System.out.println("Error occurred: " + e.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

}
