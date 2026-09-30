package com.example.moviegame.model;

import java.util.HashSet;
import java.util.Set;

public class GameState {
    private String movieName;
    private String displayedName;
    private int remainingAttempts;
    private Set<Character> guessedLetters;

    public GameState(String movie) {
        this.movieName = movie.toUpperCase();
        this.remainingAttempts = 5;
        this.guessedLetters = new HashSet<>();

        // शुरुआत में ही सभी Vowels को गेस लिस्ट में डाल देंगे (आपका हिंट रूल)
        guessedLetters.add('A');
        guessedLetters.add('E');
        guessedLetters.add('I');
        guessedLetters.add('O');
        guessedLetters.add('U');

        generateDisplayedName();
    }

    public void generateDisplayedName() {
        StringBuilder sb = new StringBuilder();
        for (char c : movieName.toCharArray()) {
            if (c == ' ') {
                sb.append("   "); // स्पेस के लिए खाली जगह
            } else if (guessedLetters.contains(c)) {
                sb.append(c).append(" "); // अगर Vowel है या गेस सही है
            } else {
                sb.append("_ "); // छिपे हुए अक्षर के लिए
            }
        }
        this.displayedName = sb.toString().trim();
    }

    public void makeGuess(char letter) {
        letter = Character.toUpperCase(letter);
        if (guessedLetters.contains(letter) || remainingAttempts <= 0) {
            return;
        }

        guessedLetters.add(letter);

        // अगर मूवी में वो अक्षर नहीं है, तो एक लाइफ कम होगी
        if (movieName.indexOf(letter) == -1) {
            remainingAttempts--;
        }

        generateDisplayedName();
    }

    // Check if player won
    public boolean isWon() {
        for (char c : movieName.toCharArray()) {
            if (c != ' ' && !guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    // Getters and Setters
    public String getDisplayedName() { return displayedName; }
    public int getRemainingAttempts() { return remainingAttempts; }
    public boolean isGameOver() { return remainingAttempts <= 0 || isWon(); }
    public Set<Character> getGuessedLetters() { return guessedLetters; }
}

