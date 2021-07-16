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
import modelos.Cliente;
import modelos.Usuario;

/**
 *
 * @author Junior de la Rocha
 */
public class TablaCliente {
    
    private Conexion conex = new Conexion() ;
    private Connection conn;
    private PreparedStatement mostrarCliente;
    private PreparedStatement insertarCliente;
    private PreparedStatement editarCliente;
    private PreparedStatement eliminarCliente;
    
    private List<Cliente> listaC = new ArrayList();
    
    
    
    
     public TablaCliente(){
        
          try{
            conn = conex.obtenerConexion();
            mostrarCliente = conn.prepareStatement("Select * from Cliente");
            insertarCliente = conn.prepareStatement("insert into Cliente(cedular,nombre,apellidos,celular) values(?, ?, ?, ?)");
            editarCliente = conn.prepareStatement("Update Cliente set cedular = ?, nombre = ?,apellidos= ?,celular=? where id = ?");
            eliminarCliente = conn.prepareStatement("Delete from Cliente where id = ?");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
}
     
      public TablaCliente(List<Cliente> lista) {
        listaC = lista;
        try{
            conn = conex.obtenerConexion();
            mostrarCliente = conn.prepareStatement("Select * from Cliente");
            insertarCliente = conn.prepareStatement("insert into Cliente(cedular,nombre,apellidos,celular) values(?, ?, ?, ?)");
            editarCliente = conn.prepareStatement("Update Cliente set cedular = ?, nombre = ?,apellidos= ?,celular=? where id = ?");
            eliminarCliente = conn.prepareStatement("Delete from Cliente where id = ?");
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        this.listarCliente();
        
        
        
    }
     
    
      
   public List<Cliente> getListaC() {
        return listaC;
    }

    public void setListaC(List<Cliente> listaC) {
        this.listaC = listaC;
    }
      
    
    
    private void listarCliente(){
        ResultSet rs;
        try{
            rs = this.mostrarCliente.executeQuery();
            listaC.clear();
            while(rs.next()){
                listaC.add(new Cliente 
                                       (
                        rs.getInt("id"),
                        rs.getString("cedular"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("celular"),
                                               
                        1
                ));
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
      
       public int guardarRegistro(int id, String cedula, String nombre, String apellido,String celular){
        int b = 0;
        try{
                  this.insertarCliente.setInt(1, id);
                  this.insertarCliente.setString(2, cedula);
                  this.insertarCliente.setString(3, nombre );
                  this.insertarCliente.setString(4, apellido );
                  this.insertarCliente.setString(5, celular);
                   b = this.insertarCliente.executeUpdate();
       
        }catch(SQLException ex){
            this.conex.close(conn);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return b;
    }
           
             public int editarRegistro(int id, String cedular, String nombre, String apellido,String celular){
        int b = 0;
        try{
            
            this.editarCliente.setInt(1, id);
            this.editarCliente.setString(1, cedular);
            this.editarCliente.setString(2, nombre );
            this.editarCliente.setString(3, apellido );
            this.editarCliente.setString(5, celular);
            b = this.editarCliente.executeUpdate();
          
        }catch(SQLException ex){
            this.conex.close(conn);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return b;
    }
             
                 public int eliminarRegistro(int id){
        int b = 0;
        try{
            this.eliminarCliente.setInt(1, id);
            b = this.eliminarCliente.executeUpdate();
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
            for(Cliente C: listaC){
                switch(C.getEstado()){
                    case 0:
                        b = this.guardarRegistro(C.getId(),C.getCedular(),C.getNombre(),C.getApellido(),C.getCelular());
                        if (b!=0) contIns++;
                        break;
                    case 1:
                        break;
                    case 2:
                        b = this.editarRegistro(C.getId(),C.getCedular(), C.getNombre(), C.getApellido(),C.getCelular());
                        if (b!=0) contEdi++;
                        break;
                    case 3:
                        b = this.eliminarRegistro(C.getId());
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
            this.listarCliente();
        }catch(HeadlessException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", 
                    JOptionPane.ERROR_MESSAGE);
        }
   }
    
                 
}





