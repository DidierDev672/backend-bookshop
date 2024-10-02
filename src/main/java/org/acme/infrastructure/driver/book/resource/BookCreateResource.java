package org.acme.infrastructure.driver.book.resource;
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
import org.acme.applications.Book.ports.BookCreatePort;
import org.acme.applications.shared.CustomException;
import org.acme.domain.models.Book;
import org.acme.infrastructure.driver.shared.CustomPresenter;

import java.util.Optional;

@Path("/create-book")
public class BookCreateResource {
    @Inject
    private BookCreatePort<Book, Book> bookBookBookCreatePort;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(Book book) throws CustomException {
        try {
            System.out.println(book.getTitle());
            Optional<Book> optionalBook = this.bookBookBookCreatePort.create(book);
            if(optionalBook.isEmpty()){
                return buildErrorResponse(Response.Status.INTERNAL_SERVER_ERROR, "Error creating book");
            }

            return buildSuccessResponse(optionalBook.get(), "Success in creating the book");
        }catch (CustomException e){
            return buildErrorResponse(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Response buildSuccessResponse(Book book, String message) throws JsonProcessingException {
        CustomPresenter presenter = createPresenter(Response.Status.CREATED, message, book);
        return Response.status(Response.Status.CREATED).entity(presenter).build();
    }

    private Response buildErrorResponse(Response.Status status, String message) {
        CustomPresenter presenter = CustomPresenter.init();
        presenter.setReason(status.getReasonPhrase());
        presenter.setStatusCode(status.getStatusCode());
        presenter.setMessage(message);
        return Response.status(status).entity(presenter).build();
    }

    private CustomPresenter createPresenter (Response.Status status, String message, Book book) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CustomPresenter presenter = CustomPresenter.init();
        presenter.setReason(status.getReasonPhrase());
        presenter.setStatusCode(status.getStatusCode());
        presenter.setMessage(message);
        presenter.setData(objectMapper.writeValueAsString(book).getBytes());
        return presenter;
    }
}
