package cz.vse.java.pryp00.adventura.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class HomeController {
    @FXML
    public TextField vstup;


    public void zpracujVstup(ActionEvent actionEvent) {
        System.out.println(vstup.getText());
    }
}
