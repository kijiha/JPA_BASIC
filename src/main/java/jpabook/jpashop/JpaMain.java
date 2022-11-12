package jpabook.jpashop;

import jpabook.jpashop.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        // DB에 접속하여 데이터를 조작하는 일반적인 단위마다 EM을 생성해주어야한다

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Address address1=new Address("home","street","zcode");
            Address address2=new Address("home","street","zcode");

            Member member1 = new Member();

            member1.getFavoriteFood().add("치킨");
            member1.getFavoriteFood().add("피자");

            member1.getAddressHistory().add( new AddressEntity("home1","street","zcode"));
            member1.getAddressHistory().add(new AddressEntity("home2","street","zcode"));
            member1.getAddressHistory().add(new AddressEntity("home3","street","zcode"));

            em.persist(member1);







           em.flush();
           em.clear();

            System.out.println("========================== before commit" );

            Member member = em.find(Member.class, member1.getId());
            System.out.println("========================== after " );

            member.getFavoriteFood().remove("치킨");
            member.getFavoriteFood().add("wine");



            //member.getAddressHistory().remove(new Address("home1","street","zcode"));
            //member.getAddressHistory().add(new Address("NeWhome1","street","zcode"));


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
