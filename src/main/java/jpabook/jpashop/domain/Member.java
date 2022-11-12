package jpabook.jpashop.domain;

import jpabook.jpashop.domain.address.Address;
import jpabook.jpashop.domain.address.AddressEntity;
import jpabook.jpashop.domain.order.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
public class Member extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;


    @Embedded
    private Period workPeriod;

    @Embedded
    private Address Address;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city",
                    column = @Column(name = "work_city")),
            @AttributeOverride(name = "street",
                    column = @Column(name = "work_street")),
            @AttributeOverride(name = "zipcode",
                    column = @Column(name = "work_zipcode")),
    })
    private Address workAddress;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD",
            joinColumns = @JoinColumn(name = "MEMBER_ID"))
    private Set<String> favoriteFood = new HashSet<>();

    /*
    @ElementCollection
    @CollectionTable(name = "ADDRESS",
            joinColumns = @JoinColumn(name = "MEMBER_ID"))
    private List<Address> addressHistory= new ArrayList<>();
    */
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressHistory= new ArrayList<>();




    // mapped by 값은 현재 엔티티의 기본키와 매핑된 연관관계 주인측의 외래키 필드
    @OneToMany(mappedBy = "member")
    private List<Order> orders=new ArrayList<>();
    // 관례상 null값이 반환되는것을 방지하기위헤 ArrayList로 초기화

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    Team team;



}
