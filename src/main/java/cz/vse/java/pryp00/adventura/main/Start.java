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
    try {
        int parametr = args[0].compareTo("text");
        if (parametr == 0) {
            IHra hra = new Hra();
            TextoveRozhrani ui = new TextoveRozhrani(hra);
            ui.hraj();
        } else {
            System.out.println("Neplatný parametr, jediný platný je: text");
            System.exit(1);
        }
    } catch (ArrayIndexOutOfBoundsException e) {
        launch(args);
    }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/home.fxml"));
        GridPane root =  loader.load();

        HomeController controller = loader.getController();

        Scene home = new Scene(root);
        primaryStage.setScene(home);
        controller.zaktivniVstup();
        primaryStage.show();
    }
}