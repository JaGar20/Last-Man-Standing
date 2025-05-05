import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class goal is important for the Enemy to shoot the soldier. It sets the speed of the bullet
 * and simply removes whenever it passes the screen.
 * Taken from Greenfoot default image "beamer" and edit it.
 * 
 * @author jasoneg@email.uscb.edu 
 * @version 2023.12.15 (Final Project ISATB145)
 */
public class EnemyBullet extends Actor
{
    /*FIELDS*/
    private int bulletSpeed;
    private boolean started;
    
    /*CONSTRUCTOR*/
    /**
     * Constructor for objects of class EnemyBullet.
     */
    public EnemyBullet()
    {
        bulletSpeed = Greenfoot.getRandomNumber(6) + 6; // Sets bullet speed ranging from 5 to 6.
    } // end constructor EnemyBullet
    
    /*METHODS*/
    /**
     * Act - do whatever the EnemyBullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(started)
        {
         start();   
        } // end if
        
        removeBulletWhenZero();
    } // end method act 
    
    /**
     * Whenever the started is to true the game start
     */
    public void start()
    {
        started = true;
    } // end method start 
    
    /**
     * In this method, whenever the bullet reaches the X coordinate 0 (left side)
     * it removes the bullet in both classes
     */
    public void removeBulletWhenZero()
    {
        String currentWorldName = getWorld().getClass().getName();
        setLocation(getX() - bulletSpeed, getY());
        
        if(currentWorldName.equals("BattleField"))
        {
            
            if (getX() <= 0)
            {
                BattleField currentWorld = (BattleField)getWorld();
                currentWorld.removeObject(this);
            } // end inner if
            
        } 
        else if (currentWorldName.equals("WinterField")) // statement is used in STAGE 2
        {
            
            if (getX() <= 0)
            {
                WinterField currentWorld = (WinterField)getWorld();
                currentWorld.removeObject(this); 
            } // end inner if
            
        } // end if/else
    } // end method checkBound 
} // end class EnemyBullet
