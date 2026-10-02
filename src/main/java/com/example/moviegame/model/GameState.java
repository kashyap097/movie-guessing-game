package com.example.moviegame.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameState {
    private String movieName;
    private List<String> displayedNameList; // 💡 स्ट्रिंग की जगह लिस्ट ली
    private int remainingAttempts;
    private Set<Character> guessedLetters;

    public GameState(String movie) {
        this.movieName = movie.toUpperCase();
        this.remainingAttempts = 9; // 'BOLLYWOOD' में 9 अक्षर होते हैं
        this.guessedLetters = new HashSet<>();

        // Vowels डिफ़ॉल्ट हिंट
        guessedLetters.add('A'); guessedLetters.add('E'); guessedLetters.add('I');
        guessedLetters.add('O'); guessedLetters.add('U');

        generateDisplayedNameList();
    }

    public void generateDisplayedNameList() {
        this.displayedNameList = new ArrayList<>();
        for (char c : movieName.toCharArray()) {
            if (c == ' ') {
                displayedNameList.add("SPACE"); // स्पेस के लिए पहचान
            } else if (Character.isDigit(c)) {
                displayedNameList.add(String.valueOf(c)); // 💡 अगर नंबर (like 2) है, तो सीधे दिखाओ
            } else if (guessedLetters.contains(c)) {
                displayedNameList.add(String.valueOf(c)); // सही अक्षर
            } else {
                displayedNameList.add("_"); // छिपा हुआ अक्षर
            }
        }
    }

    public void makeGuess(char letter) {
        letter = Character.toUpperCase(letter);
        if (guessedLetters.contains(letter) || remainingAttempts <= 0) {
            return;
        }

        guessedLetters.add(letter);

        if (movieName.indexOf(letter) == -1) {
            remainingAttempts--;
        }

        generateDisplayedNameList(); // लिस्ट दोबारा अपडेट करें
    }

    public boolean isWon() {
        for (char c : movieName.toCharArray()) {
            if (c != ' ' && !Character.isDigit(c) && !guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    // Getters
    public List<String> getDisplayedNameList() { return displayedNameList; }
    public int getRemainingAttempts() { return remainingAttempts; }
    public boolean isGameOver() { return remainingAttempts <= 0 || isWon(); }
    public Set<Character> getGuessedLetters() { return guessedLetters; }
}
