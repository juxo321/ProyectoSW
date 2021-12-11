package mx.uv.db;

public class Examen {
    private int noExamen;
	private String nombreExamen;
    private String materia;
    private double calificacion;
    private int numeroPreguntas;

    public Examen() {
    }


    public Examen(int noExamen, String nombreExamen, String materia, double calificacion, int numeroPreguntas) {
        this.noExamen = noExamen;
        this.nombreExamen = nombreExamen;
        this.materia = materia;
        this.calificacion = 0;
        this.numeroPreguntas = 0;
    }
    

    public int getNoExamen() {
        return this.noExamen;
    }

    public void setNoExamen(int noExamen) {
        this.noExamen = noExamen;
    }

    public String getNombreExamen() {
        return this.nombreExamen;
    }

    public void setNombreExamen(String nombreExamen) {
        this.nombreExamen = nombreExamen;
    }

    public String getMateria() {
        return this.materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public double getCalificacion() {
        return this.calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public int getNumeroPreguntas() {
        return this.numeroPreguntas;
    }

    public void setNumeroPreguntas(int numeroPreguntas) {
        this.numeroPreguntas = numeroPreguntas;
    }



    @Override
    public String toString() {
        return "{" +
            " noExamen='" + getNoExamen() + "'" +
            ", nombreExamen='" + getNombreExamen() + "'" +
            ", materia='" + getMateria() + "'" +
            ", calificacion='" + getCalificacion() + "'" +
            ", numeroPreguntas='" + getNumeroPreguntas() + "'" +
            "}";
    }
    

}
