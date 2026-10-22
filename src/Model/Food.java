package Model;

import Enums.Category;
import Exceptions.*;
import java.time.LocalDate;

public class Food extends Product {
    private LocalDate expirationDate; // Срок годности
    private String components; // Состав
    private double weight; // Масса в граммах

    // Конструктор
    public Food(String name, int price, int quantity,
                LocalDate expirationDate, String components, double weight){

        if(expirationDate.isBefore(LocalDate.now())){
            throw new WrongExpirationDateException("Срок годности не может быть раньше текущей даты.");
        }
        super(name, price, quantity, Category.FOOD);
        this.components = components;
        this.expirationDate = expirationDate;
        this.weight = weight;
    }

    @Override
    public String getInfo() {
        return "Наименование: " + name + ", Состав: " + components + ", Срок годности: " +
                expirationDate.toString() + ", Масса: " + weight + " г";
    }
}
