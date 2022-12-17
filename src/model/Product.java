package model;

public class Product {
    private int weight;
    private String type;
    private double quality;
    private int price;

    public void getTypeofProduct(){
        java.util.Random r = new java.util.Random();
        int product = r.nextInt(7)+1;
        switch (product){
            case 1:
                setType("Мясо");
                setWeight(14);
                setPrice(25);
                break;
            case 2:
                setType("Сухофрукты");
                setWeight(6);
                setPrice(15);
                break;
            case 3:
                setType("Зерно");
                setWeight(14);
                setPrice(17);
                break;
            case 4:
                setType("Мука");
                setWeight(5);
                setPrice(16);
                break;
            case 5:
                setType("Ткани");
                setWeight(12);
                setPrice(22);
                break;
            case 6:
                setType("Краска");
                setWeight(13);
                setPrice(24);
                break;
            case 7:
                setType("Скума");
                setWeight(14);
                setPrice(25);
                break;
        }
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
