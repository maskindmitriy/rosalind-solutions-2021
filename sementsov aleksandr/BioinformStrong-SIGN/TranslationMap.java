package com.company;

import java.util.Arrays;

public class TranslationMap {



    //-------------------------------
    //Nested classes
    public static class ReWriter {
        static int[] buffer;
        static int sizeOfBuffer;

        static void BufferInitializator(int[] sourceData) {
            sizeOfBuffer = sourceData.length;
            buffer = new int[sizeOfBuffer];
        }

        public static int[] ToReWrite(int[] sourceData) {
            BufferInitializator(sourceData);
            for (int k = 0; k < sizeOfBuffer; k++) {
                buffer[k] = sourceData[k];
            }
            return buffer;
        }

        public static int[] ReverseWrite(int[] sourceData) {
            BufferInitializator(sourceData);
            for (int k = sizeOfBuffer-1; k > -1; k--) {
                buffer[k] = sourceData[k];
            }
            return buffer;
        }
    }

    public static class MyPower {
        static int res;
        public static int pow(int level, int number) {
            res = 1;
            for (int i = 0; i < level; i++) {
                res *= number;
            }
            return res;
        }

        //builder pattern
        public static int pow(int level) {
            res = 1;
            for (int i = 0; i < level; i++) {
                res *= 2;
            }
            return res;
        }
    }
    //-------------------------------


    int[][] translationMap;

    void ConstructMap(int n, int numeration) {
        translationMap = new int[MyPower.pow(n,numeration)][n];
        for (int i = 0; i < MyPower.pow(n,numeration); i++) {
            Arrays.fill(translationMap[i], 0);
        }

        //для того, чтобы первая строка была нулевая, начнём с i=1
        for (int i = 1; i < MyPower.pow(n, numeration); i++) {
            for (int j = n-1; j > -1; j--) {

                //I don't like recursive call (:
                if (translationMap[i][j] != (numeration - 1)) {
                    translationMap[i][j] += 1;
                    break;
                }
                else {
                    translationMap[i][j] = 0;
                }
            }
            if (i+1 <  MyPower.pow(n, numeration)) {
                translationMap[i+1] = ReWriter.ReverseWrite(translationMap[i]);
            }
            else { System.out.println("TranslationMap is ready!"); }
        }
    }

    TranslationMap(int width) {
        ConstructMap(width, 2);
    }

    TranslationMap(int width, int numeration) {
        ConstructMap(width, numeration);
    }

    //каждый из этих визуализаторов, GetMap, различными способами визуализирует получившуюся карту перевода и разными способами сопоставляет её с исходными данными
    //поэтому нет необходимости реализовывать на данный момент метод, общий для всех этих перегрузок
    public boolean GetMap() {
        try {
            for (int i = 0; i < translationMap.length; i++) {
                for (int j = 0; j < translationMap[i].length; j++) {
                    System.out.print(translationMap[i][j]);
                    System.out.print(" ");
                }
                System.out.println();
            }
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public void GetMap(int[] combination) {
        for (int i = 0; i < translationMap.length; i++) {
            for (int j = 0; j < translationMap[i].length; j++) {
                if (translationMap[i][j] == 0) {
                    System.out.print(combination[j]*(-1));
                }
                else {
                    System.out.print(combination[j]);
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public int GetMap(int[] numberArray, int counter) {
        MyWriter.GoSave();
        for (int i = 0; i < translationMap.length; i++) {
            for (int j = 0; j < translationMap[i].length; j++) {
                if (translationMap[i][j] == 0) {
                    System.out.print(numberArray[j]*(-1));
                    MyWriter.WriteResult('-');
                    MyWriter.WriteResult((char)numberArray[j]);
                }
                else {
                    System.out.print(numberArray[j]);
                    MyWriter.WriteResult((char)numberArray[j]);
                }
                System.out.print(" ");
                MyWriter.WriteResult(' ');
            }
            counter++;
            System.out.println();
            MyWriter.WriteResult('\n');
        }
        return counter;
    }
}
