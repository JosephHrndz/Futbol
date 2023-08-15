/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofutbol.entidaddenegocio;

/**
 *
 * @author MINEDUCYT
 */
public class Resultados {
   private int id;
   private String equipo1;
   private String resultadoDeportes;
   private String equipo2;
   private int top_aux;

    public Resultados() {
    }

    public Resultados(int id, String equipo1, String resultadoDeportes, String equipo2, int top_aux) {
        this.id = id;
        this.equipo1 = equipo1;
        this.resultadoDeportes = resultadoDeportes;
        this.equipo2 = equipo2;
        this.top_aux = top_aux;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(String equipo1) {
        this.equipo1 = equipo1;
    }

    public String getResultadoDeportes() {
        return resultadoDeportes;
    }

    public void setResultadoDeportes(String resultadoDeportes) {
        this.resultadoDeportes = resultadoDeportes;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(String equipo2) {
        this.equipo2 = equipo2;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }
   
}
