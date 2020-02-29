package controller;

import constants.State;
import entities.Customer;
import entities.Order;
import records.OrderArchive;
import services.BookService;
import services.CustomerService;
import services.OrderArchiveService;

import java.util.Scanner;

public class CustomerController {
    BookService bookService=BookService.getInstance();
    CustomerService customerService=CustomerService.getInstance();
    OrderArchiveService orderArchive=OrderArchiveService.getInstance();
    private static CustomerController customerController=null;
    public static CustomerController getInstance(){
        if(customerController==null){
            customerController=new CustomerController();
        }
        return customerController;
    }

    //create

    public void createCustomer(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Please input the username");
        String username=sc.next();
        System.out.println("Please input the password");
        String password=sc.next();
        Customer customer=new Customer(username,password);
        if (customerService.createCustomer(customer)==1) {
            System.out.println("The customer is created!");
        }else{
            System.out.println("Something went wrong");
        }
    }

    public void takeBook(){
        orderArchive.createTable();
        Scanner sc=new Scanner(System.in);
        System.out.println("Please input the customer id");
        int customerId=sc.nextInt();
        if(customerService.getCustomerById(customerId)!=null&&customerService.getCustomerById(customerId).HasOrder()){
            System.out.println("Please input the id of a book");
            int bookId=sc.nextInt();
            if(bookService.getBookById(bookId)!=null&&bookService.getBookById(bookId).getState().equals(State.NOT_TAKEN)){
                System.out.println("Please input the password");
                String password=sc.next();
                if (customerService.takeBook(customerId,password,bookId)==2) {
                    System.out.println("Great you have taken the book");
                    Order order=new Order();
                    orderArchive.addToArchive(customerId,bookId,order.getReturnDate());
                    System.out.println("Return it before " + order.getReturnDate());
                }else{
                    System.out.println("Something went wrong");
                }
            }else{
                System.out.println("book does not exist or the book is taken");
            }
        }else{
            System.out.println("No such customer found or the user already has an order");
        }
    }

    public void returnBook(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Please input the customer id");
        int customerId=sc.nextInt();
        if (customerService.getCustomerById(customerId) != null && !customerService.getCustomerById(customerId).HasOrder()) {
            System.out.println("Please input the book id");
            int bookId=sc.nextInt();
            if(bookService.getBookById(bookId)!=null&&bookService.getBookById(bookId).getState().equals(State.TAKEN)){
                if (customerService.returnBook(customerId,bookId)==2) {
                    System.out.println("The book is returned thank you");
                }
            }else{
                System.out.println("The book not found or it is not taken");
            }
        }else{
            System.out.println("customer not found or a customer does not have orders");
        }


    }

    public void updateCustomersUsername(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Please input the id of a customer you want to update");
        int id=sc.nextInt();
        System.out.println("Please input the username");
        String username=sc.next();
        if(customerService.updateCustomersUsername(username,id)==1){
            System.out.println("Username for the customer with id " + id + " is updated to " + username);
        }else {
            System.out.println("Something went wrong");
        }
    }

    public void updateCustomersPassword(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Please input the id of a customer you want to update");
        int id=sc.nextInt();
        System.out.println("Please input the old password");
        String oldPassword=sc.next();
        System.out.println("And now input the new Password");
        String newPassword=sc.next();
        if(customerService.updateCustomersPassword(id,oldPassword,newPassword)==1){
            System.out.println("The password has been updated");
        }else{
            System.out.println("Something went wrong! Either the id or the old password did not match");
        }
    }

    public void deleteCustomerById(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Input the id of a customer you want to delete");
        int id=sc.nextInt();
        if(customerService.deleteCustomer(id)==1){
            System.out.println("The customer is deleted");
        }else{
            System.out.println("Something went wrong");
        }
    }

    public void getTheCustomerByID(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Please input the id of a customer");
        int id=sc.nextInt();
        if(customerService.getCustomerById(id)!=null){
            System.out.println(customerService.getCustomerById(id));
        }else{
            System.out.println("No such customer was found");
        }
    }


}
