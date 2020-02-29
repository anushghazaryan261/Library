package menu;

import constants.State;
import controller.AuthorController;
import controller.BookController;
import controller.CustomerController;
import entities.Order;
import org.mindrot.jbcrypt.BCrypt;
import services.BookService;
import services.CustomerService;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        BookController bookController=BookController.getInstance();
        AuthorController authorController=AuthorController.getInstance();
        CustomerController customerController=CustomerController.getInstance();
        CustomerService customerService=CustomerService.getInstance();
        BookService bookService=BookService.getInstance();
        Scanner sc=new Scanner(System.in);
        System.out.println("--> press 1 for books CRUD\n" +
                "--> press 2 for authors CRUD\n" +
                "--> press 3 for customers CRUD");
        System.out.println("--> press 4 to show all not taken books");
        System.out.println("--> press 5 to take a book");
        System.out.println("--> press 6 to return a book");
        int choose=sc.nextInt();

        if(choose==1){
            System.out.println("Press 1 to create a book");
            System.out.println("Press 2 to update a book's name");
            System.out.println("Press 3 to update a book's description");
            System.out.println("Press 4 to update a book's number of pages");
            System.out.println("Press 5 to delete a book by id");
            System.out.println("Press 6 to print all the books in the library");
            int ans=sc.nextInt();
            switch (ans){
                case 1:
                    bookController.createBookByName();
                    break;
                case 2:
                    bookController.updateBooksName();
                    break;
                case 3:
                    bookController.updateBooksDescription();
                    break;
                case 4:
                    bookController.updateBooksNumberOfPages();
                    break;
                case 5:
                    bookController.deleteBookById();
                    break;
                case 6:
                    bookController.findAll();
                    break;
            }
        }else if(choose==2){
            System.out.println("Press 1 to create an author");
            System.out.println("Press 2 to update the author's name");
            System.out.println("Press 3 to update the author's surname");
            System.out.println("Press 4 to delete the author");
            System.out.println("Press 5 to print all the authors");
            int ans=sc.nextInt();
            switch (ans){
                case 1:
                    authorController.createAuthor();
                    break;
                case 2:
                    authorController.updateAuthorsName();
                    break;
                case 3:
                    authorController.updateAuthorsSurname();
                    break;
                case 4:
                    authorController.deleteAuthorById();
                    break;
                case 5:
                    authorController.findAll();
                    break;
            }
        }else if(choose==3){
            System.out.println("Press 1 to create a customer");
            System.out.println("Press 2 to update customer's username");
            System.out.println("Press 3 to update customer's password");
            System.out.println("Press 4 to delete a customer");
            System.out.println("Press 5 to print customer by id");
            int ans=sc.nextInt();
            switch (ans){
                case 1:
                    customerController.createCustomer();
                    break;
                case 2:
                    customerController.updateCustomersUsername();
                    break;
                case 3:
                    customerController.updateCustomersPassword();
                    break;
                case 4:
                    customerController.deleteCustomerById();
                    break;
                case 5:
                    customerController.getTheCustomerByID();
                    break;
            }
        }else if(choose==4){
            bookController.findBooksThatAreNotTaken();
        }else if(choose==5){
            customerController.takeBook();
        }else if(choose==6){
            customerController.returnBook();
        }
    }
}
