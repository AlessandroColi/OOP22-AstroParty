package it.unibo.AstroParty.ui.impl;

import java.util.List;
import java.util.stream.Collectors;

import it.unibo.AstroParty.core.api.View;
import it.unibo.AstroParty.ui.api.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class SettingsController implements Controller {

    private final static String STARTING_MESSAGE = "il tuo nome qui";
    private final static int MIN_PLAYERS = 2;
    private final static List<Integer> ROUND_CHOICES = List.of(1, 2, 3);

    private View view;
    private List<TextField> nameFields;

    @FXML
    private TextField nameP1, nameP2, nameP3, nameP4;

    @FXML
    private CheckBox obstacleSelection, powerUpSelection;

    @FXML
    private ChoiceBox<Integer> roundSelection;

    @FXML
    private Button start, back;

    public SettingsController(View view) {
        this.view = view;
    }

    /**
     * event handler for "START" {@link Button}
     * @param event
     */
    @FXML
    public void startOnClick(ActionEvent event) {
        List<String> players = nameFields.stream()
                .map(t -> t.getText())
                .filter(n -> !n.isBlank())
                .collect(Collectors.toList());
        if (players.size() < MIN_PLAYERS) {
            nameFields.stream().forEach(t -> t.setStyle("-fx-border-color: " + (t.getText().isBlank() ? "red" : "black")));
        } else {
            view.start(players,
                    obstacleSelection.isSelected(),
                    powerUpSelection.isSelected(),
                    roundSelection.getValue());
        }        
    }

    /**
     * event handler for "BACK" {@link Button}
     * @param event
     */
    @FXML
    public void backOnClick(ActionEvent event) {
        try {
            this.view.switchScene(view.getSceneFactory().createMain());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        nameFields = List.of(nameP1, nameP2, nameP3, nameP4);
        nameFields.stream().forEach(t -> t.setPromptText(STARTING_MESSAGE));
        roundSelection.getItems().addAll(ROUND_CHOICES);
        roundSelection.setValue(ROUND_CHOICES.get(0));
    }

}