package model;

import java.util.List;
import java.util.Scanner;

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
    public int roughRoad(int speed){
        System.out.println("Вы попали на ровную дорогу, боги благославили Ваш путь");
        if (speed < 5){
            System.out.println("+2 к скорости повозки");
            speed+=2;
            return speed;
        } else  {
            System.out.println("Вы едите на максимальной скорости");
            return speed;
        }
    };
    public void brokenWheel(){
        System.out.println("Боги отвернулись от Вас. Колесо Вашей повозки было сломано.\n"+
                "Его нужно заменить. Сегодня Ваш день был потрачен в пустую");
    };
    public void river(){
        System.out.println("Вы наткнулись на прекрасную реку в лесу. Целый день Вы искали различный брод.");
    };
    public int metLocal(int distance){
        java.util.Random r = new java.util.Random();
        int chance = r.nextInt(3)+3;
        distance-=chance;
        System.out.println("Сегодня Вы встретили местного жителя. Он подсказал Вам короткий путь");
        System.out.printf("Вы проехали %s лиг. Вам осталось проехать %s лиг%n",chance,distance);
        return distance;
    };
     public void bandit(int distance, int speed, Merchant merchant, List<Product> products){
            Game game = new Game();
            System.out.println("Вы наткнулись на шайку бандитов.");
            System.out.println("Бандит: Не то время ты выбрал чтобы заблудится, Каджит");
            int choice = 0;
            do {
                choice = choice("Что ты будешь делать.\n"+
                    "(1)Откупиться деньгами\n"+
                    "(2)Поделиться частью товара\n");
                int money = merchant.getMoney();
                if (choice==1 && money>=20){
                    System.out.println("Вы платите 20 золота для того чтобы бандиты Вас отпустили");
                    money-=20;
                    merchant.setMoney(money);
                    System.out.printf("У Вас имеется %s золота.%n",money);
                    System.out.println("Бандиты отпускают Вас");
                    distance-=speed;
                    System.out.printf("Вы проехали %s,Вам осталось %s%n",speed,distance);
                    game.trip(distance,merchant.getSpeed(),merchant, products);
                    break;
            }else if (choice == 1 && money<20){
                System.out.println("У Вас не хватает денег для того чтобы откупиться");
            }else if (choice == 2){
                for (var str:products) {
                    int carrying = merchant.getCarrying();
                    if (str.getQuality()==1.2 || str.getQuality()==0.95 || str.getQuality() == 0.55){
                        products.remove(str);
                        merchant.setCarrying(carrying+str.getWeight());
                        System.out.printf("Бандиты забрали %s.%n",str.getType());
                        distance-=speed;
                        System.out.printf("Вы проехали %s,Вам осталось %s%n",speed,distance);
                        game.trip(distance,merchant.getSpeed(),merchant, products);
                        break;
                    }else {
                        System.out.println("У Вас нет подходящего товара");
                    }
                }
            }
        }while (choice!=221432141);

    };
    void inn(int distance,int speed, List<Product>products, Merchant merchant){
        int choice = 0;
        System.out.println("Вы нашли трактир. Вы решили остановаиться здесь на ночлег");
        while (choice!=331232){
            choice = choice("Что вы хотите сделать\n"+
                    "(1) Купить товар\n"+
                    "(2) Продать товар\n"+
                    "(3) Расплатиться за ночлег и еду\n");
            if (choice == 2 && products.isEmpty()==false){
                int i = 1;
                for (var str:products) {
                    String product = str.getType();
                    int price =(int) Math.round(str.getPrice()*str.getQuality());
                    System.out.printf("(%s)Товар %s, цена для продажи %s%n",i,product,price);
                    i++;
                }
                int sell = choice("Какой товар Вы хотите продать");
                int price =(int) Math.round(products.get(sell-1).getPrice()*products.get(sell-1).getQuality());
                int weight = products.get(sell-1).getWeight();
                int merchantMoney = merchant.getMoney()+price;
                int merchantCarrying=merchant.getCarrying();
                System.out.printf("Вы продали %s. Получено %s золота.%nТекущее количество золота %s. Грузоподъемность %s%n",products.get(sell-1).getType(),price,merchantMoney,merchantCarrying);
                merchant.setMoney(merchantMoney);
                merchant.setCarrying(merchantCarrying+weight);
                products.remove(products.get(sell-1));
            } else if (choice==1) {
                int carrying = merchant.getCarrying();
                int money = merchant.getMoney();
                int choice2 = 0;
                System.out.printf("У Вас имеется %s золота. Грузоподъемность %s единиц%n",money,carrying);
                int i = 0;
                while (choice2 !=3){
                    Product product = new Product();
                    choice2 = choice("Вы хотите купить товар? Да(1) Нет (2)");

                    if(choice2 ==1 && money>25 && carrying>14){
                        products.add(product);
                        product.getTypeofProduct();
                        System.out.printf("Вы купили %s, потрачено (%s золота)",products.get(i).getType(),products.get(i).getPrice());
                        money-=products.get(i).getPrice();
                        merchant.setMoney(money);
                        carrying-=products.get(i).getWeight();
                        merchant.setCarrying(carrying);
                        System.out.printf("Ваше золото %s, Ваша грузоподъемность %s%n",money,carrying);
                        i++;
                    }else if (choice2 == 2 && carrying!=80){
                        System.out.println("Покупка завершена");

                        break;
                    } else if (money<25) {
                        System.out.println("У Вас недостаточно золота для совершения покупки");
                    }else if (carrying<14){
                        System.out.println("Не хватает места для совершения покупки, Вы будете перегружены");
                    } else if (choice2 == 2 && carrying == 80) {
                        System.out.println("Вам нужно купить товар для начала поездки");
                    }


                }
            } else if (products.isEmpty()==true) {
                System.out.println("У Вас нет товара на продажу.");
            } else if (choice==3 && merchant.getMoney()>10) {
                System.out.println("Вы решили поесть и переночевать в трактире, стоимость услуг составила 10 золотых");
                System.out.printf("Текущее количество золота %s. Текущая грузоподъемность %s.%n",merchant.getMoney(),merchant.getCarrying());
                Game game = new Game();
                game.trip(distance,speed, merchant,products);
                break;
            } else if (choice==3 && merchant.getMoney()<10) {
                System.out.println("У Вас недостаточно денег для ночлега, Вам нужно продать товар");
            }
        }

    };
    public List<Product> goodSpoiled(List<Product> products){
        java.util.Random r = new java.util.Random();
        int size = products.size();
        int badProduct = r.nextInt(size);
        products.get(badProduct).setProductQuality(3);
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
    };
    public static int choice(String message) {
        System.out.println(message);
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                return input.nextInt();
            }
            catch (java.util.InputMismatchException e) {
                return choice("Действие недоступно, выберите действие повторно\n");
            }
        }
    }
}

