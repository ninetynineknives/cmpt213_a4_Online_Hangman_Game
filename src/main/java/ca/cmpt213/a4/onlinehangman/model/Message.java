/**
 * NAME: Veronica Lund
 * SFUID: 301292997
 * EMAIL: vlund@sfu.ca
 */
package ca.cmpt213.a4.onlinehangman.model;

/**
 * Class provided to easily store messages for display
 * @author Veronica Lund
 */
public class Message {
    private String message;

    public Message() {
        this.message = "";
    }

    public Message(String s) {
        this.message = s;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}