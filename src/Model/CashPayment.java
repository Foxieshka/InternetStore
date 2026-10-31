package Model;

public class CashPayment implements PaymentSystem{
    @Override
    public boolean pay(double amount) {
        System.out.println("Оплата наличными при получении: " + amount + " Р");
        return true;   // «при получении» — считаем, что оплатится
    }
    @Override
    public String getName() {
        return "При получении";
    }
}
