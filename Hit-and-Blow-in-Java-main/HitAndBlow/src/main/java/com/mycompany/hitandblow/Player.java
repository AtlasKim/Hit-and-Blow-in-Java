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
import java.util.Scanner;

public class Player {
    private String nome;
    private Scanner input = new Scanner(System.in);
    
    public Player(String name)
    {
        setName(name);
    }
    
    public void move(Grid grid,int x, int y,Piece pezzo) throws Exception
    {
        if (x < 0 || x > (grid.righe-1) || y < 0 || y > (grid.colonne-1)) {
            throw new Exception("Index out of bound");
        }
        grid.getSpot(x,y).setPiece(pezzo);
    }
    
    /*public void piazza_tessere(Grid grid) throws Exception
    {
        int color;
        
        for(int j=0;j<Grid.colonne;j++)
        {
            for(int i=0;i<Grid.righe;i++)
            {
                System.out.println("Giocatore "+this.nome+", inserisci il colore:");
                color = input.nextInt();
                input.nextLine();
                switch (color)
                {
                    case 0:
                        move(grid, i, j, new Piece(Color.BLU));
                        break;
                    case 1:
                        move(grid, i, j, new Piece(Color.ROSSO));
                        break;
                    case 2:
                        move(grid, i, j, new Piece(Color.GIALLO));
                        break;
                    case 3:
                        move(grid, i, j, new Piece(Color.VERDE));
                        break;
                    case 4:
                        move(grid, i, j, new Piece(Color.ROSA));
                        break;
                    case 5:
                        move(grid, i, j, new Piece(Color.BIANCO));
                        break;
                    default:
                        break;
                }
            }
        }
    }
    */
    
    public String getName()
    {
        return this.nome;
    }
    
    public void setName(String name)
    {
        this.nome = name;
    }
}
