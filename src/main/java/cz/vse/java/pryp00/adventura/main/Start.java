/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package cz.vse.java.pryp00.adventura.main;

import cz.vse.java.pryp00.adventura.logika.*;
import cz.vse.java.pryp00.adventura.uiText.TextoveRozhrani;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;


/**
 * Třída {@code Start} je hlavní třídou projektu,
 * který ...
 */
public class Start extends Application
{
    /**
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {
    /*    try {
             System.out.println(args[0]);
             System.exit(0);
        } catch (ArrayIndexOutOfBoundsException e) {
            IHra hra = new Hra();
            TextoveRozhrani ui = new TextoveRozhrani(hra);
            ui.hraj();
        } */
    launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/home.fxml"));
        GridPane root =  loader.load();
        Scene home = new Scene(root);
        primaryStage.setScene(home);
        primaryStage.show();
    }
}