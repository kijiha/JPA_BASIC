package jpabook.jpashop.domain;

import javax.persistence.Entity;

@Entity
public class Movie extends Item {
    public String director;
    public String actor;
}
