package jpabook.jpashop.domain.item;

import javax.persistence.Entity;

@Entity
public class Movie extends Item {
    public String director;
    public String actor;
}
