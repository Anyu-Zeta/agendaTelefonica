package com.icel.agendatelefonica;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.icel.agendatelefonica.vista.PrincipalDlg;
import javax.swing.UIManager;
import lombok.Getter;

/** @author anyu **/

public class AgendaTelefonica{
    
    @Getter
    private static PrincipalDlg principalDlg;
    
    public static void main(String[] args) throws Exception{
        System.out.println("Hello World!");
        FlatMacLightLaf.setup();
        UIManager.put("Button.arc", 999);
        UIManager.put("Component.arc", 999);
        UIManager.put("ProgressBar.arc", 999);
        UIManager.put("TextComponent.arc", 999);
        UIManager.setLookAndFeel(new FlatMacLightLaf());
        
        principalDlg = new PrincipalDlg();
    }
}
