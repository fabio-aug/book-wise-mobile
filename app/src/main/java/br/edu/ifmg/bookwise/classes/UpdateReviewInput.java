package br.edu.ifmg.bookwise.classes;

public class UpdateReviewInput {
    public int id;
    public float stars;

    public UpdateReviewInput(int id, float stars) {
        this.id = id;
        this.stars = stars;
    }
}
