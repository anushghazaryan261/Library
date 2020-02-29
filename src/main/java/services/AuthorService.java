package services;

import constants.MariadbConstants;
import constants.State;
import entities.Author;
import entities.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorService {
    //singleton
    private static AuthorService authorService = null;

    public static AuthorService getInstance() {
        if (authorService == null) {
            authorService = new AuthorService();
        }
        return authorService;
    }

    //creating table
    public static void createTable() {
        try (Connection con = DriverManager.getConnection(MariadbConstants.URL, MariadbConstants.User, MariadbConstants.PASS)) {
            String query = "CREATE TABLE IF NOT EXISTS authors(\n" +
                    "    id int NOT NULL AUTO_INCREMENT,\n" +
                    "    name varchar(255),\n" +
                    "    surname varchar(255),\n" +
                    "    PRIMARY KEY (id)\n" +
                    ")";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //methods

    //creating
    public int createAuthor(Author author) {
        try (Connection con = DriverManager.getConnection(MariadbConstants.URL, MariadbConstants.User, MariadbConstants.PASS)) {
            String query = "INSERT INTO authors(name,surname) VALUES(?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, author.getName());
            preparedStatement.setString(2, author.getSurname());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //updating
    public int updateAuthorsName(String name, int id) {
        try (Connection con = DriverManager.getConnection(MariadbConstants.URL, MariadbConstants.User, MariadbConstants.PASS)) {
            String query = "UPDATE authors SET name=(?) WHERE id=(?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateAuthorsSurname(String surname, int id) {
        try (Connection con = DriverManager.getConnection(MariadbConstants.URL, MariadbConstants.User, MariadbConstants.PASS)) {
            String query = "UPDATE authors SET surname=(?) WHERE id=(?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, surname);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //deleting
    public int deleteAuthorById(int id) {
        try (Connection con = DriverManager.getConnection(MariadbConstants.URL, MariadbConstants.User, MariadbConstants.PASS)) {
            String query = "DELETE FROM authors WHERE id=(?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //reading
    public List<Author> readingAuthors() {
        List<Author> authors = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(MariadbConstants.URL, MariadbConstants.User, MariadbConstants.PASS)) {
            String query = "SELECT * FROM authors";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                Author author = new Author(id, name, surname);
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }
}
