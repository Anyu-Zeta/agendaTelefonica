package com.icel.agendatelefonica.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/** @author anyu **/

public abstract class BaseDlg extends JPanel{
    
    /* 1. Definir los componentes */
    protected JLabel lbBuscar;
    protected JTextField tfBuscar;
    protected JButton btnBuscar;
    
    protected JButton btnEliminar;
    protected JButton btnEditar;
    protected JButton btnAgregar;
    
    protected DefaultTableModel model;
    protected JTable tbResultado;
    
    public BaseDlg(){
        init();
    }
    
    private void init(){
        /* 2. Crear|Inicializar los componentes */
        lbBuscar = new JLabel("Proporciona el nombre");
            lbBuscar.setFont(this.getFont().deriveFont(Font.BOLD));
        tfBuscar = new JTextField();
        btnBuscar = new JButton("Buscar");
            btnBuscar.setFont(this.getFont().deriveFont(Font.BOLD));
            btnBuscar.setBackground(Color.decode("#4CD964"));
            btnBuscar.setForeground(Color.decode("#CFD8DC"));
            btnBuscar.addActionListener(evento -> {eventoBotonBuscar();});
        
        btnEliminar = new JButton("Eliminar");
            btnEliminar.setFont(this.getFont().deriveFont(Font.BOLD));
            btnEliminar.setBackground(Color.decode("#FF2D55"));
            btnEliminar.setForeground(Color.decode("#CFD8DC"));
            btnEliminar.addActionListener(evento -> {eventoBotonEliminar();});
        btnEditar = new JButton("Editar");
            btnEditar.setFont(this.getFont().deriveFont(Font.BOLD));
            btnEditar.setBackground(Color.decode("#898989"));
            btnEditar.setForeground(Color.decode("#CFD8DC"));
            btnEditar.addActionListener(evento -> {eventoBotonEditar();});
        btnAgregar = new JButton("Agregar");
            btnAgregar.setFont(this.getFont().deriveFont(Font.BOLD));
            btnAgregar.setBackground(Color.decode("#007AFF"));
            btnAgregar.setForeground(Color.decode("#CFD8DC"));
            btnAgregar.addActionListener(evento -> {eventoBotonAgregar();});
        
        model = new DefaultTableModel();
        tbResultado = new JTable(model);
            tbResultado.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            tbResultado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        /* 3. Crear el layout y agregar los componentes*/
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        this.add(lbBuscar,crearRestricciones(0,0,12,1,true));
        this.add(tfBuscar,crearRestricciones(0,1,10,1,true));
        this.add(btnBuscar,crearRestricciones(10,1,2,1,false));
        
        this.add(new JLabel(""),crearRestricciones(0,2,6,1,true));
        this.add(btnEliminar,crearRestricciones(6,2,2,1,false));
        this.add(btnEditar,crearRestricciones(8,2,2,1,false));
        this.add(btnAgregar,crearRestricciones(10,2,2,1,false));
        
        JScrollPane scrollPane = new JScrollPane(tbResultado);
        this.add(scrollPane,crearRestricciones(0,3,12,10,false));
        
        /* 4. Personalizar el panel */
    }
    
    protected GridBagConstraints constraints;
    protected GridBagConstraints crearRestricciones(
        int posicionX,int posicionY,int tamanioX,int tamanioY,boolean responsivo){
        if(constraints == null) constraints = new GridBagConstraints();
            constraints.gridx = posicionX; // Posicion en columna (X)
            constraints.gridy = posicionY; // Posicion en fila (Y)
            constraints.gridwidth= tamanioX; // Tamaño en columnas (X)
            constraints.gridheight= tamanioY; // tamaño en filas (Y)
            constraints.ipadx = 10; // Padding X
            constraints.ipady = 10; // Padding Y
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.weightx = responsivo?1.0:0.0;
            constraints.anchor = GridBagConstraints.CENTER;
        return constraints;
    }
    
    protected long selectedId() throws Exception{
        int filaSelecciona = tbResultado.getSelectedRow();
        if(filaSelecciona < 0){return 0;}
        long id = (long)tbResultado.getValueAt(filaSelecciona,0);
        return id;
    }
    
    protected abstract void eventoBotonBuscar();
    protected abstract void eventoBotonEliminar();
    protected abstract void eventoBotonEditar();
    protected abstract void eventoBotonAgregar();
    
}
