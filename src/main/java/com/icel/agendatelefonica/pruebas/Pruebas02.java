package com.icel.agendatelefonica.pruebas;

import com.icel.agendatelefonica.modelo.entidad.Contacto;
import com.icel.agendatelefonica.modelo.repositorio.RepositorioContacto;
import java.util.List;

/** @author anyu */

public class Pruebas02{
    
    public boolean prueba() throws Exception{
        try{
            System.out.println("Creando el repositorio");
            RepositorioContacto rc = new RepositorioContacto();
            
            System.out.println("Creando prueba de inserción");
            /* Prueba de insertar */
            Contacto obj = new Contacto();
                obj.setNombres("Alia");
                obj.setApellidos("Mamayi");
                obj.setTelefono("7770000000");
                obj.setEmail("ali@icel.edu.mx");
            rc.insertar(obj);
            System.out.println("Inserción exitosa");
            
            System.out.println("Creando prueba de eliminar");
            rc.eliminar(2);
            System.out.println("Eliminacion completada");
            
            System.out.println("Creando prueba de actualizar");
            /* Prueba de insertar */
            obj = new Contacto();
                obj.setId(3);
                obj.setNombres("Arles");
                obj.setApellidos("Ayis");
                obj.setTelefono("7770000000");
                obj.setEmail("ayis@icel.edu.mx");
            rc.actualizar(obj);
            System.out.println("actualización exitosa");
            
            System.out.println("Creando prueba de consulta todos");
            List<Contacto> todos = rc.consultarTodos();
            for(Contacto c:todos){
                System.out.println(c.getId()+" "+c.toString());
            }
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
