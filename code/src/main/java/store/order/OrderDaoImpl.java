package store.order;

import store.exceptions.OrderNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OrderDaoImpl implements OrderDao {
    private static volatile OrderDaoImpl instance = null;

    private List<Order> orders = new ArrayList<>();
    private AtomicLong orderId = new AtomicLong();

    public List<Order> getOrders() {
        return orders;
    }

    public Long getOrderId() {
        return orderId.get();
    }

    private OrderDaoImpl() {
    }

    public static OrderDaoImpl getInstance() {
        OrderDaoImpl localInstance = instance;

        if (instance == null) {
            synchronized (OrderDaoImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new OrderDaoImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void save(Order order) {
        order.setId(orderId.incrementAndGet());
        orders.add(order);
        order.setSecureId(UUID.randomUUID().toString());

    }

    @Override
    public Order getBySecureId(String secureId) throws OrderNotFoundException {
        return orders.stream()
                .filter(order -> secureId.equals(order.getSecureId()))
                .findFirst()
                .orElseThrow(() -> new OrderNotFoundException("Order with  secure id: " + secureId + " not exists"));
    }
}
