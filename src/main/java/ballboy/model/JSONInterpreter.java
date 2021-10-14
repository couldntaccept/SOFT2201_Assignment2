package ballboy.model;

import ballboy.factory.EntityFactory;
import ballboy.model.Strategy.GoLeftStrategy;
import ballboy.model.Strategy.GoRightStrategy;
import ballboy.model.Strategy.HoldStillStrategy;
import ballboy.model.Strategy.Strategy;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JSONInterpreter {
    private JSONObject config;
    private Level levelToBeBuilt;

    public JSONInterpreter(String filename) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(filename)) {
            config = (JSONObject) jsonParser.parse(reader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        assert config != null;
        JSONObject levelConfig = (JSONObject) config.get("level");
        JSONObject ballboyPos = (JSONObject) levelConfig.get("ballboyPos");
        double size;

        String s = config.get("ballboySize").toString();
        switch (s) {
            case "small":
                size = 10.0;
                break;
            case "medium":
                size = 20.0;
                break;
            case "large":
                size = 30.0;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + s);
        }

        Ballboy ballboy = (Ballboy) EntityFactory.create("ballboy","ch_stand1.png",
                (double)ballboyPos.get("x"), (double)ballboyPos.get("y"), 0, 0, size, size);
        assert ballboy != null;
        levelToBeBuilt = new LevelImpl(
                new ArrayList<>(List.of(ballboy)), ballboy,
                (double) levelConfig.get("floorHeight"),
                (String) levelConfig.get("skyAppearance"),
                (String) levelConfig.get("floorAppearance"),
                (double) levelConfig.get("levelHeight"),
                (double) levelConfig.get("levelWidth"),
                (double) levelConfig.get("gravity")
        );

        createEnemy(levelConfig);
        createFloor(levelConfig);
        createPlatform(levelConfig);
        createFinish(levelConfig);
        createClouds(levelConfig);
    }

    public void createFloor(JSONObject levelConfig){

        double levelHeight = (double) levelConfig.get("levelHeight");
        double floorHeight = (double) levelConfig.get("floorHeight");
        double levelWidth = (double) levelConfig.get("levelWidth");
        levelToBeBuilt.getEntities().add(EntityFactory.create(
                "platform","foot_tile.png",
                10.0,  levelHeight - floorHeight,
                levelWidth, 10.0
        ));
    }

    public void createEnemy(JSONObject levelConfig){
        JSONArray enemiesConfig = (JSONArray) levelConfig.get("enemy");

        Strategy tmp;
        for (Object enemyConfig : enemiesConfig){
            switch( (String)(((JSONObject)enemyConfig).get("type")) ){
                case "still":
                    tmp = new HoldStillStrategy();
                    break;
                case "goLeft":
                    tmp = new GoLeftStrategy();
                    break;
                case "goRight":
                    tmp = new GoRightStrategy();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " +  (((JSONObject) enemyConfig).get("type")));
            }
            levelToBeBuilt.getEntities().add(EntityFactory.create(
                    "enemy", "slime"+((JSONObject)enemyConfig).get("color")+".png", tmp,
                    (double) ((JSONObject)enemyConfig).get("x"), (double) ((JSONObject)enemyConfig).get("y"),
                    (double) ((JSONObject)enemyConfig).get("xVel"), (double) ((JSONObject)enemyConfig).get("yVel"),
                    (double) ((JSONObject)enemyConfig).get("width"), (double) ((JSONObject)enemyConfig).get("height")
            ));
        }
    }


    public void createPlatform(JSONObject levelConfig){
        JSONArray platformsConfig = (JSONArray) levelConfig.get("platform");

        for (Object platformConfig : platformsConfig){
            levelToBeBuilt.getEntities().add(EntityFactory.create(
                    "platform","foot_tile.png",
                    (double) ((JSONObject)platformConfig).get("x"),  (double) ((JSONObject)platformConfig).get("y"),
                    20.0, 20.0
                   ));
        }
    }

    public void createFinish(JSONObject levelConfig) {
        JSONObject finishConfig = (JSONObject) levelConfig.get("finish");
        levelToBeBuilt.getEntities().add(EntityFactory.create(
//                "finish","tree.png",
                "finish","qrcode.png",
                (double) finishConfig.get("x"),  (double) finishConfig.get("y"),
                50.0, 50.0
        ));
    }

    public void createClouds(JSONObject levelConfig){
        JSONArray cloudsConfig = (JSONArray) levelConfig.get("clouds");

        for (Object cloudConfig : cloudsConfig){

            levelToBeBuilt.getEntities().add(EntityFactory.create(
                    "cloud","cloud_1.png",
                    (double) ((JSONObject)cloudConfig).get("x"),  (double) ((JSONObject)cloudConfig).get("y"),
                    (double) ((JSONObject)cloudConfig).get("xVel"),  (double) ((JSONObject)cloudConfig).get("yVel"),
                    40.0, 40.0
            ));
        }
    }

    public Level getLevel() {
        return levelToBeBuilt;
    }

}
