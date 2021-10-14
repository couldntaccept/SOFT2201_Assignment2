package ballboy.view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import ballboy.model.GameEngine;

public class BlockedBackground implements BackgroundDrawer {
    private Rectangle sky;
    private Rectangle floor;
    private Pane pane;
    private GameEngine model;


    @Override
    public void draw(GameEngine model, Pane pane) {
        this.model = model;
        this.pane = pane;

        double width = pane.getWidth();
        double height = pane.getHeight();
        double floorHeight = model.getCurrentLevel().getFloorHeight();

        sky = new Rectangle(0, 0, width, height);
        sky.setFill(Paint.valueOf(model.getCurrentLevel().getSkyAppearance()));
        sky.setViewOrder(1000.0);

        floor = new Rectangle(0, height - floorHeight, width, floorHeight);
        floor.setFill(Paint.valueOf(model.getCurrentLevel().getFloorAppearance()));
        floor.setViewOrder(1000.0);

        pane.getChildren().addAll(sky, floor);
    }

    @Override
    public void update(double xViewportOffset, double yViewportOffset) {

        floor.setY(model.getCurrentLevel().getLevelHeight()
                    -model.getCurrentLevel().getFloorHeight()-yViewportOffset);
    }
}
