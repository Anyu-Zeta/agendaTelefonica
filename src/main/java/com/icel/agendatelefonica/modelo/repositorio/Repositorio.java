package com.icel.agendatelefonica.modelo.repositorio;

import com.icel.agendatelefonica.modelo.entidad.Entidad;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/** @author anyu **/

public abstract class Repositorio<T extends Entidad>{
    
    protected MiConexion miConexion;
    protected PreparedStatement enunciado;
    
    /* query definition */
    protected String sqlInsertar;   // INSERT INTO tabla VALUES(?,?,?)
    protected String sqlConsultarPorId;     // SELECT * FROM tabla WHERE id IN (?);
    protected String sqlConsultarTodos;  // SELECT * FROM tabla;
    protected String sqlActualizar;   // REPLACE INTO tabla VALUES(?)
    protected String sqlEliminar;   // DELETE FROM tabla WHERE id IN (?);
    
    
    public Repositorio(String tabla,long parameters) throws Exception{
        try{
            miConexion = MiConexion.getInstancia();
            initQueries(tabla,parameters);
        }catch(Exception ex){
            System.out.println(
                "Error: "+ex.getMessage()
                +" in method: constructor()"
                +" in class: "+this.getClass().getName()
            );
            throw ex;
        }
    }
    
    protected void initQueries(String tabla,long parameters){
        sqlInsertar = "INSERT INTO "+tabla+" VALUES(NULL";
            for(int x=1;x<=(parameters-1);x++) sqlInsertar += ",?";
            sqlInsertar += ")";
        sqlConsultarPorId = "SELECT * FROM "+tabla+" WHERE id IN (?)";
        sqlConsultarTodos = "SELECT * FROM "+tabla+"";
        sqlActualizar = "REPLACE INTO "+tabla+" VALUES(";
            for(int x=1;x<=parameters;x++) sqlActualizar += ",?";
            sqlActualizar += ")";
            sqlActualizar = sqlActualizar.replace("(,?","(?");
        sqlEliminar = "DELETE FROM "+tabla+" WHERE id IN (?)";
        
        //System.out.println(sqlInsertar);
        //System.out.println(queryRead);
        //System.out.println(sqlActualizar);
        //System.out.println(sqlEliminar);
        
    }
    protected abstract T objectMapping(ResultSet rs) throws Exception;
    protected abstract void setStatementParameters(PreparedStatement enunciado,T obj,boolean newObj) throws Exception;
    
    public boolean insertar(T obj) throws Exception{
        try{
            enunciado = miConexion.conectar().prepareStatement(sqlInsertar);
            setStatementParameters(enunciado,obj,true);
            int result = enunciado.executeUpdate();
            return result>=0;
        }catch(Exception ex){
            System.out.println(
                "Error: "+ex.getMessage()
                +" in method: create()"
                +" in class: "+this.getClass().getName()
            );
            throw ex;
        }
    }
    
    public T consultarPorId(long id) throws Exception{
        try{
            enunciado = miConexion.conectar().prepareStatement(sqlConsultarPorId);
                enunciado.setLong(1,id);
            ResultSet rs  = enunciado.executeQuery();
            T obj = null;
            while(rs.next()){obj = objectMapping(rs);}
            return obj;
        }catch(Exception ex){
            System.out.println(
                "Error: "+ex.getMessage()
                +" in method: read()"
                +" in class: "+this.getClass().getName()
            );
            throw ex;
        }
    }
    
    public List<T> consultarTodos() throws Exception{
        try{
            enunciado = miConexion.conectar().prepareStatement(sqlConsultarTodos);
            ResultSet rs  = enunciado.executeQuery();
            T obj = null;
            List<T> list = new LinkedList<>();
            while(rs.next()){
                obj = objectMapping(rs);
                list.add(obj);
            }
            return list;
        }catch(Exception ex){
            System.out.println(
                "Error: "+ex.getMessage()
                +" in method: readAll()"
                +" in class: "+this.getClass().getName()
            );
            throw ex;
        }
    }
    
    public boolean actualizar(T obj) throws Exception{
        try{
            if(obj.getId() <= 0) throw new Exception("El id no puede ser cero en la actualizacion");
            enunciado = miConexion.conectar().prepareStatement(sqlActualizar);
            setStatementParameters(enunciado,obj,false);
            int result = enunciado.executeUpdate();
            return result>=0;
        }catch(Exception ex){
            System.out.println(
                "Error: "+ex.getMessage()
                +" in method: update()"
                +" in class: "+this.getClass().getName()
            );
            throw ex;
        }
    }
    
    public boolean eliminar(long id) throws Exception{
        try{
            enunciado = miConexion.conectar().prepareStatement(sqlEliminar);
                enunciado.setLong(1,id);
            int result = enunciado.executeUpdate();
            return result>=0;
        }catch(Exception ex){
            System.out.println(
                "Error: "+ex.getMessage()
                +" in method: delete()"
                +" in class: "+this.getClass().getName()
            );
            throw ex;
        }
    }
}
