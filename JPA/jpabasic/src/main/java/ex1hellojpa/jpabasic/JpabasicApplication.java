package ex1hellojpa.jpabasic;


import ex1hellojpa.jpabasic.cascade.Child;
import ex1hellojpa.jpabasic.cascade.Parent;
import ex1hellojpa.jpabasic.inheritance.Item;
import ex1hellojpa.jpabasic.inheritance.Movie;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

//@SpringBootApplication
public class JpabasicApplication {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Child child = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child);
            parent.addChild(child2);

            em.persist(parent);

            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            em.remove(findParent);


//            JPQL fetch join
//            List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class)
//                    .getResultList();

            // Hibernate.initialize(m1); // 강제 초기화
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void logic(Member m1, Member m2) {
        System.out.println("m1 instanceof " + (m1 instanceof Member));
        System.out.println("m2 instanceof " + (m2 instanceof Member));
    }

}
