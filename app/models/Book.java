package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book extends Model {

    @Id
    @Constraints.Required
    public Integer id;
    @Constraints.Required
    @Constraints.MinLength(1)
    @Constraints.MaxLength(255)
    public String title;
    @Constraints.Required
    @Constraints.Max(500)
    @Constraints.Min(5)
    public Integer price;
    @Constraints.Required
    public String author;

    public static Finder<Integer,Book> find = new Finder<>(Book.class);
}
