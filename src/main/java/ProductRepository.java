import exceptions.OrderException;
import java.util.HashMap;
import java.util.Map;
import products.ListProduct;
import products.Product;

public class ProductRepository {

    private static ProductRepository instance;
    private static Map<Product, Integer> productQuantity;

    private ProductRepository() {
        productQuantity = new HashMap<>();
    }

    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }

    public static void reset() {
        productQuantity.clear();
        for (Product product : ListProduct.getProducts()) {
            productQuantity.put(product, 10);
        }
    }

    //return true if the purchase success
    public static boolean release(Product product, Integer need) {
        if (!productQuantity.containsKey(product)) {
            throw new OrderException("Product is not exist");
        }
        Integer quantity = productQuantity.get(product);
        if (quantity >= need) {
            //release product
            productQuantity.put(product, quantity - need);
            return true;
        }
        return false;
    }
}
