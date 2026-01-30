package com.icel.agendatelefonica.vista;

import com.icel.agendatelefonica.AgendaTelefonica;
import com.icel.agendatelefonica.controlador.ControladorContacto;
import com.icel.agendatelefonica.modelo.entidad.Contacto;
import java.util.List;
import javax.swing.JOptionPane;

/** @author anyu **/

public class ContactoDlg extends BaseDlg{
    
    private ControladorContacto controladorContacto;
    
    public ContactoDlg(){
        super();
        init();
    }
    
    private void init(){
        try{controladorContacto = new ControladorContacto();}catch(Exception ex){}
        model.addColumn("Id");
        model.addColumn("Nombres");
        model.addColumn("Apellidos");
        model.addColumn("Email");
        model.addColumn("Telefono");
    }

    @Override
    protected void eventoBotonBuscar(){
        try{
            while(model.getRowCount()>0){model.removeRow(0);}
            String parametro = tfBuscar.getText()+"%";
            List<Contacto> lista =
                controladorContacto.getRepositorio().obtenerPorNombre(parametro);
            for(Contacto obj:lista){
                Object[] datos = {
                    obj.getId()
                    ,obj.getNombres()
                    ,obj.getApellidos()
                    ,obj.getEmail()
                    ,obj.getTelefono()
                };
                model.addRow(datos);
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this,ex.getMessage(),"Contactos",JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    protected void eventoBotonEliminar(){
        try{
            long id = selectedId();
            if(id <= 0){
                JOptionPane.showMessageDialog(
                    this //Componente padre
                    ,"No se ha seleccionado una fila" //Mensaje a mostrar
                    ,"Contacto" //Titulo de la ventanita
                    ,JOptionPane.WARNING_MESSAGE //Tipo/Icono a mostrar
                );
                return;
            }
            controladorContacto.getRepositorio().eliminar(id);
            eventoBotonBuscar();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this,ex.getMessage(),"Contactos",JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    protected void eventoBotonEditar(){
        try{
            long id = selectedId();
            if(id <= 0){
                JOptionPane.showMessageDialog(
                    this //Componente padre
                    ,"No se ha seleccionado una fila" //Mensaje a mostrar
                    ,"Contacto" //Titulo de la ventanita
                    ,JOptionPane.WARNING_MESSAGE //Tipo/Icono a mostrar
                );
                return;
            }
            ContactoModalDlg modal = new ContactoModalDlg(
                    AgendaTelefonica.getPrincipalDlg()
                ,true
                ,id
            );
            /**/
            eventoBotonBuscar();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this,ex.getMessage(),"Contacto",JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    protected void eventoBotonAgregar(){
        try{
            ContactoModalDlg modal = new ContactoModalDlg(
                AgendaTelefonica.getPrincipalDlg()
                ,true
            );
            eventoBotonBuscar();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this,ex.getMessage(),"Contacto",JOptionPane.WARNING_MESSAGE);
        }
    }
    
}
