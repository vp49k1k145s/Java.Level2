package lesson5;
/**
 * Java. Level 2. Lesson 5
 *
 * @author Dzyubenko Vadim
 * @version dated 11.08.2019
 */
import java.util.Arrays;

public class MainClass {
    private static final int SIZE = 10000000;
    private static final int HALF_SIZE = SIZE / 2;
    
    public static void main(String s[]) {
        MainClass o = new MainClass();
        o.runOneThread();
        o.runTwoThreads();
    }

    public float[] calculate(float[] arr) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = (float)(arr[i] *
                    Math.sin(0.2f + i / 5) *
                    Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        return arr;
    }

    public void runOneThread() {
        float[] arr = new float[SIZE];
        for (int i = 0; i < arr.length; i++) arr[i] = 1.0f;
        long a = System.currentTimeMillis();
        calculate(arr);
        System.out.println("One thread method ends with: " + (System.currentTimeMillis() - a));
    }

    public void runTwoThreads() {
        float[] arr = new float[SIZE];
        float[] arr1 = new float[HALF_SIZE];
        float[] arr2 = new float[HALF_SIZE];
        for (int i = 0; i < arr.length; i++) arr[i] = 1.0f;

        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, HALF_SIZE);
        System.arraycopy(arr, HALF_SIZE, arr2, 0, HALF_SIZE);

        Thread one = new Thread(new CalcArray(arr1, 0));
        Thread two = new Thread(new CalcArray(arr2, HALF_SIZE));
        one.start();
        two.start();
        try {
            one.join();
            two.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        System.arraycopy(arr1, 0, arr, 0, HALF_SIZE);
        System.arraycopy(arr2, 0, arr, HALF_SIZE, HALF_SIZE);
        System.out.println("Two threads ends with: " + (System.currentTimeMillis() - a));
    }



    class CalcArray implements Runnable {
        private float[] array;
        private int shift;

        CalcArray(float[] array, int shift) {
            this.array = array;
            this.shift = shift;
        }

        @Override
        public void run() {
            for (int i = 0; i < array.length; i++)
                array[i] = (float)(array[i] *
                        Math.sin(0.2f + (i + shift) / 5) *
                        Math.cos(0.2f + (i + shift) / 5) *
                        Math.cos(0.4f + (i + shift) / 2));
        }
    }
}