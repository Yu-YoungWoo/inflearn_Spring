package hellojpa.jpql;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@NoArgsConstructor
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
