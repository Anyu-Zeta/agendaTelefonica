package com.icel.agendatelefonica.modelo.entidad;

import lombok.Getter;
import lombok.Setter;

/** @author anyu **/

@Getter
@Setter
public class Contacto extends Entidad{
    
    private String nombres;
    private String apellidos;
    private String email;
    private String telefono;
    
    @Override
    public String toString(){
        return nombres+" "+apellidos;
    }
    
}
