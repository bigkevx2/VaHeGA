package UI;

import UI.controller.InlogSchermController;
import logic.Bedrijf;
import logic.PersistenceLayerException;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Class Frame, de basis en start van de GUI.
 */
public class Frame extends JFrame {
    private Bedrijf bedrijf;

    /**
     * Constructor van Frame
     * In de constructor wordt de Frame en alle benodigheden opgezet voor het tonen van een GUI
     * @param bedrijf, bedrijf is het hart van de applicatie waarin de aansturing van de applicatie plaatsvind
     *                 daarom wordt deze ook aan Frame meegegeven.
     */
    public Frame(Bedrijf bedrijf) {
        this.bedrijf = bedrijf;
        // Afmetingen van het frame (window) instellen.
        setSize(1000,650);
        // Gedrag bij gebruik rood kruis van het frame, gekozen voor een custom afhandeling van deze actie.
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // Starten van het eerste panel dat in de frame wordt geplaatst, het eerste wat de user te zien krijgt.
        startInlogscherm();
        // het geheel tonen op het scherm
        setVisible(true);
        // Windowlistener om het opvangen van een druk op het rode kruis op te vangen
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    // Als het rode kruis wordt gebruikt wordt de lokaal opgeslagen repo nog naar de db geschreven via Bedrijf.
                    bedrijf.updateRepositoryToDb();
                }
                // en als het fout mocht gaan, hier de melding voor de console.
                catch (PersistenceLayerException db) {
                    System.out.println("Fout tijdens maken connectie met de db, foutmelding: " + db);
                }
                // Stoppen van het systeem
                System.exit(0);
            }
        });
    }

    /**
     * Methode voor het starten van het eerst zichtbare scherm voor de gebruiker, deze krijgt twee parameters mee:
     * Bedrijf, het hart van de applicatie en dit frame, het hart van de hele GUI.
     */
    private void startInlogscherm() {
        InlogSchermController inlogSchermController = new InlogSchermController(bedrijf, this);
    }
}
