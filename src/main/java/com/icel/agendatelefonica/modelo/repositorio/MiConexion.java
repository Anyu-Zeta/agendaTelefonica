package com.icel.agendatelefonica.modelo.repositorio;

import java.sql.Connection;
import java.sql.DriverManager;

/** @author anyu **/

public class MiConexion{
    
    /**
     * Esta clase se destina para la definición de un Singleton, encargado de 
     * realizar la isntancia de una unica conexión.
     **/
    
    public static int SQLITE = 1;
    public static int MARIADB = 2;
    
    private String url;
    private String driverClass;
    private String servidor;
    private long puerto;
    private String baseDeDatos;
    private String usuario;
    private String contrasenia;
    
    private void cargarDatosSQLite(){
        driverClass = "org.sqlite.JDBC";
        servidor = "";
        puerto = 0;
        baseDeDatos = "agendaTelefonica.db";
        usuario = "";
        contrasenia = "";
        url = "jdbc:sqlite:"+baseDeDatos;
    }
    
    private void cargarDatosMariaDB(){
        driverClass = "org.mariadb.jdbc.Driver";
        servidor = "localhost";
        puerto = 3306;
        baseDeDatos = "agendaTelefonica";
        usuario = "root";
        contrasenia = "yolo2304";
        url = "jdbc:mariadb://"+servidor+":"+puerto+"/"+baseDeDatos;
    }
    
    private static MiConexion instancia;
    public static MiConexion getInstancia() throws Exception{
        if(instancia == null){
            instancia = new MiConexion(MARIADB);//CAMBIO
        }
        return instancia;
    }
    
    private MiConexion(int manejador) throws Exception{
        switch(manejador){
            case 1: cargarDatosSQLite(); break;
            case 2: cargarDatosMariaDB(); break;
            default:
                throw new Exception("El manejador solicitado no esta configurado");
        }
    }
    
    private Connection conexion;
    public Connection conectar() throws Exception{
        try{
            if(conexion == null){
                Class.forName(driverClass);
                conexion = DriverManager.getConnection(url,usuario,contrasenia);
            }
            return conexion;
        }catch(Exception ex){
            System.out.println("Error: "+this.getClass().getName()+" => "+ ex.getMessage());
            throw ex;
        }
    }
    
    public boolean desconectar() throws Exception{
        try{
            if(conexion != null)
                conexion.close();
            return true;
        }catch(Exception ex){
            System.out.println("Error: "+this.getClass().getName()+" => "+ ex.getMessage());
            throw ex;
        }
    }
}
