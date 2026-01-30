package com.icel.agendatelefonica.vista;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/** @author anyu **/

public abstract class BaseModalDlg extends JDialog{
    
    protected long id;
    protected JPanel panelOps;
    protected JButton btnAceptar;
    protected JButton btnCancelar;
    
    public BaseModalDlg(JFrame padre,boolean modal){
        super(padre,modal);
        init();
    }
    
    private void init(){
        id = 0;
        btnCancelar = new JButton("Cancelar");
            btnCancelar.setFont(this.getFont().deriveFont(Font.BOLD));
            btnCancelar.setBackground(Color.decode("#FF2D55"));
            btnCancelar.setForeground(Color.decode("#CFD8DC"));
            btnCancelar.addActionListener(evento -> {eventoBotonCancelar();});
        btnAceptar = new JButton("Aceptar");
            btnAceptar.setFont(this.getFont().deriveFont(Font.BOLD));
            btnAceptar.setBackground(Color.decode("#007AFF"));
            btnAceptar.setForeground(Color.decode("#CFD8DC"));
            btnAceptar.addActionListener(evento -> {eventoBotonAceptar();});
        panelOps = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,10));
            panelOps.add(btnAceptar);
            panelOps.add(btnCancelar);
        
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
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
    
    protected void eventoBotonCancelar(){
        this.dispose();
    }
    
    protected abstract void eventoBotonAceptar();
    
}
