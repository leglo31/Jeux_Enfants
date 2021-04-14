package entity;

/**
 *
 * @author BsT
 */
public class Question {

    public Question(String question, String reponse) {
        this.question = question;
        this.reponse = reponse;
    }

    private Integer id, niveau;
    private String question, reponse;

    public Question(Integer niveau, String question, String reponse) {
        this.niveau = niveau;
        this.question = question;
        this.reponse = reponse;
    }

    public Question() {

    }

    public Question(Integer id, Integer niveau, String question, String reponse) {
        this.id = id;
        this.niveau = niveau;
        this.question = question;
        this.reponse = reponse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    @Override
    public String toString() {
        return "Question numéro =" + id + ", niveau=" + niveau
                + ", question=" + question + ", reponse=" + reponse;
    }

}
