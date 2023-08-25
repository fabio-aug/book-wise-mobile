package br.edu.ifmg.bookwise.classes;

public class User {
    public int id;
    public String name;
    public String email;
    public String password;
    public Review[] reviews;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
