package mx.uv.db;

public class Pregunta {
    private int noPregunta;
    private int noExamen;
    private String interrogante;
    private String respuesta;
    private String opcion1;
    private String opcion2;
    private String opcion3;
    private String opcion4;
    private String tipo;



    public Pregunta() {
    }


    public Pregunta(int noPregunta, int noExamen, String interrogante, String respuesta, String opcion1, String opcion2, String opcion3, String opcion4, String tipo) {
        this.noPregunta = noPregunta;
        this.noExamen = noExamen;
        this.interrogante = interrogante;
        this.respuesta = respuesta;
        this.opcion1 = opcion1;
        this.opcion2 = opcion2;
        this.opcion3 = opcion3;
        this.opcion4 = opcion4;
        this.tipo = tipo;
    }

    public int getNoPregunta() {
        return this.noPregunta;
    }

    public void setNoPregunta(int noPregunta) {
        this.noPregunta = noPregunta;
    }

    public int getNoExamen() {
        return this.noExamen;
    }

    public void setNoExamen(int noExamen) {
        this.noExamen = noExamen;
    }

    public String getInterrogante() {
        return this.interrogante;
    }

    public void setInterrogante(String interrogante) {
        this.interrogante = interrogante;
    }

    public String getRespuesta() {
        return this.respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getOpcion1() {
        return this.opcion1;
    }

    public void setOpcion1(String opcion1) {
        this.opcion1 = opcion1;
    }

    public String getOpcion2() {
        return this.opcion2;
    }

    public void setOpcion2(String opcion2) {
        this.opcion2 = opcion2;
    }

    public String getOpcion3() {
        return this.opcion3;
    }

    public void setOpcion3(String opcion3) {
        this.opcion3 = opcion3;
    }

    public String getOpcion4() {
        return this.opcion4;
    }

    public void setOpcion4(String opcion4) {
        this.opcion4 = opcion4;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    @Override
    public String toString() {
        return "{" +
            " noPregunta='" + getNoPregunta() + "'" +
            ", noExamen='" + getNoExamen() + "'" +
            ", interrogante='" + getInterrogante() + "'" +
            ", respuesta='" + getRespuesta() + "'" +
            ", opcion1='" + getOpcion1() + "'" +
            ", opcion2='" + getOpcion2() + "'" +
            ", opcion3='" + getOpcion3() + "'" +
            ", opcion4='" + getOpcion4() + "'" +
            ", tipo='" + getTipo() + "'" +
            "}";
    }

    
    
}
