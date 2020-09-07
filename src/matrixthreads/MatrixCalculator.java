package matrixthreads;

import java.util.ArrayList;
import java.util.List;

public class MatrixCalculator extends Thread{
    private final int[][] matrix;
    private int sum = 0;
    private int delay = 0;
    
    public MatrixCalculator(int[][] matrix){
        this.matrix = matrix;
    }
    
    public MatrixCalculator(int[][] matrix, int delay){
        this(matrix);
        this.delay = Math.max(0, delay);
    }
    
    public synchronized void addSum(int add){
        System.out.println("\t"+Thread.currentThread().getName()+" Acessando recurso addSum()");
        sum += add;
    }
    
    public int getSum() {
        return sum;
    }
    
    @Override
    public void run() {
        this.sum = 0;
        List<Thread> threads = new ArrayList<>(matrix.length);
        System.out.println("Execução das Somas iniciada");
        int countThread = 0;
        for (int[] row : matrix){
            Thread sumRow;
            sumRow = new Thread("\tThread da Linha "+(countThread+1)+" ->"){
                public int sum;
                @Override
                public void run(){
                    System.out.println(this.getName()+" Iniciada");
                    this.sum = 0;
                    
                    try {
                        for(int i : row){
                            if(delay != 0) 
                                Thread.sleep(delay);
                            System.out.println(this.getName()+" Somando");
                            this.sum += i;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    System.out.println(this.getName()+" Acesso ao recurso addSum() iniciado");
                    addSum(this.sum);
                    System.out.println(this.getName()+" Acesso ao recurso addSum() finalizado");
                    
                    System.out.println(this.getName()+" Finalizada");
                }
            };
            sumRow.start();
            threads.add(sumRow);
            countThread++;
        }
        try {
            for (Thread t : threads) {
                t.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Execução das Somas finalizada");
    }
}