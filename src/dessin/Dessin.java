/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dessin;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.MatteBorder;

/**
 *
 * @author Solene <your.name at your.org>
 */
public class Dessin extends JPanel {

    // Attributs d'instance
    int x = 0, y = 0;
    JLabel label2 = new JLabel(); // getters, setters
    JPanel ardoise = new JPanel();
    JPanel dessin = new JPanel();
    Color color = Color.black;
    Color c = new Color(70, 130, 180);

    // Pour la palette de couleurs
    private JColorChooser chooser = new JColorChooser();

    public Dessin() {

        this.setLayout(new BorderLayout()); // this correspond au constructeur Dessin

        //Panel titre
        JPanel titre = new JPanel(); // Titre "Dessin"
        titre.setLayout(new FlowLayout());

        this.add(titre, BorderLayout.NORTH);
        JLabel labtitre = new JLabel("DESSIN");
        titre.add(labtitre);
//        labtitre.setSize(100, 200); // Taille de la zone labtitre

        Font font = new Font(Font.SERIF, Font.BOLD, 30);
        labtitre.setFont(font);
        titre.setBorder(BorderFactory.createLineBorder(Color.black));
        labtitre.setForeground(Color.black);
        labtitre.setHorizontalAlignment(JLabel.CENTER);
        labtitre.setBackground(Color.blue);

        // Boite à outils
        JToolBar toolBar = new JToolBar("Dessin");
        this.add(toolBar, BorderLayout.SOUTH);

        JButton beff = new JButton(new ImageIcon("img/new3.png"));
        //beff.setForeground(Color.DARK_GRAY);
        JButton brouge = new JButton(new ImageIcon("img/red.png"));
        JButton bbleu = new JButton(new ImageIcon("img/blue.png"));
        JButton bvert = new JButton(new ImageIcon("img/green.png"));
        JButton bgris = new JButton(new ImageIcon("img/gray.jpeg"));
        JButton bjaune = new JButton(new ImageIcon("img/yellow.jpg"));
        JButton borange = new JButton(new ImageIcon("img/orange.jpg"));
        JButton bviolet = new JButton(new ImageIcon("img/purple.jpg"));
        JButton brose = new JButton(new ImageIcon("img/pink.jpg"));
        JButton bbleuclair = new JButton(new ImageIcon("img/lightblue.jpeg"));
        JButton bnoir = new JButton(new ImageIcon("img/black.png"));
        JButton bcarre = new JButton(new ImageIcon("img/carre.png"));
        JButton brond = new JButton(new ImageIcon("img/rond.png"));
        JButton palette = new JButton(new ImageIcon("img/arcoiris.jpg"));

        toolBar.add(beff);
        toolBar.add(bjaune);
        toolBar.add(borange);
        toolBar.add(brouge); // ajouter bouton à la barre d'outils
        toolBar.add(bvert);
        toolBar.add(bbleuclair);
        toolBar.add(bbleu);
        toolBar.add(bgris);
        toolBar.add(bviolet);
        toolBar.add(brose);
        toolBar.add(bnoir);
        toolBar.add(palette);
        toolBar.add(bcarre);
        toolBar.add(brond);

        // Création d'un écouteur sur les différents boutons pour le changement de couleur
        // Le bouton bleu clair ne s'affiche pas
        brouge.addActionListener((ActionEvent ae) -> {
            color = Color.RED;
        });

        bbleu.addActionListener((ActionEvent ae) -> {
            color = Color.BLUE;
        });

        bvert.addActionListener((ActionEvent ae) -> {
            color = Color.GREEN;
        });

        bgris.addActionListener((ActionEvent ae) -> {
            color = Color.GRAY;
        });

        bjaune.addActionListener((ActionEvent ae) -> {
            color = Color.YELLOW;
        });

        borange.addActionListener((ActionEvent ae) -> {
            color = Color.ORANGE;
        });

        bviolet.addActionListener((ActionEvent ae) -> {
            color = Color.MAGENTA;
        });

        brose.addActionListener((ActionEvent ae) -> {
            color = Color.PINK;
        });

        bbleuclair.addActionListener((ActionEvent ae) -> {
            color = Color.CYAN;
        });

        bnoir.addActionListener((ActionEvent ae) -> {
            color = Color.BLACK;
        });

        //
        beff.addActionListener((ActionEvent ae) -> {
            repaint();
        });

        // Ardoise magique
        // Bordure Ardoise
        dessin
                .setBackground(Color.WHITE);
        //dessin.getBorder();
        //dessin.setBorder(BorderFactory.createLineBorder(Color.black));
        dessin.setBorder(new MatteBorder(4, 4, 4, 4, Color.orange));

        dessin.setPreferredSize(
                new Dimension(400, 400));

        this.add(ardoise, BorderLayout.CENTER);

        ardoise.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e
            ) {

                x = e.getX();
                y = e.getY();
                setLabel(x, y);
            }
        }
        );
        ardoise.addMouseMotionListener(
                new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e
            ) {
                Graphics2D g = (Graphics2D) dessin.getGraphics();
                g.setColor(color);
                // Pour trait rond/carré
                // Cf pour Action Listener (boutons pour trait rond/carré)
                g.setStroke(new BasicStroke(15, BasicStroke.CAP_ROUND, BasicStroke.CAP_SQUARE));
                g.drawLine(x, y, e.getX(), e.getY());
                x = e.getX();
                y = e.getY();
                setLabel(x, y);
            }
        }
        );

        // Ecouteur du bouton de la palette de couleurs
        palette.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                color = JColorChooser.showDialog(chooser,
                        "Plus de couleurs :", color);
                palette.setBackground(color);
            }
        });
        setLabel(x, y);

        ardoise.setLayout(
                new BorderLayout());
        ardoise.add(label2, BorderLayout.SOUTH);

        ardoise.add(dessin, BorderLayout.CENTER);

    }

    private void setLabel(int x, int y) {
        label2.setText("x=" + x + ", y=" + y);
    }

    class CouleurListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

}
