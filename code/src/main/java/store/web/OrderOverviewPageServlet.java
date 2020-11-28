package store.web;

import store.exceptions.OrderNotFoundException;
import store.order.Order;
import store.order.OrderDao;
import store.order.OrderDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderOverviewPageServlet extends HttpServlet {

    private OrderDao orderDao;

    @Override
    public void init() {
        orderDao = OrderDaoImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order = null;
        try {
            order = orderDao.getBySecureId(extractId(request));
        } catch (OrderNotFoundException exception) {
            response.sendError(404, exception.getMessage());
            return;
        }
        request.setAttribute("order", order);
        request.getRequestDispatcher("/WEB-INF/pages/orderOverview.jsp").forward(request, response);
    }

    private String extractId(HttpServletRequest request) {
        return request.getPathInfo().replaceAll("/", "");
    }


}
