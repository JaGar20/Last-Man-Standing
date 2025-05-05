import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Health. When the solider touches the object, then the score increase. Useful for winning...
 * Art by me.
 * 
 * @author jasoneg@email.uscb.edu
 * @version 2023.12.15 (Final Project ISATB145)
 */
public class Health extends Actor
{
    /*FIELDS*/
    private GreenfootSound healthSound;
    private boolean started;
    
    /*CONSTRUCTOR*/
    /**
     * Constructor for objects of class EnemyBullet.
     */
    public Health()
    {
        healthSound = new GreenfootSound("health_pickup.mp3");
    } // end constructor Health
     
    /*METHODS*/
    /**
     * Act - do whatever the Health wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(!started)
        {
         start();   
        } // end if
        
        healthTouch();
    } // end method act 
    
    /**
     * When the started is set to true, then the game will start along with the image.
     */
    public void start()
    {
        String currentWorldName = getWorld().getClass().getName();
        if(currentWorldName.equals("WinterField")){
           setImage("smow_health.png");
        } // end if
        
        started = true;
    } // end method start 
    
    /**
     * If the soldier picks up this class, then the score increase by 30 and 60 (in winterfield). 
     * When the solider picks it up, it plays a sound and removes the object.
     */
    public void healthTouch()
    {
        String currentWorldName = getWorld().getClass().getName();
        
        if(currentWorldName.equals("BattleField")){
            
            if(isTouching(ArtillerySoldier.class))
            {
                BattleField change = (BattleField)getWorld();
                change.scoring(30);
                healthSound.play();
                change.removeObject(this);
            } // end inner if
            
        } 
        else if (currentWorldName.equals("WinterField")) 
        {
            
               if(isTouching(ArtillerySoldier.class))
            {
                WinterField change = (WinterField)getWorld();
                change.scoring(50);
                healthSound.play();
                change.removeObject(this);
            } // end inner if
            
        } // end if/else
    } // end method healthTouch 
} // end class Health 
