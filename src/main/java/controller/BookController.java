package controller;

import constants.State;
import entities.Book;
import services.BookService;

import java.util.List;
import java.util.Scanner;

public class BookController {
    private BookService bookService=BookService.getInstance();
    //singleton
    private static BookController bookController=null;
    public static BookController getInstance(){
        BookService.createTheTable();
        if(bookController==null){
            bookController=new BookController();
        }
        return bookController;
    }

    //methods
    public void createBookByName(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Please input the name of a book");
        String name=sc.nextLine();
        Book book=new Book(name);
        if(bookService.createBookByName(book)==1){
            System.out.println("The book is created");
        }else {
            System.out.println("Something went wrong");
        }
    }

    public void updateBooksName(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Please input the name for the changed book");
        String name=sc.nextLine();
        System.out.println("Input the id of a book you want to change");
        int id=sc.nextInt();
        if(bookService.updateBooksName(name,id)==1){
            System.out.println("The name is updated");
        }else{
            System.out.println("Something went wrong");
        }
    }

    public void updateBooksDescription(){
        Scanner sc=new Scanner(System.in);
        System.out.println("And now input the description of a book you want to update");
        String description=sc.nextLine();
        System.out.println("Please input the id of a book you want to update");
        int id=sc.nextInt();
        if(bookService.updateBooksDescription(description,id)==1){
            System.out.println("The description is updated");
        }else{
            System.out.println("Something went wrong");
        }
    }

    public void updateBooksNumberOfPages(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Please input the number of pages for an update");
        int num=sc.nextInt();
        System.out.println("Please input the id of a book you want to update");
        int id=sc.nextInt();
        if(bookService.updateBooksNumberOfPages(num,id)==1){
            System.out.println("The number of pages is updated");
        }else{
            System.out.println("Something went wrong");
        }
    }

    public void updateStateToTaken(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Input the id");
        int id=sc.nextInt();
        if (bookService.updateStateToTakenById(id)==1) {
            System.out.println("Updated!");
        }else{
            System.out.println("Something went wrong");
        }
    }

    public void updateStateToNotTaken(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Input the id");
        int id=sc.nextInt();
        if (bookService.updateStateToNotTakenById(id)==1) {
            System.out.println("Updated!");
        }else{
            System.out.println("Something went wrong");
        }
    }


    public void deleteBookById(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Please input the id of a book you want to delete");
        int id=sc.nextInt();
        if(bookService.deleteBookById(id)==1){
            System.out.println("The book is deleted");
        }else{
            System.out.println("Something went wrong");
        }
    }

    public void getBookById(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Input the id of a book");
        int id=sc.nextInt();
        if(bookService.getBookById(id)!=null){
            System.out.println(bookService.getBookById(id));
        }else{
            System.out.println("Something went wrong");
        }
    }

    public void findAll(){
        List<Book> books = bookService.readingBooks();
        books.forEach(System.out::println);
    }
    public void findBooksThatAreNotTaken(){
        List<Book> books = bookService.notTakenBooks();
        books.forEach(System.out::println);
    }
}
