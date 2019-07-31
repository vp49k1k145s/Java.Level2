package lesson2;

/**
 * Java. Level 2. Lesson 2
 *
 * @author Dzyubenko Vadim
 * @version dated 31.07.2019
 */

public class MainClass {
    public static void main(String[] args) {

        int result = 0;

        String[][] array = {{"1", "55", "1", "1"},
                {"1", "1", "77", "1"},
                {"2", "545", "43", "232"},
                {"3", "9", "49", "232"}};

        String[][] error_array = {{"1", "2", "3", "4", "1"},
                {"1", "2", "3", "4"},
                {"3", "545", "43", "232"},
                {"3", "9", "43", "232"}};

        String[][] error_data = {{"1", "1", "3", "4",},
                {"1", "2", "аа3", "4"},
                {"3", "545", "43", "232"},
                {"3", "9", "43", "232"}};

        try {
            result = 0;
            result = analyze(array);
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Результат суммирования элементов массива равна " + String.valueOf(result));
            System.out.println("=======================");
        }

        System.out.println("Случай некорректного массива");
        try {
            result = 0;
            result = analyze(error_array);
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Случай некорректных данных");
        try {
            result = 0;
            result = analyze(error_data);
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

    }

    public static int analyze(String[][] array) throws MyArraySizeException, MyArrayDataException {

        int sum = 0;
        int row = 0;
        int cell = 0;
        int value = 0;
        if (array.length != 4 || array[0].length != 4 || array[1].length != 4 || array[2].length != 4 || array[3].length != 4) {
            throw new MyArraySizeException();
        }

        for ( int i = 0; i < array.length; i++ )
            for ( int j = 0; j < array[i].length; j++ ) {
                try {
                    value = Integer.parseInt(array[i][j]);
                    sum += value;

                } catch (IllegalArgumentException e) {

                    throw new MyArrayDataException(" Неверный элемент массива содержится в ряду " + (i+1) + " ячейке " + (j+1));
                }
            }


        return sum;
    }
}