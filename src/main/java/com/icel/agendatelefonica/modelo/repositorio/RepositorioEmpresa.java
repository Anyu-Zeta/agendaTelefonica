package com.icel.agendatelefonica.modelo.repositorio;

import com.icel.agendatelefonica.modelo.entidad.Empresa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/** @author anyu **/

public class RepositorioEmpresa extends Repositorio<Empresa>{

    public RepositorioEmpresa() throws Exception {
        super("empresa",5);
    }

    @Override
    protected Empresa objectMapping(ResultSet rs) throws Exception{
        Empresa obj = new Empresa();
            obj.setId(rs.getLong(1));
            obj.setNombre(rs.getString(2));
            obj.setRfc(rs.getString(3));
            obj.setTelefono(rs.getString(4));
            obj.setEmail(rs.getString(5));
        return obj;
    }

    @Override
    protected void setStatementParameters(PreparedStatement enunciado, Empresa obj, boolean newObj) throws Exception{
        int i = 1;
        if(!newObj) enunciado.setLong(i++,obj.getId());
        enunciado.setString(i++,obj.getNombre());
        enunciado.setString(i++,obj.getRfc());
        enunciado.setString(i++,obj.getTelefono());
        enunciado.setString(i++,obj.getEmail());
    }
    
}
