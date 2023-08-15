/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofutbol.entidaddenegocio;

/**
 *
 * @author MINEDUCYT
 */
public class Ligas {
    private int idLigas;
    private String nombreLiga;
    private String paisLiga;
    private int top_aux;

    public Ligas() {
    }

    public Ligas(int idLigas, String nombreLiga, String paisLiga, int top_aux) {
        this.idLigas = idLigas;
        this.nombreLiga = nombreLiga;
        this.paisLiga = paisLiga;
        this.top_aux = top_aux;
    }

    public int getIdLigas() {
        return idLigas;
    }

    public void setIdLigas(int idLigas) {
        this.idLigas = idLigas;
    }

    public String getNombreLiga() {
        return nombreLiga;
    }

    public void setNombreLiga(String nombreLiga) {
        this.nombreLiga = nombreLiga;
    }

    public String getPaisLiga() {
        return paisLiga;
    }

    public void setPaisLiga(String paisLiga) {
        this.paisLiga = paisLiga;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }
    
    
}
