package model;

import model.City;
import model.Event;
import model.Merchant;
import model.Product;

import java.sql.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Game {
    public  void run(){
        java.util.Random r = new java.util.Random();
        Merchant merchant = new Merchant();
        print("Вы торговец на просторах земель Скайрима.\n"+"Вам нужно закупиться товаром для продажи");
        List<Product>products = new ArrayList<>();
        merchant.merchantInitialMoney();
        buyProduct(products,merchant);

    }
    public void buyProduct(List<Product> products,Merchant merchant){
        products = new ArrayList<>();
        merchant.setCarrying(80);
        int carrying = merchant.getCarrying();
        int money = merchant.getMoney();
        int choice = 0;
        System.out.printf("У Вас имеется %s золота. Грузоподъемность %s единиц%n",money,carrying);
        int i = 0;
        while (choice !=3){
            Product product = new Product();
                choice = choice("Вы хотите купить товар? Да(1) Нет (2)");

                if(choice ==1 && money>25 && carrying>14){
                    products.add(product);
                    product.getTypeofProduct();
                    System.out.printf("Вы купили %s, потрачено (%s золота)",products.get(i).getType(),products.get(i).getPrice());
                    money-=products.get(i).getPrice();
                    merchant.setMoney(money);
                    carrying-=products.get(i).getWeight();
                    merchant.setCarrying(carrying);
                    System.out.printf("Ваше золото %s, Ваша грузоподъемность %s%n",money,carrying);
                    i++;
                }else if (choice == 2 && carrying!=80){
                    print("Покупка завершена");
                    startTrip(merchant,products);
                    break;
                } else if (money<25) {
                    print("У Вас недостаточно золота для совершения покупки");
                }else if (carrying<14){
                    print("Не хватает места для совершения покупки, Вы будете перегружены");
                } else if (choice == 2 && carrying == 80) {
                    print("Вам нужно купить товар для начала поездки");
                }


        }


    }
    public static void print(String message){
        System.out.println(message);
    }
    public static int choice(String message) {
        print(message);
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
    public void startTrip(Merchant merchant, List<Product> products){
        java.util.Random r = new java.util.Random();
        int city = r.nextInt(5)+1;
        switch (city) {
            case 1:
                System.out.printf("Мы отправлямся в город %s%n", City.SOLITUDE.getValue());
                break;
            case 2:
                System.out.printf("Мы отправлямся в город %s%n", City.RIFTON.getValue());
                break;
            case 3:
                System.out.printf("Мы отправлямся в город %s%n", City.DAWNSTAR.getValue());
                break;
            case 4:
                System.out.printf("Мы отправлямся в город %s%n", City.WHITERUN.getValue());
                break;
            case 5:
                System.out.printf("Мы отправлямся в город %s%n", City.WINDHELM.getValue());
                break;
        }

            int distance = r.nextInt(50)+50;
            int speed = merchant.setMerchantSpeed();
            System.out.printf("Расстояние до города %s.%n",distance);
            trip(distance,speed,merchant,products);
    }

    public  void trip(int distance, int speed, Merchant merchant, List<Product> products){
        java.util.Random r = new java.util.Random();
        int choice=0;
        Event events = new Event();
        do {
            int event = r.nextInt(9)+1;

                choice = choice("Продолжаем путь(1)\n" +
                        "Закончить поездку (3)");
                if (choice == 1) {
                    if (distance>1) {
                        switch (event) {
                            case 1:
                                events.regularDay();
                                distance -= speed;
                                System.out.printf("За сегодня вы прошли %s лиг. До пункта назначения осталось %s лиг.%n", speed, distance);
                                break;
                            case 2:
                                products = events.rainyDay(products);
                                if (speed > 2) {
                                    speed -= 2;
                                    distance -= speed;
                                } else {
                                    print("Вы движитесь на минимальной скорости");
                                    distance -= speed;
                                }
                                System.out.printf("Скорость повозки %s.До пункта назначения осталось %s%n", speed, distance);
                                break;
                            case 3:
                                speed = events.roughRoad(speed);
                                distance -= speed;
                                System.out.printf("Скорость повозки %s.До пункта назначения осталось %s%n", speed, distance);
                                break;
                            case 4:
                                events.brokenWheel();
                                System.out.printf("Скорость повозки %s.До пункта назначения осталось %s%n", speed, distance);
                                break;
                            case 5:
                                events.river();
                                System.out.printf("Скорость повозки %s.До пункта назначения осталось %s%n", speed, distance);
                                break;
                            case 6:
                                distance = events.metLocal(distance);
                                break;
                            case 7:
                                events.bandit(distance,speed, merchant, products);
                                break;
                            case 8:
                                events.inn(distance, speed, products, merchant);
                                break;
                            case 9:
                                events.goodSpoiled(products);
                                distance -= speed;
                                System.out.printf("Скорость повозки %s.До пункта назначения осталось %s%n", speed, distance);
                                break;
                        }
                    } else if (distance<=0){
                        System.out.println("Вы прибыли в пункт назначения");
                        int cash = 0;
                        for (var str:products) {
                            int quality = (int) Math.round(str.getPrice()*str.getQuality());
                            cash+=quality;
                        }
                        products.clear();
                        int money = merchant.getMoney();
                        merchant.setMoney(money+cash);
                        merchant.setCarrying(80);
                        if (cash>0){
                            System.out.printf("Вы заработали %s%n",cash);
                        }else {
                            print("К сожалению Вам не удалось заработать. Поездка была напрасной");
                        }
                        buyProduct(products,merchant);
                    }
                }
        }while (choice!=3);
        print("Конец поездки");
    }
}
