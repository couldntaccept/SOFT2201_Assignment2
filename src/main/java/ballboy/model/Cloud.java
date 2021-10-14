package ballboy.model;

public class Cloud extends MovableEntity{
    public Cloud(String imagePath, double xPos, double yPos, double xVel, double yVel, double width, double height){
        super.setxPos(xPos);
        super.setyPos(yPos);
        super.setxVel(xVel);
        super.setyVel(yVel);
        super.setWidth(width);
        super.setHeight(height);
        super.setImagePath(imagePath);
    }

    @Override
    public void move() {
        setyPos(getYPos()+getyVel());
        setxPos(getXPos()+getxVel());
    }

    @Override
    public Layer getLayer() {
        return Layer.EFFECT;
    }

}
