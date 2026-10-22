package Model;

import Exceptions.*;
import Enums.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Customer {
    private static int counter = 0; // Счетчик для артикула
    private final int id; // ID
    private String name; // Имя
    private String surname; // Фамилия
    private String email; // Электронная почта
    private String password; // Пароль
    private int balance = 0; // Баланс

    private Cart cart = new Cart(); // Корзина
    private ArrayList<Order> orders = new ArrayList<>(); // Заказы

    public Customer(String name, String surname, String email, String password) {
        if(!validateEmail(email)){
            throw new InvalidEmailException("Неверный формат email.");
        }
        if(!validatePassword(password)){
            throw new InvalidPasswordException("Неверный формат пароля.");
        }
        if(name.isEmpty() || surname.isEmpty()){
            throw new InvalidNameOrSurnameException("Имя и фамилия не должны быть пустыми.");
        }

        id = ++counter; // Увеличиваем счетчик и присваиваем его ID
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    // Методы для валидации
    public static boolean validateEmail(String email) {
        if(email == null || !email.contains("@") || !email.contains(".")) return false;
        return email.length() >= 5;
    }
    public static boolean validatePassword(String password) {
        return password.length() >= 8;
    }

    // Геттеры
    public int getId() { return id; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getEmail() { return email; }
    public int getBalance() { return balance; }
    public Cart getCart() { return cart; }
    public ArrayList<Order> getOrders() { return orders; }

    // Сеттеры
    public void setPassword(String password) {
        if (!validatePassword(password))
            throw new IllegalArgumentException("Пароль короче 8 символов");
        this.password = password;
    }

    public void setEmail(String email) {
        if(!validateEmail(email))
            throw new InvalidEmailException("Неверный формат электронной почты");
        this.email = email;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()){
            throw new InvalidNameOrSurnameException("Имя не может быть пустым");
        }
        this.name = name;
    }

    public void setSurname(String surname) {
        if (surname == null || surname.isEmpty()){
            throw new InvalidNameOrSurnameException("Фамилия не может быть пустой");
        }
        this.surname = surname;
    }

    public void setBalance(int balance) {
        if (balance < 0) balance = 0;
        this.balance = balance;
    }

    // Методы для работы с корзиной
    public void addToCart(Product product, int quantity) {
        cart.add(product, quantity);
    }

    // Удалить товар из корзины
    public void removeFromCart(String productName) {
        cart.remove(productName);
    }

    // Методы для работы с заказами

    // Заказать все
    public void buyAll(){
        LinkedHashMap<String, CartItem> items = cart.getItems();
        if(items.isEmpty()){
            throw new EmptyOrderItemsException("Корзина пуста.");
        }
        int total = cart.getTotal();
        if(total > balance){
            throw new NotEnoughBalanceException(
                    "Не хватает " + (total - balance) + " Р");
        }
        Order order = new Order(OrderStatus.ASSEMBLED, PaymentStatus.UPON_RECEIVING,
                "Улица Ленина, дом 482/1", items);
        orders.add(order); // Добавляем в историю

        // списать товар со склада
        for (CartItem item : cart.getItems().values()) {
            item.getProduct().decreaseQuantity(item.getQuantity());
        }

        balance -= total; // Списываем средства
        cart.clear(); // Очищаем корзину
        System.out.println("Заказ #" + order.getId() + " оформлен на " + total + " Р");
    }
}
