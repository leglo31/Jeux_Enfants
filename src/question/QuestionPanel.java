package question;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Vincent
 */
public class QuestionPanel extends JPanel {

    Box centreBox;
    JPanel titrePanel, consignePanel, questionPanel, reponsePanel, resultatPanel, solutionPanel, relancerPanel;
    JButton validerButton, solutionButton, relancerButton, retourButton;
    JLabel titreLabel, consigneLabel, questionLabel, resultatLabel;
    JTextField reponseTextField;
    String saisie;

    QuestionFonction qf = new QuestionFonction();

    public QuestionPanel() {
        createGUI();
    }

    private void createGUI() {

        // TITRE DE PAGE ========================================
        titreLabel = new JLabel("QUESTION");
        titreLabel.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        titrePanel = new JPanel(new FlowLayout());
        titrePanel.add(titreLabel);
        //=======================================================

        // AFFICHAGE CONSIGNE ======================================
        consigneLabel = new JLabel("Répondre à cette question :");
        consigneLabel.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        consignePanel = new JPanel(new FlowLayout());
        consignePanel.add(consigneLabel);
        //==========================================================

        // AFFICHAGE QUESTION ======================================
        qf.nouvelleQuestion();
        questionLabel = new JLabel(qf.questionUtil);
        questionLabel.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        questionPanel = new JPanel(new FlowLayout());
        questionPanel.add(questionLabel);
        //==========================================================

        // CHAMP DE TEXTE REPONSE =====================================
        reponseTextField = new JTextField();
        reponseTextField.setPreferredSize(new Dimension(250, 50));
        reponseTextField.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        // BOUTON VALIDATION ==========================================
        validerButton = new JButton("VALIDER");
        validerButton.setPreferredSize(new Dimension(200, 50));
        validerButton.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        // Panel reponseTextField + validerButton
        reponsePanel = new JPanel(new FlowLayout());
        reponsePanel.add(reponseTextField);
        reponsePanel.add(validerButton);
        //=============================================================

        // AFFICHAGE SOLUTION =============================================================
        resultatLabel = new JLabel(" ");
        resultatLabel.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        // Panel resultatLabel
        resultatPanel = new JPanel(new FlowLayout());
        resultatPanel.add(resultatLabel);
        //=================================================================================

        // BOUTON SOLUTION ================================================================
        solutionButton = new JButton("SOLUTION");
        solutionButton.setPreferredSize(new Dimension(200, 50));
        solutionButton.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        // Panel solutionButton
        solutionPanel = new JPanel(new FlowLayout());
        solutionPanel.add(solutionButton);
        //=================================================================================

        // BOUTON NOUVELLE QUESTION =================================
        relancerButton = new JButton("NOUVELLE QUESTION");
        relancerButton.setPreferredSize(new Dimension(400, 50));
        relancerButton.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        // Panel relancerButton
        relancerPanel = new JPanel(new FlowLayout());
        relancerPanel.add(relancerButton);
        //===========================================================

        // BOUTON RETOUR MENU =====================================
        retourButton = new JButton("RETOUR");
        retourButton.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        //=========================================================

        // BOX GESTION PLACEMENT ======================================================
        centreBox = Box.createVerticalBox();
        centreBox.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.black));
        // Ajout des éléments dans la box
        JPanel espacement = new JPanel(new FlowLayout());
        centreBox.add(espacement);
        centreBox.add(consignePanel);
        centreBox.add(questionPanel);
        centreBox.add(reponsePanel);
        centreBox.add(resultatPanel);
        centreBox.add(solutionPanel);
        centreBox.add(relancerPanel);
        //=============================================================================

        // MISE EN PAGE ============================
        this.setLayout(new BorderLayout());
        this.add(titrePanel, BorderLayout.NORTH);
        this.add(centreBox, BorderLayout.CENTER);
        this.add(retourButton, BorderLayout.SOUTH);
        //==========================================

        // ACTION BOUTON "VALIDER" =======================
        validerButton.addActionListener(ae -> {
            String texte;
            saisie();
            if (saisie().equals(qf.reponse())) {
                texte = "Bonne réponse";
                resultatLabel.setForeground(Color.green);
            }
            else {
                texte = "Mauvaise réponse";
                resultatLabel.setForeground(Color.red);
            }
            resultatLabel.setText(texte);
            reponseTextField.requestFocusInWindow();
        });
        //================================================

        // ACTION BOUTON "SOLUTION" =================
        solutionButton.addActionListener(ae -> {
            reponseTextField.setText(qf.reponse());
            reponseTextField.requestFocusInWindow();
        });
        //===========================================

        // ACTION BOUTON "NOUVELLE QUESTION" ========
        relancerButton.addActionListener(ae -> {
            qf.nouvelleQuestion();
            questionLabel.setText(qf.questionUtil);
            reponseTextField.setText("");
            resultatLabel.setText(" ");
            reponseTextField.requestFocusInWindow();
        });
        //===========================================
    }

    // FONCTION RECUPERATION SAISIE ET FILTRAGE ==========
    public String saisie() {
        saisie = reponseTextField.getText().toLowerCase()
                .replace("[àâä]", "a")
                .replace("[éèêë]", "e")
                .replace("[îï]", "i")
                .replace("[ôö]", "o")
                .replace("[ùûü]", "u")
                .replace("[ŷÿ]", "y")
                .replace("[ç]", "c");
        return saisie;
    }
    //====================================================
}
