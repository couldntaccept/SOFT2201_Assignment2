## A discussion on how your design for assignment 1 helped or hindered your extensions made in this assignment

 

Change:

The Floor has been changed from consecutive instances of platform to one instance of platform with width of levelWidth, because of performance issues.

JSONReader has now be renamed to JSONInterpreter and be moved to model package. As the name suggest, it is now responsible to not only reading JSON file, but also using EntityFactory to initialize the Level and assign Entity(s) to the Level.

 

Kept:

Level is still responsible for how the ballboy move. 

GameEngine still handle all the entity interaction and instruct the entity in the currentLevel to progress.

Entitys are still split into MovableEntity and StillEntity, this help reducing execution time in LevelImpl.tick(). Tick() determine the interaction between Entitys, and this segregation introduce a first round of type check, so each iteration can run the first round of type check before type check for each type. For instance, only MovableEntity should invoke move() on each iteration.

<div style="page-break-after: always; break-after: page;"></div>

## Rationalise changes you have made to your assignment 1 design

 

BlockedBackground will now take the config as JSON states to enable different appearance for level, as the specification required.



To enable the ballboy “continuously bouncing”, Enemy and Ballboy now will be affected by gravity.

Ballboy now hold a few more attributes include 

- The values to enable the "ballboy continuously bouncing and has some control over the height of the bounce"
  - landed: if landed is true, Ballboy now will bounce() on each tick(). Landed will be turned to true every time collide with instances of Platform. Landed will be turned to false on invoke of bounce().
  - bounceForce: initial value 2.0, and minimum value 2.0. It will increase by 1.0 if UP key is pressed. It will decrease by 1.0 if DOWN key is pressed.
- The values to enable the "ballboy can accelerate left and right (until top speed is reached)"
  - MoveSpeed: the absolute value of moving speed, it will continuously increase LEFT or RIGHT key is pressed, capped at 4.0.
  - toRight: boolean value to indicate whether to accelerate to right.
  - toLeft:boolean value to indicate whether to accelerate to left.

Ballboy now hold a few more methods include 

- setter method for toRight, toLeft and landed, getter method for landed.
- boostHeight() and dropHeight() for change bounceForce.
- accelerateToRight() and accelerateToLeft() to tweak moveSpeed and xVel.
- stop() to set toLeft and toRight to false, and moveSpeed and xVel to 0.0.

 <div style="page-break-after: always; break-after: page;"></div>

## Factory method pattern

### Where you used it

ballboy.factory.EntityFactory is the *Creator*.

ballboy.factory.EntityFactory.create() is the *creator methods*.

ballboy.model.Entity is the *product interface*.

ballboy.model.Ballboy , ballboy.model.Cloud , ballboy.model.Enemy , ballboy.model.Finish , ballboy.model.Platform are the *Concrete Product*.

Ballbyboy.model.JSONInterpreter is the *context*.

### What this pattern does for your code in terms of SOLID/GRASP principles

Entityfactory take the responsibility of creation of all the Entity, this maintain the *Single Responsibility Principle*.  

Also, new Entity can be introduced without breaking the code in the *context*, and this maintain *Open/Closed Principle*. 

*Low coupling* can be maintained by separating  the *creator* and the *concrete products*.

### What overall benefits this pattern provides (be specific to your code, not the pattern in general)

EntityFactory has three static method that are called create(), but with different signatures. Thus in JSONInterpreter, without breaking the existing code, new Entity can be introduced and loaded by just invoke the existing create() or new create() if parameters differ.



<img src="/Users/hello/Library/Application Support/typora-user-images/image-20211014145332290.png" alt="image-20211014145332290" style="zoom:33%;" />



<img src="/Users/hello/Library/Application Support/typora-user-images/image-20211014142821404.png" alt="image-20211014142821404" style="zoom: 33%;" />

### What drawbacks this pattern causes

The code might be overcomplicated since introduce a new class(EntityFactory) to implement the pattern, if the amount of Entity is not huge. But this can be passed on for a better structure and maintaining the SOLID principle and the GRASP principle

<div style="page-break-after: always; break-after: page;"></div>

## Strategy pattern

### Where you used it 

ballboy.model.Strategy.Strategy is the *strategy interface*.

ballboy.model.Strategy.HoldStillStrategy, ballboy.model.Strategy.GoRightStrategy and ballboy.model.Strategy.GoLeftStrategy are the *concrete strategy*.

ballboy.model.Enemy is the *context*.

ballboy.model.LevelImpl.tick() and ballboy.model.JSONInterpreter.createEnemy is the client.

### What this pattern does for your code in terms of SOLID/GRASP principles

Maintains *Open/Closed Principle*, since new strategies can be introduced without breaking the code in the *context*.



### What overall benefits this pattern provides (be specific to your code, not the pattern in general)

Strategy of Enemy can be switched during runtime, which is tick() in LevelImpl.  

The details of the algorithm of the move strategy is isolated from Enemy.



### What drawbacks this pattern causes

 It might be overcomplicated since only three strategies has been introduced and they does not change.

*Client* must be aware of the difference between the MoveStrategy.





<div style="page-break-after: always; break-after: page;"></div>

## Assignment 2 updated UML class diagram

 

![A picture containing text, sky  Description automatically generated](file:////Users/hello/Library/Group%20Containers/UBF8T346G9.Office/TemporaryItems/msohtmlclip/clip_image001.jpg)