package it.unibo.astroparty.ui.impl;

import java.io.IOException;
import java.util.List;

import it.unibo.astroparty.core.api.GameView;
import it.unibo.astroparty.core.impl.GameApp;
import it.unibo.astroparty.graphics.impl.GameSceneImpl;
import it.unibo.astroparty.input.api.InputControl;
import it.unibo.astroparty.ui.api.Controller;
import it.unibo.astroparty.ui.api.SceneFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.transform.Scale;

/**
 * Implementation of SceneFactory.
 */
public class SceneFactoryImpl implements SceneFactory {

    private final GameView view;

    /**
     * Constructor for SceneFatctoryImpl.
     * @param view of the app, used by all the scene controllers to make changes
     */
    public SceneFactoryImpl(final GameView view) {
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scene createMain() throws IOException {
        return loadFXML("layouts/MainPage.fxml", new MainPageController(view));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scene createTutorial() throws IOException {
        return loadFXML("layouts/Tutorial.fxml", new TutorialController(view));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scene createSettings() throws IOException {
        return loadFXML("layouts/Settings.fxml", new SettingsController(view));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scene createGame(final InputControl inputControl) {
        return new GameSceneImpl(inputControl);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scene createScoreboard(final List<Integer> scores, final int rounds) throws IOException {
        return loadFXML("layouts/Scoreboard.fxml", new ScoreboardController(view, scores, rounds));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scene createOver(final String winnerPlayer) throws IOException {
        return loadFXML("layouts/GameOver.fxml", new OverController(view, winnerPlayer));
    }

    /**
     * Loads a new {@link Scene} from a fxml file.
     * @param path to the fxml file
     * @param controller of the scene
     * @return a new scene loaded from the path given in input
     * @throws IOException could be launched while loading the file .fxml
     */
    private Scene loadFXML(final String path, final Controller controller) throws IOException {
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(path));
        loader.setController(controller);
        final Parent root = loader.load();
        final double size = GameApp.WINDOW_SIZE / root.prefHeight(0);     // get the multiplier for the scene size
        root.getTransforms().add(new Scale(size, size));
        return new Scene(root, GameApp.WINDOW_SIZE, GameApp.WINDOW_SIZE);
    }
}
