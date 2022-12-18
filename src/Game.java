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
        List<Product> products = buyProduct();
        Event events = new Event();
        startTrip();
        int distance = r.nextInt(50)+50;
        int speed = merchant.setMerchantSpeed();
        System.out.printf("Расстояние до города %s.%n",distance);
        do {
            int event = r.nextInt(2)+1;
            int choice = choice("Продолжаем путь(1)");
            if (choice == 1) {
                switch (event) {
                    case 1:
                        events.regularDay();
                        distance-=speed;
                        System.out.printf("За сегодня вы прошли %s лиг. До пункта назначения осталось %s лиг.%n",speed,distance);
                        break;
                    case 2:
                        products = events.rainyDay(products);
                        speed-=2;
                        distance-=speed;
                        System.out.printf("Скорость повозки %s.До пункта назначения осталось %s%n",speed,distance);
                        speed+=2;
                        break;
                }
            }
        }while (distance>0);
    }
    public static List<Product> buyProduct(){
        List<Product> products = new ArrayList<>();
        Merchant merchant = new Merchant();
        merchant.setCarrying(80);
        int carrying = merchant.getCarrying();
        merchant.merchantInitialMoney();
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
                    carrying-=products.get(i).getWeight();
                    System.out.printf("Ваше золото %s, Ваша грузоподъемность %s%n",money,carrying);
                    i++;
                }else if (choice == 2 && carrying!=80){
                    print("Покупка завершена");
                    break;
                } else if (money<25) {
                    print("У Вас недостаточно золота для совершения покупки");
                }else if (carrying<14){
                    print("Не хватает места для совершения покупки, Вы будете перегружены");
                } else if (choice == 2 && carrying == 80) {
                    print("Вам нужно купить товар для начала поездки");
                }


        }
        return products;
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
    public void startTrip(){
        java.util.Random r = new java.util.Random();
        int city = r.nextInt(5)+1;
        switch (city){
            case 1:
            System.out.printf("Мы отправлямся в город %s%n",City.SOLITUDE.getValue());
            break;
            case 2:
                System.out.printf("Мы отправлямся в город %s%n",City.RIFTON.getValue());
                break;
            case 3:
                System.out.printf("Мы отправлямся в город %s%n",City.DAWNSTAR.getValue());
                break;
            case 4:
                System.out.printf("Мы отправлямся в город %s%n",City.WHITERUN.getValue());
                break;
            case 5:
                System.out.printf("Мы отправлямся в город %s%n",City.WINDHELM.getValue());
                break;

        }
    }
}
