/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utills;

import javax.swing.JOptionPane;

/**
 *
 * @author Dimas Aditya P
 */
public class Helper {
    public static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Gagal", JOptionPane.WARNING_MESSAGE) ;
    }
    
    public static String nullToEmpty(String value) {
        return value == null ? "" : value;
    }
}
