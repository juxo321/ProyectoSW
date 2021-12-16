package mx.uv.db;

public class Respuesta {
    private int noRespuesta;
    private int noPregunta;
    private int noExamen;
    private String respuesta;


    public Respuesta() {
    }


    public Respuesta(int noRespuesta, int noPregunta, int noExamen, String respuesta) {
        this.noRespuesta = noRespuesta;
        this.noPregunta = noPregunta;
        this.noExamen = noExamen;
        this.respuesta = respuesta;
    }


    public int getNoRespuesta() {
        return this.noRespuesta;
    }

    public void setNoRespuesta(int noRespuesta) {
        this.noRespuesta = noRespuesta;
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

    public String getRespuesta() {
        return this.respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return "{" +
            " noRespuesta='" + getNoRespuesta() + "'" +
            ", noPregunta='" + getNoPregunta() + "'" +
            ", noExamen='" + getNoExamen() + "'" +
            ", respuesta='" + getRespuesta() + "'" +
            "}";
    }


}
