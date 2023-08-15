/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofutbol.entidaddenegocio;

/**
 *
 * @author MINEDUCYT
 */
public class SerieA {
     private int id;
   private int idLigas;
   private int posicion;
   private String equipo;
   private int puntos;
   private Ligas ligas;
   private int top_aux;

    public SerieA() {
    }

    public SerieA(int id, int idLigas, int posicion, String equipo, int puntos, Ligas ligas, int top_aux) {
        this.id = id;
        this.idLigas = idLigas;
        this.posicion = posicion;
        this.equipo = equipo;
        this.puntos = puntos;
        this.ligas = ligas;
        this.top_aux = top_aux;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLigas() {
        return idLigas;
    }

    public void setIdLigas(int idLigas) {
        this.idLigas = idLigas;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Ligas getLigas() {
        return ligas;
    }

    public void setLigas(Ligas ligas) {
        this.ligas = ligas;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }
   
}
