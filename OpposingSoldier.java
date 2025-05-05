import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class makes the enemies moves from right to left by a constant speed and it's goal is to
 * attack the Artillery Soldier by shooting them and causes damage. The shooting will not be instant 
 * and will include a timer for it.
 * 
 * @author jasoneg@email.uscb.edu
 * @version 2023.12.15 (Final Project ISATB145)
 */
public class OpposingSoldier extends Actor
{
    /*FIELDS*/
    private int fireDelay;
    private GreenfootSound boomHit;
    private boolean started;

    /*CONSTRUCTOR*/
    /**
     * Constructor for objects of class OpposingSoldier.
     */
    public OpposingSoldier()
    {
        fireDelay = 60; // initial timer
        boomHit = new GreenfootSound("boom_hit.mp3");
    } // end constructor OpposingSolider
    
    /*METHODS*/
    /**
     * Act - do whatever the OpposingSoldier wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(!started)
        {
         start();   
        }
        
        shoot();
        touchSoldier();
    } // end method act 
    
    public void start()
    {   
        String currentWorldName = getWorld().getClass().getName();
        if(currentWorldName.equals("WinterField")){
            setImage("opposing_soldier_snow.png");
        } // end if
        
        started = true;
    } // end method start

    /**
     * Similar to the spaceShooting method from ArtillerySoldier class. The enemies will shoot with an initial timer
     * In the first 110 ms the enemies won't shoot until it reaches to 0. After shooting for the first time,
     * then the firing delay sets from 110 to 150. 
     */
    public void shoot()
    {
        String currentWorldName = getWorld().getClass().getName();
        
        if((currentWorldName.equals("BattleField")))
        {
            
            if (fireDelay == 0) // if the timer reaches to zero
            {   
               getWorld().addObject(new EnemyBullet(), getX()-30, getY()-5);
               fireDelay = 110; // the new timer sets to 150 when the timer
                            // reaches to zero for the first time.
                            
            } // end inner if
        
            /*
             * In this case, if the timer reaches to zero (or resets)
             * then, the countdown will begin again.
             */
        
            if (fireDelay > 0)
            {
               fireDelay--; 
            } // end inner if
            
        }
        else if ((currentWorldName.equals("WinterField"))) // statement is used in STAGE 2
        {
            
            if (fireDelay == 0) // if the timer reaches to zero
            {   
               getWorld().addObject(new EnemyBullet(), getX()-30, getY()-5);
               fireDelay = 150; // the new timer sets to 150 when the timer
                                // reaches to zero for the first time.
                                
            } // end inner if

            if (fireDelay > 0)
            {
               fireDelay--; 
            } // end inner if
            
        } // end if/else
    } // end method shoot 
    
    /**
     * This method removes the bullet in two cases
     * First if the bullet reaches to zero (passes the left side of screen) then it will be removed. 
     * If the bullet touches the ArtillerySoldier, then it will remove the bullet AND
     * change the score.
     */
    public void touchSoldier()
    {
        String currentWorldName = getWorld().getClass().getName();
        
        if (currentWorldName.equals("BattleField")){
            setLocation(getX() - 4, getY()); // the speed of the bullet
            
            if (getX() <= 0)
            {
                BattleField battlefield = (BattleField)getWorld();
                battlefield.removeObject(this);
            } // end if
            
        } 
        else if (currentWorldName.equals("WinterField")) // statement is used in STAGE 2
        {
            setLocation(getX() - 2, getY());
            
            if (getX() <= 0) // Whenever it reaches the coordinates less than zero (passing the less side of screen)  
            {                // then it removes the class itself
                WinterField battlefield = (WinterField)getWorld();
                battlefield.removeObject(this);
            } // end if
            
        } // end if/else
    } // end method touchSoldier 
} // end class OpposingSoldier 