package Model;

public class BalancePayment implements PaymentSystem{
    private final Customer customer; // Храним пользователя
    public BalancePayment(Customer customer) { this.customer = customer; }

    @Override
    public boolean pay(double amount) {
        if (customer.getBalance() < amount) {
            System.out.println("Не хватает " + (customer.getBalance() - amount) + " Р");
            return false;
        }
        customer.setBalance(customer.getBalance() - amount);
        System.out.println("Списано с баланса: " + amount + " Р");
        return true;
    }
    @Override
    public String getName() { return "Баланс"; }
}
