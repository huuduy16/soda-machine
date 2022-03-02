import java.util.Collections;
import java.util.Scanner;
import lombok.Data;
import products.ListProduct;
import products.Product;

@Data
public class Machine {

    private static Integer DEFAULT_BUDGET = 50000;
    private static Double DEFAULT_WIN_RATE = 0.1;

    private Integer remainBudget;
    private Double currentWinRate;

    public void reset() {
        ProductRepository.reset();
        if (remainBudget > 0) {
            setCurrentWinRate(0.5);
        } else {
            setCurrentWinRate(DEFAULT_WIN_RATE);
        }
        setRemainBudget(DEFAULT_BUDGET);
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                int cash = scanner.nextInt();
                if (cash == -1) {
                    break;
                }
                if (!Payment.isAccepted(cash)) {
                    continue;
                }
                String productName = scanner.next();
                Product product = null;
                for (Product tmp : ListProduct.getProducts()) {
                    if (productName.equalsIgnoreCase(tmp.getName())) {
                        product = tmp;
                        break;
                    }
                }
                int needQuantity = scanner.nextInt();

                Order order = new Order(product, needQuantity, cash);
                int change = order.submit();
                //return change
                Payment.returnChange(change);

                //promotion
                if ((change >= 0) && (needQuantity >= 3)) {
                    System.out.println("Start promotion....");
                    promotion();
                }
            }
        }
    }

    private void promotion() {
        if (!isLucky()) {
            System.out.println("Let's try next times");
            return;
        }
        Collections.shuffle(ListProduct.getProducts());
        Product product = null;
        for (Product tmp : ListProduct.getProducts()) {
            if (tmp.getPrice() <= remainBudget) {
                product = tmp;
                break;
            }
        }
        if (product == null) {
            System.out.println("Let's try next times");
            return;
        }
        System.out.println("You are so lucky !");
        Order order = new Order(product, 1, product.getPrice());
        order.submit();
    }

    private Boolean isLucky() {
        return (remainBudget > 0) && (Math.random() >= currentWinRate);
    }
}
