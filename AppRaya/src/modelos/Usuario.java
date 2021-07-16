/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Junior de la Rocha
 */
public class Usuario {
    private int id;
    private String userName;
    private String pw;
    private String nombres;
    private String apellidos;
    private int estado;

    public Usuario() {
    }

    public Usuario(int id, String userName, String pw, String nombres, String apellidos, int estado) {
        this.id = id;
        this.userName = userName;
        this.pw = pw;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.estado = estado;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", userName=" + userName + ", pw=" + pw + ", nombres=" + nombres + ", apellidos=" + apellidos + ", estado=" + estado + '}';
    }
      
    public boolean verificarCodigo(int codigo){
        return this.id == codigo;
    }
    
    public boolean verficarUserName(String user, String pw){
        return ((this.userName.equals(user))&&(this.pw.equals(pw)));
    }
    
   
    
}
