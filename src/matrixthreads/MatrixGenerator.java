/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrixthreads;

import java.util.Arrays;

/**
 *
 * @author alexa
 */
public class MatrixGenerator {
    private static int max = 0;
    private static int min = 100;
    
    public static int[][] generateIntMatrix(int dim1, int dim2) throws Exception{
        if(dim1 <= 0 || dim2 <= 0)
            throw new Exception("Dimensão da Matriz Errada");
        int[][] matrix = new int[dim1][dim2];
        for(int i=0; i<dim1; i++)
            for(int j=0; j<dim2; j++)
                matrix[i][j] = (int) (Math.random()*((max-min)+1))+min;
        return matrix;
    }
    
    public static void printMatrix(int[][] matrix){
        System.out.println("Matriz={");
        for (int[] row : matrix)
            System.out.println("\t"+Arrays.toString(row));
        System.out.println("}");
    }
    
    public static void setRandomicLimiter(int min, int max){
        if(min != max){
            MatrixGenerator.max = Math.max(min, max);
            MatrixGenerator.min = Math.min(min, max);
        }
    }

    public static int getMaxValue() {
        return max;
    }
    
    public static int getMinValue() {
        return min;
    }
}