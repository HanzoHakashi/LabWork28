package model;

import java.util.Random;

public class Merchant {
    private int carrying;
    private int speed;
    int money;

    public void merchantInitialMoney(){
        java.util.Random r = new java.util.Random();
        int money = r.nextInt(20)+80;
        setMoney(money);
    }

    public int setMerchantSpeed(){
        java.util.Random r = new java.util.Random();
        setSpeed(r.nextInt(3)+3);
        int speed = getSpeed();
        return speed;
    }

    public int getCarrying() {
        return carrying;
    }

    public void setCarrying(int carrying) {
        this.carrying = carrying;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
