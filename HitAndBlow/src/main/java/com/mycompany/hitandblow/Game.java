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
enum Color
{
    BLU,ROSSO,GIALLO,VERDE,ROSA,BIANCO;
}

public class Game {
    private Grid griglia;
    private Player giocatore;
    private Solution soluzione;
    private int hit;
    private int blow;
    
    public Game()
    {
        griglia = new Grid();
        giocatore = new Player("Erika");
        soluzione = new Solution();
        hit = 0;
        blow = 0;
    }
    
    public Player getPlayer() 
    {
        return this.giocatore;
    }
    
    public Grid getGrid()
    {
        return this.griglia;
    }
    
    public Solution getSolution()
    {
        return this.soluzione;
    }
    
    public void printGrid () throws Exception
    {
        Color spotColor;
        
        for(int i=0;i<Grid.righe;i++)
        {
            for(int j=0;j<Grid.colonne;j++)
            {
                if(griglia.getSpot(i, j).getPiece()!=null)
                {
                    spotColor = griglia.getSpot(i, j).getPiece().getPieceColor();
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
                }
                System.out.print(" ");
            }
            
            System.out.println("");
        }
        
        System.out.println("");
    }
    
    public boolean victoryCheck(int y) throws Exception
    {
        for(int i=0;i<Grid.righe;i++)                                               //controlliamo se la colonna selezionata è uguale a quella vincente
        {
            if(griglia.getSpot(i, y).getPiece().getPieceColor()==soluzione.getSpot(i).getPiece().getPieceColor())       //per ogni pedina azzeccata nella posizione giusta incrementiamo un contatore
                hit++;
            else if(colorCheck(this.soluzione,griglia.getSpot(i, y).getPiece().getPieceColor())==true)                  //per ogni pedina di colore giusto incrementiamo un secondo contatore
                blow++;
        }    
        return hit == Solution.dimensione;                                      //se tutte le pedine sono del colore corretto abbiamo vinto!
    }
    
    private boolean colorCheck(Solution sol, Color toCheckColor) throws Exception
    {
        for(int i = 0; i<Solution.dimensione;i++)
        {
            if(sol.getSpot(i).getPiece().getPieceColor()==toCheckColor)
                return true;
        }
        return false;
    }
    
    public int getHit()
    {
        return this.hit;
    }
    
    public  void resetHit()
    {
        this.hit = 0;
    }
    
    public  void resetBlow()
    {
        this.blow = 0;
    }
    
    public  int getBlow()
    {
        return this.blow;
    }
}
