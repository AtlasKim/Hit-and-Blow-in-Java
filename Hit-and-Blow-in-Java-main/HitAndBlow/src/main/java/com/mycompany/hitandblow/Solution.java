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
import java.util.Random;

public class Solution {
    public static int dimensione = 4;
    private Spot[] solution = new Spot[dimensione];
    
    public Solution()
    {
        resetVector();
        randomizeSolution();
    }
    
    public void resetVector()
    {
        for(int i=0;i<dimensione;i++)
        {  
            solution[i] = new Spot(i);   
        }
    }
    
    public Spot getSpot(int x) throws Exception
    {
        if (x < 0 || x > (dimensione-1)) {
            throw new Exception("Index out of bound");
        }
  
        return solution[x];
    }
    
    public void randomizeSolution()
    {
        Random rand = new Random();
        int [] colori_scelti={0,0,0,0};
        int colore_generato=0;
        
        
        for(int i=0;i<dimensione;i++)
        {  
            while(check(colori_scelti,colore_generato))                         //continua a generare un intero randomico finché non ne trovo uno 
                colore_generato = rand.nextInt(6);                              //non ripetuto
            switch (colore_generato)
            {
                case 0:
                    solution[i].setPiece(new Piece(Color.BLU));
                    break;
                case 1:
                    solution[i].setPiece(new Piece(Color.ROSSO));
                    break;
                case 2:
                    solution[i].setPiece(new Piece(Color.GIALLO));
                    break;
                case 3:
                    solution[i].setPiece(new Piece(Color.VERDE));
                    break;
                case 4:
                    solution[i].setPiece(new Piece(Color.ROSA));
                    break;
                case 5:
                    solution[i].setPiece(new Piece(Color.BIANCO));
                    break;
                default:
                    break;
            }
           
            colori_scelti[i]=colore_generato;                                   //Generiamo un vettore soluzione a caso
        }
        
    }
    
    private boolean check(int[] arr, int toCheckValue)
    {
        for (int element : arr) {
            if (element == toCheckValue) {
                return true;
            }
        }
        return false;
    }
    
    public void printSolution()
    {
        Color spotColor;
        
        for(int i=0;i<dimensione;i++)
        {
            spotColor = solution[i].getPiece().getPieceColor();
            switch (spotColor)
            {
                case ROSSO:
                System.out.print("R");
                    break;
                case VERDE:
                    System.out.print("G");
                    break;
                case BLU:
                    System.out.print("B");
                    break;
                case GIALLO:
                    System.out.print("Y");
                    break;
                case ROSA:
                    System.out.print("P");
                    break;
                case BIANCO:
                    System.out.print("W");
                    break;
                default:
                    System.out.print("O");
                    break;
            }
            
            System.out.println(" ");
        }
    }
}
