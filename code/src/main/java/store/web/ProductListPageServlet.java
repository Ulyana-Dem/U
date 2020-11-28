package store.web;


import store.model.product.ProductDao;
import store.model.product.ProductDaoImpl;
import store.recentlyViewed.RecentlyViewedService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductListPageServlet extends HttpServlet {

    private ProductDao productDao;
    private RecentlyViewedService recentlyViewedService;

    @Override
    public void init(ServletConfig config) {

        productDao = ProductDaoImpl.getInstance();
        recentlyViewedService = RecentlyViewedService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        String sort = request.getParameter("sort");
        String order = request.getParameter("order");
        request.setAttribute("products", productDao.findProducts(query, order, sort));
        request.setAttribute("recentlyViewed", recentlyViewedService.getRecentlyViewedProductList(request));
        request.getRequestDispatcher("/WEB-INF/pages/productList.jsp").forward(request, response);
    }
}
