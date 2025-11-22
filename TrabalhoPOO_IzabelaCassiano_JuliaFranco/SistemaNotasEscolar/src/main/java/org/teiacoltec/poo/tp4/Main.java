package org.teiacoltec.poo.tp4;

import org.teiacoltec.poo.tp4.ui.LoginFrame;

import javax.swing.*;

/**
 * Ponto de entrada da atividade 4 com UI Swing e DAOs.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppContext ctx = new AppContext();
            new LoginFrame(ctx).setVisible(true);
        });
    }
}