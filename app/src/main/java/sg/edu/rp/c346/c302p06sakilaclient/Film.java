package sg.edu.rp.c346.c302p06sakilaclient;

import java.io.Serializable;

public class Film implements Serializable {

    private int id;
    private String title;
    private String year;
    private String rating;

    public Film(int id, String title, String year, String rating) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.rating = rating;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}

