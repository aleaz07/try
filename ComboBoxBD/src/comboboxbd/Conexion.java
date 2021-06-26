package comboboxbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Somos Programadores
 * https://www.facebook.com/developers08062019/
 */
public class Conexion {

    private static Connection Conection;
    private static Statement Sentencia;
    private static ResultSet Resultado;
    private final String bd;
    private final String user;
    private final String password;

    public Conexion(String bd, String user, String password) {
        Conection = null;
        Sentencia = null;
        Resultado = null;
        this.bd = bd;
        this.user = user;
        this.password = password;
    }

    public boolean conectar() {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            Conection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/" + bd, user, password);
            return Conection != null;
        } catch (Exception e) {
            return false;
        }
    }

    public void desconectar() {
        try {
            this.Conection.close();
        } catch (Exception e) {
        }
    }

    public ArrayList getListaAlumnos() {
        ArrayList mListaAlumnos = new ArrayList();
        alumnos mAlumnos = null;
        Statement consulta;
        ResultSet resultado;
        try {
            consulta = Conection.createStatement();
            resultado = consulta.executeQuery("select * from alumnos");

            while (resultado.next()) {
                mAlumnos = new alumnos();
                mAlumnos.setId(resultado.getInt("id"));
                mAlumnos.setNombre(resultado.getString("nombre"));
                mListaAlumnos.add(mAlumnos);
            }
        } catch (SQLException e) {
        }
        return mListaAlumnos;
    }

}
