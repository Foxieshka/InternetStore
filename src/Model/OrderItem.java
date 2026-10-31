package Model;

public class OrderItem {
    private int quantity; // Количество единиц заказа
    private final Product product; // Единица заказа

    // Конструктор
    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Метод для получения общей цены
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    // Геттеры
    public int getQuantity() { return quantity; }
    public Product getProduct() { return product; }

    // Вывод информации
    public String getInfo() {
        return product.getName() + " x " + quantity + " - " + this.getTotalPrice();
    }
}
