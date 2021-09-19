/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import dominio.Md_Usuario;
import interfaz.principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author STEFANNIE FLANDEZ
 */
public class CLSUsuarioJDBC {
    
    private static final String SQL_INSERT="insert into tb_progracomision (user,password) values (?,?);";
    private static final String SQL_SELECT_VALIDACION="select * from  tb_usuarios where usuario=? "+ "and contraseña=?";
    public int insert(Md_Usuario usuario){
        String texto="",encriptado ="";
         Connection conexion=null;
        PreparedStatement statement=null;
        int rows=0;
        try{
            conexion=Conexion.getConnection();
            statement=conexion.prepareStatement(SQL_INSERT);
            statement.setString(1, usuario.getUsername());
            texto=usuario.getPassword();
            
            statement.setString(2, encriptado);
            rows=statement.executeUpdate();
        }
         catch (SQLException ex) {
           ex.printStackTrace(System.out);
        }finally{
           Conexion.CloseStatement(statement);
           Conexion.CloseConnection(conexion);
        }
        return rows;
    }

    public boolean  Validar(Md_Usuario datos){
         Connection conexion=null;
        PreparedStatement statement=null;
        ResultSet rs=null;
        Md_Usuario usuario =new Md_Usuario();
        String texto="",Mostrar ="";
        boolean tienepermiso=false;
       
        
        try {
            conexion=Conexion.getConnection();
            texto=datos.getPassword();
                     
            String Condicion="SELECT * FROM tb_usuario where user='"+datos.getUsername()+"'"
                    + " and password='"+Mostrar+"';";
            statement=conexion.prepareStatement(Condicion);
            rs=statement.executeQuery();
            
            while (rs.next()) {
                    tienepermiso=true;
            }      
        } catch (SQLException ex) {
           ex.printStackTrace(System.out);
        }finally{
           Conexion.CloseConnection(conexion);
           Conexion.CloseResul(rs);
           Conexion.CloseStatement(statement);
        }
        
        return tienepermiso;
    }
    
    public static void main (String[] args){
        principal interfaz = new principal();
        interfaz.setVisible(true);
    }
}

    

