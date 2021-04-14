package administration;

import dao.QuestionDAO;
import entity.Question;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author BsT
 */
public class Admin extends JPanel {

    QuestionDAO q = new QuestionDAO();
    Question nQuestion;
    JTextField afficheQuestion;
    JTextField afficheReponse;
    ButtonGroup group;
    JRadioButton choixNiveaux1;
    JRadioButton choixNiveaux2;
    int niveau = 0;

    public Admin() {
        createGUI();
    }

    private void createGUI() {
        nQuestion = new Question();
        this.setLayout(new BorderLayout());

        // PANEL du Titre
        JPanel titre = new JPanel();
        titre.setLayout(new FlowLayout());

        JLabel jTitre = new JLabel("Panneau Administration");
        jTitre.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        titre.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.black));

        titre.add(jTitre);
        //-----------------------------------

        //---------------- PANEL de la modification --------------------
        // Création d'une Box pour la mise en page
        Box boxPrincipale = Box.createVerticalBox();

        JPanel sQuestion = new JPanel();
        sQuestion.setLayout(new FlowLayout());
        sQuestion.setMaximumSize(new Dimension(400, 100));
        //Creation d'un espace sur la hauteur
        boxPrincipale.add(Box.createRigidArea(new Dimension(0, 100)));
        boxPrincipale.add(sQuestion);

        JLabel selectQuestion = new JLabel("Veuillez séléctionner votre question :");
        selectQuestion.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        sQuestion.add(selectQuestion);

        // Panel de la comboBox qui s'hydrate avec les questions de DB
        JPanel jQuestion = new JPanel();
        jQuestion.setLayout(new GridLayout());

        //On dimensionne la taille max du Gridlayout
        jQuestion.setMaximumSize(new Dimension(650, 60));

        /*  On peut mettre une bordure pour voir la taille du GridLayout :
        jQuestion.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.yellow));
         */
        //Ajout du GridLayout a la Box
        boxPrincipale.add(jQuestion);
        JComboBox question = new JComboBox(q.getAll().toArray());
        question.addActionListener((ae) -> {

            afficheQuestion.setText(((Question) (question.getSelectedItem())).getQuestion());
            afficheReponse.setText(((Question) (question.getSelectedItem())).getReponse());
            if ((((Question) (question.getSelectedItem())).getNiveau() == 1)) {
                choixNiveaux1.setSelected(true);
            } else {
                choixNiveaux2.setSelected(true);
            }
        });

        //Ajout de la comboBox au GridLayout
        jQuestion.add(question);
        //-------------------------------------------

        //------------ Modification Question ---------------
        JPanel champQuestion = new JPanel();
        champQuestion.setLayout(new FlowLayout());
        champQuestion.setMaximumSize(new Dimension(550, 100));
        boxPrincipale.add(Box.createRigidArea(new Dimension(0, 100)));
        boxPrincipale.add(champQuestion);

        JLabel textQuestion = new JLabel("Champs texte de modification/création d'une question :");
        textQuestion.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        champQuestion.add(textQuestion);

        // Panel du texte qui recevra une question
        JPanel jTexteQuestion = new JPanel();
        jTexteQuestion.setLayout(new GridLayout());

        jTexteQuestion.setMaximumSize(new Dimension(600, 150));

        boxPrincipale.add(jTexteQuestion);

        afficheQuestion = new JTextField();

        jTexteQuestion.add(afficheQuestion);
        //-------------------------------------------

        //------------ Modification Réponse ---------------
        JPanel champReponse = new JPanel();
        champReponse.setLayout(new FlowLayout());
        champReponse.setMaximumSize(new Dimension(550, 100));
        boxPrincipale.add(Box.createRigidArea(new Dimension(0, 50)));
        boxPrincipale.add(champReponse);

        JLabel textReponse = new JLabel("Champs texte de modification/création d'une réponse :");
        textReponse.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        champReponse.add(textReponse);

        // Panel du texte qui recevra une reponse
        JPanel jTexteReponse = new JPanel();
        jTexteReponse.setLayout(new GridLayout());

        jTexteReponse.setMaximumSize(new Dimension(600, 150));

        boxPrincipale.add(jTexteReponse);

        afficheReponse = new JTextField();

        jTexteReponse.add(afficheReponse);
        //-------------------------------------------

        //------------ Choix niveaux ---------------
        JPanel jTexteNiveau = new JPanel();
        jTexteNiveau.setLayout(new FlowLayout());
        jTexteNiveau.setMaximumSize(new Dimension(550, 100));

        boxPrincipale.add(Box.createRigidArea(new Dimension(0, 50)));
        boxPrincipale.add(jTexteNiveau);

        JLabel texteNiveau = new JLabel("Champs texte de modification/création d'une question :");
        texteNiveau.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        jTexteNiveau.add(texteNiveau);

        JPanel jGroupBoutton = new JPanel();
        jGroupBoutton.setLayout(new FlowLayout());
        group = new ButtonGroup();

        choixNiveaux1 = new JRadioButton("Niveau 1");
        choixNiveaux1.addActionListener((ae) -> {
            niveau = 1;
            nQuestion.setNiveau(niveau);
        });

        choixNiveaux2 = new JRadioButton("Niveau 2");
        choixNiveaux2.addActionListener((ae) -> {
            niveau = 2;
            nQuestion.setNiveau(niveau);
        });

        group.add(choixNiveaux1);
        group.add(choixNiveaux2);
        jGroupBoutton.add(choixNiveaux1);
        jGroupBoutton.add(choixNiveaux2);
        boxPrincipale.add(jGroupBoutton);

        //-------------------------------------------
        //-------- Boutons Modifier et Créer-------------
        JPanel jBouton = new JPanel();
        jBouton.setLayout(new FlowLayout());

        boxPrincipale.add(Box.createRigidArea(new Dimension(0, 20)));
        boxPrincipale.add(jBouton);
        //Bouton Modifier
        JButton jModifier = new JButton("Modifier");
        jModifier.addActionListener((ae) -> {

            nQuestion = new Question(((Question) (question.getSelectedItem())).getId(), niveau, afficheQuestion.getText(), afficheReponse.getText());

            q.update(nQuestion);
            System.out.println("Bravo");
        });
        jModifier.setPreferredSize(new Dimension(150, 70));

        //Bouton Créer
        JButton jCreer = new JButton("Enregistrer");

        jCreer.addActionListener((ae) -> {
            nQuestion = new Question(niveau, afficheQuestion.getText(), afficheReponse.getText());
            q.create(nQuestion);

        });
        jCreer.setPreferredSize(new Dimension(150, 70));

        //Bouton Supprimer
        JButton jSupprimer = new JButton("Supprimer");

        jSupprimer.addActionListener((ae) -> {

            q.delete(((Question) (question.getSelectedItem())).getId());

        });
        jSupprimer.setPreferredSize(new Dimension(150, 70));

        jBouton.add(jModifier);
        jBouton.add(jCreer);
        jBouton.add(jSupprimer);

        //-----------------------------------
        this.add(titre, BorderLayout.NORTH);
        this.add(boxPrincipale, BorderLayout.CENTER);

    }

}
