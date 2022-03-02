package products;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Product {

    protected String name;
    protected Integer price;
}
