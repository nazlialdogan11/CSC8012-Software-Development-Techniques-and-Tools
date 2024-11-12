import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainProgram {

    public static void main(String[] args) {
        SortedLinkedList<Subscriber> subscribersList = new SortedLinkedList<>();
        SortedLinkedList<Meal> mealsList = new SortedLinkedList<>();

        Scanner scanner = new Scanner(System.in);
        FileWriter clerkFile = null;
        PrintWriter lettersFile = null;

        try {
            clerkFile = new FileWriter("clerk.txt");
            lettersFile = new PrintWriter("letters.txt");

            char choice;
            do {
                //Displaying the user menu
                System.out.println("Menu:");
                System.out.println("f - Finish");
                System.out.println("m - Display all meals");
                System.out.println("s - Display all subscribers");
                System.out.println("a - Add meals to subscriber subscription");
                System.out.println("r - Remove meals from subscriber subscription");

                System.out.print("Enter your choice: ");
                choice = scanner.next().charAt(0);
                scanner.nextLine();

                switch (choice) {
                    case 'm':
                        displayMealsList(mealsList);
                        break;
                    case 's':
                        displayingAllSubscribers(subscribersList);
                        break;
                    case 'a':
                        addingMealToSubscription(subscribersList, mealsList, lettersFile, scanner);
                        break;
                    case 'r':
                        removingMealFromSubscriber(subscribersList, mealsList, lettersFile, scanner);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
            } while (choice != 'f');

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closing(scanner, clerkFile, lettersFile);
        }
    }

    //Displaying the meal list
    private static void displayAllMeals(SortedLinkedList<Meal> mealsList) {
        for (Meal meal : mealsList) {
            System.out.println(meal);
        }
    }

    //Displaying the subscriber list
    private static void displayingAllSubscribers(SortedLinkedList<Subscriber> subscribersList) {
        for (Subscriber subscriber : subscribersList) {
            System.out.println(subscriber);
        }
    }

    //Adding meals to a subscription
    private static void addingMealToSubscription(SortedLinkedList<Subscriber> subscribersList,
                                                 SortedLinkedList<Meal> mealsList,
                                                 PrintWriter lettersFile, Scanner scanner) {

        System.out.println("Please enter the first name of the subscriber: ");
        String firstName = scanner.nextLine();
        System.out.println("Please enter the last name of the subscriber: ");
        String lastName = scanner.nextLine();

        Subscriber subscriber = findingSubscriber(subscribersList, firstName, lastName);
        if (subscriber != null) {
            System.out.println("Subscriber was found.");

            System.out.println("Available meals: ");
            displayMealsList(mealsList);

            System.out.println("Enter the meal name you want to add: ");
            String mealName = scanner.nextLine();
            System.out.println("Enter the number of meals you want to add: ");
            int noOfMeals = scanner.nextInt();

            Meal selectedMeal = findingMealByName(mealsList, mealName);
            if (selectedMeal != null) {
                subscriber.addingMealToSubscription(selectedMeal, noOfMeals);
            } else {
                System.out.println("Meal was not found.");
            }
        } else {
            System.out.println("Subscriber was not found.");
        }
    }

    //Removing meals from a subscription
    private static void removingMealFromSubscriber(SortedLinkedList<Subscriber> subscribersList,
                                                 SortedLinkedList<Meal> mealsList, PrintWriter lettersFile,
                                                 Scanner scanner) {

        //Inputting the first and last name of the subscriber
        System.out.println("Please enter the first name of the subscriber: ");
        String firstName = scanner.nextLine();
        System.out.println("Please enter the last name of the subscriber: ");
        String lastName = scanner.nextLine();

        Subscriber subscriber = findingSubscriber(subscribersList, firstName, lastName);
        if (subscriber != null) {
            System.out.println("Subscriber was found.");

            //Inputting the meals and the number of the meals for removal
            System.out.println("Please enter the meal name you want to remove: ");
            String mealName = scanner.nextLine();
            System.out.println("Please enter the number of meals you want to remove: ");
            int noOfMeals = scanner.nextInt();

            Meal selectedMeal = findingMealByName(subscriber.getSubscribedMeals(), mealName);
            if (selectedMeal != null) {
                subscriber.removingMealFromSubscription(selectedMeal, noOfMeals);
            } else {
                System.out.println("The meal is not included in the subscription.");
            }
        } else {
            System.out.println("Subscriber was not found.");
        }
    }

    //Finding the subscriber in the list by their first and last name
    private static Subscriber findingSubscriber(SortedLinkedList<Subscriber> subscribersList,
                                             String firstName, String lastName) {
        for (Subscriber subscriber : subscribersList) {
            if (subscriber.getFirstName().equalsIgnoreCase(firstName) &&
                    subscriber.getLastName().equalsIgnoreCase(lastName)) {
                return subscriber;
            }
        }
        return null;
    }

    //Finding the meal in the list by the name
    private static Meal findingMealByName(Map<Meal, Integer> subscribedMeals, String mealName) {
        for (Meal meal : mealsList) {
            if (meal.getName().equalsIgnoreCase(mealName)) {
                return meal;
            }
        }
        return null;
    }
    
    //Closing the scanner & writer
    private static void closing(Scanner scanner, FileWriter clerkFile, PrintWriter lettersFile) {
        try {
            if (scanner != null) {
                scanner.close();
            }
            if (clerkFile != null) {
                clerkFile.close();
            }
            if (lettersFile != null) {
                lettersFile.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
