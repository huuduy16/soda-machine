package products;

import java.util.ArrayList;
import java.util.List;

public class ListProduct {

    private static ListProduct instance;
    private static List<Product> products;

    //initial: 3 type of product
    private ListProduct() {
        products = new ArrayList<>();
        products.add(new Coke());
        products.add(new Pepsi());
        products.add(new Soda());
    }

    public static ListProduct getInstance() {
        if (instance == null) {
            instance = new ListProduct();
        }
        return instance;
    }

    public static void addProduct(Product newProduct) {
        products.add(newProduct);
    }

    public static void removeProduct(Product product) {
        products.remove(product);
    }

    public static List<Product> getProducts() {
        return products;
    }
}
