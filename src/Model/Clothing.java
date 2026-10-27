package Model;

import Enums.Category;
import Enums.Sizes;

public class Clothing extends Product {
    Sizes size; // Размер одежды
    String color; // Цвет одежды

    public Clothing(String name, int price, int quantity, Sizes size, String color) {
        super(name, price, quantity, Category.CLOTHING);
        if (size == null){
            throw new IllegalArgumentException("Size cannot be null");
        }
        this.size = size;
        this.color = color;
    }
    @Override
    public String getInfo() {
        return "Наименование: " + name + ", Размер: " +
                size.toString() + ", Цвет: " + color + ",\nЦена: " + price + "Р.";
    }
}
