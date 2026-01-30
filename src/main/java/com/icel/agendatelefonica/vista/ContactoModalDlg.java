package com.icel.agendatelefonica.vista;

import com.icel.agendatelefonica.controlador.ControladorContacto;
import com.icel.agendatelefonica.modelo.entidad.Contacto;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/** @author anyu **/

public class ContactoModalDlg extends BaseModalDlg{
    
    private ControladorContacto controladorContacto;
    
    public JLabel lbNombres;
    public JTextField tfNombres;
    public JLabel lbApellidos;
    public JTextField tfApellidos;
    public JLabel lbEmail;
    public JTextField tfEmail;
    public JLabel lbTelefono;
    public JTextField tfTelefono;
    
    public ContactoModalDlg(JFrame padre,boolean modal){
        super(padre,modal);
        init();
    }
    
    public ContactoModalDlg(JFrame padre,boolean modal,long id){
        super(padre,modal);
        this.id = id;
        init();
    }
    
    public void init(){
        lbNombres = new JLabel("Nombres");
        tfNombres = new JTextField();
        lbApellidos = new JLabel("Apellidos");
        tfApellidos = new JTextField();
        lbEmail = new JLabel("Email");
        tfEmail = new JTextField();
        lbTelefono = new JLabel("Telefono");
        tfTelefono = new JTextField();
        
        try{
            controladorContacto = new ControladorContacto();
        }catch(Exception ex){}
        
        setLayout(new GridBagLayout());
        add(lbNombres,crearRestricciones(0,0,12,1,true));
        add(tfNombres,crearRestricciones(0,1,12,1,true));
        add(lbApellidos,crearRestricciones(0,2,12,1,true));
        add(tfApellidos,crearRestricciones(0,3,12,1,true));
        add(lbEmail,crearRestricciones(0,4,12,1,true));
        add(tfEmail,crearRestricciones(0,5,12,1,true));
        add(lbTelefono,crearRestricciones(0,6,12,1,true));
        add(tfTelefono,crearRestricciones(0,7,12,1,true));
        add(panelOps,crearRestricciones(0,12,12,1,true));
        
        if(id > 0) cargarDatos();
        
        this.setTitle("Contacto");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    private void cargarDatos(){
        try{
            Contacto obj = controladorContacto.getRepositorio().consultarPorId(id);
            id = obj.getId();
            tfNombres.setText(obj.getNombres());
            tfApellidos.setText(obj.getApellidos());
            tfEmail.setText(obj.getEmail());
            tfTelefono.setText(obj.getTelefono());
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this,ex.getMessage(),"Contactos",JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    protected void eventoBotonAceptar(){
        try{
            Contacto obj = new Contacto();
                obj.setId(id);
                obj.setNombres(tfNombres.getText());
                obj.setApellidos(tfApellidos.getText());
                obj.setEmail(tfEmail.getText());
                obj.setTelefono(tfTelefono.getText());
            if(controladorContacto.guardar(obj)){
                JOptionPane.showMessageDialog(this,"Los datos se han guardado","Contacto",JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this,ex.getMessage(),"Contactos",JOptionPane.WARNING_MESSAGE);
        }
    }
    
}
