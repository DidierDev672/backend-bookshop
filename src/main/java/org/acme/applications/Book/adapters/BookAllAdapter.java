package org.acme.applications.Book.adapters;

import org.acme.applications.Book.ports.BookAllPort;
import org.acme.applications.shared.CustomException;
import org.acme.domain.models.Book;
import org.acme.domain.repository.GenericGetAllRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookAllAdapter implements BookAllPort {
    private final GenericGetAllRepository<Book> bookGenericGetAllRepository;

    private BookAllAdapter(GenericGetAllRepository<Book> bookGenericGetAllRepository){
        this.bookGenericGetAllRepository = bookGenericGetAllRepository;
    }

    public static BookAllAdapter init(GenericGetAllRepository<Book> bookGenericGetAllRepository){
        return new BookAllAdapter(bookGenericGetAllRepository);
    }

    @Override
    public Optional<List<Book>> all() throws CustomException {
        return  this.bookGenericGetAllRepository.getAll()
                .map(this::convertToBookList);
    }

    private List<Book> convertToBookList(List<Book> bookList) {
        return bookList.stream()
               .map(this::convertToBook)
               .collect(Collectors.toList());
    }

    private Book convertToBook(Book book){
        return new Book(
                book.getUuid(),
                book.getTitle(),
                book.getAuthors(),
                book.getEditorial(),
                book.getDetailModel(),
                book.getDescriptionModel(),
                book.getPhoto()
        );
    }
}
