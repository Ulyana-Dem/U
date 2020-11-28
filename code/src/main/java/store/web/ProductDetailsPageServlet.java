package store.web;

import store.cart.Cart;
import store.cart.CartService;
import store.cart.HttpSessionCartService;
import store.exceptions.OutOfStockException;
import store.model.product.Product;
import store.model.product.ProductDao;
import store.model.product.ProductDaoImpl;
import store.exceptions.ProductNotFoundException;
import store.recentlyViewed.RecentlyViewedService;
import store.utility.RequestUtility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

public class ProductDetailsPageServlet extends HttpServlet {

    private RecentlyViewedService recentlyViewedService;
    private ProductDao productDao;
    private CartService cartService;
    private RequestUtility requestUtility;

    @Override
    public void init() {
        requestUtility = RequestUtility.getInstance();
        recentlyViewedService = RecentlyViewedService.getInstance();
        productDao = ProductDaoImpl.getInstance();
        cartService = HttpSessionCartService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long productId = requestUtility.getProductId(request);

            LinkedList<Product> recentlyViewedList = recentlyViewedService.getRecentlyViewedProductList(request);

            request.setAttribute("recentlyViewed", recentlyViewedList);
            request.setAttribute("products", productDao.getProduct(productId));
            request.getRequestDispatcher("/WEB-INF/pages/productDetails.jsp").forward(request, response);
            recentlyViewedService.addToRecentlyViewedProductList(recentlyViewedList, productId);
        } catch (ProductNotFoundException | NumberFormatException exception) {
            response.sendError(404, exception.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long productId = requestUtility.getProductId(request);
        int quantity;
        try {
            quantity = Integer.valueOf(request.getParameter("quantity"));
        } catch (NumberFormatException exception) {
            request.setAttribute("error", "Not a number");
            doGet(request, response);
            return;
        } catch (IllegalArgumentException exception) {
            request.setAttribute("error", "Invalid input");
            doGet(request, response);
            return;
        }
        Cart cart = cartService.getCart(request);
        try {
            cartService.add(cart, productId, quantity);
        } catch (OutOfStockException | IllegalArgumentException ex) {
            request.setAttribute("error", ex.getMessage());
            doGet(request, response);
            return;
        }

        response.sendRedirect(request.getRequestURI() + "?message=Added successfully&quantity=" + quantity);


    }
}
