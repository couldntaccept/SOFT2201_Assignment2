package ballboy.model;

public class Finish extends StillEntity{

    public Finish(String imagePath, double xPos, double yPos, double width, double height) {
        super.setImagePath(imagePath);
        super.setxPos(xPos);
        super.setyPos(yPos);
        super.setWidth(width);
        super.setHeight(height);
    }
    
    @Override
    public Layer getLayer() {
        return Layer.FOREGROUND;
    }
}
