/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofutbol.entidaddenegocio;

/**
 *
 * @author MINEDUCYT
 */
public class Equipos {
 private int idEquipos;
 private String nombreEquipo;
 private String paisEquipo;
 private int idLigas;
 private int top_aux;

    public Equipos() {
    }

    public Equipos(int idEquipos, String nombreEquipo, String paisEquipo, int idLigas, int top_aux) {
        this.idEquipos = idEquipos;
        this.nombreEquipo = nombreEquipo;
        this.paisEquipo = paisEquipo;
        this.idLigas = idLigas;
        this.top_aux = top_aux;
    }

    public int getIdEquipos() {
        return idEquipos;
    }

    public void setIdEquipos(int idEquipos) {
        this.idEquipos = idEquipos;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getPaisEquipo() {
        return paisEquipo;
    }

    public void setPaisEquipo(String paisEquipo) {
        this.paisEquipo = paisEquipo;
    }

    public int getIdLigas() {
        return idLigas;
    }

    public void setIdLigas(int idLigas) {
        this.idLigas = idLigas;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }
         
   
}
