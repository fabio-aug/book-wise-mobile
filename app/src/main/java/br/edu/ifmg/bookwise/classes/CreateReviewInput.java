package br.edu.ifmg.bookwise.classes;

public class CreateReviewInput {
    public float stars;
    public int idBook;
    public int idUser;

    public CreateReviewInput(float stars, int idBook, int idUser) {
        this.stars = stars;
        this.idBook = idBook;
        this.idUser = idUser;
    }
}
