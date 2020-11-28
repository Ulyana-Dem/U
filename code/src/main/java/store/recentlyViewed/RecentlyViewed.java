package store.recentlyViewed;

import store.model.product.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;

public interface RecentlyViewed {
    LinkedList<Product> getRecentlyViewedProductList(HttpServletRequest request);

    void addToRecentlyViewedProductList(LinkedList<Product> recentlyViewedList, long productId);
}
