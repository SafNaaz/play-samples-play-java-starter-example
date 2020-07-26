package controllers;

import models.Book;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

import views.html.books.*;

import javax.inject.Inject;

public class BooksController extends Controller {

    @Inject
    FormFactory formFactory;

    @Inject
    MessagesApi messagesApi;

    //show all books
    public Result index(Http.Request request){
        List<Book> books = Book.find.all();
        return ok(index.render(books,request));
    }

    // to create book
    public Result create(Http.Request request){
        Form<Book> bookForm = formFactory.form(Book.class);
        return ok(create.render(bookForm,request,messagesApi.preferred(request)));
    }

    // to save book
    public Result save(Http.Request request){
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest(request);
        if(bookForm.hasErrors()){
            return redirect(routes.BooksController.create())
                    .flashing("danger", "Please correct the form below");
        }
        Book book = bookForm.get();
        Book oldBook = Book.find.byId(book.id);
        if(oldBook != null){
            return redirect(routes.BooksController.create())
                    .flashing("danger", "Book already Exists");
        }
        book.save();
        return redirect(routes.BooksController.index()).flashing("success", "Book Saved Successfully!");
    }

    //edit one book
    public Result edit(Integer id, Http.Request request){
        Book book = Book.find.byId(id);
        if(book == null){
            return notFound("Book not found");
        }
        Form<Book> bookForm = formFactory.form(Book.class).fill(book);
        return ok(edit.render(bookForm,request,messagesApi.preferred(request)));
    }

    //update in database
    public Result update(Http.Request request){
        Form<Book> bookForm =formFactory.form(Book.class).bindFromRequest(request);
        if(bookForm.hasErrors()){
            return redirect(routes.BooksController.edit(bookForm.get().id))
                    .flashing("danger", "Please correct the form below");
        }
        Book book = bookForm.get();
        Book oldBook = Book.find.byId(book.id);
        if(oldBook == null){
            return notFound("Book not found");
        }
        oldBook.title = book.title;
        oldBook.author = book.author;
        oldBook.price = book.price;
        oldBook.update();
        return redirect(routes.BooksController.index());
    }

    //delete book
    public Result destroy(Integer id){
        Book book = Book.find.byId(id);
        if(book == null){
            return notFound("Book not found");
        }
        book.find.deleteById(book.id);
        return redirect(routes.BooksController.index());
    }

    //details of single book
    public Result show(Integer id){
        Book book = Book.find.byId(id);
        if(book == null){
            return notFound("Book not found");
        }
        return ok(show.render(book));
    }

}
