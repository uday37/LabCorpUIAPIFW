package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RawBody {
    String order_id;
    Customers customer;
    Items items[];
    Payments payment;
//    shipping, order_status, created_at;

}
