package model;

public class Product {
    private int weight;
    private String type;
    private double quality;
    private int price;
    private  java.util.Random r = new java.util.Random();

    public void getTypeofProduct(){
        int product = r.nextInt(7)+1;
        int quality = r.nextInt(1)+9;
        switch (product){
            case 1:
                setType("Мясо");
                setWeight(14);
                setPrice(25);
                setQuality(1.2);
                break;
            case 2:
                setType("Сухофрукты");
                setWeight(6);
                setPrice(15);
                setQuality(1.2);
                break;
            case 3:
                setType("Зерно");
                setWeight(14);
                setPrice(17);
                setQuality(1.2);
                break;
            case 4:
                setType("Мука");
                setWeight(5);
                setPrice(16);
                setQuality(1.2);
                break;
            case 5:
                setType("Ткани");
                setWeight(12);
                setPrice(22);
                setQuality(1.2);
                break;
            case 6:
                setType("Краска");
                setWeight(13);
                setPrice(24);
                setQuality(1.2);
                break;
            case 7:
                setType("Скума");
                setWeight(14);
                setPrice(25);
                setQuality(1.2);
                break;
        }
    }

    public void setProductQuality(int chance){
        if (chance<=3) {
            int quality = r.nextInt(4) + 1;
            switch (quality) {
                case 1:
                    setQuality(0.95);
                    break;
                case 2:
                    setQuality(0.55);
                    break;
                case 3:
                    setQuality(0.25);
                    break;
                case 4:
                    setQuality(0.1);
                    break;
            }
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
