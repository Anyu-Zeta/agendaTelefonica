package com.icel.agendatelefonica.modelo.entidad;

import lombok.Getter;
import lombok.Setter;

/** @author anyu **/

@Getter
@Setter
public class Empresa extends Entidad{
    
    private String nombre;
    private String rfc;
    private String email;
    private String telefono;
    
    @Override
    public String toString(){
        return nombre;
    }
    
}
