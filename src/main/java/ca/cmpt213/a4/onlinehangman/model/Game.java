/**
 * NAME: Veronica Lund
 * SFUID: 301292997
 * EMAIL: vlund@sfu.ca
 */
package ca.cmpt213.a4.onlinehangman.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Class used to define game object properties
 * @author Veronica Lund
 */
public class Game {
    public long id;
    public Word word;
    public String messageAsString;
    private File file;
    public int guesses = 7;
    public int numGuesses = 0;
    public int numBadGuesses = 0;
    public Status status;
    public String guessedChars;
    public String guessedCharsPretty;

    public Game(long id) {
        try {
            file = new File("src/commonWords.txt");
            Scanner in = new Scanner(file);
            List<String> words = new ArrayList<String>();
            while (in.hasNextLine()) {
                Scanner check = new Scanner(in.nextLine());
                while(check.hasNext()){
                    words.add(check.next());
                }
            }
            in.close();
            Random random = new Random();
            this.word = new Word(words.get(random.nextInt(words.size())));
            this.messageAsString = this.word.getLettersAsString();
            this.id = id;
            this.status = Status.ACTIVE;
            this.guessedChars = "";
            this.guessedCharsPretty = "";
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Game() {
        this.id = 0;
    }

    public long getId() {
        return this.id;
    }

    public void incorrectGuess() {
        this.guesses++;
    }

    public boolean win() {
        return this.word.allDiscovered();
    }

    public boolean lose() {
        return (this.numBadGuesses > 7);
    }

    public boolean gameOver() {
        return (this.win() || this.lose());
    }

    public void processGuess(char ch) {
        if (this.guessedChars.indexOf(ch) < 0) {
            if (!this.word.processGuess(ch)) {
                this.numBadGuesses++;
            }
            this.numGuesses++;
            this.messageAsString = this.word.getLettersAsString();
        }
        this.guessedChars += ch;
        makeGuessedCharsPretty();
    }

    public void makeGuessedCharsPretty() {
        String temp = "";
        for (int i = 0; i < guessedChars.length(); i++) {
            temp += Character.toString(guessedChars.charAt(i));
            if (i < guessedChars.length() - 1) {
                temp += ", ";
            }
        }
        this.guessedCharsPretty = temp;
        if (win()) {
            this.status = Status.WON;
        }
        if (lose()) {
            this.status = Status.LOST;
        }
    }
}
