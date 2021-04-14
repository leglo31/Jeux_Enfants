/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import administration.Admin;
import static dao.DAO.connection;
import entity.Question;
import entity.Utilisateur;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BsT
 */
public class UtilisateurDAO implements DAO<Utilisateur> {

    List<Utilisateur> listeUtilisateurs = new ArrayList<>();
    Statement stmt;
    PreparedStatement pstmt;
    String reqUtilisateur;
    ResultSet resUtilisateur;

    @Override
    public List<Utilisateur> getAll() {
        try {
            stmt = connection.createStatement();
            reqUtilisateur = "SELECT * FROM Utilisateur";
            resUtilisateur = stmt.executeQuery(reqUtilisateur);

            while (resUtilisateur.next()) {

                Utilisateur utilisateur = new Utilisateur(resUtilisateur.getString("identifiant"),
                        resUtilisateur.getString("mdp"));

                listeUtilisateurs.add(utilisateur);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listeUtilisateurs;

    }

    @Override
    public void update(Utilisateur object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Utilisateur object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
