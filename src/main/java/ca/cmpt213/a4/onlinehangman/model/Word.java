/**
 * NAME: Veronica Lund
 * SFUID: 301292997
 * EMAIL: vlund@sfu.ca
 */
package ca.cmpt213.a4.onlinehangman.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to define useful properties for a hangman game word
 * @author Veronica Lund
 */
public class Word {
    private String message;
    private List<Letter> letters = new ArrayList<>();
    private int length;

    public Word() {
        this.message = "";
    }

    public Word(String s) {
        this.message = s;
        this.length = message.length();
        for (char ch: message.toCharArray()) {
            Letter letter = new Letter(ch);
            letters.add(letter);
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Letter> getLetters() {
        return letters;
    }

    public void setLetters(List<Letter> letters) {
        this.letters = letters;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean allDiscovered() {
        for (Letter letter: letters) {
            if (letter.isGuessed() == false) {
                return false;
            }
        }
        return true;
    }

    public String getLettersAsString() {
        String temp = "";
        for (Letter letter: letters) {
            if (letter.isGuessed()) {
                temp += letter.getValue();
                temp += ' ';
            } else {
                temp += '_';
                temp += ' ';

            }
        }
        return temp;
    }

    public boolean processGuess(char ch) {
        boolean goodGuess = false;
        for (Letter letter: letters) {
            if (letter.getValue() == ch) {
                goodGuess = true;
                letter.setGuessed(true);
            }
        }
        return goodGuess;
    }
}
