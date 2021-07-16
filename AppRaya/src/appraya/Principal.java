/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appraya;

import dao.TablaRepuesto;
import dao.TablaUsuario;
import formularios.FrmAutenticar;
import formularios.FrmRepuesto;
import java.util.ArrayList;
import java.util.List;
import modelos.Repuesto;
import modelos.Usuario;

/**
 *
 * @author Junior de la Rocha
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // No tocar 
        List<Usuario> listaU = new ArrayList();
        TablaUsuario tU = new TablaUsuario(listaU);
        
               
   
        
    
                
                
        FrmAutenticar frmIS = new FrmAutenticar(listaU);
        frmIS.setVisible(true);
    }
    
}
