package store.cart;

import store.exceptions.OutOfStockException;

import javax.servlet.http.HttpServletRequest;

public interface CartService {

    Cart getCart(HttpServletRequest request);

    void add(Cart cart, long productId, int quantity) throws OutOfStockException;

    void update(Cart cart, long productId, int quantity) throws OutOfStockException;

    void deleteProduct(Cart cart, long productId);

    void clearCart(Cart cart, HttpServletRequest request);
}
