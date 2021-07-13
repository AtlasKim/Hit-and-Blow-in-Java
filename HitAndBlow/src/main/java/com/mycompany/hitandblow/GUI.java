/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hitandblow;

/**
 *
 * @author Boku no Melo
 */
import java.awt.BorderLayout;
//import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JPanel;
//import java.awt.Graphics2D;
//import java.awt.Graphics;
import java.io.IOException;
//import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.OverlayLayout;
import javax.swing.JOptionPane;

public class GUI extends JFrame
{
    protected static int width = 1069;                  
    protected static int height = 942;
    private JLabel boardLabel,solutionLabel,hitAndBlowLabel,colorLabel,buttonLabel;             //queste sono le label delle varie sezioni del nostro gioco
    
    
    public GUI()
    {
        super("Pieni e parziali");
        
        setSize(width,height);  
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        boardLabel = new JLabel();
        boardLabel.setBackground(java.awt.Color.red);
        boardLabel.setPreferredSize(800,842);
    }
}
