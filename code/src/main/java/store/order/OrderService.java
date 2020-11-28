package store.order;

import store.cart.Cart;

import java.util.List;

public interface OrderService {
    Order createOrder(Cart cart, DeliveryMode deliveryMode);

    List<PaymentMethod> getPaymentMethods();

    List<DeliveryMode> getDeliveryModes();

    void placeOrder(Order order);
}
