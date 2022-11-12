package jpabook.jpashop.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class Book extends Item {
    public String isbn;
    public String author;
}
