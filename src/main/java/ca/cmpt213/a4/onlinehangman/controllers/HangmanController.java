/**
 * NAME: Veronica Lund
 * SFUID: 301292997
 * EMAIL: vlund@sfu.ca
 */
package ca.cmpt213.a4.onlinehangman.controllers;

import ca.cmpt213.a4.onlinehangman.model.Game;
import ca.cmpt213.a4.onlinehangman.model.Message;
import ca.cmpt213.a4.onlinehangman.model.Word;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Controller class used to define network calls
 * @author Veronica Lund
 */
@Controller
public class HangmanController {
    private Message promptMessage; //a resusable String object to display a prompt word at the screen
    private AtomicLong nextId = new AtomicLong();
    private List<Game> games = new ArrayList<>();
    private Game newGame;
    private Message currentGuess;

    //works like a constructor, but wait until dependency injection is done, so it's more like a setup
    @PostConstruct
    public void hangmanControllerInit() {
        promptMessage = new Message("Initializing...");
    }

    @GetMapping("/welcome")
    public String showWelcomePage(Model model) {

        promptMessage.setMessage("You are at the helloworld page!");
        model.addAttribute("promptMessage", promptMessage);

        this.newGame = new Game(nextId.incrementAndGet());
        this.currentGuess = new Message();
        games.add(newGame);

        // take the user to welcome.html
        return "welcome";
    }

    @PostMapping("/game")
    public String showGamePage(Model model) {
        model.addAttribute("game", this.newGame);
        model.addAttribute("currentGuess", this.currentGuess);
        model.addAttribute("promptMessage", promptMessage);
        if (this.newGame.gameOver()) {
            return "gameover";
        } else {
            return "game";
        }
    }

    @PostMapping("/guess")
    public String submitGuess(@ModelAttribute("currentGuess") Message guess, Model model) {
        model.addAttribute("game", this.newGame);
        model.addAttribute("promptMessage", promptMessage);
        model.addAttribute("currentGuess", this.currentGuess);
        if (guess.getMessage() != null && guess.getMessage().length() > 0) {
            newGame.processGuess(guess.getMessage().charAt(0));
        }
        if (this.newGame.win()) {
            promptMessage.setMessage("YOU WIN!");
            return "redirect:gameover";
        } else if (this.newGame.lose()) {
            promptMessage.setMessage("YOU LOSE LMAO!");
            return "redirect:gameover";
        } else {
            return "game";
        }
    }

    @GetMapping("/gameover")
    public String showGameOverPage(Model model) {
        model.addAttribute("game", this.newGame);
        model.addAttribute("promptMessage", promptMessage);
        model.addAttribute("currentGuess", this.currentGuess);
        return "gameover";
    }

    @GetMapping("/game/{id}")
    public String getGameById(@PathVariable("id") long id, Model model) throws GameNotFoundException {
        model.addAttribute("game", this.newGame);
        model.addAttribute("promptMessage", promptMessage);
        model.addAttribute("currentGuess", this.currentGuess);
        for (Game game : games) {
            if (game.getId() == id) {
                this.newGame = game;
                return "game";
            }
        }
        throw new GameNotFoundException();
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST,
            reason = "Invalid values.")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badArgumentExceptionHandler() {

    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND,
            reason = "RequestID not Found.")
    @ExceptionHandler(GameNotFoundException.class)
    public String badIdExceptionHandler() {
        return "redirect:/gamenotfound";
    }



}