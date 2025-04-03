package mk.finki.ukim.wp.emt2.service.domain;

import mk.finki.ukim.wp.emt2.model.domain.Book;
import mk.finki.ukim.wp.emt2.model.domain.Wishlist;

import java.util.List;
import java.util.Optional;

public interface WishlistService {

    List<Book> listAllBooksInWishlist(Long wishlistId);

    Optional<Wishlist> getActiveWishlist(String username);

    Optional<Wishlist> addBookToWishlist(String username, Long bookId);
}
