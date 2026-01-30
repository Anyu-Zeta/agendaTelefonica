package com.icel.agendatelefonica.vista;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/** @author anyu **/

public class PrincipalDlg extends JFrame{
    
    public PrincipalDlg(){
        init();
    }
    
    private void init(){
        
        JMenuBar menuPrincipal = new JMenuBar();
            JMenu menuAgenda = new JMenu("Agenda");
                JMenuItem contacto = new JMenuItem("Contacto");
                    contacto.addActionListener(cmd -> {cambiarContenido(new ContactoDlg());});
                menuAgenda.add(contacto);
            menuPrincipal.add(menuAgenda);
        this.setJMenuBar(menuPrincipal);
        
        this.setTitle("Agenda Telefonica");
        this.setSize(800,630);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    private void cambiarContenido(JPanel contenido){
        this.getContentPane().removeAll();
        this.getContentPane().add(contenido);
        this.pack();
        this.setVisible(true);
    }
    
}
