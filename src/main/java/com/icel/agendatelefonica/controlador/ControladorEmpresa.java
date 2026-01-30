package com.icel.agendatelefonica.controlador;

import com.icel.agendatelefonica.modelo.entidad.Empresa;
import com.icel.agendatelefonica.modelo.repositorio.RepositorioEmpresa;

/** @author contacto **/

public class ControladorEmpresa extends Controlador<RepositorioEmpresa,Empresa>{

    @Override
    protected boolean validar(Empresa obj) throws Exception{
        if(obj.getNombre().isEmpty()) throw new Exception("No hay nombre");
        if(obj.getRfc().isEmpty()) throw new Exception("No hay RFC");
        if(obj.getTelefono().isEmpty()) throw new Exception("No hay telefono");
        if(obj.getEmail().isEmpty()) throw new Exception("No hay email");
        return true;
    }
    
    @Override
    protected void inicializarRepositorio(){
        try{
            repositorio = new RepositorioEmpresa();
        }catch (Exception ex){
            System.getLogger(ControladorContacto.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
}
