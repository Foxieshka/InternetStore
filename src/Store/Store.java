package Store;

import Enums.*;
import Exceptions.*;
import Model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Store {
    private final Customer user; // Тестовый пользователь
    private List<Product> products; // Товары
    private Map<String, Promocode> promenades; // Промокоды

    // Геттеры
    public Customer getUser() { return user; }
    public List<Product> getProducts() { return  products; }
    public Map<String, Promocode> getPromenades() { return promenades; }

    // Конструктор
    public Store() {
        products = new ArrayList<>();
        promenades = new LinkedHashMap<>();
        // Создаем тестового пользователя
        user = new Customer("Алина","Лисичкина",
                "foxieshka@vk.com", "Tkm97rushy");
        // Устанавливаем баланс
        user.setBalance(5000);
        seedData(); // Заполняем каталог тестовыми товарами
    }

    // Тестовые данные
    public void seedData(){
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
        // Заполняем список промокодов
        promenades = new LinkedHashMap<>();
        promenades.put("LAPKI30", new Promocode("LAPKI30", 30));
        promenades.put("PAWS10", new Promocode("PAWS10", 10));
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

    // Найти промокод по коду
    public Promocode findPromocode(String code){
        Promocode p = promenades.get(code);
        if (p == null){
            throw new PromocodeNotFoundException("Промокод " + code + " не найден.");
        }
        return p;
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
    }

    // Удалить из корзины
    public void removeFromCart(String productName) {
        user.removeFromCart(productName);
    }

    // Заказать все товары из корзины
    public Order buyAll(PaymentSystem paymentSystem){
        return user.buyAll(paymentSystem);
    }
}
