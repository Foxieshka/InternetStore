package Model;

public interface PaymentSystem {
    boolean pay(double amount); // Провести оплату на сумму
    String getName(); // человекочитаемое имя способа
}
