# Ballboy

## How to run this game

Make sure the environment is using Java 11 or above, and run `gradle run` at the root directory of this project.

## JSON format

- _comment
- ballboy
- _ballboySizeComment 
- level
  - levelHeight
  - levelWidth
  - gravity
  - floorAppearance
  - skyAppearance
  - floorHeight
  - ballboyPos
    - x
    - y
  - _ballboyPosComment
  - finish
    - x
    - y
  - _finishComment
  - platform [{x,y}]
  - _platformComment
  - enemy [ {type, color, width, height, xVel, yVel, x, y} ]
  - clouds [ {x, y, xVel, yVel} ]
  - _cloudComment

## Example JSON configuration

`Config.json` at the root directory is the example JSON configuration.



`Config.json` will 

- set the size the ballboy as `large`.
- set the start position of ballboy at this level at (20.0, 30.0).
- set the height of this level as `400.0`.
- set the width of this level as `1000.0`.
- apply gravity with value 9.8n/s to *ballboy* and *enemy*.
- set the color of the floor as green.
- set the color of the sky as light blue.
- set the floor height as 50.0.
- set the finish object at (700.0, 300.0).
- render three platform object at (300.0, 200.0), (200.0, 200.0), (100.0, 200.0) respectively.
- Render a red *goRight* enemy with 0.0 x velocity and 0.0 y velocity at (100.0,300.0), whose height is 20.0 and width is 30.0.
- Spawn three constantly moving clouds with 0.03 x velocity and 0.0 y velocity at (40.0,130.0), (120.0, 200.0), (300.0, 300.0) respectively.



## Factory method pattern and the involved files and classes 

ballboy.factory.EntityFactory is the *Creator*.

ballboy.factory.EntityFactory.create() is the *creator methods*.

ballboy.model.Entity is the *product interface*.

ballboy.model.Ballboy , ballboy.model.Cloud , ballboy.model.Enemy , ballboy.model.Finish , ballboy.model.Platform are the *Concrete Product*. 

## Strategy pattern and the involved files and classes 

ballboy.model.Strategy.Strategy is the *strategy interface*.

ballboy.model.Strategy.HoldStillStrategy, ballboy.model.Strategy.GoRightStrategy and ballboy.model.Strategy.GoLeftStrategy are the *concrete strategy*.

ballboy.model.Enemy is the *context*.

ballboy.model.LevelImpl.tick() and ballboy.model.JSONInterpreter.createEnemy is the client.