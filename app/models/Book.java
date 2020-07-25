package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book extends Model {

    @Id
    public Integer id;
    public String title;
    public Integer price;
    public String author;

    public static Finder<Integer,Book> find = new Finder<>(Book.class);
}
