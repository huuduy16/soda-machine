import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Payment {

    private static Payment instance;
    private static List<Integer> acceptNotes;

    private Payment() {
        acceptNotes = new ArrayList<>(Arrays.asList(10000, 20000, 50000, 100000, 200000));
    }

    public static Payment getInstance() {
        if (instance == null) {
            instance = new Payment();
        }
        return instance;
    }

    public void addAcceptNote(int num) {
        acceptNotes.add(num);
    }

    public void removeAcceptNote(int num) {
        for (int i = 0; i < acceptNotes.size(); i++) {
            if (acceptNotes.get(i).equals(num)) {
                acceptNotes.remove(i);
                break;
            }
        }
    }

    public static Boolean isAccepted(int amount) {
        return acceptNotes.contains(amount);
    }

    public static void returnChange(int amount) {
    }
}
