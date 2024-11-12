import java.util.HashMap;
import java.util.Map;

//Managing meal subscriptions with subscriber class
public class Subscriber implements Comparable<Subscriber> {
    
    //Subscriber attributes
    private final String firstName;
    private final String lastName;
    private final Map<Meal, Integer> subscribedMeals;

    //Subscriber constructors
    public Subscriber(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subscribedMeals = new HashMap<>();
    }

    //Getter methods of subscribers
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    //Adding a meal to the subscription
    public boolean addingMealToSubscription(Meal meal, int noOfMeals) {
        if (subscribedMeals.size() >= 3) {
            return false;
        }

        //Number of meals in the subscription updated
        int currentNoOfMeals = subscribedMeals.getOrDefault(meal, 0);
        int totalNoOfMeals = currentNoOfMeals + noOfMeals;

        if (totalNoOfMeals <= 0) {
            subscribedMeals.remove(meal);
        } else {
            subscribedMeals.put(meal, totalNoOfMeals);
        }
        return true;
    }

    // Removing a meal from the subscription
    public boolean removingMealFromSubscription(Meal meal, int noOfMeals) {
        if (!subscribedMeals.containsKey(meal)) {
            return false;
        }

        //Number of meals in the subscription updated
        int currentNoOfMeals = subscribedMeals.get(meal);
        int updatedNoOfMeals = currentNoOfMeals - noOfMeals;

        if (updatedNoOfMeals <= 0) {
            subscribedMeals.remove(meal);
        } else {
            subscribedMeals.put(meal, updatedNoOfMeals);
        }
        return true;
    }

    //Counting the meal types subscribed
    public int getSubscribedMealTypesCounter() {
        return subscribedMeals.size();
    }

    //Getting a copy of subscribed meal & number of subscribed meal
    public Map<Meal, Integer> getSubscribedMeals() {
        return new HashMap<>(subscribedMeals);
    }

    //Sorting subscribers in ascending order
    @Override
    public int compareTo(Subscriber otherSubscriber) {
        int lastNameComparison = this.lastName.compareTo(otherSubscriber.lastName);
        if (lastNameComparison == 0) {
            return this.firstName.compareTo(otherSubscriber.firstName);
        }
        return lastNameComparison;
    }

    //Displaying subscriber information
    @Override
    public String toString() {
        StringBuilder subscriptionInfo = new StringBuilder();
        subscriptionInfo.append("Subscriber: ").append(firstName).append(" ").append(lastName).append("\nSubscribed Meals:\n");
        for (Map.Entry<Meal, Integer> entry : subscribedMeals.entrySet()) {
            subscriptionInfo.append(entry.getKey().getName()).append(": ").append(entry.getValue()).append("\n");
        }
        return subscriptionInfo.toString();
    }
}
