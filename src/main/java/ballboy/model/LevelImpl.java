package ballboy.model;

import java.util.List;

public class LevelImpl implements Level{

    private List<Entity> entities;
    private Ballboy ballboy;
    private double startPointX;
    private double startPointY;
    private double floorHeight;
    private double levelHeight;
    private double levelWidth;
    private double gravity;
    private String skyAppearance;
    private String floorAppearance;



    public LevelImpl(List<Entity> entities, Ballboy ballboy, double floorHeight, String skyAppearance, String floorAppearance, double levelHeight, double levelWidth, double gravity) {
        this.entities = entities;
        this.ballboy = ballboy;
        this.floorHeight = floorHeight;
        this.skyAppearance = skyAppearance;
        this.floorAppearance = floorAppearance;
        this.levelHeight = levelHeight;
        this.levelWidth = levelWidth;
        this.gravity = gravity;
        this.startPointX = ballboy.getXPos();
        this.startPointY = ballboy.getYPos();
    }
    public String getFloorAppearance() {
        return floorAppearance;
    }
    public String getSkyAppearance() {
        return skyAppearance;
    }

    public void restartBallboy(){
        ballboy.setxPos(startPointX);
        ballboy.setyPos(startPointY);
    }

    public Ballboy getBallboy() {
        return ballboy;
    }


    @Override
    public List<Entity> getEntities() {
        return entities;
    }

    @Override
    public double getLevelHeight() {
        return levelHeight;
    }

    @Override
    public double getLevelWidth() {
        return levelWidth;
    }

    public boolean isCollided(Entity entityA, Entity entityB){
        return (entityA.getXPos() < (entityB.getXPos() + entityB.getWidth())) &&
                ((entityA.getXPos() + entityA.getWidth()) > entityB.getXPos()) &&
                (entityA.getYPos() < (entityB.getYPos() + entityB.getHeight())) &&
                ((entityA.getYPos() + entityA.getHeight()) > entityB.getYPos());
    }

    public boolean isOnSameLayer(Entity entityA, Entity entityB){
        return entityA.getLayer() == entityB.getLayer();
    }

    @Override
    public void tick() {
        for (Entity entity : entities){
            if (entity instanceof Ballboy ){
                for (Entity entity_check : entities){
                    if (isOnSameLayer(entity, entity_check) && isCollided(entity, entity_check)){
                        if (entity_check instanceof Finish) {System.exit(0);}
                        if (entity_check instanceof Enemy) {restartBallboy();}

                        if (entity_check instanceof Platform && !((Ballboy) entity).isLanded()){
                            ((Ballboy) entity).setyPos(entity_check.getYPos()-entity.getHeight());
                            ((Ballboy) entity).setLanded(true);
                        }else{
                            ((Ballboy) entity).setLanded(false);
                        }
                    }
                }
            }
            if(entity instanceof MovableEntity){
                ((MovableEntity) entity).move();
                restrictWithinLevel((MovableEntity) entity);
                if(entity.getLayer() == Entity.Layer.FOREGROUND){
                    ((MovableEntity) entity).setyVel(
                            ((MovableEntity) entity).getyVel() + (gravity / 60.0)
                    );
                }
            }

        }
    }

    public void restrictWithinLevel(MovableEntity entity){

        if( entity.getXPos() < 0){
            entity.setxPos(0);
        }else if( entity.getXPos() > levelWidth){
            entity.setxPos(levelWidth);
        }

        if( entity.getYPos() < 400-levelHeight){
            entity.setyPos(0);
        }else if( entity.getYPos() > levelHeight){
            entity.setyPos(levelHeight);
        }

    }


    @Override
    public double getFloorHeight() {
        return floorHeight;
    }

    @Override
    public double getHeroX() {
        return ballboy.getXPos();
    }

    @Override
    public double getHeroY() {
        return ballboy.getYPos();
    }

    @Override
    public boolean boostHeight() {
        return ballboy.boostHeight();
    }

    @Override
    public boolean dropHeight() {
        return ballboy.dropHeight();
    }

    @Override
    public boolean moveLeft() {
        ballboy.setToLeft(true);
        return true;
    }

    @Override
    public boolean moveRight() {
        ballboy.setToRight(true);
        return true;
    }

    @Override
    public boolean stop() {
        ballboy.stop();

        return true;
    }


}
