package store.utility;

import store.model.product.ProductDaoImpl;

import javax.servlet.http.HttpServletRequest;

public class RequestUtility {
    private static volatile RequestUtility instance;

    private RequestUtility() {
    }

    public static RequestUtility getInstance() {

        RequestUtility localInstance = instance;

        if (instance == null) {
            synchronized (ProductDaoImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new RequestUtility();
                }

            }

        }
        return instance;
    }

    public Long getProductId(HttpServletRequest request) {
        String uri = request.getRequestURI();

        int index = uri.indexOf(request.getServletPath());
        return Long.parseLong(uri.substring(index + request.getServletPath().length() + 1));
    }

}
