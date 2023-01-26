package ex1hellojpa.jpabasic;


import ex1hellojpa.embedded.Address;
import ex1hellojpa.embedded.Period;
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

            Address address = new Address("city", "street", "10032");

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setHomeAddress(address);;
            em.persist(member1);

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
