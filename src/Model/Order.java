package Model;

import Enums.OrderStatus;
import Enums.PaymentStatus;
import Exceptions.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Order {
    private int id; // Номер заказа
    private static int counter; // Счетчик для id
    private PaymentStatus paymentStatus; // Статус оплаты
    private OrderStatus status; // Статус заказа
    private String address; // Адрес доставки
    private ArrayList<OrderItem> orderItems = new ArrayList<>(); // Позиции заказа

    public Order(OrderStatus status,
                 PaymentStatus paymentStatus, String address,
                 LinkedHashMap<String, CartItem> items) {

        if (paymentStatus == null){
            throw new InvalidPaymentStatus("Статус оплаты не может быть пустым.");
        }
        if (status == null){
            throw new InvalidOrderStatus("Статус заказа не может быть пустым.");
        }
        if(items == null || items.isEmpty()){
            throw new EmptyOrderItemsException("Заказ не может быть пустым");
        }
        id = ++counter;
        this.address = address;
        this.status = status;
        this.paymentStatus = paymentStatus;

        // Заполняем заказ товарами из корзины
        for(String key: items.keySet()){
            CartItem current = items.get(key);
            OrderItem position = new OrderItem(current.getProduct(), current.getQuantity());
            orderItems.add(position);
        }
    }

    // Геттеры
    public int getId() { return id; }
    public PaymentStatus getPaymentStatus() { return paymentStatus; }
    public OrderStatus getStatus() { return status; }
    public String getAddress() { return address; }

    // Сеттеры
    public void setPaymentStatus(PaymentStatus paymentStatus)
    throws InvalidPaymentStatus
    {
        if (paymentStatus == null){
            throw new InvalidPaymentStatus("Статус оплаты не может быть пустым.");
        }
        this.paymentStatus = paymentStatus;
    }

    public void setStatus(OrderStatus orderStatus)
    throws InvalidOrderStatus
    {
        if (orderStatus == null){
            throw new InvalidOrderStatus("Статус заказа не может быть пустым.");
        }
        this.status = orderStatus;
    }

    public void setAddress(String address){
        this.address = address;
    }

    // Информация о заказе
    public String getInfo(){
        String result = "Заказ #" + id + "\n";
        result += "Состав заказа: \n";
        for (OrderItem item: orderItems){
            result += item.getInfo() + "\n";
        }
        result += "Статус оплаты: " + paymentStatus + ", Статус заказа: " + status;
        return result;
    }
}
