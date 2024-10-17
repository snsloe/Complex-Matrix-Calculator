public class Matrix {
    private Complex [][] matrix;

    public Matrix(int row, int col) {
        if (row < 0 || col < 0) {
            System.out.println("Error: Invalid row or column index.");
            return;
        }
        matrix = new Complex[row][col];
    }

    public int getRow() {
        if (matrix == null) {
            System.out.println("Error: Matrix is not initialized. Number of rows or columns must be positive.");
            return 0;
        }
        return matrix.length;
    }

    public int getCol() {
        if (matrix == null) {
            System.out.println("Error: Matrix is not initialized. Number of rows or columns must be a positive.");
            return 0;
        }
        if (matrix.length == 0) {
            return 0;
        }
        return matrix[0].length;
    }

    public void setValue(int row, int col, Complex value) {
        if (matrix == null) {
            System.out.println("Error: Matrix is not initialized. The value cannot be set.");
            return;
        }
        if (row < 0 || col < 0 || row >= getRow() || col >= getCol()) {
            System.out.println("Error: Invalid row or column index.");
            return;
        }
        this.matrix[row][col] = value;
    }

    public Complex getValue(int row, int col) {
        if (matrix == null) {
            System.out.println("Error: Matrix is not initialized. Value cannot be get.");
            return null;
        }
        if (row < 0 || col < 0 || row >= getRow() || col >= getCol()) {
            System.out.println("Error: Invalid row or column index.");
            return null;
        }
        return matrix[row][col];
    }

    public boolean check(Matrix second, String flag) {
        if (flag.equals("addition") || flag.equals("subtraction")) {
            return this.getRow() == second.getRow() && this.getCol() == second.getCol();
        } else if (flag.equals("multiplication")) {
            return this.getCol() == second.getRow();
        }
        return false;
    }

    public Matrix matrix_addition(Matrix second) {
        if (!check(second, "addition")) {
            System.out.println("Error: Matrices dimensions do not match for addition");
            return null;
        }
        Matrix res = new Matrix(this.getRow(), this.getCol());
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < this.getCol(); j++) {
                try {
                    res.setValue(i, j, this.getValue(i, j).complex_addition(second.getValue(i, j)));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: " + e.getMessage());
                    return null;
                }
            }
        }
        return res;
    }

    public Matrix matrix_subtraction(Matrix second) {
        if (!check(second, "subtraction")) {
            System.out.println("Error: Matrices dimensions do not match for subtraction");
            return null;
        }
        Matrix res = new Matrix(this.getRow(), this.getCol());
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < this.getCol(); j++) {
                try {
                    res.setValue(i, j, this.getValue(i, j).complex_subtraction(second.getValue(i, j)));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: " + e.getMessage());
                    return null;
                }
            }
        }
        return res;
    }


    public Matrix matrix_multiplication(Matrix second) {
        if (!check(second, "multiplication")) {
            System.out.println("Error: Matrices dimensions do not match for multiplication");
            return null;
        }
        Matrix res = new Matrix(this.getRow(), second.getCol());
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < second.getCol(); j++) {
                Complex sum = new Complex(0, 0);
                for (int k = 0; k < second.getRow(); k++) {
                    try {
                        sum = sum.complex_addition(this.getValue(i, k).complex_multiplication(second.getValue(k, j)));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Error: " + e.getMessage());
                        return null;
                    }
                }
                res.setValue(i, j, sum);
            }
        }
        return res;
    }

    public Matrix matrix_transposition() {
        Matrix res = new Matrix(this.getCol(), this.getRow());
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < this.getCol(); j++) {
                try {
                    res.setValue(j, i, getValue(i, j));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: " + e.getMessage());
                    return null;
                }
            }
        }
        return res;
    }

    public Complex matrix_determinant() {
        if (this.getRow() != this.getCol()) {
            System.out.println("Error: Determinant can only be calculated for square matrices.");
            return null;
        }
        int size = this.getCol();

        if (size == 1) {
            return this.getValue(0, 0);
        }
        if (size == 2) {
            return this.getValue(0, 0).complex_multiplication(this.getValue(1, 1))
                    .complex_subtraction(this.getValue(0, 1).complex_multiplication(this.getValue(1, 0)));
        }
        if (size == 3) {
            Complex mult1 = this.getValue(0, 0).complex_multiplication(this.getValue(1, 1)).complex_multiplication(this.getValue(2, 2));
            Complex mult2 = this.getValue(0, 1).complex_multiplication(this.getValue(1, 2)).complex_multiplication(this.getValue(2, 0));
            Complex mult3 = this.getValue(0, 2).complex_multiplication(this.getValue(1, 0)).complex_multiplication(this.getValue(2, 1));
            Complex mult4 = this.getValue(0, 2).complex_multiplication(this.getValue(1, 1)).complex_multiplication(this.getValue(2, 0));
            Complex mult5 = this.getValue(0, 1).complex_multiplication(this.getValue(1, 0)).complex_multiplication(this.getValue(2, 2));
            Complex mult6 = this.getValue(0, 0).complex_multiplication(this.getValue(1, 2)).complex_multiplication(this.getValue(2, 1));
            return mult1.complex_addition(mult2).complex_addition(mult3).complex_subtraction(mult4).complex_subtraction(mult5).complex_subtraction(mult6);
        }
        Complex determinant = new Complex(0, 0);
        for (int i = 0; i < size; i++) {
            Complex sign = new Complex(1, 0);
            if (i % 2 != 0) {
                sign = new Complex(-1, 0);
            }
            try {
                Matrix minor = matrix_minor(0, i, size);
                determinant = determinant.complex_addition(sign.complex_multiplication(this.getValue(0, i).complex_multiplication(minor.matrix_determinant())));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: " + e.getMessage());
                return null;
            }
        }
        return determinant;
    }

    public Matrix matrix_minor(int row, int col, int size) {
        Matrix minor = new Matrix(size - 1, size - 1);
        int minorRow = 0;
        for (int i = 0; i < size; i++) {
            if (i == row) {
                continue;
            }
            int minorCol = 0;
            for (int j = 0; j < size; j++) {
                if (j == col) {
                    continue;
                }
                try {
                    minor.setValue(minorRow, minorCol, this.getValue(i, j));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: " + e.getMessage());
                    return null;
                }
                minorCol++;
            }
            minorRow++;
        }
        return minor;
    }

    public String format_matrix() {
        String res = "";
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < this.getCol(); j++) {
                res += this.getValue(i, j).format_complex() + "   ";
            }
            res += "\n";
        }
        return res;
    }
}