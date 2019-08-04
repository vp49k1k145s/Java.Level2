package lesson2;

public class MyArraySizeException extends RuntimeException{
    public MyArraySizeException() {
        super("Массив может быть размером 4х4, иначе никак.");
    }
}