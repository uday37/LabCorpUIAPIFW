package pojos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Address {
    String street, city, state, zipcode, country;

}
