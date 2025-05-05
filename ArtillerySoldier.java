import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The ArtillerySolider is the only controllable class in this scenario
 * It's only ability is to shoot the enemies where it can deal damage
 * You can control this class using the arrow keys up and down.
 * 
 * @author jasoneg@email.uscb.edu
 * @version 2023.12.15 (Final Project ISATB145)
 */
public class ArtillerySoldier extends Actor
{
    /*FIELDS*/
    private boolean spaceShooting;
    private int shotTimer;
    private GreenfootSound bang;
    private GreenfootSound ouchSound;
    private boolean started;
    private int speed;
    
    /*CONSTRUCTOR*/
    /**
     * Constructor for objects of class ArtillerySolider.
     */
    public ArtillerySoldier()
    {
        spaceShooting = false;
        shotTimer = 100;
        bang = new GreenfootSound("artillery_bang.mp3");
        ouchSound = new GreenfootSound("hurt.mp3");
    } // end constructor ArtillerySoldier
    
    /*METHODS*/
    /**
     * Act - do whatever the ArtillerySoldier wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
       if(!started)
       {
         start();   
       } // end if
        
       spaceShooting();
       removeSoldierAndBullet();
       move();
    } // end method act
    
    /**
     * Whenever the NFC is scanned, then it will increase the speed of the Artillery Soldier
     * from 4 to 7 (Vertically).
     */
    public void start()
    {
       if(getWorld().getClass().getName().equals("BattleField"))
       {
            BattleField myBattleField = (BattleField)getWorld();
            
            if(myBattleField.getReferenceToCurrentGameManager().isNfcEnabled())
            {
                 speed = 7;
            } // end inner if
            else
            {
                 speed = 4;
            } // end inner if/else
            
       } 
       else if (getWorld().getClass().getName().equals("WinterField"))
       {
           WinterField myWinterField = (WinterField)getWorld();
           
           if(myWinterField.getReferenceToCurrentGameManager().isNfcEnabled())
           {
                speed = 7;
           } // end inner if
           else
           {
                speed = 4;
           } // end inner if/else
           
       } // end if/else
        
       String currentWorldName = getWorld().getClass().getName(); 
       if(currentWorldName.equals("WinterField"))
       {
           setImage("soldier_snow.png");
       } // end if

       started = true;
    } // end method start
    
    /**
     * This method allows the Artillery to shoot when pressing the space button
     * The bullet will include a timer, it will initially have a wait time of 100px
     * After the time finish, you will be able to shoot, but you'll have to wait for
     * another 60px after shooting.
     */
    public void spaceShooting()
    {
        String currentWorldName = getWorld().getClass().getName(); 
           
        if (currentWorldName.equals("BattleField"))
        {
            
            if (shotTimer > 0 && shotTimer-- > 0) 
            {
                return; // when the timer reaches to 0, then its ready to shoot. 100->0
            } // end inner if
            
            if (spaceShooting && !Greenfoot.isKeyDown("space"))
            {
                spaceShooting = false;
                bang.play();
                bang.stop();
            } // end inner if
            
            if (Greenfoot.isKeyDown("space") &&!spaceShooting)
            {
                spaceShooting = true;
                bang.play();
                fireBullet();
                shotTimer = 60; // IF the user shoots once, then the timer will return to 60.
            } // end inner if
            
        }
        else if (currentWorldName.equals("WinterField")) // statement is used in STAGE 2
        {
            
            if (shotTimer > 0 && shotTimer-- > 0) 
            {
                return; // when the timer reaches to 0, then its ready to shoot. 100->0.
            } // end inner if
            
            if (spaceShooting && !Greenfoot.isKeyDown("space")) 
            {
                spaceShooting = false;
                bang.play();
                bang.stop();
            } // end inner if
            
            if (Greenfoot.isKeyDown("space") &&!spaceShooting)
            {
                spaceShooting = true;
                bang.play();
                fireBullet();
                shotTimer = 100; // IF the user shoots once, then the timer will return to 100. Very slow.
            } // end inner if 
            
        } // end if/else
    } // end keyPressArrow method
    
    /**
     * This method removes the Enemies and Bullet whenever this class touches them
     * When touching the objects, then it will lose points and play a sound.
     */
    public void removeSoldierAndBullet()
    {
        String currentWorldName = getWorld().getClass().getName();
        
        if (currentWorldName.equals("BattleField")){
            
            if(isTouching(OpposingSoldier.class))
            {
                BattleField change = (BattleField)getWorld();
                change.scoring(-20);
                ouchSound.play();
                removeTouching(OpposingSoldier.class);
            } // end inner if
            
            if(isTouching(EnemyBullet.class))
            {
                BattleField currentWorld = (BattleField)getWorld();
                currentWorld.scoring(-20);
                ouchSound.play();
                removeTouching(EnemyBullet.class);
            } // end inner if
        
        }
        else if (currentWorldName.equals("WinterField")) // statement is used in STAGE 2
        {
            
            if(isTouching(OpposingSoldier.class))
            {
                WinterField change = (WinterField)getWorld();
                change.scoring(-40);
                removeTouching(OpposingSoldier.class);
            } // end inner if
            
            if(isTouching(EnemyBullet.class))
            {
                WinterField currentWorld = (WinterField)getWorld();
                currentWorld.scoring(-40);
                ouchSound.play();
                removeTouching(EnemyBullet.class);
            } // end inner if
            
        } // end if/else
    } // end removeSoliderAndBullet method 
    
    /**
     * This method moves the Artillery up and down by pressing the 
     * arrow keys "up" and "down" with a certain speed
     * In addition, it restricts the x and y coordinates to prevent bypassing the scoreboard and screen.
     */
    public void move()
    {
        int x = this.getX(); // The reason being is that this only applies in this class ONLY, not others.
        int y = this.getY();
        
        if(Greenfoot.isKeyDown("up")) 
        {
            y -= speed; // moves up by 4
        } // end if
        
        if (Greenfoot.isKeyDown("down")) 
        {
            y += speed; // moves down by 4
        } // end if
        
        /*
         * This is the main key of this method, the args in the if statement is restriction coordinates
         * The object not pass if is over or less then x or y cooridnates, then it will be set once
         * it has the axis.
         */
        
        if(y > 140 && y < 350)
        {
            this.setLocation(x,y);
        } // end if 
    } // end method move  
    
    /**
     * This method sets the location of the bullet to the ArtillerySolider
     * It sets in the right place where it shoots at. 
     */
    public void fireBullet()
    {
        getWorld().addObject(new Bullet(), getX()+41, getY());
    } // end method fireBullet 
} // end class ArtillerySoldier  