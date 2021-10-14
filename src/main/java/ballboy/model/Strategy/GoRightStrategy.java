package ballboy.model.Strategy;

import ballboy.model.MovableEntity;

public class GoRightStrategy implements Strategy{
    @Override
    public void move(MovableEntity movableEntity) {
        movableEntity.setxPos(movableEntity.getXPos() + 0.2);
    }
}
