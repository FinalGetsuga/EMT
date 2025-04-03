package mk.finki.ukim.wp.emt2.service.domain.impl;

import mk.finki.ukim.wp.emt2.model.domain.Book;
import mk.finki.ukim.wp.emt2.model.domain.User;
import mk.finki.ukim.wp.emt2.model.domain.Wishlist;
import mk.finki.ukim.wp.emt2.model.exceptions.BookAlreadyInWishlistException;
import mk.finki.ukim.wp.emt2.model.exceptions.BookNotFoundException;
import mk.finki.ukim.wp.emt2.model.exceptions.WishlistNotFoundException;
import mk.finki.ukim.wp.emt2.repository.WishlistRepository;
import mk.finki.ukim.wp.emt2.service.domain.BookService;
import mk.finki.ukim.wp.emt2.service.domain.UserService;
import mk.finki.ukim.wp.emt2.service.domain.WishlistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserService userService;
    private final BookService bookService;

    public WishlistServiceImpl(WishlistRepository wishlistRepository, UserService userService, BookService bookService) {
        this.wishlistRepository = wishlistRepository;
        this.userService = userService;
        this.bookService = bookService;
    }

    @Override
    public List<Book> listAllBooksInWishlist(Long wishlistId) {
        if (wishlistRepository.findById(wishlistId).isEmpty())
            throw new WishlistNotFoundException(wishlistId);
        return wishlistRepository.findById(wishlistId).get().getBooks();
    }

    @Override
    public Optional<Wishlist> getActiveWishlist(String username) {
        User user = userService.findByUsername(username);

        return Optional.of(wishlistRepository.findByUser(user)
                .orElseGet(() -> wishlistRepository.save(new Wishlist(user))));

    }

    @Override
    public Optional<Wishlist> addBookToWishlist(String username, Long bookId) {
        if (getActiveWishlist(username).isPresent()) {
            Wishlist wishlist = getActiveWishlist(username).get();

            Book book = bookService.findById(bookId)
                    .orElseThrow(() -> new BookNotFoundException(bookId));
            if (!wishlist.getBooks().stream().filter(i -> i.getId().equals(bookId)).toList().isEmpty())
                throw new BookAlreadyInWishlistException(bookId, username);
            wishlist.getBooks().add(book);
            return Optional.of(wishlistRepository.save(wishlist));
        }
        return Optional.empty();

    }
}
