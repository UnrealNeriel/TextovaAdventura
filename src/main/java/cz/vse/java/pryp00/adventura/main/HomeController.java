package cz.vse.java.pryp00.adventura.main;

import cz.vse.java.pryp00.adventura.logika.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

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
    @FXML
    public MenuItem novaHra;
    @FXML
    public MenuItem napoveda;

    private Map<String, Point2D> souradniceProstoru = new HashMap<>();
    private Map<String, String> adresyObrazku = new HashMap<>();
    private ListView<Vec> batohListView = new ListView<>();

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

        /*batohListView.setCellFactory(listView -> new ListCell<Vec>() {
            private ImageView ikonaView = new ImageView();

            @Override
            public void updateItem(Vec vec, boolean empty) {
                super.updateItem(vec, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Image ikona = new Image(adresyObrazku.get(vec.getJmeno()));
                    ikonaView.setSmooth(true);
                    ikonaView.setFitHeight(30);
                    ikonaView.setFitWidth(30);
                    ikonaView.setImage(ikona);
                    setText(vec.getJmeno());
                    setGraphic(ikonaView);
                }
            }
        });*/
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

    public void zacniNovouHru(ActionEvent actionEvent) {
        this.hra = new Hra();
        initialize();
        HerniPlan.hracJeOblecen = false;
        System.out.println("Začala nová hra");
    }

    public void vypisNapovedu(ActionEvent actionEvent) {
        Stage stage = new Stage();
        WebView webView = new WebView();
        webView.getEngine().load(getClass().getResource("/napoveda.html").toExternalForm());
        stage.setScene(new Scene(webView, 1200, 650));
        stage.show();
        System.out.println("Vypsání nápovědy");
    }
}