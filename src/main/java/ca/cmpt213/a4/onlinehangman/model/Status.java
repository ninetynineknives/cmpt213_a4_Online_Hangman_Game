/**
 * NAME: Veronica Lund
 * SFUID: 301292997
 * EMAIL: vlund@sfu.ca
 */
package ca.cmpt213.a4.onlinehangman.model;

/**
 * Enum class used to define game status
 * @author Veronica Lund
 */
public enum Status {
    ACTIVE("Active"),
    WON("Won"),
    LOST("Lost");

    private String value;
    private Status(String value) {
        this.value = value;
    }
    public String getValue() {
        return this.value;
    }
}