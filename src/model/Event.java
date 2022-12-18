package model;

import java.util.List;

public class Event {
    public void regularDay(){
        System.out.println("Ничего не произошло сегодня, один день в вашем путешествии миновал");
    };
    public List<Product> rainyDay( List<Product> products){
        System.out.println("Сегодня боги отвернулись от Вас, был дождливый день, скорость повозки была снижена");
        java.util.Random r = new java.util.Random();
        int chance = r.nextInt(10)+1;
        if (chance<=3) {
            int size = products.size();
            int badProduct = r.nextInt(size);
            products.get(badProduct).setProductQuality(chance);
            double badProductQuality= products.get(badProduct).getQuality();
            if (badProductQuality == 0.95){
                String quality = "Слегка испорчен";
                System.out.printf("Был испорчен продукт %s, Качество товара %s.%n",products.get(badProduct).getType(),quality);
            } else if (badProductQuality == 0.55) {
                String quality = "Половина испортилась";
                System.out.printf("Был испорчен продукт %s, Качество товара %s.%n",products.get(badProduct).getType(),quality);
            } else if (badProductQuality == 0.25) {
                String quality = "Почти весь пропал";
                System.out.printf("Был испорчен продукт %s, Качество товара %s.%n",products.get(badProduct).getType(),quality);
            } else if (badProductQuality == 0.1) {
                String quality = "Испорчен в хлам";
                System.out.printf("Был испорчен продукт %s, Качество товара %s.%n",products.get(badProduct).getType(),quality);
            }
            return products;
        }
        return products;
    }
    void roughRoad(){
        System.out.println("Вы попали на ровную дорогу, боги благославили Ваш путь");
    };
    void brokenWheel(){};
    void river(){};
    void metLocal(){};
    void bandit(){};
    void inn(){};
    void goodSpoiled(){};
}
