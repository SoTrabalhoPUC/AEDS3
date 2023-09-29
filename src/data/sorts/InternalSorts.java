package data.sorts;

import data.db.Game;

import java.io.RandomAccessFile;

public class InternalSorts {


    public static void quickSort(int esq, int dir, Game[] vec){
        int i = esq, j = dir, pivo = vec[(esq+dir)/2].getGameId();
        while (i<=j){
            while(vec[i].getGameId()<pivo){
                i++;
            }
            while (vec[j].getGameId()>pivo){
                j--;
            }
            if(i<=j){
                Game tmp = vec[i];
                vec[i] = vec[j];
                vec[j] = tmp;
                i++;
                j--;
            }
        }
        if(esq<j){
            quickSort(esq,j,vec);
        }
        if(i<dir){
            quickSort(i,dir,vec);
        }
    }



}
