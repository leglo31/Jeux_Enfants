package dao;

import administration.Admin;
import entity.Question;
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
public class QuestionDAO implements DAO<Question> {

    public List<Question> listeQuestions = new ArrayList<>();
    Statement stmt;
    PreparedStatement pstmt;
    String reqQuestion;
    ResultSet resQuestion;

    @Override
    public List<Question> getAll() {
        try {
            stmt = connection.createStatement();
            reqQuestion = "SELECT * FROM Questions";
            resQuestion = stmt.executeQuery(reqQuestion);

            while (resQuestion.next()) {

                Question question = new Question(resQuestion.getInt("id"), resQuestion.getInt("niveau"),
                                                 resQuestion.getString("question"), resQuestion.getString("reponse"));

                listeQuestions.add(question);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listeQuestions;
    }

    @Override
    public void update(Question q) {
        try {
            reqQuestion = "UPDATE Questions SET niveau= ?, question= ?, reponse= ? WHERE id = ?";
            pstmt = connection.prepareStatement(reqQuestion);
            pstmt.setInt(1, q.getNiveau());
            pstmt.setString(2, q.getQuestion());
            pstmt.setString(3, q.getReponse());
            pstmt.setInt(4, q.getId());
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void create(Question q) {
        try {
            reqQuestion = "INSERT INTO Questions (question, reponse, niveau) VALUES (?,?,?)";
            pstmt = connection.prepareStatement(reqQuestion);
            pstmt.setString(1, q.getQuestion());
            pstmt.setString(2, q.getReponse());
            pstmt.setInt(3, q.getNiveau());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            reqQuestion = "DELETE FROM Questions WHERE id=?";
            pstmt = connection.prepareStatement(reqQuestion);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
