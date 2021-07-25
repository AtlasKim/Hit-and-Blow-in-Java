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
    private Game hitAndBlow;
    protected static int width = 1265;                  
    protected static int height = 972;
    private JLabel boardLabel,solutionLabel,hitAndBlowLabel,colorLabel,buttonLabel,backgroundLabel,solutionPin,orangePeg,whitePeg,okLabel;             //queste sono le label delle varie sezioni del nostro gioco
    private JLayeredPane boardPanel,hitAndBlowPanel,colorPanel;
    
    private Icon redIcon,greenIcon,whiteIcon,blueIcon,yellowIcon,pinkIcon,background,orangeSolution,whiteSolution,okIcon;
    
    private double [] xArea;                            //Sono due array che ci aiutano a capire meglio in quale posizione inserire le tessere
    private double [] yArea;
    private double yOffset;
    private double xOffset;
    private int xPos;
    private int yPos;
    private int yColumnFound;
    private int xRowFound;
    private int colore;
    private int round = 0;
    
    
    public GUI() throws IOException, Exception
    {
        super("Pieni e parziali");
        
        hitAndBlow = new Game();
        
        Icon board = new ImageIcon(getClass().getResource( "MainBoard.png"));       //tutte le immaggini devono essere nella stessa cartella dei file .class
        Icon hitAndBlow = new ImageIcon(getClass().getResource( "HitAndBlow.png"));
        Icon pegSelection = new ImageIcon(getClass().getResource( "PegSelection.png"));
        Icon solutionIcon = new ImageIcon(getClass().getResource( "CoverColumn.png"));
        Icon background = new ImageIcon(getClass().getResource( "background.png"));
        Icon okIcon = new ImageIcon(getClass().getResource( "OkButton.png"));
        
        redIcon = new ImageIcon(getClass().getResource("rosso.png"));          //import immagini varie tessere
        greenIcon = new ImageIcon(getClass().getResource("verde.png"));
        whiteIcon = new ImageIcon(getClass().getResource("bianco.png"));
        blueIcon = new ImageIcon(getClass().getResource("blu.png"));
        yellowIcon = new ImageIcon(getClass().getResource("giallo.png"));
        pinkIcon = new ImageIcon(getClass().getResource("rosa.png"));
        orangeSolution = new ImageIcon(getClass().getResource("pegarancio.png"));
        whiteSolution = new ImageIcon(getClass().getResource("pegbianco.png"));
        
        setSize(width,height);  
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        boardPanel = new JLayeredPane();
        //hitAndBlowPanel = new JLayeredPane();
        //colorPanel = new JLayeredPane();
        
        
        boardLabel = new JLabel(board);                                          //Convertire in un tipo diverso di immagine se JLayeredPane dà problemi
        hitAndBlowLabel = new JLabel(hitAndBlow);
        colorLabel = new JLabel(pegSelection);
        solutionLabel = new JLabel(solutionIcon);
        backgroundLabel  = new JLabel(background);     
        okLabel = new JLabel(okIcon);
        
        
        
        boardPanel.add(boardLabel,1);
        boardPanel.add(okLabel,1,-1);
        boardPanel.add(solutionLabel,0);
        boardPanel.add(hitAndBlowLabel,1);
        boardPanel.add(colorLabel,1);
        boardPanel.add(backgroundLabel,6);
        
        boardPanel.setBounds(0, 0, height, width);  //stabilisce come fissare una dimensione massima della boardPanel
        
        
        boardPanel.setPreferredSize(boardPanel.getSize());
        boardPanel.setMaximumSize(boardPanel.getSize());
        boardPanel.setMinimumSize(boardPanel.getSize());
        boardPanel.setLayout(new OverlayLayout(boardPanel));
        
        
        
        add(boardPanel,BorderLayout.CENTER);
        boardPanel.addMouseListener(new MouseClickHandler());
        
        xArea=setXArea(xArea);
        yArea=setYArea(yArea);
        printArray(yArea);
        printArray(xArea);
        round = 0;
    }
    
    public class MouseClickHandler implements MouseListener,MouseMotionListener 
    {
        @Override
        public void mouseClicked( MouseEvent event )
        {
            xPos = event.getX(); // get x-position of mouse
            yPos = event.getY(); // get y-position of mouse
            yColumnFound = findColonna(xPos,yArea);
            xRowFound = findRiga(yPos,xArea);
            boolean victory = false;
            JLabel pin;
            
            
            System.out.println("X :"+xPos);
            System.out.println("Y :"+yPos);
            System.out.println("Colonna effettiva :"+yColumnFound);
            System.out.println("Riga effettiva :"+xRowFound);
            
            if(xRowFound == 5)
            {
                if(yColumnFound>1&&yColumnFound<8)
                    colore = yColumnFound;
                else if(yColumnFound==0)
                {
                    try {
                            
                        hitAndBlow.printGrid();
                        victory = hitAndBlow.victoryCheck(round);                                                                   
                        System.out.println("Round:"+round);
                        System.out.println("Numero di Hit:"+hitAndBlow.getHit());
                        System.out.println("Numero di Blow:"+hitAndBlow.getBlow());
                        printSolution(round);
                        hitAndBlow.resetHit();
                        hitAndBlow.resetBlow();
                        round++;                                                    //se si clicka nella posizione dell'OK allora fare il controllo di vittoria e andare avanti di turno
                    } catch (Exception ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            else if(yColumnFound == round &&xRowFound !=0)
            {
                switch (colore)
                {
                    case 2:
                    {
                        try {
                            hitAndBlow.getPlayer().move(hitAndBlow.getGrid(), xRowFound-1, round, new Piece(Color.BLU));
                            pin = new JLabel(blueIcon);
                            pin.setBounds(0,0,blueIcon.getIconWidth(),blueIcon.getIconHeight());
                            pin.setLocation((int)yArea[yColumnFound]+3,(int)xArea[xRowFound]+3);          
                            boardPanel.add(pin,4);
                        } catch (Exception ex) {
                            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                        break;

                    case 3:
                    {
                        try {
                            hitAndBlow.getPlayer().move(hitAndBlow.getGrid(), xRowFound-1, round, new Piece(Color.ROSSO));
                            pin = new JLabel(redIcon);
                            pin.setBounds(0,0,redIcon.getIconWidth(),redIcon.getIconHeight());
                            pin.setLocation((int)yArea[yColumnFound]+3,(int)xArea[xRowFound]+3);          
                            boardPanel.add(pin,4);
                        } catch (Exception ex) {
                            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                        break;

                    case 4:
                    {
                        try {
                            hitAndBlow.getPlayer().move(hitAndBlow.getGrid(), xRowFound-1, round, new Piece(Color.GIALLO));
                            pin = new JLabel(yellowIcon);
                            pin.setBounds(0,0,yellowIcon.getIconWidth(),yellowIcon.getIconHeight());
                            pin.setLocation((int)yArea[yColumnFound]+3,(int)xArea[xRowFound]+3);          
                            boardPanel.add(pin,4);
                        } catch (Exception ex) {
                            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                        break;

                    case 5:
                    {
                        try {
                            hitAndBlow.getPlayer().move(hitAndBlow.getGrid(), xRowFound-1, round, new Piece(Color.VERDE));
                            pin = new JLabel(greenIcon);
                            pin.setBounds(0,0,greenIcon.getIconWidth(),greenIcon.getIconHeight());
                            pin.setLocation((int)yArea[yColumnFound]+3,(int)xArea[xRowFound]+3);          
                            boardPanel.add(pin,4);
                        } catch (Exception ex) {
                            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                        break;

                    case 6:
                    {
                        try {
                            hitAndBlow.getPlayer().move(hitAndBlow.getGrid(), xRowFound-1, round, new Piece(Color.ROSA));
                            pin = new JLabel(pinkIcon);
                            pin.setBounds(0,0,pinkIcon.getIconWidth(),pinkIcon.getIconHeight());
                            pin.setLocation((int)yArea[yColumnFound]+3,(int)xArea[xRowFound]+3);          
                            boardPanel.add(pin,4);
                        } catch (Exception ex) {
                            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                        break;

                    case 7:
                    {
                        try {
                            hitAndBlow.getPlayer().move(hitAndBlow.getGrid(), xRowFound-1, round, new Piece(Color.BIANCO));
                            pin = new JLabel(whiteIcon);
                            pin.setBounds(0,0,whiteIcon.getIconWidth(),whiteIcon.getIconHeight());
                            pin.setLocation((int)yArea[yColumnFound]+3,(int)xArea[xRowFound]+3);          
                            boardPanel.add(pin,4);
                        } catch (Exception ex) {
                            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                        break;

                    default:
                        break;
                }
            }
            
            if(victory)
            {
                System.out.println(hitAndBlow.getPlayer().getName()+" HAI VINTO! SPARISCIH");       //Fa sparire la Solution Label che copre la soluzione e la stampa a schermo
                boardPanel.remove(solutionLabel);
                for(int i=0;i<4;i++)
                {
        
                    try {
                        switch (hitAndBlow.getSolution().getSpot(i).getPiece().getPieceColor())
                        {
                            case BLU:
                            {
                                try {
                                    
                                    solutionPin = new JLabel(blueIcon);
                                    solutionPin.setBounds(0,0,blueIcon.getIconWidth(),blueIcon.getIconHeight());
                                    solutionPin.setLocation((int)yArea[8]+3,(int)xArea[i+1]+3);
                                    boardPanel.add(solutionPin,4);
                                } catch (Exception ex) {
                                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            break;
                            
                            case ROSSO:
                            {
                                try {
                                    
                                    solutionPin = new JLabel(redIcon);
                                    solutionPin.setBounds(0,0,redIcon.getIconWidth(),redIcon.getIconHeight());
                                    solutionPin.setLocation((int)yArea[8]+3,(int)xArea[i+1]+3);
                                    boardPanel.add(solutionPin,4);
                                } catch (Exception ex) {
                                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            break;
                            
                            case GIALLO:
                            {
                                try {
                                    
                                    solutionPin = new JLabel(yellowIcon);
                                    solutionPin.setBounds(0,0,yellowIcon.getIconWidth(),yellowIcon.getIconHeight());
                                    solutionPin.setLocation((int)yArea[8]+3,(int)xArea[i+1]+3);
                                    boardPanel.add(solutionPin,4);
                                } catch (Exception ex) {
                                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            break;
                            
                            case VERDE:
                            {
                                try {
                                    
                                    solutionPin = new JLabel(greenIcon);
                                    solutionPin.setBounds(0,0,greenIcon.getIconWidth(),greenIcon.getIconHeight());
                                    solutionPin.setLocation((int)yArea[8]+3,(int)xArea[i+1]+3);
                                    boardPanel.add(solutionPin,4);
                                } catch (Exception ex) {
                                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            break;
                            
                            case ROSA:
                            {
                                try {
                                    
                                    solutionPin = new JLabel(pinkIcon);
                                    solutionPin.setBounds(0,0,pinkIcon.getIconWidth(),pinkIcon.getIconHeight());
                                    solutionPin.setLocation((int)yArea[8]+3,(int)xArea[i+1]+3);
                                    boardPanel.add(solutionPin,4);
                                } catch (Exception ex) {
                                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            break;
                            
                            case BIANCO:
                            {
                                try {                       
                                    solutionPin = new JLabel(whiteIcon);
                                    solutionPin.setBounds(0,0,whiteIcon.getIconWidth(),whiteIcon.getIconHeight());
                                    solutionPin.setLocation((int)yArea[8]+3,(int)xArea[i+1]+3);
                                    boardPanel.add(solutionPin,4);
                                } catch (Exception ex) {
                                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            break;
                            
                            default:
                                break;
                        }   } catch (Exception ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                boardPanel.repaint();
            }
        }
        
        
        
        @Override
        public void mousePressed(MouseEvent e){}
        @Override
        public void mouseEntered(MouseEvent e){}
        @Override
        public void mouseExited(MouseEvent e){}
        @Override
        public void mouseReleased(MouseEvent e){}
        
        @Override
        public void mouseDragged(MouseEvent e){}
        @Override
        public void mouseMoved(MouseEvent e){}
    }
    
    
    public double[] setYArea(double []yArea)
    {
        
        yArea = new double [9];                                      //avrà una dimensione che dipende da quella dell'immagine e della griglia
        yOffset = boardLabel.getIcon().getIconWidth()/9; 
        
        for(int i = 0; i<9;i++)
        {
            yArea[i]=(yOffset*i);                                               // |0| |offset| |offset*2|
        }
        
        return yArea;
    }
    
    public double[] setXArea(double []xArea)
    {
        xArea = new double [6];
        xOffset = boardLabel.getIcon().getIconHeight()/6;
        
        for(int i = 0; i<6;i++)
        {
            xArea[i]=(xOffset*i);                                               // |0| |offset| |offset*2|
        }
        
        return xArea;
    }
    
    public void printArray(double []pos)
    {
        for(int i=0;i<pos.length;i++)
        {
            System.out.println(pos[i]);
        }
    }
    
    public int findColonna(int xPos, double []xArea)                            //ritorna l'indice corretto della colonna dove andrebbe inserita la tessera
    {
        for(int i=0;i<xArea.length;i++)
        {
            if(xPos<=xArea[i])                                                   //            170
                return i-1;                                                     // 0    157,4    300
        }
        return xArea.length-1;
    }
    
    public int findRiga(int yPos, double []yArea)                               //ritorna l'indice corretto della colonna dove andrebbe inserita la tessera
    {
        for(int i=0;i<yArea.length;i++)
        {
            if(yPos<=yArea[i])                                                   //            170
                return i-1;                                                     // 0    157,4    300
        }
        return yArea.length-1;
    }
    
    public void printSolution(int column) throws Exception                      //ridefinire come stampare i peg bianchi e arancioni
    {
        int hit,blow;
        hit = hitAndBlow.getHit();
        blow = hitAndBlow.getBlow();
       
                                    
        for(int i=0;i<2;i++)
        {
            for(int j=0;j<2;j++)
            {
                if(hit!=0)
                {
                    orangePeg = new JLabel(orangeSolution);
                    orangePeg.setBounds(0,0,orangeSolution.getIconWidth(),orangeSolution.getIconHeight());
                    orangePeg.setLocation(((int)yArea[round])+(70*j),((int)xArea[0])+(70*i));
                    boardPanel.add(orangePeg,4);
                    hit--;
                }
                else if(blow!=0)
                {
                    whitePeg = new JLabel(whiteSolution);
                    whitePeg.setBounds(0,0,whiteSolution.getIconWidth(),whiteSolution.getIconHeight());
                    whitePeg.setLocation(((int)yArea[round])+(70*j),((int)xArea[0])+(70*i));
                    boardPanel.add(whitePeg,4);
                    blow--;
                }
            }
        }   
        
    }
}
