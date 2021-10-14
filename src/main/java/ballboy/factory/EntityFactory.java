package ballboy.factory;

import ballboy.model.*;
import ballboy.model.Strategy.Strategy;

public class EntityFactory {

    public static Entity create(String entityName,
                                String imagePath,
                                double xPos,
                                double yPos,
                                double xVel,
                                double yVel,
                                double width,
                                double height){

        switch (entityName) {
            case "ballboy":
                return new Ballboy(imagePath, xPos, yPos, xVel, yVel, width, height);
            case "cloud":
                return new Cloud(imagePath, xPos, yPos, xVel, yVel, width, height);
            default:
                return null;
        }
    }

    public static Entity create(String entityName,
                                String imagePath,
                                Strategy moveStrategy,
                                double xPos,
                                double yPos,
                                double xVel,
                                double yVel,
                                double width,
                                double height){

        switch (entityName) {
            case "enemy":
                return new Enemy(imagePath, moveStrategy, xPos, yPos, xVel, yVel, width, height);
            default:
                return null;
        }
    }
    public static Entity create(String entityName,
                                String imagePath,
                                double xPos,
                                double yPos,
                                double width,
                                double height){

        switch (entityName) {
            case "finish":
                return new Finish(imagePath, xPos, yPos, width, height);
            case "platform":
                return new Platform(imagePath, xPos, yPos, width, height);
            default:
                return null;
        }
    }

}
