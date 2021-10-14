package ballboy.model;

import ballboy.model.Strategy.Strategy;

public class Enemy extends MovableEntity{

    public Strategy moveStrategy;

    public Enemy(String imagePath, Strategy moveStrategy, double xPos, double yPos, double xVel, double yVel, double width, double height) {
        super.setxPos(xPos);
        super.setyPos(yPos);
        super.setxVel(xVel);
        super.setyVel(yVel);
        super.setWidth(width);
        super.setHeight(height);
        super.setImagePath(imagePath);
        this.moveStrategy = moveStrategy;
    }

    @Override
    public void move() {
        moveStrategy.move(this);
    }

    @Override
    public Layer getLayer() {
        return Layer.FOREGROUND;
    }

}
