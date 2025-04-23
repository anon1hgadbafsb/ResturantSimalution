package org.example;

public class mChef {
    private int x;
    private int y;
    private int diameter;

    public mChef(int x, int y, int diameter) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDiameter() {
        return diameter;
    }
    // recives order from waiter
    public void receiveOrder(String appetizer, String main, String dessert) {
        System.out.println("Order received by the chef:");
        System.out.println("Appetizer: " + appetizer);
        System.out.println("Main: " + main);
        System.out.println("Dessert: " + dessert);
    }
    }


