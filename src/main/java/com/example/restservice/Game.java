package com.example.restservice;

import java.util.ArrayList;

public class Game {

    private String visibleWord;
    private int lives;
    private ArrayList<String> usedLetters;
    private String isGameOver;
    private String invisibleWord;

    public Game(String visibleWord, int lives, ArrayList<String> usedLetters, String isGameOver) {
        this.visibleWord = visibleWord;
        this.lives = lives;
        this.usedLetters = usedLetters;
        this.isGameOver = isGameOver;


    }

    public Game(String visibleWord, int lives, ArrayList<String> usedLetters, String isGameOver, String invisibleWord){
        this.visibleWord = visibleWord;
        this.lives = lives;
        this.usedLetters = usedLetters;
        this.isGameOver = isGameOver;
        this.invisibleWord = invisibleWord;
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

    public String getIsGameOver() {
        return isGameOver;
    }

    public void setIsGameOver(String isGameOver) {
        this.isGameOver = isGameOver;
    }

    public String getInvisibleWord() {
        return invisibleWord;
    }

    public void setInvisibleWord(String invisibleWord) {
        this.invisibleWord = invisibleWord;
    }
}

