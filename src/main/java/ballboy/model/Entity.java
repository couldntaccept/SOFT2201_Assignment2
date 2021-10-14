package ballboy.model;
import javafx.scene.image.Image;

public interface Entity {
    /**
     * Returns the current Image used by this Entity. This may change over time, such as for simple animations.
     * @return An Image representing the current state of this Entity
     */
    Image getImage();

    /**
     * Returns the current x position of this Entity
     * @return The x position using the top left hand corner as 0,0 and increasing as the position moves right on the screen.
     */
    double getXPos();

    /**
     * Returns the current y position of this Entity
     * @return The y position using the top left hand corner as 0.0 and increasing as the position moves down the screen
     */
    double getYPos();

    /**
     *  Returns the current height of this Entity
     * @return The height in coordinate space (e.g. number of pixels)
     */
    double getHeight();
    /**
     *  Returns the current width of this Entity
     * @return The width in coordinate space (e.g. number of pixels)
     */
    double getWidth();

    /**
     * Returns the current 'z' position to draw this entity. Order within each layer is undefined.
     * @return The layer to draw the entity on.
     */
    Layer getLayer();

    /**
     * The set of available layers
     */
    enum Layer{
        BACKGROUND, FOREGROUND, EFFECT
    }
}
