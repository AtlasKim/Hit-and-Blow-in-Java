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

public class Grid {
    public static int righe = 4;
    public static int colonne = 8;
    private Spot[][] grid = new Spot[righe][colonne];
    
    public Grid()
        {
            resetGrid();
        }
    
    public void resetGrid()
    {
        for(int i=0;i<righe;i++)
        {
            for(int j=0;j<colonne;j++)
            {
                grid[i][j] = new Spot(i,j);
            }
        }
    }
    
    public Spot getSpot(int x,int y) throws Exception
    {
        if (x < 0 || x > (righe-1) || y < 0 || y > (colonne-1)) {
            throw new Exception("Index out of bound");
        }
  
        return grid[x][y];
    }
}