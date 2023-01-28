package hellojpa.jpql;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public class JpqlApplication {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(teamA);
            member.setAge(0);
            member.setType(MemberType.ADMIN);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setTeam(teamA);
            member2.setAge(0);
            member2.setType(MemberType.ADMIN);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("member3");
            member3.setTeam(teamB);
            member3.setAge(0);
            member3.setType(MemberType.ADMIN);
            em.persist(member3);


            // flush 자동 호출
            int resultCount = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();

            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            System.out.println("findMember = " + findMember.getAge());

            /**
             * distinct 기능
             * 1. SQL에 DISTINCT 추가
             * 2. 애플리케이션에서 엔티티 중복 제거
             */
            // 컬렉션을 페치 조인하면 페이징 API는 절대 사용 X (일대다)

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
