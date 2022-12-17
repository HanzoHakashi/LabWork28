import model.Merchant;
import model.Product;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    public  void run(){
       List<Product> products = buyProduct();
    }
    public static List<Product> buyProduct(){
        List<Product> products = new ArrayList<>();
        Merchant merchant = new Merchant();
        merchant.setCarrying(80);
        print("Вы торговец на просторах земель Скайрима.\n"+"Вам нужно закупиться товаром для продажи");
        int carrying = merchant.getCarrying();
        merchant.merchantInitialMoney();
        int money = merchant.getMoney();
        int choice = 0;
        System.out.printf("У Вас имеется %s золота. Грузоподъемность %s единиц%n",money,carrying);
        int i = 0;
        while (choice !=2){
            Product product = new Product();
            choice = choice("Вы хотите купить товар? Да(1) Нет (2)");
               if(choice ==1 && money>15 && carrying>14){
                   products.add(product);
                   product.getTypeofProduct();
                   System.out.printf("Вы купили %s, потрачено (%s золота)",products.get(i).getType(),products.get(i).getPrice());
               }else if (choice == 2){
                   print("Покупка завершена");
                   break;
               } else if (money<15) {
                   print("У Вас недостаточно золота для совершения покупки");
                   break;
               }else if (carrying<14){
                   print("Не хватает места для совершения покупки, Вы будете перегружены");
                   break;
               }
               money-=products.get(i).getPrice();
               carrying-=products.get(i).getWeight();
               System.out.printf("Ваше золото %s, Ваша грузоподъемность %s%n",money,carrying);
               i++;
        }
        return products;
    }
    public static void print(String message){
        System.out.println(message);
    }

    public static int choice(String message){
        print(message);
        Scanner sc = new Scanner(System.in);
        try {
            int choice = sc.nextInt();
            return choice;
        }catch (Exception e){
            e.printStackTrace();
            return choice("Данное действие недоступно");
        }
    }
}
