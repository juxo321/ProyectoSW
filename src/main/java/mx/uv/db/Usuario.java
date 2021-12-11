package mx.uv.db;

// DTO
public class Usuario {
    private int noUsuario;
    private String usuario;
    private String pass;
    private int tipo;


    public Usuario(int noUsuario, String usuario, String pass, int tipo) {
        this.noUsuario = noUsuario;
        this.usuario = usuario;
        this.pass = pass;
        this.tipo = tipo;
    }


    public int getNoUsuario() {
        return this.noUsuario;
    }

    public void setNoUsuario(int noUsuario) {
        this.noUsuario = noUsuario;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getTipo() {
        return this.tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }


    @Override
    public String toString() {
        return "{" +
            " noUsuario='" + getNoUsuario() + "'" +
            ", usuario='" + getUsuario() + "'" +
            ", pass='" + getPass() + "'" +
            ", tipo='" + getTipo() + "'" +
            "}";
    }
  
    
}
