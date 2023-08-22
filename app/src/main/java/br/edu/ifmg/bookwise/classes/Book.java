package br.edu.ifmg.bookwise.classes;

public class Book {
    public Integer id;
    public String title;
    public String author;
    public String synopsis;
    public String image;
    public Float averageReview;
    public Gender[] genders;

    public Book(Integer id, String title, String author, String synopsis, String image, Float averageReview, Gender[] genders) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.synopsis = synopsis;
        this.image = image;
        this.averageReview = averageReview;
        this.genders = genders;
    }
}

