package mk.finki.ukim.wp.emt2.service.application;

import mk.finki.ukim.wp.emt2.dto.DisplayBookDto;
import mk.finki.ukim.wp.emt2.dto.WishlistDto;

import java.util.List;
import java.util.Optional;

public interface WishlistApplicationService {

    List<DisplayBookDto> listAllBooksInWishlist(Long wishlistId);

    Optional<WishlistDto> getActiveWishlist(String username);

    Optional<WishlistDto> addBookToWishlist(String username, Long bookId);
}
