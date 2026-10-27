package Menu;

import Model.*;
import Store.Store;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Menu {
    public Scanner scanner = new Scanner(System.in);

    // Ввод команды
    public String readCommand(){
        System.out.print("> ");
        return scanner.nextLine().trim().toLowerCase();
    }

    // Ввод числа
    public int readInt(String prompt){
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Это не число, попробуйте снова.");
            }
        }
    }

    public String readString(String invite){
        System.out.print(invite);
        return scanner.nextLine().trim();
    }

    // Вывод справки
    public void printHelp(){
        System.out.println("\n========= Доступные команды: =========");
        System.out.println("help - вывести справку по программе");
        System.out.println("exit - выход из программы");
        System.out.println("add - добавить товар в корзину");
        System.out.println("remove - удалить товар из корзины");
        System.out.println("show - просмотреть содержимое корзины");
        System.out.println("buy - купить все товары из корзины");
        System.out.println("info - получить информацию о товаре");
        System.out.println("orders - вывести историю заказов");
        System.out.println("products - вывести список товаров");
        System.out.println("======================================\n");
    }

    // Вывод ошибки
    public void printError(String msg){
        System.out.println("⚠ " + msg);
    }

    // Просмотр каталога
    public void printCatalog(Store store) {
        LinkedHashMap<String, Promocode> promenades = store.getPromenades();
        ArrayList<Product> products = store.getProducts();
        // Выводим список товаров
        System.out.println("\n========= Каталог товаров ==========");
        for (Product product : products) System.out.println(product.getInfo());
        System.out.println("====================================\n");

        System.out.println("\n============ Промокоды ============");
        // Выводим список промокодов
        for (String key: promenades.keySet()) System.out.println(promenades.get(key).getInfo());
        System.out.println("====================================\n");
    }

    // Просмотр корзины
    public void printCart(Cart cart) {
        System.out.println("Корзина: ");
        if (cart.isEmpty()) {
            System.out.println("Корзина пуста\n");
        }
        // Выводим содержимое корзины
        for (String key : cart.getItems().keySet()) {
            // Получаем товар из корзины
            CartItem item = cart.getItems().get(key);
            System.out.println(item.getInfo());
        }
    }

    // Вывести историю заказов
    public void printOrders(Store store) {
        System.out.println("\nЗаказы:");
        if(store.getUser().getOrders().isEmpty()){
            System.out.println("История заказов пуста.\n");
        }else {
            for (Order order : store.getUser().getOrders())
                System.out.println(order.getInfo());
        }
    }

    // Выбор платежной системы
    public int choosePaymentSystem(){
        System.out.println("Выберите способ оплаты (введите число):");
        System.out.println("1 - Карта");
        System.out.println("2 - Баланс");
        System.out.println("3 - При получении");
        return readInt(">");
    }

    // Просмотр информации о товаре
    public void printInfo(){

    }

    //Вывести информацию о заказе
    public void printOrderInfo(){
        
    }
}
