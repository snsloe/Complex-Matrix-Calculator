public class MatrixCalculator {
    public static void main(String[] args) {
        // Комплексная арифметика
        Complex c1 = new Complex(1.8, -2.77);
        Complex c2 = new Complex(-4.04, 9.5);
        Complex c3 = c1.complex_addition(c2);
        System.out.println("Addition of complex numbers: " + "\n" + c3.format_complex());
        Complex c4 = c1.complex_subtraction(c2);
        System.out.println("Subtraction of complex numbers: " + "\n" + c4.format_complex());
        Complex c5 = c1.complex_multiplication(c2);
        System.out.println("Multiplication of complex numbers: " + "\n" + c5.format_complex() + "\n");

        // Матрицы
        Matrix matrix1 = new Matrix(2, 2);
        matrix1.setValue(0, 0, new Complex(2.7, 4));
        matrix1.setValue(0, 1, new Complex(0, -4.5));
        matrix1.setValue(1, 0, new Complex(-7, -5.2));
        matrix1.setValue(1, 1, new Complex(6.3, 0));

        Matrix matrix2 = new Matrix(2, 2);
        matrix2.setValue(0, 0, new Complex(-0.9, 0));
        matrix2.setValue(0, 1, new Complex(-6.4, 1));
        matrix2.setValue(1, 0, new Complex(2.4, -3));
        matrix2.setValue(1, 1, new Complex(0, 2.4));

        // Проверка операций на корректных данных
        Matrix additionResult = matrix1.matrix_addition(matrix2);
        if (additionResult != null) {
            System.out.println("Addition of matrices:\n" + additionResult.format_matrix());
        }

        Matrix subtractionResult = matrix1.matrix_subtraction(matrix2);
        if (subtractionResult != null) {
            System.out.println("Subtraction of matrices:\n" + subtractionResult.format_matrix());
        }

        Matrix multiplicationResult = matrix1.matrix_multiplication(matrix2);
        if (multiplicationResult != null) {
            System.out.println("Multiplication of matrices:\n" + multiplicationResult.format_matrix());
        }

        Matrix transpositionResult = matrix1.matrix_transposition();
        if (transpositionResult != null) {
            System.out.println("Transposed matrix1:\n" + transpositionResult.format_matrix());
        }

        Complex determinant = matrix2.matrix_determinant();
        if (determinant != null) {
            System.out.println("Determinant of matrix2: " + determinant.format_complex());
        }

        System.out.println();

        // Исключения
        // Некорректное число строк/столбцов
        Matrix matrix3 = new Matrix(-1, 0);

        // Некорректная установка значения
        Matrix matrix4 = new Matrix(1, 1);
        matrix4.setValue(9, 0, new Complex(7, -2));

        // Операции при разной размерности
        Matrix matrix5 = new Matrix(1, 2);
        matrix5.setValue(0, 0, new Complex(1, 2));
        matrix5.setValue(0, 1, new Complex(9, 8));

        Matrix additionResult_2 = matrix1.matrix_addition(matrix5);
        if (additionResult_2 != null) {
            System.out.println("Addition of matrices:\n" + additionResult_2.format_matrix());
        }

        Matrix subtractionResult_2 = matrix1.matrix_subtraction(matrix5);
        if (subtractionResult_2 != null) {
            System.out.println("Subtraction of matrices:\n" + subtractionResult_2.format_matrix());
        }

        Matrix multiplicationResult_2 = matrix1.matrix_multiplication(matrix5);
        if (multiplicationResult_2 != null) {
            System.out.println("Multiplication of matrices:\n" + multiplicationResult_2.format_matrix());
        }

        Complex determinant_2 = matrix5.matrix_determinant();
        if (determinant_2 != null) {
            System.out.println("Determinant of matrix1: " + determinant_2.format_complex());
        }
    }
}

