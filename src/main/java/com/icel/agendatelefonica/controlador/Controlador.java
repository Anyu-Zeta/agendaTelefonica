package com.icel.agendatelefonica.controlador;

import com.icel.agendatelefonica.modelo.entidad.Entidad;
import com.icel.agendatelefonica.modelo.repositorio.Repositorio;
import lombok.Getter;

/** @author anyu **/

public abstract class Controlador<R extends Repositorio<E>,E extends Entidad>{
    
    @Getter
    protected R repositorio;
    
    public Controlador(){
        inicializarRepositorio();
    }
    
    protected abstract void inicializarRepositorio();
    protected abstract boolean validar(E obj) throws Exception;
    
    public boolean guardar(E obj) throws Exception{
        try{
            if(validar(obj)){
                if(obj.getId() == 0)
                    return repositorio.insertar(obj);
                return repositorio.actualizar(obj);
            }
            return false;
        }catch(Exception ex){
            System.out.println(
                "Error: "+ex.getMessage()
                +" in method: guardar()"
                +" in class: "+this.getClass().getName()
            );
            throw ex;
        }
    }
}
