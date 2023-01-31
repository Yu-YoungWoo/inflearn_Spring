package ex1hellojpa.jpabasic;


import ex1hellojpa.jpabasic.embedded.Address;
import ex1hellojpa.jpabasic.embedded.AddressEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

//@SpringBootApplication
public class JpabasicApplication {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // flush -> commit, query
        try {



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
