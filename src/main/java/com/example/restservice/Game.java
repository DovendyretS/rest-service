package com.example.restservice;

import java.util.ArrayList;

public class Game {

    private String visibleWord;
    private int lives;
    private ArrayList<String> usedLetters;
    private boolean isGameOver;

    public Game(String visibleWord, int lives, ArrayList<String> usedLetters, boolean isGameOver) {
        this.visibleWord = visibleWord;
        this.lives = lives;
        this.usedLetters = usedLetters;
        this.isGameOver = isGameOver;
    }

    public Game(){}

    public Game(String visibleWord){
        this.visibleWord = visibleWord;
    }

    public String getVisibleWord() {
        return visibleWord;
    }

    public void setVisibleWord(String visibleWord) {
        this.visibleWord = visibleWord;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public ArrayList<String> getUsedLetters() {
        return usedLetters;
    }

    public void setUsedLetters(ArrayList<String> usedLetters) {
        this.usedLetters = usedLetters;
    }
}

