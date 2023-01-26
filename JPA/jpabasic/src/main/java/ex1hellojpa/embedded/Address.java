package ex1hellojpa.embedded;


import ex1hellojpa.jpabasic.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    // 주소
    private String city;
    private String street;
    @Column(name = "ZIPCODE")
    private String zipcode;


}
