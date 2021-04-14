package jeux_enfants;

import accueil.AccueilPanel;
import administration.Admin;
import dao.UtilisateurDAO;
import entity.Utilisateur;
import calcul.CalculInterface;
import dessin.Dessin;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import question.QuestionPanel;

/**
 *
 * @author BsT
 */
public class BasFrame extends JFrame {

    JTabbedPane tabbedPane = new JTabbedPane();
    JMenuBar jMenuBar = new JMenuBar();
    Utilisateur utilisateur;
    UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    AccueilPanel accueil = new AccueilPanel();
    Dessin dessin = new Dessin();
    CalculInterface calcul = new CalculInterface();
    QuestionPanel question = new QuestionPanel();
    Admin admin = new Admin();

    public BasFrame() {

        //Configuration de la JFrame
        this.setTitle("Bac à Sable");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(800, 600));
        this.setLocationRelativeTo(null);
//        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/sand_bucket.png")));
        //-------------------------------------------------
        JScrollPane mainScrollPane = new JScrollPane(tabbedPane);
        //Appel aux méthodes
        creationMenu();
        creationOnglets();

        //Ajout d'élements a notre frame
        this.pack();
        this.setJMenuBar(jMenuBar);

        this.setContentPane(mainScrollPane);
        //--------------------------------

    }

    private void creationMenu() {

        //On créer notre Menu
        JMenu menu = new JMenu("Activités");
        JMenu menuAdmin = new JMenu("Administrateur");
        menuAdmin.setIcon(new ImageIcon("img/admin.png"));

        jMenuBar.add(menu);

        JMenuItem jMenuItemAc = new JMenuItem("Accueil");
        jMenuItemAc.addActionListener((ae) -> {
            tabbedPane.setSelectedComponent(accueil);

        });
        jMenuItemAc.setIcon(new ImageIcon("img/accueil.png"));
        jMenuItemAc.setMnemonic(KeyEvent.VK_ESCAPE);
        jMenuItemAc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, KeyEvent.CTRL_DOWN_MASK));

        JMenuItem jMenuItemD = new JMenuItem("Dessin");
        jMenuItemD.addActionListener((ae) -> {
            tabbedPane.setSelectedComponent(dessin);

        });
        jMenuItemD.setIcon(new ImageIcon("img/crayon.png"));
        //Création de raccourci clavier
        jMenuItemD.setMnemonic(KeyEvent.VK_D);
        jMenuItemD.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));

        JMenuItem jMenuItemC = new JMenuItem("Calcul");
        jMenuItemC.addActionListener((ae) -> {
            tabbedPane.setSelectedComponent(calcul);

        });
        jMenuItemC.setIcon(new ImageIcon("img/calculette.png"));
        jMenuItemC.setMnemonic(KeyEvent.VK_C);
        jMenuItemC.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));

        JMenuItem jMenuItemQ = new JMenuItem("Questions");
        jMenuItemQ.addActionListener((ae) -> {
            tabbedPane.setSelectedComponent(question);

        });
        jMenuItemQ.setIcon(new ImageIcon("img/question.png"));
        jMenuItemQ.setMnemonic(KeyEvent.VK_Q);
        jMenuItemQ.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));

        JMenuItem jMenuItemA = new JMenuItem("Panneau Administration ...");
        jMenuItemA.setMnemonic(KeyEvent.VK_A);
        jMenuItemA.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        //Evenement pour acceder au panneau admin
        jMenuItemA.addActionListener((ae) -> {

            JTextField id = new JTextField();
            JTextField motDePasse = new JPasswordField();
            int choix;
            Object[] message = {
                "Identifiant :", id, "Mot de Passe :", motDePasse};
            utilisateur = utilisateurDAO.getAll().get(0);
            choix = JOptionPane.showConfirmDialog(this, message, "Identifiez-vous", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, new ImageIcon("img/lock.png"));

            if (choix == JOptionPane.OK_OPTION) {
                try {
                    if (id.getText().equals(utilisateur.getIdentifiant()) && verify(motDePasse.getText()).equals(utilisateur.getMdp())) {
                        tabbedPane.addTab("Administrateur", admin);
                        tabbedPane.setSelectedComponent(admin);

                    } else {
                        JOptionPane.showMessageDialog(null, "Identifiant ou Mot de passe invalide", "Erreur !", JOptionPane.OK_OPTION);
                    }
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(BasFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        JMenuItem jMdp = new JMenuItem("Modifier le mot de passe ...");
        jMdp.addActionListener((ae) -> {
            // TODO MODIFICATION MOT DE PASSE un jour ...
            JOptionPane.showMessageDialog(null, "EN TRAVAUX !!!");
        });

        JMenuItem jMenuItemQuitter = new JMenuItem("Quitter");
        // Evenement pour quitter l'application
        jMenuItemQuitter.addActionListener((ae) -> {
            int quitter = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir quitter ?",
                    "Quitter l'application",
                    JOptionPane.YES_NO_OPTION);
            if (quitter == JOptionPane.YES_OPTION) {
                dispose();
            }
        });

        menu.add(jMenuItemAc);
        menu.add(jMenuItemD);
        menu.add(jMenuItemC);
        menu.add(jMenuItemQ);
        menu.addSeparator();
        menu.add(menuAdmin);
        menuAdmin.add(jMenuItemA);
        menuAdmin.add(jMdp);
        menu.addSeparator();
        menu.add(jMenuItemQuitter);

    }

    private void creationOnglets() {

        // On instancie les différents JPanel
        //-------------------------------------------------
        // On créer nos onglets
        tabbedPane.addTab("Accueil", accueil);
        tabbedPane.addTab("Dessin", dessin);
        tabbedPane.addTab("Calcul", calcul);
        tabbedPane.addTab("Questions", question);

    }

    public String verify(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        //convertir le tableau de bits en une format hexadécimal - méthode 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        System.out.println("En format hexa : " + sb.toString());

        return sb.toString();
    }
}
