package Model;

import java.lang.Math;

public class CartItem {
    private int quantity; // Количество товаров в корзине
    private final Product product; // Товар

    // Конструктор
    public CartItem(Product product, int quantity) {
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

    // Методы для изменения количества
    public void increaseQuantity(int quantity) {
        this.quantity += Math.abs(quantity);
    }

    public void decreaseQuantity(int quantity) {
        if (quantity > 0 && this.quantity >= quantity) {
            this.quantity -= quantity;
        }
    }
    // Вывод информации
    public String getInfo(){
        return product.getName() + " x " + quantity + " - " + this.getTotalPrice();
    }
}
