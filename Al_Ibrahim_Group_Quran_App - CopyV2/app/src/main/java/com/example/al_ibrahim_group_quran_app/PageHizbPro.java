package com.example.al_ibrahim_group_quran_app;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.stream.IntStream;

@RequiresApi(api = Build.VERSION_CODES.N)
public class PageHizbPro {
    private int start;
    private int finish;
    private int hizbNum ;
    public PageHizbPro() {
    }

    public PageHizbPro(int hizbNum) {
        this.hizbNum = hizbNum;
    }




    private void StartandFinish(){

        int [][] indexArr = {
                {1,11},
                {11,21},
                {22,31},
                {32,41},
                {42,51},
                {51,62},
                {62,72},
                {72,81},
                {82,92},
                {92,101},
                {102,112},
                {112,121},
                {121,131},
                {132,141},
                {142,150},
                {151,161},
                {162,172},
                {173,181},
                {182,192},
                {192,201},
                {201,211},
                {212,221},
                {222,231},
                {231,241},
                {242,251},
                {252,261},
                {262,272},
                {272,281},
                {282,292},
                {292,301},
                {302,312},
                {312,321},
                {322,331},
                {332,341},
                {342,351},
                {352,361},
                {362,371},
                {371,381},
                {382,391},
                {392,401},
                {402,413},
                {413,421},
                {422,431},
                {431,441},
                {442,451},
                {451,461},
                {462,471},
                {472,481},
                {482,491},
                {491,502},
                {502,513},
                {513,521},
                {522,531},
                {531,541},
                {542,552},
                {553,561},
                {562,571},
                {572,581},
                {582,591},
                {591,604},
        };
        start = indexArr[hizbNum][0];
        finish = indexArr[hizbNum][1];
    }

    public int[] getMyArray() {
        StartandFinish();
        int[] array = IntStream.rangeClosed(start-1, finish-1).toArray();
        reverse(array);
        return array;
    }


    public static void reverse(int[] data) {
        for (int left = 0, right = data.length - 1; left < right; left++, right--) {
            // swap the values at the left and right indices
            int temp = data[left];
            data[left] = data[right];
            data[right] = temp;
        }


    }
    public void setHizbNum(int hizbNum) {
        this.hizbNum = hizbNum;
    }
}