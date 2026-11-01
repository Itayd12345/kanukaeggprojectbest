package com.example.myapplication;

public class Player implements Comparable<Player> {
    private String name;
    private String password;
    private int games;

    public Player(String name, String password, int games) {
        this.name = name;
        this.password = password;
        this.games = games;
    }

    public String getName() {
        return name;
    }

    public int getGames() {
        return games;
    }

    public String getPassword() {
        return password;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public int compareTo(Player other) {
        return (this.games - other.games);
    }
}
