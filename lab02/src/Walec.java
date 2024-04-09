public class Walec {
    private float baseRadius;
    private float height;

    public float getBaseRadius() {
        return baseRadius;
    }

    public float getHeight() {
        return height;
    }

    public  Walec(float baseRadius, float height) {
        this.baseRadius = baseRadius;
        this.height = height;
    }

    public Walec() {
        this.baseRadius = 0.0f;
        this.height = 0.0f;
    }

    public void setBaseRadius(float newRadius) {
        this.baseRadius = newRadius;
    }

    public void setHeight(float newHeight) {
        this.height = newHeight;
    }

    public double baseArea() {
        return Math.PI * Math.pow(this.baseRadius, 2);
    }

    public double lateralArea() {
        return 2 * Math.PI * this.baseRadius * this.height;
    }

    public double totalArea() {
        return 2 * baseArea() + lateralArea();
    }

    public double volume() {
        return baseArea() * this.height;
    }
}
