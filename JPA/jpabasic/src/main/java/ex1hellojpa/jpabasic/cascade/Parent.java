package ex1hellojpa.jpabasic.cascade;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Parent {
    @Id
    @GeneratedValue
    @Column(name = "PARENT_ID")
    private Long id;

    private String name;

    /** cascade
     * 연관되어 있는 객체를 따라서 저장
     * 부모, 자식 라이프 사이클이 똑같을때
     * 단일 소유자(Parent 엔티티만 Child를 가지고 있을때)
    **/
    /** 고아 객체 - orphanRemoval
     * 부모 엔티티와 연관관계가 끊어진 자식 엔티티를 자동으로 삭제
     * 참조하는 곳이 하나일 때 사용(주의)
     * 특정 엔티티가 개인 소유할 때 사용(주의)
     * 부모 객체를 삭제하면 자식 객체 모두 삭제됨 == CascadeType.Remove
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child> childList = new ArrayList<>();

    public void addChild(Child child) {
        childList.add(child);
        child.setParent(this);
    }
}
