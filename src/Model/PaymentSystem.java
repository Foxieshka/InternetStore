package Model;

public interface PaymentSystem {
    boolean pay(int amount); // Провести оплату на сумму
    String getName(); // человекочитаемое имя способа
}
