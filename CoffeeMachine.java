package machine.coffeemachine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Coffee machine = new Coffee(400, 540, 120, 9, 550);
        Coffee espresso = new Coffee(250, 0, 16, 1, 4);
        Coffee latte = new Coffee(350, 75, 20, 1, 7);
        Coffee cappuccino = new Coffee(200, 100, 12, 1, 6);
        Scanner scanner = new Scanner(System.in);
        String command = "";
        do {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            command = scanner.next();
            switch (command) {
                case "buy":
                    buy(machine, espresso, latte, cappuccino);
                    break;
                case "fill":
                    fill(machine);
                    break;
                case "take":
                    take(machine);
                    break;
                case "remaining":
                    raport(machine);
                    break;
                case "exit":
                    break;
                default:
                    System.out.println("Unknown command! - try again");
                    break;
            }
        } while (!command.equals("exit"));
    }

    private static void buy(Coffee machine, Coffee espresso, Coffee latte, Coffee cappuccino) {
        Coffee coffeeToMake;
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        Scanner scanner = new Scanner(System.in);
        String coffeType = scanner.next();
        switch (coffeType) {
            case "1":
                coffeeToMake = espresso;
                sellCoffee (machine, coffeeToMake);
                break;
            case "2":
                coffeeToMake = latte;
                sellCoffee(machine, coffeeToMake);
                break;
            case "3":
                coffeeToMake = cappuccino;
                sellCoffee(machine, coffeeToMake);
                break;
            case "back":
                break;
            default:
                buy(machine, espresso, latte, cappuccino);
        }
    }

    private static void sellCoffee(Coffee machine, Coffee coffeeToMake) {
        if (checkStocks(machine.waterAmount, coffeeToMake.waterAmount, machine.milkAmount, coffeeToMake.milkAmount, machine.beansAmount, coffeeToMake.beansAmount, machine.cupsAmount)) {
            machine.waterAmount -= coffeeToMake.waterAmount;
            machine.milkAmount -= coffeeToMake.milkAmount;
            machine.beansAmount -= coffeeToMake.beansAmount;
            machine.cupsAmount --;
            machine.moneyAmount += coffeeToMake.moneyAmount;
        }
    }

    private static boolean checkStocks(int waterAmount, int waterAmount1, int milkAmount, int milkAmount1, int beansAmount, int beansAmount1, int cupsAmount) {
        boolean haveEnough = true;
        if (waterAmount < waterAmount1) {
            System.out.println("Sorry, not enough water!");
            haveEnough = false;
        }
        if (milkAmount < milkAmount1) {
            System.out.println("Sorry, not enough milk!");
            haveEnough = false;
        }
        if (beansAmount < beansAmount1) {
            System.out.println("Sorry, not enough beans!");
            haveEnough = false;
        }
        if (cupsAmount < 1) {
            System.out.println("Sorry, not enough cups!");
            haveEnough = false;
        }
        if (haveEnough) {
            System.out.println("I have enough resources, making you a coffee!");
        }
        return haveEnough;
    }

    private static void fill(Coffee machine) {
        System.out.println("Write how many disposable cups of coffee do you want to add: ");
        Scanner scanner = new Scanner(System.in);
        machine.cupsAmount += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add: ");
        machine.beansAmount += scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add: ");
        machine.milkAmount += scanner.nextInt();
        System.out.println("Write how many ml of water do you want to add:");
        machine.waterAmount += scanner.nextInt();

    }

    private static void take(Coffee machine) {
        System.out.println("I gave you $" + machine.moneyAmount);
        machine.moneyAmount = 0;
    }

    private static void raport(Coffee machine) {
        System.out.println("The coffee machine has:");
        System.out.println(machine.waterAmount + " of water");
        System.out.println(machine.milkAmount + " of milk");
        System.out.println(machine.beansAmount + " of coffee beans");
        System.out.println(machine.cupsAmount + " of disposable cups");
        System.out.println(machine.moneyAmount + " of money");
        System.out.println();
    }
}
