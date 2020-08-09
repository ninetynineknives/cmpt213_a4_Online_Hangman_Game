/**
 * NAME: Veronica Lund
 * SFUID: 301292997
 * EMAIL: vlund@sfu.ca
 */
package ca.cmpt213.a4.onlinehangman.model;

/**
 * Class used to define the properties of letters in a word within the game
 * @author Veronica Lund
 */
public class Letter {
    private char value;
    private boolean guessed;

    Letter(){}
    Letter(char value) {
        this.value = value;
        this.guessed = false;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public boolean isGuessed() {
        return guessed;
    }

    public void setGuessed(boolean guessed) {
        this.guessed = guessed;
    }
}
