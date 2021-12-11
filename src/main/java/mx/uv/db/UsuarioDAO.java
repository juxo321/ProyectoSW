package mx.uv.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

public class UsuarioDAO {
    private Conexion conexion = new Conexion();


    public int validarUsuario(Usuario usuario) throws Exception{
        Statement stm;
        ResultSet usuarios;
        String sql = "select * from usuario;";
        
        try (Connection conn = conexion.getConnection();){
            stm = conn.createStatement();
            usuarios = stm.executeQuery(sql);
            while (usuarios.next()){
                if(usuarios.getString("usuario").equals(usuario.getUsuario()) && usuarios.getInt("pass") == Integer.parseInt(usuario.getPass())){
                    if(usuarios.getInt("tipo")==1){
                        return 1;
                    }else if(usuarios.getInt("tipo")==0){
                        return 0;
                    }
                }
            }
        }catch (SQLException e) {
            throw new Exception("Error en readAll SQLException " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error en readAll Exception " + e.getMessage());
        }
        return -1;
    }


    public String insertarUsuario(Usuario usuario) {
        Connection conn = null;
        PreparedStatement prestm = null;
        String msj = "";

        conn = conexion.getConnection();
        try {
            String sql = "INSERT INTO usuario (noUsuario, usuario, pass, tipo) VALUES (?, ?, ?, ?)";
            prestm = conn.prepareStatement(sql);
            prestm.setInt(1, usuario.getNoUsuario());
            prestm.setString(2, usuario.getUsuario());
            prestm.setString(3, usuario.getPass());
            prestm.setInt(4, usuario.getTipo());
            if (prestm.executeUpdate() >0) 
                msj = "Usuario agregado";
            else
                msj = "Erros al agregar usuario";
            
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

    /*

    public List<Usuario> listadoUsuario() {
        Statement stm = null;
        ResultSet rs = null;
        Connection conn = null;
        List<Usuario> resultado = new ArrayList<>(); 

        conn = conexion.getConnection();
        try {
            String sql = "SELECT * FROM usuario";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()){
                Usuario u = new Usuario(rs.getString("id"), rs.getString("email"), rs.getString("password"));
                resultado.add(u);
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
        return resultado;
    }

    public String eliminarUsuario (String email){
        Connection conn = null;
        PreparedStatement prestm = null;
        String msj = "";

        conn = conexion.getConnection();
        try {
            String sql = "delete from usuario where email = ?";
            prestm = conn.prepareStatement(sql);
            prestm.setString(1, email);
    
            if (prestm.executeUpdate() >0) 
                msj = "Usuario eliminado";
            else
                msj = "No se pudo eliminar el usuario";
            
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

    public String actualizarUsuario(String antiguoEmail, String nuevoEmail, String nuevoPassword){
        Connection conn = null;
        PreparedStatement prestm = null;
        String msj = "";

        conn = conexion.getConnection();
        try {
            String sql = "UPDATE usuario SET email = ?, password = ? WHERE email = ?";
            prestm = conn.prepareStatement(sql);
            prestm.setString(1, nuevoEmail);
            prestm.setString(2, nuevoPassword);
            prestm.setString(3, antiguoEmail);
            if (prestm.executeUpdate() >0) 
                msj = "Usuario actualizado";
            else
                msj = "No se puedo actualizar el usuario";
            
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
    }*/
}
