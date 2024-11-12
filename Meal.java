import java.io.FileWriter;
import java.io.IOException;

public class Meal implements Comparable<Meal> {

    //Meal attributes
    private String name;
    private int accessibleQuantity;

    //Meal constructor
    public Meal(String name, int accessibleQuantity) {
        this.name = name;
        this.accessibleNoOfMeals= accessibleQuantity;
    }

    //Getter method for meal name
    public String getName() {
        return name;
    }

    //Getter method for meal quantity available
    public int getAvailableQuantity() {
        return accessibleQuantity;
    }

    //Setter method for meal name
    public void setName(String name) {
        this.name = name;
    }

    //Setter method for meal quantity available
    public void setAvailableQuantity(int accessibleQuantity) {
        this.accessibleNoOfMeals= accessibleQuantity;
    }

    //Comparing meals
    @Override
    public int compareTo(Meal other) {
        return this.name.compareTo(other.getName());
    }

    //Checking meal availability
    public boolean checkingAvailability(int wantedQuantity) {
        return this.accessibleNoOfMeals>= wantedQuantity;
    }

    //Updating meal availability
    public void updatingAvailableQuantity(int wantedQuantity) {
        this.accessibleNoOfMeals-= wantedQuantity;
    }

    //Adding to the meal availability
    public void addingToAvailableQuantity(int returnedQuantity) {
        this.accessibleNoOfMeals+= returnedQuantity;
    }

    //Responding to the request of unavailable meals
    public void unavailableMealResponse(Subscriber subscriber, FileWriter writer) {
        try {
            String letter = "Dear " + subscriber.getFirstName() + " " + subscriber.getLastName() +
                    ",\nThe meal you wanted(" + this.name + ") is not available at the moment.";
            writer.write(letter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Adding meal to a subscription
    public boolean addingMealToSubscription(Subscriber subscriber, int wantedQuantity, FileWriter writer) {
        if (!checkingAvailability(wantedQuantity)) {
            unavailableMealResponse(subscriber, writer);
            return false;
        }
    }
}