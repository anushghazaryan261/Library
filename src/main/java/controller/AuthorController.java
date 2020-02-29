package controller;

import entities.Author;
import services.AuthorService;

import java.util.List;
import java.util.Scanner;

public class AuthorController {
    AuthorService authorService = AuthorService.getInstance();
    //singleton
    private static AuthorController authorController = null;

    public static AuthorController getInstance() {
        AuthorService.createTable();
        if (authorController == null) {
            authorController = new AuthorController();
        }
        return authorController;
    }

    //methods
    public void createAuthor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input the name of the author");
        String name = sc.nextLine();
        System.out.println("And the surname");
        String surname = sc.nextLine();
        Author author = new Author(name, surname);
        if (authorService.createAuthor(author) == 1) {
            System.out.println("The author is created");
        } else {
            System.out.println("Something went wrong");
        }
    }

    public void updateAuthorsName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input the name for an update");
        String name = sc.nextLine();
        System.out.println("Please input the id of an author you want to update");
        int id = sc.nextInt();
        if (authorService.updateAuthorsName(name, id) == 1) {
            System.out.println("The name has been updated");
        } else {
            System.out.println("Something went wrong");
        }
    }

    public void updateAuthorsSurname() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input the surname for an update");
        String surname = sc.nextLine();
        System.out.println("Please input the id of an author you want to update");
        int id = sc.nextInt();
        if (authorService.updateAuthorsSurname(surname, id) == 1) {
            System.out.println("The surname has been updated");
        } else {
            System.out.println("Something went wrong");
        }
    }

    public void deleteAuthorById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input the id of an author you want to delete");
        int id = sc.nextInt();
        if (authorService.deleteAuthorById(id) == 1) {
            System.out.println("The author is deleted");
        } else {
            System.out.println("something went wrong");
        }
    }

    public void findAll() {
        List<Author> authors = authorService.readingAuthors();
        authors.forEach(System.out::println);
    }
}
