package com.icel.agendatelefonica.modelo.repositorio;

import com.icel.agendatelefonica.modelo.entidad.Contacto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/** @author anyu **/

public class RepositorioContacto extends Repositorio<Contacto>{

    public RepositorioContacto() throws Exception {
        super("contacto",5);
    }

    @Override
    protected Contacto objectMapping(ResultSet rs) throws Exception{
        Contacto obj = new Contacto();
            obj.setId(rs.getLong(1));
            obj.setNombres(rs.getString(2));
            obj.setApellidos(rs.getString(3));
            obj.setTelefono(rs.getString(4));
            obj.setEmail(rs.getString(5));
        return obj;
    }

    @Override
    protected void setStatementParameters(PreparedStatement enunciado, Contacto obj, boolean newObj) throws Exception{
        int i = 1;
        if(!newObj) enunciado.setLong(i++,obj.getId());
        enunciado.setString(i++,obj.getNombres());
        enunciado.setString(i++,obj.getApellidos());
        enunciado.setString(i++,obj.getTelefono());
        enunciado.setString(i++,obj.getEmail());
    }
    
    public List<Contacto> obtenerPorNombre(String nombre) throws Exception {
        try {
            String sql = "SELECT * FROM contacto WHERE nombres LIKE (?)";
            enunciado = miConexion.conectar().prepareStatement(sql);
            enunciado.setString(1,nombre);
            ResultSet rs = enunciado.executeQuery();
            Contacto obj = null;
            List<Contacto> list = new LinkedList<>();
            while (rs.next()){
                obj = objectMapping(rs);
                list.add(obj);
            }
            return list;
        } catch (Exception ex) {
            System.out.println(
                    "Error: " + ex.getMessage()
                    + " in method: obtenerPorNombre()"
                    + " in class: " + this.getClass().getName()
            );
            throw ex;
        }
    }
    
}
