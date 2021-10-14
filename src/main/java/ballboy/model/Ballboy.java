package ballboy.model;

public class Ballboy extends MovableEntity{


    private boolean landed;
    private boolean toRight;
    private boolean toLeft;

    private double bounceForce = 2.0;
    private double moveSpeed = 0.0;


    public Ballboy(String imagePath, double xPos, double yPos, double xVel, double yVel, double width, double height) {
        super.setxPos(xPos);
        super.setyPos(yPos);
        super.setxVel(xVel);
        super.setyVel(yVel);
        super.setWidth(width);
        super.setHeight(height);
        super.setImagePath(imagePath);
    }

    @Override
    public Layer getLayer() {
        return Layer.FOREGROUND;
    }

    public boolean isLanded() {
        return landed;
    }

    public void setLanded(boolean landed) {
        this.landed = landed;
    }

    public void bounce(){
        if(landed){
            setyVel(-bounceForce);
        }
    }

    public void setToRight(boolean toRight) {
        this.toRight = toRight;
    }

    public void setToLeft(boolean toLeft) {
        this.toLeft = toLeft;
    }

    public boolean boostHeight(){
        bounceForce += 1.0;
        return true;
    }
    public boolean dropHeight(){
        if(bounceForce > 2.0) {
            bounceForce -= 1.0;
        }
        return true;
    }

    public boolean accelerateToRight(){
        if(moveSpeed < 4.0){
            moveSpeed += 0.1;
        }
        setxVel(moveSpeed);
        return true;
    }

    public boolean accelerateToLeft(){
        if(moveSpeed < 4.0){
            moveSpeed += 0.1;
        }
        setxVel(-moveSpeed);
        return true;
    }

    public void stop(){
        toLeft = false;
        toRight = false;
        moveSpeed = 0.0;
        setxVel(moveSpeed);

    }

    @Override
    public void move() {
        if(toLeft){
            accelerateToLeft();
        }
        if(toRight){
            accelerateToRight();
        }
        bounce();
        setyPos(getYPos()+getyVel());
        setxPos(getXPos()+getxVel());
    }

    @Override
    public double getHeight() {
        return super.getHeight();
    }

    @Override
    public double getWidth() {
        return super.getWidth();
    }
}
