package store.model.product;

import java.util.List;

public interface ProductDao {
    Product getProduct(Long id);

    List<Product> findProducts(String query, String order, String sort);

    void setNewStock(long productId, int newStock);
}
