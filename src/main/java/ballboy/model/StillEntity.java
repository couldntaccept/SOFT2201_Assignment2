package ballboy.model;

import javafx.scene.image.Image;

public abstract class StillEntity implements Entity{
    private String imagePath;
    private double xPos;
    private double yPos;
    private double width;
    private double height;

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public Image getImage() {
        return new Image(imagePath);
    }

    @Override
    public double getXPos() {
        return xPos;
    }

    @Override
    public double getYPos() {
        return yPos;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public abstract Layer getLayer();
}
