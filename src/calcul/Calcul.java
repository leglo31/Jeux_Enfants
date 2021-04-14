/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcul;

import java.util.Random;

/**
 *
 * @author laurent
 */
public class Calcul {

//déclaration des variables
    int nbr1;
    int nbr2;
    char operation;

//GETTERS/SETTERS
    public int getNbr1() {
        return nbr1;
    }

    public void setNbr1(int nbr1) {
        this.nbr1 = nbr1;
    }

    public int getNbr2() {
        return nbr2;
    }

    public void setNbr2(int nbr2) {
        this.nbr2 = nbr2;
    }

    public char getOperation() {
        return operation;
    }

    public void setOperation(char operation) {
        this.operation = operation;
    }
//FIN GETTERS/SETTERS

    //fonction ToString qui retourne LE CALCUL
    //condition pour pour eviter un resultat négatif
    //nbr 2 toujours superieur au nbr1 en cas de soustraction
    @Override
    public String toString() {
        return "" + nbr1 + operation + nbr2;
    }

    //CONSTRUCTEUR avec égalité des variables avec la méthode Random
    public Calcul(int niveau) {
        if (niveau == 1) {
            this.nbr1 = RandomChiffres(0, 9);//genère chiffre 0/9
            this.operation = RandomOperation("+-");//genère operation + ou -
            this.nbr2 = RandomChiffres(0, 9);
            if ((operation == '-') && (nbr2 > nbr1)) {//double condition
                // on dit que nbr2 prend la place de nbr1
                //on ne veut pas de résultat négatif style 3-4
                int a = nbr1;
                nbr1 = nbr2;
                nbr2 = a;
            }
        } else {
            this.operation = RandomOperation("+-*/");
            if (operation == '*') {
                //génère chiffres entre 0/9
                this.nbr1 = RandomChiffres(0, 9);
                this.nbr2 = RandomChiffres(0, 9);
                //pour la division génère un diviseur 0/3 et un quotient 1/3
            } else if (operation == '/') {
                this.nbr1 = RandomChiffres(0, 3);
                this.nbr2 = RandomChiffres(1, 3);
                //sinon pour opération + et - chiffres 0/999
            } else {
                this.nbr1 = RandomChiffres(0, 999);
                this.nbr2 = RandomChiffres(0, 999);
            }

        }
    }

    //méthode random pour définir un chiffre aléatoire compris entre min et max qui retourne un int nbr
    //cette méthode sera utilisée par le constructeur Calcul()
    //on pourra rentrer dans le constructeur le mini et maxi
    private int RandomChiffres(int min, int max) {
        Random obj = new Random();
        int nbr = obj.nextInt(max - min) + min;
        return nbr;
    }

    //idem pour l'opération avec une condition pour obtenir soit un +, -, *, /
    //s.lenght permet de connaitre la longueur : si o par exemple alors +
    //permet de recuperer un caractère dans un rang aléatoire
    private char RandomOperation(String s) {
        Random obj1 = new Random();
        int rang = obj1.nextInt(s.length());
        return s.charAt(rang);
    }

    //méthode pour générer le calcul (si signe + alors on a nbr1 + nbr2....)
    //RETOURNE LE RESULTAT pour chaque condition
    public Integer Resultat() {

        if (operation == '+') {
            return nbr1 + nbr2;
        } else if (operation == '-') {
            return nbr1 - nbr2;
        } else if (operation == '*') {
            return nbr1 * nbr2;
        } else {
            return nbr1 / nbr2;
        }
    }

}
