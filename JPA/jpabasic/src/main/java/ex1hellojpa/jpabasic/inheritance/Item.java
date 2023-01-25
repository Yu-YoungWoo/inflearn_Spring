package ex1hellojpa.jpabasic.inheritance;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn  // 자식의 테이블 이름이 DTYPE으로 들어옴
public abstract class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;
}
