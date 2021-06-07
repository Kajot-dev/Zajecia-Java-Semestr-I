public class Floor {

    private double width;
    private double height;

    public Floor() {
        //dopisek d czyni numery typem double
        this(0d, 0d);
    }

    public Floor(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        /**
         * skrócone if, else
         * WARUNEK ? RZECZ, JEŻELI SPEŁNIONY : RZECZ, JEŻELI NIE JEST SPEŁNIONY
         */
        this.width = width >= 0 ? width : 0d;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        /**
         * skrócone if, else
         * WARUNEK ? RZECZ, JEŻELI SPEŁNIONY : RZECZ, JEŻELI NIE JEST SPEŁNIONY
         */
        this.height = height >= 0 ? height : 0d;
    }

    public double getArea() {
        return this.height * this.width;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(height);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(width);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Floor other = (Floor) obj;
        
        return !(Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height) || Double.doubleToLongBits(width) != Double.doubleToLongBits(other.width));
    }
    
}
