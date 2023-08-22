package br.edu.ifmg.bookwise.classes;

public class Review {
    public Integer id;
    public Integer idUser;
    public Integer idBook;
    public Float stars;

    public Review(Integer id, Integer idUser, Integer idBook, Float stars) {
        this.id = id;
        this.idUser = idUser;
        this.idBook = idBook;
        this.stars = stars;
    }
}