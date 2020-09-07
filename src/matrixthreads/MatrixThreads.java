package matrixthreads;

public class MatrixThreads {

    public static void main(String[] args) {
        int[][] myMatrix = null;
        try {
            myMatrix = MatrixGenerator.generateIntMatrix(10, 10);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        MatrixGenerator.printMatrix(myMatrix);
        MatrixCalculator mc = new MatrixCalculator(myMatrix, 100);
        mc.start();
        
        try {
            mc.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Resultado da Soma = " + mc.getSum());
    }  
}