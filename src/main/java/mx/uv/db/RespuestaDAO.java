package mx.uv.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class RespuestaDAO {
    private Conexion conexion = new Conexion();

    public String insertarRespuesta(Respuesta respuesta) {
        Connection conn = null;
        PreparedStatement prestm = null;
        String msj = "";

        conn = conexion.getConnection();
        try {
            String sql = "INSERT INTO respuesta (noRespuesta, noPregunta , noExamen, respuesta) VALUES (?, ?, ?, ?)";
            prestm = conn.prepareStatement(sql);
            prestm.setInt(1, respuesta.getNoRespuesta());
            prestm.setInt(2, respuesta.getNoPregunta());
            prestm.setInt(3, respuesta.getNoExamen());
            prestm.setString(4, respuesta.getRespuesta());
            if (prestm.executeUpdate() >0) 
                msj = "Respuesta creada";
            else
                msj = "Error al crear respuesta";
            
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
}
