package it.unibo.astroparty.ui.impl;

import java.util.List;

import it.unibo.astroparty.core.api.GameView;
import it.unibo.astroparty.ui.api.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

/**
 * Controller for the Scoreboard scene.
 */
public class ScoreboardController implements Controller {

    @FXML
    private ProgressBar progressP1, progressP2, progressP3, progressP4;

    @FXML
    private Button next;

    private final GameView view;
    private final List<Integer> scores;
    private final int rounds;

    /**
     * Constructor for the class ScoreboardController.
     * @param view
     * @param scores a list of the score (integer) of each player
     * @param rounds the number of rounds won required to win the game
     */
    public ScoreboardController(final GameView view, final List<Integer> scores, final int rounds) {
        this.view = view;
        this.scores = List.copyOf(scores);
        this.rounds = rounds;
    }

    /**
     * event handler for "NEXT" {@link Button}.
     * @param event
     */
    @FXML
    public void nextOnClick(final ActionEvent event) {
        view.nextRound();
    }

    /**
     * Called to initialize a controller after its root element has been completely processed.
     */
    @FXML
    public void initialize() {
        final List<ProgressBar> progress = List.of(progressP1, progressP2, progressP3, progressP4);
        for (int i = 0; i < scores.size(); i++) {
            progress.get(i).setProgress((double) scores.get(i) / rounds);
        }
    }
}

