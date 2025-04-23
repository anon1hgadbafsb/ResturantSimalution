package org.example;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import org.example.Menu;




public class RestaurantMain extends JPanel {

    static ArrayList<Waiter> waiters = new ArrayList<Waiter>();
    static ArrayList<Table> tables = new ArrayList<Table>();
    static mChef mChef = new mChef(420, 270, 50);
    static Menu menu = new Menu();
    static Table currentTargetTable = null;
    static int waiterWaitCounter = 0;
    static final int WAITER_WAIT_TIME = 90;
    //private static String appetizer;
   // private static String main;
   // private static String dessert;
    //static boolean returningToKitchen = false;


    // In here all objects that are needed for operating the restaurant should be created.
    // This is initialisation and determines the initial state of the program.
    static void setupRestaurant() {

        tables.add(new Table(600, 500, 50, 0));
        tables.add(new Table(700, 500, 50, 1));
        tables.add(new Table(800, 500, 50, 2));
        tables.add(new Table(900, 500, 50, 3));

        tables.add(new Table(600, 100, 50, 4));
        tables.add(new Table(700, 100, 50, 5));
        tables.add(new Table(800, 100, 50, 6));
        tables.add(new Table(900, 100, 50, 7));

        waiters.add(new Waiter(300, 500, 0, 30, 5));
//menu items

        menu.addAppetizer("salad");
        menu.addAppetizer("Bread");

        menu.addMainDish("Spaghetti");
        menu.addMainDish("meatballs");

        menu.addDessert("icecream");
        menu.addDessert("milkshake");

    }


    // Contains the simulation logic, should probably be broken into smaller pieces as the program expands

    static boolean returningToKitchen = false;

    static void update() {
        Random rand = new Random();
        Waiter waiter = waiters.get(0);
    //needs to be definded here, dosen't work
        String appetizer = null;
        String main = null;
        String dessert = null;

        // if no other table is ready and waiter is available make new table ready
        if (currentTargetTable == null && !returningToKitchen) {
            for (Table t : tables) {
                if (!t.isReadyToOrder() && rand.nextInt(500) == 0) {
                    t.setReadyToOrder(true);
                    currentTargetTable = t;
                    System.out.println("Table " + t.getTableNumber() + " is ready to order!");
                    break;
                }
            }
        }

        // walks to table
        if (currentTargetTable != null) {
            waiter.walkToTable(currentTargetTable);

            // takes order when waiter arives at table
            if (Math.abs(waiter.getX() - currentTargetTable.getX()) < 100 &&
                    Math.abs(waiter.getY() - currentTargetTable.getY()) < 100) {

                // Assign the order items
                appetizer = menu.getAppetizers().get(rand.nextInt(menu.getAppetizers().size()));
                main = menu.getMainDishes().get(rand.nextInt(menu.getMainDishes().size()));
                dessert = menu.getDesserts().get(rand.nextInt(menu.getDesserts().size()));

                System.out.println("Order from Table " + currentTargetTable.getTableNumber() + ":");
                System.out.println("Appetizer: " + appetizer);
                System.out.println("Main: " + main);
                System.out.println("Dessert: " + dessert);

                currentTargetTable.setReadyToOrder(false);
                currentTargetTable = null;
                returningToKitchen = true;
            }
        }

        // Return to kitchen
        if (returningToKitchen) {
            waiter.walkToKitchen();
            if (Math.abs(waiter.getX() - 500) < 10 && Math.abs(waiter.getY() - 300) < 10) {
                // Pass the order to mchef
                mChef.receiveOrder(appetizer, main, dessert);//dosent work returns null
                returningToKitchen = false;
            }
        }
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(new Color(255, 245, 158, 184)); //  // Set the background color to light yellow

        g.setColor(Color.DARK_GRAY); // Set the color for the border lines
        g.drawRect(500, 0, 600, getHeight() - 5);
        //g.drawRect(800, 0, getWidth() - 5, getHeight() - 5);
        g.setColor(Color.BLACK);
        g.drawRect(500, 0, 695, getHeight() - 5);

        // Draw kitchen door
        g.setColor(Color.DARK_GRAY);
        g.fillRect(490, 270, 20, 100);
        g.fillRect(1090, 270, 20, 100);

        // Draw tables
        drawTables(g);

        // Draw the waiters
        drawWaiters(g);

        // MORE CODE HERE
        // Draw master chef office
        g.setColor(new Color(93, 191, 73, 255));
        g.fillRect(410, 200, 60, 200);

        // Draw the master chef
        g.setColor(Color.WHITE);
        g.fillOval(mChef.getX(), mChef.getY(), mChef.getDiameter(), mChef.getDiameter());
    }

    static void drawTables(Graphics g) {
        for (Table table : tables) {
            g.setColor(Color.RED);
            g.fillOval(table.getX(), table.getY(), table.getDiameter(), table.getDiameter()); // Draw circle with diameter of 50 pixels
            g.setColor(Color.WHITE);
            g.fillOval(table.getX()+3, table.getY()+3, table.getDiameter()-6, table.getDiameter()-6);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(table.getTableNumber()), table.getX()+20, table.getY()+30);
        }
    }

    static  void drawWaiters(Graphics g){
        for (Waiter waiter : waiters) {
            g.setColor(Color.BLACK);
            g.fillOval(waiter.getX(), waiter.getY(), waiter.getDiameter(), waiter.getDiameter()); // Draw circle with diameter of 50 pixels
            g.setColor(Color.WHITE);
            g.fillOval(waiter.getX()+7, waiter.getY()+7, waiter.getDiameter()-14, waiter.getDiameter()-14); // Draw circle with diameter of 50 pixels
        }
    }
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Restuarant Simulation");
        frame.setSize(1200, 640); // Set window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Add the custom panel (with circles) to the frame
        RestaurantMain panel = new RestaurantMain();
        frame.add(panel);



        // Display the window
        frame.setVisible(true);

        // Setup for the restaurant
        setupRestaurant();
        System.out.println(menu);

        while (true) {
            try {
                Thread.sleep(33); // With the goal of having about 30 fps.
            } catch (Exception threadException) {
                System.out.println("Sleep exception: " + threadException.getMessage());
            }
            update();
            panel.repaint();
        }
    }
}

