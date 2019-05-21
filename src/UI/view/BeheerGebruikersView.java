package UI.view;

import UI.controller.BeheerGebruikersController;
import logic.Administrator;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe die de view opzet van het beheerGebruikers scherm.
 */
public class BeheerGebruikersView extends JPanel implements TableModelListener {
    private BeheerGebruikersController beheerGebruikersController;
    private DefaultTableModel defaultTableModel;
    private String btnGebruikersBeheer;
    private JButton btnOpslaan;
    private JLabel lblSysteemMelding = new JLabel(" "); // label krijgt bewust een spatie als tekst om het label
    // vast te tonen in de layout al is hij dan nog wel niet feitelijk zichtbaar. Dit voorkomt een springer in de layout
    // nadat iemand iets wijzigt.

    /**
     * Constructor om deze view te creêeren.
     * @param beheerGebruikersController BeheerGebruikersController
     * @param btnGebruikersBeheer String
     */
    public BeheerGebruikersView(BeheerGebruikersController beheerGebruikersController,String btnGebruikersBeheer) {
        this.beheerGebruikersController = beheerGebruikersController;
        this.btnGebruikersBeheer = btnGebruikersBeheer;
    }

    /**
     * Methode om een panel te creëren voor de beheerGebruikersView.
     * @param buttonOpslaan String
     * @return JPanel
     */
    public JPanel createBeheerGebruikersPanel(String buttonOpslaan) {
        // Hier check op defaultTableModel, als deze leeg is is er een probleem met de db connectie
        if (defaultTableModel.getDataVector().size() == 0 || defaultTableModel == null) {
            lblSysteemMelding.setText(beheerGebruikersController.setSysteemMelding());
            lblSysteemMelding.setForeground(Color.red);
        }
        JTable tblTable = new JTable(defaultTableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                boolean editable = true;
                if (column == 3) {
                    editable = false;
                }
                return editable;
            }
        };
//      Kolom breedte op nul zetten om hem te verbergen, de index die in deze kolom staat hebben we wel nodig maar mag niet aangepast worden
        tblTable.getColumnModel().getColumn(0).setMinWidth(0);
        tblTable.getColumnModel().getColumn(0).setMaxWidth(0);
//      wachtwoordveld van het type 'masked' maken zodat wachtwoorden niet zichtbaar zijn.
        tblTable.getColumnModel().getColumn(4).setCellRenderer(masked);
//      table listener toevoegen.
        tblTable.getModel().addTableModelListener(this);
//      combobox maken voor keuze uit drie voorgedefineerde mogelijkheden om uit te kiezen.
        TableColumn rolColumn = tblTable.getColumnModel().getColumn(5);
        JComboBox comboBox = new JComboBox<String>();
        comboBox.addItem("Gedeactiveerd");
        comboBox.addItem("Administrator");
        comboBox.addItem("Verkoopmedewerker");
        rolColumn.setCellEditor(new DefaultCellEditor(comboBox));
        BeheerGebruikersView.ClickListener listener = new ClickListener();
        btnOpslaan = new JButton(buttonOpslaan);
        btnOpslaan.setEnabled(false);
        btnOpslaan.addActionListener(listener);
        JButton btnMenu = new JButton(btnGebruikersBeheer);
        btnMenu.addActionListener(listener);
        JScrollPane scrollPane = new JScrollPane(tblTable);

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        add(scrollPane,c);
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10,0,0,0);
        add(btnOpslaan,c);
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0,0,0,0);
        add(lblSysteemMelding,c);
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(10,0,0,0);
        add(btnMenu,c);
        return this;
    }

    /**
     * Methode om het wachtwoordveld te maskeren.
     * @param length int
     * @return String van astrixen met de lengte van het wachtwoord.
     */
    private String mask(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append('\u25CF');
        }
        return new String(sb);
    }

    /**
     * Code gevonden op het internet om een tabel te creëren van alle gebruikers.
     */
    private TableCellRenderer masked = new DefaultTableCellRenderer(){
        private static final long serialVersionUID = 1L;
        public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) {
            int length = 0;
            if (arg1 instanceof String) {
                length =  ((String) arg1).length();
            } else if (arg1 instanceof char[]) {
                length = ((char[])arg1).length;
            }
            setText(mask(length));
            return this;
        }
    };

    /**
     * Methode om de defaultTableModel te initialiseren.
     * @param defaultTableModel DefaultTableModel
     */
    public void setDefaultTableModel(DefaultTableModel defaultTableModel) {
            this.defaultTableModel = defaultTableModel;
    }

    /**
     * Methode om een systeemmelding te creēeren.
     * @param systeemMelding String
     */
    public void setLblSysteemMelding(String systeemMelding) {
        this.lblSysteemMelding.setText(systeemMelding);
    }

    /**
     * Methode om de tekstkleur van een systeemmelding in te stellen.
     * @param color Color
     */
    public void setLblTextColor(Color color) {
        lblSysteemMelding.setForeground(color);
    }

    /**
     * Actionlistener voor het opvangen van clicks op menu buttons.
     */
    class ClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Opslaan":
                    beheerGebruikersController.updateGebruikers();
                    btnOpslaan.setEnabled(false);
                    break;
                case "Gebruikers beheer":
                    beheerGebruikersController.hideBeheerGebruikersPanel();
                    beheerGebruikersController.startGebruikersBeheer();
                    break;
            }
        }
    }

    /**
     * Interface methode om wijzigingen in de tabel mee op te vangen.
     * @param e TableModelEvent
     */
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        TableModel model = (TableModel)e.getSource();
// Als er een wijziging is aangebracht de 'opslaan' knop activeren
        btnOpslaan.setEnabled(true);
        lblSysteemMelding.setText(" ");
// Juiste gegevens voor in de db bepalen aan de hand van de status van een gebruiker.
        int rolID;
        if (model.getValueAt(row,5).equals("Gedeactiveerd")){
            rolID = 2;
        } else if (model.getValueAt(row,5).equals("Administrator")) {
            rolID = 3;
        } else {
            rolID = 4;
        }
// Wijziging klaarzetten voor opslag repo / db.
        beheerGebruikersController.buildUpdate(new Administrator(
                        (String)model.getValueAt(row,3),
                        (String)model.getValueAt(row,1),
                        (String)model.getValueAt(row,2),
                        rolID,
                        (String)model.getValueAt(row,4),
                        (String)model.getValueAt(row,0),
                        0));

    }
}
