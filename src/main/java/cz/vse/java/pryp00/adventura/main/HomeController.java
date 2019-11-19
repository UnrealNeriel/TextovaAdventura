package cz.vse.java.pryp00.adventura.main;

import cz.vse.java.pryp00.adventura.logika.Hra;
import cz.vse.java.pryp00.adventura.logika.IHra;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;



public class HomeController extends GridPane implements IObserver {
    @FXML
    public TextField vstup;
    @FXML
    public TextArea vystup;
    @FXML
    public Button odesli;
    @FXML
    public ListView seznamVychodu;

    private IHra hra = new Hra();

    public void initialize() {
        vystup.setText(hra.vratUvitani()+"\n\n");
        vystup.setEditable(false);
        hra.getHerniPlan().registerObserver(this);
        seznamVychodu.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
    }

    public void zaktivniVstup() {
        vstup.requestFocus();
    }

    private void zkontrolujKonecHry() {
        if(hra.konecHry()) {
            vystup.appendText(hra.vratEpilog());
            vstup.setDisable(true);
            odesli.setDisable(true);
        }
    }

    public void zpracujVstup(ActionEvent actionEvent) {
        vystup.appendText("Příkaz: "+vstup.getText()+"\n");
        String vysledek = hra.zpracujPrikaz(vstup.getText());
        vystup.appendText(vysledek+"\n\n");
        vstup.clear();
        zkontrolujKonecHry();
    }

    @Override
    public void update() {
        System.out.println("aktualizace");
        seznamVychodu.getItems().clear();
        seznamVychodu.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
    }
}
