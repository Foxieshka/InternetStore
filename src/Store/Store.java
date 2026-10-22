package Store;

import Enums.*;
import Exceptions.*;
import Model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Store {
    public Customer user; // Тестовый пользователь
    public ArrayList<Product> products; // Товары

    // Конструктор
    public Store() {
        // Создаем тестового пользователя
        user = new Customer("Алина",
        "Лисичкина", "foxieshka@vk.com", "Tkm97rushy");
        // Устанавливаем баланс
        user.setBalance(20000);
        // Тестовая дата
        LocalDate fishDate = LocalDate.now().plusDays(28);
        LocalDate chocolateDate = LocalDate.now().plusMonths(3);
        // Создаем список товаров
        products = new ArrayList<>();
        products.add(new Clothing("Платье", 4000,
                24, Sizes.S, "Зелёное"));
        products.add(new Pet("Домик для шиншиллы", 3700, 100,
                Animal.RODENTS));
        products.add(new Pet("Корм для попугая", 520, 130,
                Animal.BIRD));
        products.add(new Food("Форель слабосолёная", 230, 2,
                fishDate, "Охлаждённое мясо форели, соль.", 100));
        products.add(new Food("Молочный шоколад", 180, 5,
                chocolateDate, "Какао, масло, молоко, фундук, сахар.", 160));
    }

    // Просмотр каталога
    public void showCatalog() {
        // Выводим список товаров
        System.out.println("\n========= Каталог товаров ==========");
        for (Product product : products) {
            System.out.println(product.getInfo() + " - " + product.getPrice() + " Р");
        }
        System.out.println("====================================\n");
    }

    // Просмотр корзины
    public void showCart() {
        user.getCart().showCart();
    }

    // Найти товар по названию
    public Product findProduct(String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        return null;
    }

    // Вывести информацию о товаре
    public void getInfo(String productName){
        Product product = findProduct(productName);
        if(product == null){
            throw new ProductNotFoundException("Товар не найден.");
        }
        System.out.println(product.getInfo());
    }

    // Добавить в корзину
    public void addToCart(String productName, int quantity) {
        if (quantity <= 0)
            throw new QuantityBelowZeroException("Количество должно быть > 0");
        Product product = findProduct(productName);
        if (product == null)
            throw new ProductNotFoundException("Товар не найден: " + productName);
        if (product.getQuantity() < quantity)
            throw new NotEnoughStockException("На складе только " + product.getQuantity() + " шт.");
        user.addToCart(product, quantity);
        System.out.println("Добавлено: " + productName + " x" + quantity);
    }

    // Удалить из корзины
    public void removeFromCart(String productName) {
        try {
            user.removeFromCart(productName);
            System.out.println("Товар успешно удален из корзины");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    // Вывести все заказы
    public void showOrders(){
        System.out.println("\nЗаказы:");
        if(user.getOrders().isEmpty()){
            System.out.println("История заказов пуста.\n");
        }else {
            for (Order order : user.getOrders())
                System.out.println(order.getInfo());
        }
    }

    // Заказать все товары из корзины
    public void buyAll(){
        try {
            user.buyAll();
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

}
