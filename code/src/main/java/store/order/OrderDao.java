package store.order;

import store.exceptions.OrderNotFoundException;

public interface OrderDao {

    void save(Order order);

    Order getBySecureId(String secureId) throws OrderNotFoundException;

}
