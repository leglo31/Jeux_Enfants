package question;

import dao.QuestionDAO;
import entity.Question;
import java.util.Random;

/**
 * @author Vincent
 */
public class QuestionFonction extends QuestionDAO {

    String questionUtil, reponseUtil;
    int niveauUtil, niveau = 1;

    Question questionObjet;

    // FONCTION NOUVELLE QUESTION =================================================
    public void nouvelleQuestion() {
        getAll();
        Random random = new Random();
        do {
            questionObjet = listeQuestions.get(random.nextInt(listeQuestions.size()));
            niveauUtil = questionObjet.getNiveau();
        }
        while (niveauUtil != niveau);
        questionUtil = questionObjet.getQuestion();
        reponseUtil = questionObjet.getReponse();
    }
    //=============================================================================

    // FONCTION FILTRAGE REPONSE ========
    public String reponse() {
        return reponseUtil.toLowerCase()
                .replace("[àâä]", "a")
                .replace("[éèêë]", "e")
                .replace("[îï]", "i")
                .replace("[ôö]", "o")
                .replace("[ùûü]", "u")
                .replace("[ŷÿ]", "y")
                .replace("[ç]", "c");
    }
    //===================================
}
