package org.acme.applications.Book.adapters;

import org.acme.applications.Book.ports.BookCreatePort;
import org.acme.applications.shared.CustomException;
import org.acme.applications.shared.ValidationUtil;
import org.acme.domain.models.Book;
import org.acme.domain.repository.GenericCreate;

import java.util.Objects;
import java.util.Optional;

public class BookCreateAdapter implements BookCreatePort<Book, Book> {
    private final GenericCreate<Book, Book> create;

    private BookCreateAdapter(GenericCreate<Book, Book> create){
        this.create = create;
    }

    public static BookCreateAdapter init(GenericCreate<Book, Book> create){
        return new BookCreateAdapter(create);
    }

    @Override
    public Optional<Book> create(Book entities) throws CustomException {
        this.validateModel(entities);
        return this.create.create(entities);
    }

    private void validateModel(Book book) throws CustomException {
        if(Objects.isNull(book)){
            throw new CustomException("Book cannot be null");
        }

        //System.out.print(book.getAuthors().get(0).getUuid());
        ValidationUtil.validateField(book.getTitle(), "The book name cannot be null");
        ValidationUtil.validateString(book.getTitle(), "The name of the book cannot be empty");
        ValidationUtil.validateField(book.getAuthors(), "The author cannot be empty");
        ValidationUtil.validateField(book.getEditorial(), "The editorial cannot be");
        ValidationUtil.validateField(book.getDetailModel(), "The detail model cannot be");
        ValidationUtil.validateField(book.getDescriptionModel(), "Description cannot be null");
        ValidationUtil.validateField(book.getPhoto(), "The photo cannot be null");
        ValidationUtil.validateString(book.getPhoto(), "The photo must not be clean");
    }
}
