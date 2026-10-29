import Menu.Menu;
import Model.BalancePayment;
import Model.CardPayment;
import Model.CashPayment;
import Model.PaymentSystem;
import Store.Store;

import java.util.*;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Store catalog = new Store(); // Каталог товаров
    public static Menu menu = new Menu(); // Консольное меню

    // Добавить товар в корзину по названию
    public static void handleAdd(){
        String productName = menu.readString("Введите название товара: "); // Ввод названия
        int quantity = menu.readInt("Введите количество: "); // ввод количества
        catalog.addToCart(productName, quantity);
        System.out.println("Добавлено: " + productName + " x" + quantity);
    }

    // Удалить из корзины
    public static void handleRemove(){
        String productName = menu.readString("Введите название товара: ");
        catalog.removeFromCart(productName);
        System.out.println("Товар успешно удален из корзины");
    }

    // Вывести информацию о товаре
    public static void handleInfo(){
        String productName = menu.readString("Введите название товара: ");
        catalog.getInfo(productName);
    }

    // Метод для обработки заказа
    public static void handleBuy(){
        int choice = menu.choosePaymentSystem();
        PaymentSystem payment;
        switch (choice) {
            case 1: payment = new CardPayment(); break;
            case 2: payment = new BalancePayment(catalog.getUser()); break;
            case 3: payment = new CashPayment(); break;
            default: menu.printError("Неверный выбор"); return;
        }
        menu.printOrderInfo(catalog.buyAll(payment));
    }

    public static void main(String[] args) {
        String command; // Команда пользователя
        System.out.println("Добро пожаловать в магазин Lapki!"); // Выводим приветствие
        menu.printCatalog(catalog);
        menu.printHelp();

        while(true){
            System.out.println("======================================");
            command = menu.readCommand();
            try {
                switch (command) {
                    case "help": menu.printHelp(); break;
                    case "show": menu.printCart(catalog.getUser().getCart()); break;
                    case "add": handleAdd(); break;
                    case "remove": handleRemove(); break;
                    case "orders": menu.printOrders(catalog); break;
                    case "buy": handleBuy(); break;
                    case "info": handleInfo(); break;
                    case "exit": return;
                    case "products": menu.printCatalog(catalog); break;
                    default: menu.printError("Такой команды нет");
                }
            } catch (Exception e) {
                menu.printError(e.getMessage());
            }
            System.out.println("======================================\n");
        }
    }
}
