package entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Order {
    private int id = 0;
    private String returnDate;

    public Order() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 15);
        returnDate = sdf.format(c.getTime());
        id++;
    }

    public int getId() {
        return id;
    }

    public String getReturnDate() {
        return returnDate;
    }
}
