package ex1hellojpa.jpabasic;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member extends BaseEntity{

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


    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

//    @OneToOne
//    @JoinColumn(name = "LOCKER_ID", unique = true)
//    private Locker locker;

}
