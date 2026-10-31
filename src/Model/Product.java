package Model;

import Enums.Category;
import Exceptions.*;

import java.util.Objects;

public abstract class Product {
    private static int counter = 0; // Счетчик для артикула
    private final int article; // Артикул
    protected String name; // Наименование
    protected double price; // Цена
    protected int quantity; // Количество на складе
    protected final Category category; // Категория

    // Конструктор
    public Product(String name, double price, int quantity, Category category)
    throws ProductConstructorException {
        if (price < 0 || quantity < 0) {
            throw new ProductConstructorException("Цена и количество не могут быть " +
                    "отрицательными");
        }
        if (name == null || name.isEmpty()) {
            throw new ProductConstructorException("Наименование не может быть пустым");
        }
        if (category == null) {
            throw new ProductConstructorException("Категория не может быть null");
        }
        article = ++counter;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    // Геттеры
    public int getArticle() { return article; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public Category getCategory() { return category; }

    // Сеттеры
    public void setPrice(double price) {
        if (price <= 0) {
            throw new PriceBelowZeroException("Цена не может быть отрицательной");
        }
        this.price = price;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new QuantityBelowZeroException("Количество не может быть отрицательным");
        }
        this.quantity = quantity;
    }

    // Уменьшение количества товара на складе
    public void decreaseQuantity(int delta) {
        if (delta > this.quantity) {
            throw new DecreaseQuantityException("Невозможно уменьшить количество товара "
                    + this.quantity + " на " + delta);
        }
        this.quantity -= delta;
    }
    // Метод для вывода информации о товаре
    public abstract String getInfo();
}

