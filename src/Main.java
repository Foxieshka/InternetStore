import Store.Store;

import java.util.*;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Store catalog = new Store(); // Каталог товаров

    public static void printHelp(){
        System.out.println("\n========= Доступные команды: =========");
        System.out.println("1. help - вывести справку по программе");
        System.out.println("2. exit - выход из программы");
        System.out.println("3. add - добавить товар в корзину");
        System.out.println("4. remove - удалить товар из корзины");
        System.out.println("5. show - просмотреть содержимое корзины");
        System.out.println("6. buy - купить все товары из корзины");
        System.out.println("7. info - получить информацию о товаре");
        System.out.println("8. orders - вывести историю заказов");
        System.out.println("======================================\n");
    }

    // Добавить товар в корзину по названию
    public static void add(){
        System.out.print("Введите название товара: ");
        String productName = readStr(); // Ввод названия
        System.out.print("Введите количество: ");
        int quantity = readInt(); // ввод количества
        try {
            catalog.addToCart(productName, quantity);
        } catch (Exception e){
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    // Удалить из корзины
    public static void remove(){
        System.out.print("Введите название товара: ");
        String productName = readStr();
        catalog.removeFromCart(productName);
    }

    // Вывести информацию о товаре
    public static void getInfo(){
        System.out.print("Введите название товара: ");
        String productName = readStr();
        catalog.getInfo(productName);
    }

    // Методы для считывания строк и чисел
    public static int readInt(){
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    public static String readStr(){
        String str = scanner.nextLine().trim();
        return str;
    }

    public static void main(String[] args) {
        String command; // Команда пользователя
        System.out.println("Добро пожаловать в магазин Lapki!"); // Выводим приветствие
        catalog.showCatalog();
        printHelp();

        while(true){
            System.out.println("======================================");
            System.out.print("Введите команду: ");
            command = scanner.nextLine().trim().toLowerCase();
            try {
                switch (command) {
                    case "help": printHelp(); break;
                    case "show": catalog.showCart(); break;
                    case "add": add(); break;
                    case "remove": remove(); break;
                    case "orders": catalog.showOrders(); break;
                    case "buy": catalog.buyAll(); break;
                    case "info": getInfo(); break;
                    case "exit": return;
                    default: System.out.println("Такой команды нет");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
            System.out.println("======================================\n");
        }
    }
}
