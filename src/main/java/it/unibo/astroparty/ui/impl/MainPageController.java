package it.unibo.astroparty.ui.impl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.astroparty.core.api.GameView;
import it.unibo.astroparty.ui.api.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller of the MainPage scene.
 */
public class MainPageController implements Controller {

    @FXML
    private Button play;

    @FXML
    private Button tutorial;

    private static final Logger LOGGER = Logger.getLogger("MainPageController");
    private final GameView view;

    /**
     * Constructor for MainPageController.
     * @param view
     */
    public MainPageController(final GameView view) {
        this.view = view;
    }

    /**
     * event handler for "PLAY" {@link Button}.
     * @param event
     */
    @FXML
    public void playOnClick(final ActionEvent event) {
        try {
            this.view.renderScene(view.getSceneFactory().createSettings());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error during loading of Settings scene");
        }
    }

    /**
     * event handler for "TUTORIAL" {@link Button}.
     * @param event
     */
    @FXML
    public void tutorialOnClick(final ActionEvent event) {
        try {
            this.view.renderScene(view.getSceneFactory().createTutorial());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error during loading of Tutorial scene");
        }
    }
}



