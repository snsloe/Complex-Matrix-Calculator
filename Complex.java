public class Complex {
    private double real, img;

    public Complex(double real, double img) {
        this.real = real;
        this.img = img;
    }
    /*
    public double getReal() {
        return real;
    }
    public double getImg() {
        return img;
    }
    public void setReal(double real) {
        this.real = real;
    }
    public void setImg(double img) {
        this.img = img;
    }
    */

    public Complex complex_addition(Complex second) {
        return new Complex(this.real + second.real, this.img + second.img);
    }

    public Complex complex_subtraction(Complex second) {
        return new Complex(this.real - second.real, this.img - second.img);
    }

    public Complex complex_multiplication(Complex second) {
        return new Complex(this.real * second.real + (-1) * this.img * second.img, this.real * second.img + this.img * second.real);
    }

    // Округление
    public String format_complex() {
        double round_real = Math.round(this.real * 1000.0) / 1000.0;
        double round_img = Math.round(this.img * 1000.0) / 1000.0;
        if (round_img >= 0) {
            return "(" + round_real + " + " + round_img + "i)";
        }
        else {
            return "(" + round_real + " - " + Math.abs(round_img) + "i)";
        }
    }
}

