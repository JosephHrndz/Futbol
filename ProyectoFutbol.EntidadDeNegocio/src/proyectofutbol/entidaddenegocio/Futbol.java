/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofutbol.entidaddenegocio;

/**
 *
 * @author MINEDUCYT
 */
public class Futbol {
    private int idFutbol;
    private String titulo;
    private String noticias;
    private int idLigas;
    private int idUsuario;
    private int top_aux;

    public Futbol() {
    }

    public Futbol(int idFutbol, String titulo, String noticias, int idLigas, int idUsuario, int top_aux) {
        this.idFutbol = idFutbol;
        this.titulo = titulo;
        this.noticias = noticias;
        this.idLigas = idLigas;
        this.idUsuario = idUsuario;
        this.top_aux = top_aux;
    }

    public int getIdFutbol() {
        return idFutbol;
    }

    public void setIdFutbol(int idFutbol) {
        this.idFutbol = idFutbol;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNoticias() {
        return noticias;
    }

    public void setNoticias(String noticias) {
        this.noticias = noticias;
    }

    public int getIdLigas() {
        return idLigas;
    }

    public void setIdLigas(int idLigas) {
        this.idLigas = idLigas;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }
    
}
