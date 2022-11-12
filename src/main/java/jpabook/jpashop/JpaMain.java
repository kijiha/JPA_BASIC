package jpabook.jpashop;

import jpabook.jpashop.domain.*;

import javax.persistence.*;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        // DB에 접속하여 데이터를 조작하는 일반적인 단위마다 EM을 생성해주어야한다

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team1 = new Team();
            team1.setName("TEAM!");
            em.persist(team1);

            Team team2 = new Team();
            team1.setName("TEAM2");
            em.persist(team2);

            Member member = new Member();
            member.setModifiedBy("kim");
            member.setTeam(team1);

            em.persist(member);

            Member member2 = new Member();
            member.setModifiedBy("kim12");
            member.setTeam(team2);

            em.persist(member2);


            em.flush();
            em.clear();


            Member findMember = em.find(Member.class, member.getId());
            Member findMember2 = em.find(Member.class, member2.getId());
            System.out.println(" ===================================");
            System.out.println("findMember.team1 class = " + findMember.getTeam().getClass());

            System.out.println(" ===================================");
            System.out.println("findMember.team1 class = " + findMember.getTeam().getName());






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
