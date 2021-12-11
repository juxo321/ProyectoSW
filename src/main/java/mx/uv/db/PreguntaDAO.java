package mx.uv.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PreguntaDAO {
    private Conexion conexion = new Conexion();

    public String insertarPregunta(Pregunta pregunta) {
        Connection conn = null;
        PreparedStatement prestm = null;
        String msj = "";

        conn = conexion.getConnection();
        try {
            String sql = "INSERT INTO pregunta (noPregunta, noExamen , interrogante, respuesta, opcion1, opcion2, opcion3, opcion4, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            prestm = conn.prepareStatement(sql);
            prestm.setInt(1, pregunta.getNoPregunta());
            prestm.setInt(2, pregunta.getNoExamen());
            prestm.setString(3, pregunta.getInterrogante());
            prestm.setString(4, pregunta.getRespuesta());
            prestm.setString(5, pregunta.getOpcion1());
            prestm.setString(6, pregunta.getOpcion2());
            prestm.setString(7, pregunta.getOpcion3());
            prestm.setString(8, pregunta.getOpcion4());
            prestm.setString(9, pregunta.getTipo());
            if (prestm.executeUpdate() >0) 
                msj = "Pregunta creada";
            else
                msj = "Error al crear pregunta";
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (prestm != null){
                try {
                    prestm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return msj;
    }

    public List<Pregunta> listaPreguntasExamen(int noExamen) {
        Statement stm = null;
        ResultSet rs = null;
        Connection conn = null;
        List<Pregunta> listaPreguntasExamen= new ArrayList<>(); 

        conn = conexion.getConnection();
        try {
            String sql = "SELECT * FROM pregunta where noExamen ="+noExamen+" order by noPregunta";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()){
                Pregunta pregunta = new Pregunta(rs.getInt("noPregunta"), rs.getInt("noExamen"), rs.getString("interrogante"), rs.getString("respuesta"),rs.getString("opcion1"),rs.getString("opcion2"),rs.getString("opcion3"),rs.getString("opcion4"),rs.getString("tipo"));
                listaPreguntasExamen.add(pregunta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (stm != null){
                try {
                    stm.close();
                } catch (SQLException e) {
                    stm = null;
                    e.printStackTrace();
                }
            }
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    rs = null;
                    e.printStackTrace();
                }
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listaPreguntasExamen;
    }
}
