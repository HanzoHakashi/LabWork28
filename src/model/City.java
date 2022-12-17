package model;

public enum City {
    SOLITUDE("Солитьюд"),
    RIFTON("Рифтен"),
    DAWNSTAR("Данстар"),
    WINDHELM("Виндхельм"),
    WHITERUN("Вайтран");
    private String value;
    City(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
