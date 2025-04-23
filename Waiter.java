package org.example;

public class Waiter {
        private int x;
        private int y;
        private int diameter;
        private static int speed;
        private static int id;

    public Waiter(int id, int x, int y, int diameter, int speed){
        this.id = id;
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.speed = speed;

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

    public int getSpeed(){return speed;}

    public int getId(){return id;}

    public boolean isAtTable(Table table) {
        // Check if the waiter is close
        return Math.abs(this.getX() - table.getX()) < 50 && Math.abs(this.getY() - table.getY()) < 50;
    }

    public void walkToTable(Table table){
        int targetX = table.getX();
        int targetY = table.getY();
        if ((table.getTableNumber() == 4) || (table.getTableNumber() == 5) || (table.getTableNumber() == 6)|| (table.getTableNumber() == 7)) {
            x += speed * Integer.signum(targetX - x);
            y += speed * Integer.signum(targetY - y + 60);

        }else{
            x += speed * Integer.signum(targetX - x);
            y += speed * Integer.signum(targetY - y -40);
        }

        }

    public boolean walkToKitchen() {
        int kitchenX = 500;
        int kitchenY = 300;

        x += speed * Integer.signum(kitchenX - x);
        y += speed * Integer.signum(kitchenY - y);

        return Math.abs(x - kitchenX) < 50 && Math.abs(y - kitchenY) < 50;
    }


}


