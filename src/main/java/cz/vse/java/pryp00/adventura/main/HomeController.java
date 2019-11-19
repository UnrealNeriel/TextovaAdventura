package cz.vse.java.pryp00.adventura.main;

import cz.vse.java.pryp00.adventura.logika.Hra;
import cz.vse.java.pryp00.adventura.logika.IHra;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextArea;


public class HomeController extends GridPane {
    @FXML
    public TextField vstup;
    @FXML
    public TextArea vystup;

    private IHra hra = new Hra();

    public void initialize() {
        vystup.setText(hra.vratUvitani()+"\n\n");
        vystup.setEditable(false);
    }

    public void zaktivniVstup() {
        vstup.requestFocus();
    }

    private void zacniHru() {
        vystup.appendText("Příkaz: "+vstup.getText()+"\n");
        String vysledek = hra.zpracujPrikaz(vstup.getText());
        vystup.appendText(vysledek+"\n\n");
        vstup.clear();
    }

    private void zkontrolujKonecHry() {
        if(hra.konecHry()) {
            vystup.appendText(hra.vratEpilog());
            vstup.setDisable(true);
        }
    }


    public void zpracujVstup(ActionEvent actionEvent) {
        zacniHru();
        zkontrolujKonecHry();
    }
}
