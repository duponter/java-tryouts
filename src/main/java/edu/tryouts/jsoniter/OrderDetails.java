package edu.tryouts.jsoniter;

import java.io.IOException;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.jsoniter.JsonIterator;
import com.jsoniter.ValueType;
import com.jsoniter.any.Any;

/**
 * http://jsoniter.com/
 */
public class OrderDetails {
    private String start;
    private int product_id;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("start", start)
                .append("product_id", product_id)
                .toString();
    }

    public static void main(String[] args) throws IOException {
        try (JsonIterator iter = JsonIterator.parse(OrderDetails.class.getResourceAsStream("orders.txt"), 1024)) {
            OrderDetails orderDetails = new OrderDetails(); // reused
            while (iter.whatIsNext() != ValueType.INVALID) {
                Any order = iter.readAny(); // lazy
                int orderId = order.toInt(0); // weakly typed
                System.out.println("orderId = " + orderId);
                String start = order.get(1).bindTo(orderDetails).start; // data binding
                System.out.println("start = " + start);
                System.out.println("orderDetails = " + orderDetails);
            }
        }
    }
}
