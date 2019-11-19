package cz.vse.java.pryp00.adventura.main;

import cz.vse.java.pryp00.adventura.logika.Hra;
import cz.vse.java.pryp00.adventura.logika.IHra;
import cz.vse.java.pryp00.adventura.logika.Prostor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.Map;

public class HomeController extends GridPane implements IObserver {
    @FXML
    public TextField vstup;
    @FXML
    public TextArea vystup;
    @FXML
    public Button odesli;
    @FXML
    public ListView seznamVychodu;
    @FXML
    public ImageView hrac;

    private Map<String, Point2D> souradniceProstoru = new HashMap<>();


    private IHra hra = new Hra();

    public void initialize() {
        vystup.setText(hra.vratUvitani()+"\n\n");
        vystup.setEditable(false);
        hra.getHerniPlan().registerObserver(this);
        souradniceProstoru.put("pokoj",new Point2D(2,84));
        souradniceProstoru.put("dvur",new Point2D(74,38));
        souradniceProstoru.put("ulice",new Point2D(147,82));
        souradniceProstoru.put("slepa_ulice",new Point2D(147,174));
        souradniceProstoru.put("park",new Point2D(219,39));
        souradniceProstoru.put("kamaraduv_byt",new Point2D(292,85));
        update();
    }

    public void zaktivniVstup() {
        vstup.requestFocus();
    }

    private void zkontrolujKonecHry() {
        if(hra.konecHry()) {
            vystup.appendText(hra.vratEpilog());
            vstup.setDisable(true);
            odesli.setDisable(true);
            seznamVychodu.setDisable(true);
        }
    }

    public void zpracujVstup(ActionEvent actionEvent) {
        zpracujPrikaz(vstup.getText());
    }


    private void zpracujPrikaz(String prikaz) {
        vystup.appendText("Příkaz: "+prikaz+"\n");
        String vysledek = hra.zpracujPrikaz(prikaz);
        vystup.appendText(vysledek+"\n\n");
        vstup.clear();
        zkontrolujKonecHry();
    }

    @Override
    public void update() {
        System.out.println("aktualizace");
        seznamVychodu.getItems().clear();
        seznamVychodu.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
        String nazevProstoru = hra.getHerniPlan().getAktualniProstor().getNazev();
        hrac.setX(souradniceProstoru.get(nazevProstoru).getX());
        hrac.setY(souradniceProstoru.get(nazevProstoru).getY());

    }

    public void kliknutiNaVychod(MouseEvent mouseEvent) {
        Prostor prostor = (Prostor) seznamVychodu.getSelectionModel().getSelectedItem();
        zpracujPrikaz("jdi "+prostor.getNazev());
    }
}
