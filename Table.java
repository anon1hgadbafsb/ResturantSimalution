package org.example;

public class Table {

    private int x;
    private int y;
    private int diameter;
    private int TableNumber;

    public Table(int x, int y, int diameter, int TableNumber){
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.TableNumber = TableNumber;
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

    public int getTableNumber(){
        return TableNumber;
    }
    private boolean readyToOrder = false;

    public boolean isReadyToOrder() {
        return readyToOrder;
    }

    public void setReadyToOrder(boolean ready) {
        this.readyToOrder = ready;
    }

}
