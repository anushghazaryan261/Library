package records;

import entities.Customer;

public class OrderArchive {
    int customerId;
    int bookId;
    String returnDate;

    public OrderArchive(int customerId, int bookId, String returnDate) {
        this.customerId = customerId;
        this.bookId = bookId;
        this.returnDate = returnDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
