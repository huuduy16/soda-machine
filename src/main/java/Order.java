import exceptions.OrderException;
import lombok.AllArgsConstructor;
import lombok.Data;
import products.Product;

@Data
@AllArgsConstructor
public class Order {

    private Product product;
    private Integer quantity;
    private Integer cash;

    //throw exception if the order not success, else return change
    public Integer submit() {
        if (product == null) {
            throw new OrderException("Please choose some products");
        }
        if (quantity <= 0) {
            throw new OrderException("Please choose the quantity");
        }
        Integer totalCost = quantity * product.getPrice();
        if (cash < totalCost) {
            throw new OrderException("Please put money in");
        }
        if (ProductRepository.release(product, quantity)) {
            cash -= totalCost;
        } else {
            throw new OrderException("Sorry, the order not success");
        }
        return cash;
    }
}
