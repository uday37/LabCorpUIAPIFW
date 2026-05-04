package pojos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Payments {
    String method, transaction_id, amount, currency;

}
