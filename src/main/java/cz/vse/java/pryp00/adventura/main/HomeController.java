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
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Ovladac FXML souboru, obsahuje nastroje pro ovladani hry v grafickem rozhrani
 */
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
    @FXML
    public ListView veciVbatohu;

    private Map<String, Point2D> souradniceProstoru = new HashMap<>();
    private IHra hra = new Hra();

    /**
     * provadi hlavni inicializaci hry pri spusteni
     */
    public void initialize() {
        vystup.setText(hra.vratUvitani()+"\n\n");
        vystup.setEditable(false);
        hra.getHerniPlan().registerObserver(this);
        hra.getBatoh().registerObserver(this);
        souradniceProstoru.put("pokoj",new Point2D(2,84));
        souradniceProstoru.put("dvur",new Point2D(74,38));
        souradniceProstoru.put("ulice",new Point2D(147,82));
        souradniceProstoru.put("slepa_ulice",new Point2D(147,174));
        souradniceProstoru.put("park",new Point2D(219,39));
        souradniceProstoru.put("kamaraduv_byt",new Point2D(292,85));
        update();
        }

    /**
     * umozni hraci rovnou pouzivat pole vstupu bez kliknuti mysi
     */
    public void zaktivniVstup() {
        vstup.requestFocus();
    }

    /**
     * Kontroluje, zda hra jiz neskoncila
     */
    private void zkontrolujKonecHry() {
        if(hra.konecHry()) {
            vystup.appendText(hra.vratEpilog());
            vstup.setDisable(true);
            odesli.setDisable(true);
            seznamVychodu.setDisable(true);
        }
    }

    /**
     * reaguje na vstup hrace
     * @param actionEvent
     */
    public void zpracujVstup(ActionEvent actionEvent) {
        zpracujPrikaz(vstup.getText());
    }

    /**
     * zpracuje zadany prikaz
     * @param prikaz vstup pro ovladani hry
     */
    private void zpracujPrikaz(String prikaz) {
        vystup.appendText("Příkaz: "+prikaz+"\n");
        String vysledek = hra.zpracujPrikaz(prikaz);
        vystup.appendText(vysledek+"\n\n");
        vstup.clear();
        zkontrolujKonecHry();
        zobrazitObrazky();
    }

    /**
     * provadi aktualizaci na zmeny
     */
    @Override
    public void update() {
        System.out.println("aktualizace");
        seznamVychodu.getItems().clear();
        seznamVychodu.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
        String nazevProstoru = hra.getHerniPlan().getAktualniProstor().getNazev();
        hrac.setX(souradniceProstoru.get(nazevProstoru).getX());
        hrac.setY(souradniceProstoru.get(nazevProstoru).getY());
    }

    /**
     * umoznuje hraci rovnou klikat na prostor misto zadavani prikazu pro prechod
     * @param mouseEvent
     */
    public void kliknutiNaVychod(MouseEvent mouseEvent) {
        Prostor prostor = (Prostor) seznamVychodu.getSelectionModel().getSelectedItem();
        zpracujPrikaz("jdi "+prostor.getNazev());
    }

    /**
     * zahodi soucasne rozehranou hru a zalozi zcela novou
     * @param actionEvent
     */
    public void zacniNovouHru(ActionEvent actionEvent) {
        this.hra = new Hra();
        initialize();
        HerniPlan.hracJeOblecen = false;
        vystup.appendText(hra.vratUvitani());
        vstup.setDisable(false);
        odesli.setDisable(false);
        seznamVychodu.setDisable(false);
        veciVbatohu.getItems().clear();
        zaktivniVstup();
        vystup.clear();
        vystup.setText(hra.vratUvitani()+"\n\n");

        System.out.println("Začala nová hra");
    }

    /**
     * vypise napovedu
     * @param actionEvent
     */
    public void vypisNapovedu(ActionEvent actionEvent) {
        Stage stage = new Stage();
        WebView webView = new WebView();
        webView.getEngine().load(getClass().getResource("/napoveda.html").toExternalForm());
        stage.setScene(new Scene(webView, 1200, 650));
        stage.show();
        System.out.println("Vypsání nápovědy");
    }

    /**
     * zobrazi obrazky sebranych veci v inventari
     */
    public void zobrazitObrazky() {
        veciVbatohu.getItems().clear();
        Set<String> seznam = hra.getBatoh().vratVeci();
        for(String vec : seznam){
            ImageView obrazek = new ImageView();
            obrazek.setImage(new Image("/" + vec.toLowerCase() + ".png"));
            veciVbatohu.getItems().add(obrazek);
        }
        zaktivniVstup();
    }
}