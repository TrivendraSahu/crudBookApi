package com.api.springcrudbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.springcrudbook.dao.CartRepository;
import com.api.springcrudbook.entity.Book;
import com.api.springcrudbook.entity.Cart;
import com.api.springcrudbook.entity.User;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Override
    public Cart addToCart(Long bookId, int quantity, Long userId) {
        Book book = bookService.getBookById(bookId);
        if (book == null) {
            throw new RuntimeException("Book not found");
        }

        if (!isAvailable(book, quantity)) {
            throw new RuntimeException("Not enough quantity available");
        }

        User user = userService.getUserById(userId); // Fetch user from database
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Cart cartItem = new Cart();
        cartItem.setBook(book);
        cartItem.setQuantity(quantity);
        cartItem.setUser(user); // Associate the user with the cart item

        // Update copiesIssued and copiesAvailable fields of the book
        book.setCopiesIssued(book.getCopiesIssued() + quantity);
        book.setCopiesAvailable(book.getTotalCopies() - book.getCopiesIssued());
        bookService.updateBook(book.getId(), book); // Update book in database

        return cartRepository.save(cartItem);
    }



    private boolean isAvailable(Book book, int quantity) {
        return book.getCopiesAvailable() >= quantity;
    }

	
	  @Override public List<Cart> getCartItems(Long userId) { return
	  cartRepository.findByUserId(userId); }
	 

    @Override
    public void updateCartItem(Long cartItemId, int quantity) {
        Cart cartItem = cartRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("Cart item not found"));
        cartItem.setQuantity(quantity);
        cartRepository.save(cartItem);
    }

    @Override
    public void removeFromCart(Long cartItemId) {
        cartRepository.deleteById(cartItemId);
    }
}
