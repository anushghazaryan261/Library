package entities;

import constants.State;

import java.util.Objects;

public class Book {
    private int id=0;
    private String name;
    private String description;
    private int numberOfPages;
    private State state;

    public Book(String name) {
        this.name = name;
        this.state=State.NOT_TAKEN;
        id++;
    }

    public Book(int id, String name, String description, int numberOfPages, State state) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.numberOfPages = numberOfPages;
        this.state = state;
    }

    public Book() {
        id++;
        this.state=State.NOT_TAKEN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", state=" + state +
                '}';
    }
}
