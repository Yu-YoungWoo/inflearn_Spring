package ex1hellojpa.jpabasic;


import ex1hellojpa.jpabasic.embedded.Address;
import ex1hellojpa.jpabasic.embedded.AddressEntity;
import ex1hellojpa.jpabasic.embedded.Period;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
public class Member{

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @ManyToOne
//    @JoinColumn(name = "TEAM_ID")
//    private Team team;

    // 지연 로딩이 LAZY일 경우 Member 객체 로딩 시 Team 객체는 프록시
    // Team 객체를 사용할 때 실제 Team 객체 가져옴
    // @ManyToOne(fetch = FetchType.LAZY)
    // Member 객체 로딩 시 Team 객체도 한번에 Join으로 가져옴
    // 가급적 지연(LAZY) 로딩만 사용 추천
    // (JPQL N+1 문제) -> 하나의 쿼리에 N개가 날라감
    // @ManyToOne, @OneToOne은 무조건 LAZY
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Embedded
    private Period period;

    @Embedded
    private Address homeAddress;


    /**
     * 값 타입 컬렉션
     * 해당 클래스의 생명주기를 따라감
     * 값 타입 컬렉션은 전부 지연로딩
     */

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD",
            joinColumns = @JoinColumn(name = "MEMBER_ID")

    )
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

//    @ElementCollection
////    @CollectionTable(name = "ADDRESS",
////            joinColumns = @JoinColumn(name = "MEMBER_ID")
////    )
////    private List<Address> addressHistory = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressHistory = new ArrayList<>();




//    @OneToMany(mappedBy = "member")
//    private List<MemberProduct> memberProducts = new ArrayList<>();

//    @OneToOne
//    @JoinColumn(name = "LOCKER_ID", unique = true)
//    private Locker locker;

}
