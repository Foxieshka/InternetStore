package Model;

import Exceptions.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    // Ключ - имя товара, значение - позиция картины
    private final Map<String, CartItem> items; // Корзина

    // Конструктор
    public Cart(){
        items = new LinkedHashMap<>();
    }

    // Геттеры
    public Map<String, CartItem> getItems() { return items; }

    // Методы для работы с корзиной
    public void add(Product product, int quantity){
        CartItem existing = items.get(product.name);
        if(existing == null){
            items.put(product.name, new CartItem(product, quantity));
        }else{
            existing.increaseQuantity(quantity);
        }
    }

    // Удалить товар по ключу
    public void remove(String productName)
    throws ProductNotFoundException{
        if(!items.containsKey(productName)){
            throw new ProductNotFoundException("Удаляемый товар не найден.");
        }
        items.remove(productName);
    }

    // Пуста ли корзина?
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // Общая сумма заказа
    public int getTotal(){
        int totalSum = 0;
        for (String key : items.keySet()) {
            // Получаем товар из корзины
            CartItem item = items.get(key);
            totalSum += item.getTotalPrice();
        }
        return totalSum;
    }

    // Очистить корзину
    public void clear(){ items.clear(); }
}
