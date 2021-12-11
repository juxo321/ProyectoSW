package mx.uv.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExamenDAO {
    private Conexion conexion = new Conexion();

    public String insertarExamen(Examen examen) {
        Connection conn = null;
        PreparedStatement prestm = null;
        String msj = "";

        conn = conexion.getConnection();
        try {
            String sql = "INSERT INTO examen (noExamen , nombreExamen, materia, calificacion, numeroPreguntas) VALUES (?, ?, ?, ?, ?)";
            prestm = conn.prepareStatement(sql);
            prestm.setInt(1, examen.getNoExamen());
            prestm.setString(2, examen.getNombreExamen());
            prestm.setString(3, examen.getMateria());
            prestm.setDouble(4, examen.getCalificacion());
            prestm.setInt(5, examen.getNumeroPreguntas());
            if (prestm.executeUpdate() >0) 
                msj = "Examen creado";
            else
                msj = "Error al crear examen";
            
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


    public List<Examen> listaExamenes() {
        Statement stm = null;
        ResultSet rs = null;
        Connection conn = null;
        List<Examen> listaExamenes = new ArrayList<>(); 

        conn = conexion.getConnection();
        try {
            String sql = "SELECT * FROM examen";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()){
                Examen examen = new Examen(rs.getInt("noExamen"), rs.getString("nombreExamen"), rs.getString("materia"), rs.getDouble("calificacion"),rs.getInt("numeroPreguntas"));
                listaExamenes.add(examen);
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
        return listaExamenes;
    }

    public String borrarExamen(int noExamen) {
        Connection conn = null;
        PreparedStatement prestm = null;
        String sql = "DELETE FROM examen where noExamen=?";
        String msj = "";

        conn = conexion.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, noExamen);
            if (stmt.executeUpdate()>0){
                conn.close();
                msj = "Examen eliminado correctamente";
            }else{
                msj = "Error al eliminar el examen";
            }
        }  catch (Exception e) {
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


    public int ultimoExamen() {
        Statement stm = null;
        ResultSet rs = null;
        Connection conn = null;
        int ultimoExamen = 0;

        conn = conexion.getConnection();
        try {
            String sql = "SELECT * FROM examen WHERE noExamen=(SELECT max(noExamen) FROM examen);";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()){
                 ultimoExamen = rs.getInt("noExamen");
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
        return ultimoExamen;
    }
}
