/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.awt.HeadlessException;
import java.sql.*; 
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelos.Usuario;


/**
 *
 * @author Junior de la Rocha
 */
public class TablaUsuario {
    private Conexion conex = new Conexion();
    private Connection conn;
    private PreparedStatement mostrarUsuario;
    private PreparedStatement insertarUsuario;
    private PreparedStatement editarUsuario;
    private PreparedStatement eliminarUsuario;
    
    private List<Usuario> listaU = new ArrayList();

    public TablaUsuario() {
        try{
            conn = conex.obtenerConexion();
            mostrarUsuario = conn.prepareStatement("Select * from Usuario");
            insertarUsuario = conn.prepareStatement("insert into Usuario(username, pw, nombres, apellidos) values(?, ?, ?, ?)");
            editarUsuario = conn.prepareStatement("Update Usuario set pw = ?, nombres = ?, apellidos = ? where id = ?");
            eliminarUsuario = conn.prepareStatement("Delete from Usuario where id = ?");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public TablaUsuario(List<Usuario> lista) {
        listaU = lista;
        try{
            conn = conex.obtenerConexion();
            mostrarUsuario = conn.prepareStatement("Select * from Usuario");
            insertarUsuario = conn.prepareStatement("insert into Usuario(username, pw, nombres, apellidos) values(?, ?, ?, ?)");
            editarUsuario = conn.prepareStatement("Update Usuario set pw = ?, nombres = ?, apellidos = ? where id = ?");
            eliminarUsuario = conn.prepareStatement("Delete from Usuario where id = ?");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        this.listarUsuario();
    }

    public List<Usuario> getListaU() {
        return listaU;
    }

    public void setListaU(List<Usuario> listaU) {
        this.listaU = listaU;
    }
    
    private void listarUsuario(){
        ResultSet rs;
        try{
            rs = this.mostrarUsuario.executeQuery();
            listaU.clear();
            while(rs.next()){
                listaU.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("userName"),
                        rs.getString("pw"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        1
                ));
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public int guardarRegistro(String nameUser, String pw, String nombres, String apellidos){
        int b = 0;
        try{
            this.insertarUsuario.setString(1, nameUser);
            this.insertarUsuario.setString(2, pw);
            this.insertarUsuario.setString(3, nombres);
            this.insertarUsuario.setString(4, apellidos);
            b = this.insertarUsuario.executeUpdate();
       
        }catch(SQLException ex){
            this.conex.close(conn);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return b;
    }
    
    public int editarRegistro(String pw, String nombres, String apellidos, int id){
        int b = 0;
        try{
            this.editarUsuario.setString(1, pw);
            this.editarUsuario.setString(2, nombres);
            this.editarUsuario.setString(3, apellidos);
            this.editarUsuario.setInt(4, id);
            b = this.editarUsuario.executeUpdate();
        }catch(SQLException ex){
            this.conex.close(conn);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return b;
    }
    
    public int eliminarRegistro(int id){
        int b = 0;
        try{
            this.eliminarUsuario.setInt(1, id);
            b = this.eliminarUsuario.executeUpdate();
        }catch(SQLException ex){
            this.conex.close(conn);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return b;
    }
    
    public void actualizarBD(){
        try{
            int b;
            int contIns = 0, contEdi = 0, contEli = 0, contErr = 0;
            String msn="Operaciones realizadas: \n";
            for(Usuario u: listaU){
                switch(u.getEstado()){
                    case 0:
                        b = this.guardarRegistro(u.getUserName(), u.getPw(), u.getNombres(), u.getApellidos());
                        if (b!=0) contIns++;
                        break;
                    case 1:
                        break;
                    case 2:
                        b = this.editarRegistro(u.getPw(), u.getNombres(), u.getApellidos(), u.getId());
                        if (b!=0) contEdi++;
                        break;
                    case 3:
                        b = this.eliminarRegistro(u.getId());
                        if (b!=0) contEli++;
                        break;
                    default:
                        System.out.println("Estado invalido");
                }
            }
            msn += "Registros insertados: " + contIns +"\nRegistros Editados: "
                    + contEdi +"\nRegistros Eliminados: "+ contEli+".";
            JOptionPane.showMessageDialog(null, "Operación de actualización terminada...\n"+
                    msn, "Taler La Raya", JOptionPane.INFORMATION_MESSAGE);
            this.listarUsuario();
        }catch(HeadlessException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", 
                    JOptionPane.ERROR_MESSAGE);
        }
   }
    
}
