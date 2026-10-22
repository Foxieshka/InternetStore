package Model;

import Enums.*;
public class Pet extends Product {
    Animal animal; // Вид животного
    // Конструктор
    public Pet(String name, int price, int quantity, Animal animal) {
        super(name, price, quantity, Category.PET);
        this.animal = animal;
    }
    @Override
    public String getInfo() {
        return "Наименование: " + name + ", Вид животного: " + animal + "\n";
    }
}
