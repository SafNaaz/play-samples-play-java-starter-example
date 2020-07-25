package controllers;

import models.Book;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.Set;

import views.html.books.*;

public class BooksController extends Controller {

    //show all books
    public Result index(){
        Set<Book> books = Book.allBooks();
        return ok(index.render(books));
    }

    // to create book
    public Result create(){
        return status(NOT_IMPLEMENTED);
    }

    // to save book
    public Result save(){
        return status(NOT_IMPLEMENTED);
    }

    //edit one book
    public Result edit(Integer id){
        return status(NOT_IMPLEMENTED);
    }

    //update in database
    public Result update(){
        return status(NOT_IMPLEMENTED);
    }

    //delete book
    public Result destroy(Integer id){
        return status(NOT_IMPLEMENTED);
    }

    //details of single book
    public Result show(Integer id){
        return status(NOT_IMPLEMENTED);
    }

}
