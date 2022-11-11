package jpabook.jpashop;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        // DB에 접속하여 데이터를 조작하는 일반적인 단위마다 EM을 생성해주어야한다

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Order order = new Order();
            order.addOrderItem(new OrderItem());



            em.flush();
            // em.detach(member1);
            System.out.println("========================== before commit" );

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("========================== ee" );
            tx.rollback();
        } finally {
            em.close(); // 트랜잭션 끝나면 클로즈 되어야 리소스가 릴리즈 된다.
        }


        emf.close();



    }
}
