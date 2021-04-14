/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcul;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author laurent
 */
public class CalculInterface extends JPanel {

    Box cent;
    Calcul c;
    Integer resultat;
    int level;
    JLabel aleatoire;
    JLabel bonfaux;
    JTextField saisie;

    //CONSTRUCTEUR
    public CalculInterface() {
        this.saisie = new JTextField();//création jtext pour la saisie
        this.bonfaux = new JLabel(" ");//espace entre guillemet pour éviter des sauts de labels
        //création label bonfaux pour phrase bonne ou mauvaise réponse
        this.aleatoire = new JLabel(" ");//création label calcul aléatoire

        //Création du Panel Calcul
        JPanel calcul = new JPanel();//panel dans la Jframe
        this.setLayout(new BorderLayout());//Panel prend toutela place de la fenêtre

        //Création du Panel titre placé au nord du Panel Calcul ou on mettra le Label CALCUL
        JPanel titre = new JPanel();
        this.add(titre, BorderLayout.NORTH);
        //Trait horizontal sous "CALCUL"
        titre.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.black));

        //Création du Label placé dans le Panel titre avec affichage "CALCUL"
        JLabel label = new JLabel("CALCUL");
        //Police et taille
        label.setFont(new Font(Font.SERIF, Font.BOLD, 35));
        //Ajout le label dans le Panel titre
        titre.add(label);

        //CREATION DU PANEL ET BOUTON RETOUR
        JPanel retour = new JPanel();
        JButton JButton = new JButton("RETOUR");
        JButton.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        //Création du Bouton retour
        this.add(retour, BorderLayout.SOUTH);//Panel retour au sud du panel calcul(this)
        JButton.setPreferredSize(new Dimension(200, 50));
        retour.add(JButton);//button retour dans Jpanel retour

        /*JButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

            }
        });*/
        //CREATION DU PANEL CENTRE
        JPanel centre = new JPanel();
        this.add(centre, BorderLayout.CENTER);
        cent = Box.createVerticalBox();//cration d'une box au centre pour aligné vertivalement les boutons/panel
        centre.add(cent);
        espace(0, 70);//appel methode pour l'espacement des boutons

        //CREATION DU LABEL AFFICHAGE de la phrase Calcul à effectuer
        JPanel affichage = new JPanel();
        JLabel phrase = new JLabel("Calcul à effectuer :");
        affichage.add(phrase);
        phrase.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        cent.add(affichage);
        espace(0, 30);

        //CREATION DU LABEL AFFICHAGE DU CALCUL ALEATOIRE
        JPanel caleatoire = new JPanel();
        cent.add(caleatoire);//Panel caleatoire dans Box
        aleatoire.setFont(new Font(Font.SERIF, Font.BOLD, 60));//police label
        aleatoire.setForeground(Color.blue);//couleur champ calcul label en bleu
        caleatoire.add(aleatoire);//Jlabel aléatoire dans JPanel caléatoire
        espace(0, 30);

        //CREATION DU JTEXT SAISIE UTILISATEUR
        saisie.setFont(new Font(Font.SERIF, Font.BOLD, 60));
        saisie.setPreferredSize(new Dimension(400, 70));
        cent.add(saisie);
        cent.add(Box.createRigidArea(new Dimension(700, 3)));

        //CREATION DU BOUTON VALIDER
        JPanel val = new JPanel();
        JButton valider = new JButton("VALIDER");
        valider.setPreferredSize(new Dimension(140, 50));

        //Action listener sur le bouton VALIDER
        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //action effectuée après le clic VALIDER
                //Comparaison de la saisie avec resultat avec une condition si
                if (saisie.getText().equals(c.Resultat().toString())) {
                    bonfaux.setText("BRAVO, TU ES LE MEILLEUR!!");
                    bonfaux.setFont(new Font(Font.SERIF, Font.BOLD, 30));
                    bonfaux.setForeground(Color.green);
                } else {
                    bonfaux.setText("DOMMAGE, ESSAIE ENCORE!!");
                    bonfaux.setFont(new Font(Font.SERIF, Font.BOLD, 30));
                    bonfaux.setForeground(Color.red);
                    saisie.setText("");//efface le contenu du Texte dans JTextfield-saisie utilisateur
                }
                //Remet le curseur sur le JText
                saisie.requestFocus();
            }
        });
        valider.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        val.add(valider);
        //espace(0, 60);

        //CREATION DU PANEL(FLOWLAYOUT) ou on mettra saisie et valider
        JPanel flow = new JPanel(new FlowLayout());// permet de mettre panel à la suite
        cent.add(flow);
        flow.add(Box.createRigidArea(new Dimension(140, 50)));//création espace avant le Jtext saisie
        flow.add(saisie);
        flow.add(val);
        espace(0, 30);

        //CREATION DU PANEL ET LABEL bonne réponse/Mauvaise réponse
        JPanel resultat = new JPanel();
        cent.add(resultat);
        bonfaux.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        resultat.add(bonfaux);
        espace(0, 30);

        //CREATION DU BOUTON SOLUTION
        JPanel solu = new JPanel();
        cent.add(solu);
        JButton solution = new JButton("SOLUTION");
        solution.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        solu.add(solution);
        espace(0, 60);

        //Action listener sur le bouton SOLUTION
        solution.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                saisie.setText(c.Resultat().toString());//action faite dès que
                //le bouton solution est sélectionné: affichage du résultat
            }
        });

        // CREATION DU PANEL NIVEAU1
        JPanel niveau = new JPanel();
        cent.add(niveau);
        ButtonGroup groupe = new ButtonGroup();
        JRadioButton niveau1 = new JRadioButton("niveau 1");
        JRadioButton niveau2 = new JRadioButton("niveau 2");
        niveau.add(niveau1);//le radio niveau 1 bouton dans le panel niveau
        niveau.add(niveau2);//le radio niveau 2 bouton dans le panel niveau
        groupe.add(niveau1);//le radio niveau 1 bouton dans le buttongroup
        groupe.add(niveau2);//le radio niveau 2 bouton dans le buttongroup

        //CREATION DU BOUTON NOUVEAU CALCUL
        JPanel newcalcul = new JPanel();
        cent.add(newcalcul);
        JButton nouveaucalcul = new JButton("NOUVEAU CALCUL");
        nouveaucalcul.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        newcalcul.add(nouveaucalcul);
        espace(0, 10);
        //Action listener sur le bouton NOUVEAU CALCUL
        nouveaucalcul.addActionListener(new ActionListener() {//écouteur du bouton NOUVEAU CALCUL
            @Override
            public void actionPerformed(ActionEvent nouveaucalc) {//action sur produite après clic du bouton NOUVEAU CALCUL
                genererCalcul();//appel de la méthode pour générer calcul
            }
        });
        // Action listener sur le bouton niveau 1
        niveau1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                level = 1;
                genererCalcul();
            }
        });

        // Action listener sur le bouton niveau 2
        niveau2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                level = 2;
                genererCalcul();
            }
        });
    }
    //FIN DE L'INTERFACE GRAPHIQUE

    //méthode pour créer des espaces entre les composants
    private void espace(int x, int y) {
        cent.add(Box.createRigidArea(new Dimension(x, y)));
    }

    //méthode pour générer un calcul en fonction du niveau
    //condition en fonction du niveau = paramètres Calcul(soit 1 soit 2)
    private void genererCalcul() {
        if (level == 1) {
            c = new Calcul(1);
        } else if (level == 2) {
            c = new Calcul(2);
        }
        aleatoire.setText(c.toString());//affiche le calcul
        bonfaux.setText(" ");//espace entre les guillemets pour éviter des sauts de labels
        //affiche la phrase erreur/bonne réponse
        saisie.setText("");//efface la saisie
        saisie.requestFocus();//remet le curseur dans le label saisie
    }
}
