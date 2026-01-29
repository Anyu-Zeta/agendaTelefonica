package com.icel.agendatelefonica.pruebas;

import com.icel.agendatelefonica.modelo.repositorio.MiConexion;

/** @author anyu */

public class Pruebas01{
    
    public boolean prueba() throws Exception{
        try{
            MiConexion miConexion = MiConexion.getInstancia();
            miConexion.conectar();
            System.out.println("La conexión se ha realizado con exito");
            miConexion.desconectar();
            System.out.println("La conexión se cerrado con exito");
            return true;
        }catch (Exception ex){
            System.err.println(
                "Error: "+ex.getMessage()
                +" in method: prueba()"
                +" in class: "+this.getClass().getName()
            );
            throw ex;
        }
    }
}
