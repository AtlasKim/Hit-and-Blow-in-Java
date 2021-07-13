/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hitandblow;

import java.util.Scanner;

/**
 *
 * @author Boku no Melo
 */
public class HitAndBlowTest {   
    public static void main(String[] args) throws Exception
    {
        Scanner input = new Scanner(System.in); //scanner per leggere gli input dei due giocatori
        Game hitAndBlow = new Game();
        boolean victory;
        String replay = "Y";
        int colore;
        
        while(replay.equals("Y"))
        {
            victory = false;
            hitAndBlow.getGrid().resetGrid();
            System.out.println("Hit and Blow!");
            
            for(int j=0;j<Grid.colonne;j++)
            {
                for(int i=0;i<Grid.righe;i++)
                {
                    System.out.println("Giocatore "+hitAndBlow.getPlayer().getName()+", inserisci il colore:");
                    colore = input.nextInt();
                    input.nextLine();
                    switch (colore)
                    {
                        case 0:
                            hitAndBlow.getPlayer().move(hitAndBlow.getGrid(), i, j, new Piece(Color.BLU));
                            break;
                        case 1:
                            hitAndBlow.getPlayer().move(hitAndBlow.getGrid(), i, j, new Piece(Color.ROSSO));
                            break;
                        case 2:
                            hitAndBlow.getPlayer().move(hitAndBlow.getGrid(), i, j, new Piece(Color.GIALLO));
                            break;
                        case 3:
                            hitAndBlow.getPlayer().move(hitAndBlow.getGrid(), i, j, new Piece(Color.VERDE));
                            break;
                        case 4:
                            hitAndBlow.getPlayer().move(hitAndBlow.getGrid(), i, j, new Piece(Color.ROSA));
                            break;
                        case 5:
                            hitAndBlow.getPlayer().move(hitAndBlow.getGrid(), i, j, new Piece(Color.BIANCO));
                            break;
                        default:
                            break;
                    }
                        
                    
                }
                hitAndBlow.printGrid();
                victory=hitAndBlow.victoryCheck(j);
                System.out.println("Numero di Hit:"+hitAndBlow.getHit());
                System.out.println("Numero di Blow:"+hitAndBlow.getBlow());
                
                
                hitAndBlow.resetHit();
                hitAndBlow.resetBlow();
                
                
                
                if(victory == true)
                {
                    System.out.println(hitAndBlow.getPlayer().getName()+" HAI VINTO! SPARISCIH");
                    hitAndBlow.getSolution().printSolution();
                    return;
                }
            }
            if(victory!=true)
            {
                System.out.println(hitAndBlow.getPlayer().getName()+" HAI PERSO SCARSO! SPARISCIH");
                hitAndBlow.getSolution().printSolution();
                return;
            }
        }
    }
}
