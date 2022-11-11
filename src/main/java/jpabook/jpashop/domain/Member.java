package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;

    // mapped by 값은 현재 엔티티의 기본키와 매핑된 연관관계 주인측의 외래키 필드
    @OneToMany(mappedBy = "member")
    private List<Order> orders=new ArrayList<>();
    // 관례상 null값이 반환되는것을 방지하기위헤 ArrayList로 초기화



}
