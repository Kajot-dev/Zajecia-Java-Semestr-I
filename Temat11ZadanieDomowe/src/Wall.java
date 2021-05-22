public class Wall {

    private double width;
    private double height;

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if (width < 0)
            width = 0d;
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if (height < 0)
            height = 0d;
        this.height = height;
    }

    public double getArea() {
        return this.width * this.height;
    }

    public Wall() {
        this.width = 0d;
        this.height = 0d;
    }

    public Wall(double width, double height) {
        this.height = height;
        this.width = width;
    }

    public static void main(String[] args) {
        //skopiowane z przykładu
        Wall wall = new Wall(5, 4);
        System.out.println("area= " + wall.getArea());

        wall.setHeight(-1.5);
        System.out.println("width= " + wall.getWidth());
        System.out.println("height= " + wall.getHeight());
        System.out.println("area= " + wall.getArea());
    }
}
