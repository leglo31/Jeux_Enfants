package accueil;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * @author Vincent and BsT
 */
public class AccueilPanel extends JPanel {

    Box centreBox;
    JPanel titrePanel, niveauPanel, activitePanel;
    JRadioButton niveau1RadioButton, niveau2RadioButton;
    ButtonGroup niveauGroup;
    JButton dessinButton, calculButton, questionButton;
    JLabel titreLabel, niveauLabel;

    public AccueilPanel() {
        createGUI();

    }

    private void createGUI() {
        this.setLayout(new BorderLayout());
        centreBox = Box.createVerticalBox();
        // TITRE DE PAGE ========================================
        titrePanel = new JPanel(new FlowLayout());
        titrePanel.setMaximumSize(new Dimension(1600, 200));
        titreLabel = new JLabel("bienvenue dans le Bac a Sable");
        titreLabel.setFont(new Font("Childs Play", Font.PLAIN, 120));

        JPanel astucePanel = new JPanel();
        JLabel astuceLabel = new JLabel("Utilise les onglets et le menu pour naviguer entre les jeux");
        astuceLabel.setFont(new Font("Childs Play", Font.PLAIN, 50));

        titrePanel.add(titreLabel);
        astucePanel.add(astuceLabel);
        //=======================================================
        // BOX GESTION PLACEMENT ======================================================
        centreBox.add(Box.createRigidArea(new Dimension(0, 150)));
        // BOUTON RADIO NIVEAU =============================
        niveauGroup = new ButtonGroup();
        JLabel travaux = new JLabel("   EN TRAVAUX  -->");
        travaux.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        travaux.setIcon(new ImageIcon("img/travaux.png"));
        niveauLabel = new JLabel("[ Choix de la difficulté :");

        niveau1RadioButton = new JRadioButton("Niveau 1");

        niveau2RadioButton = new JRadioButton("Niveau 2 ]");

        niveauGroup.add(niveau1RadioButton);
        niveauGroup.add(niveau2RadioButton);
        // Panel niveauLabel + niveau1RadioButton + niveau2RadioButton
        niveauPanel = new JPanel(new FlowLayout());
        niveauPanel.add(travaux);
        niveauPanel.add(niveauLabel);

        niveauPanel.add(niveau1RadioButton);
        niveauPanel.add(niveau2RadioButton);
        //==================================================

        // BOUTON ACTIVITE ==========================================
        JLabel travauxLabel = new JLabel("   EN TRAVAUX  -->");
        travauxLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        travauxLabel.setIcon(new ImageIcon("img/travaux.png"));
        dessinButton = new JButton("DESSIN");
        dessinButton.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        calculButton = new JButton("CALCUL");
        calculButton.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        questionButton = new JButton("QUESTION");
        questionButton.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        // Panel dessinButton + calculButton + questionButton
        activitePanel = new JPanel(new FlowLayout());
        activitePanel.add(travauxLabel);
        activitePanel.add(dessinButton);
        activitePanel.add(calculButton);
        activitePanel.add(questionButton);
        //===========================================================

        // Ajout des éléments dans la box
        centreBox.add(titrePanel);
        centreBox.add(astucePanel);
        centreBox.add(niveauPanel);
        centreBox.add(activitePanel);
        //=============================================================================

        // MISE EN PAGE ==========================
        this.add(centreBox, BorderLayout.CENTER);

        //========================================
    }
}
