package ballboy.model;

public class GameEngineImpl implements GameEngine{
    private Level currentLevel;

    public GameEngineImpl(String config) {
        currentLevel = new JSONInterpreter(config).getLevel();

    }

    @Override
    public Level getCurrentLevel() {
        return currentLevel;
    }

    @Override
    public void startLevel() {

    }

    @Override
    public boolean boostHeight() {
        return currentLevel.boostHeight();
    }

    @Override
    public boolean dropHeight() {
        return currentLevel.dropHeight();
    }

    @Override
    public boolean moveLeft() {
        return currentLevel.moveLeft();
    }

    @Override
    public boolean moveRight() {
        return currentLevel.moveRight();
    }

    @Override
    public boolean stop() {
        return currentLevel.stop();
    }

    @Override
    public void tick() {
        currentLevel.tick();
    }
}
