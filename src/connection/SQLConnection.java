package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BsT
 */
public class SQLConnection {

    private final static String HOST = "172.16.0.48"; // L'ip de l'hôte;
    private final static String PORT = "3306"; // Le port de connexion, 3306 par défaut sur MySQL;
    private final static String DATABASE = "2020devJSEg1"; // La base de données sur laquelle se connecter;
    private final static String LOGIN = "2020devJSEg1"; // Le login;
    private final static String PASSWORD = "Angela"; // Le mot de passe;
    private static Connection connection = null;
    private final static String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;

    private SQLConnection() {
    }

    public static Connection getInstance() {

        try {
            if (connection == null) {
                System.out.println("Connction à la base de données" + DATABASE + "avec l'url" + URL);
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

}
