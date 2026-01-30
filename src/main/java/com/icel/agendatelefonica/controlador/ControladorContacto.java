package com.icel.agendatelefonica.controlador;

import com.icel.agendatelefonica.modelo.entidad.Contacto;
import com.icel.agendatelefonica.modelo.repositorio.RepositorioContacto;

/** @author contacto **/

public class ControladorContacto extends Controlador<RepositorioContacto,Contacto>{

    @Override
    protected boolean validar(Contacto obj) throws Exception{
        if(obj.getNombres().isEmpty()) throw new Exception("No hay nombre");
        if(obj.getApellidos().isEmpty()) throw new Exception("No hay apellidos");
        if(obj.getTelefono().isEmpty()) throw new Exception("No hay telefono");
        if(obj.getEmail().isEmpty()) throw new Exception("No hay email");
        return true;
    }

    @Override
    protected void inicializarRepositorio(){
        try{
            repositorio = new RepositorioContacto();
        }catch (Exception ex){
            System.getLogger(ControladorContacto.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
}
