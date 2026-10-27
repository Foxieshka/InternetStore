package Model;

public class Promocode {
    private String code; // Код
    private int percent; // Скидка (в %)
    private boolean used = false;

    // Конструктор
    public Promocode(String code, int percent){
        this.code = code;
        this.percent = percent;
    }

    // Геттеры
    public String getCode() { return code; }
    public int getPercent() { return percent; }
    public boolean getUsed() { return used; }

    // Сеттеры
    public void setCode(String code) { this.code = code; }
    public void setPercent(int percent) {this.percent = percent; }
    public void setUsed(boolean used) {this.used = used; }

    // Строковое представление
    public String getInfo(){
        String info = "Промокод: " + code + ", Скидка: " + percent + " %, ";
        info += used ? "Использован" : "Активный";
        return info;
    }
}
