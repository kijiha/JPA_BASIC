package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
public class Team {

    @Id @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "team")
    List<Member> members = new ArrayList<>();
    public String name;
}
