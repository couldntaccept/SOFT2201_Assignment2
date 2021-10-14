package ballboy.model;

/**
 * The base interface for interacting with the Ballboy model
 */
public interface GameEngine {
    /**
     * Return the currently loaded level
     * @return The current level
     */
    Level getCurrentLevel();

    /**
     * Start the level
     */
    void startLevel();

    // Hero inputs - boolean for success (possibly for sound feedback)
    // These may just be passed straight through to the hero
    boolean boostHeight();
    boolean dropHeight();
    boolean moveLeft();
    boolean moveRight();
    boolean stop();

    /**
     * Instruct the model to progress forward in time by one increment.
     */
    void tick();
}
