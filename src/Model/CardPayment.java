package Model;

public class CardPayment implements PaymentSystem{
    @Override
    public boolean pay(double amount) {
        // Имитация: с карты оплата проходит всегда
        System.out.println("Списано " + amount + " Р с карты");
        return true;
    }

    @Override
    public String getName() { return "Карта"; }
}
