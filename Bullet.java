import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is part of the ArtillerySolider interactions, whenever the ArtillerySoldier shoots,
 * then this class comes out. Its goal is to shoot to the enemies which will deal damage.
 * This mechanic is important for winning the game.
 * Taken from Greenfoot default image "beamer" and edit it.
 * 
 * @author jasoneg@email.uscb.edu 
 * @version 2023.12.15 (Final Project ISATB145)
 */
public class Bullet extends Actor
{
    /*FIELDS*/
    private GreenfootSound boomHit;
    
    /*CONSTRUCTOR*/
    /**
     * Constructor for objects of class Bullet.
     */
    public Bullet()
    {
        boomHit = new GreenfootSound("boom_hit.mp3");
    } // end constructor Bullet
    
    /*METHODS*/
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        lookForEnemy();
    } // end method act 
    
    /**
     * This method allows the bullet to move/modify. Whenever it hits an enemy the score increases
     * and plays a sound. I also want to make it more realistic, so I make it stop in a certain coordinate so it 
     * portrays as a arch (like a canon).
     */
    public void lookForEnemy()
    {
        String currentWorldName = getWorld().getClass().getName();
        
        if (currentWorldName.equals("BattleField")){
            setLocation(getX()+8, getY());
            
            if(isTouching(OpposingSoldier.class)){
                BattleField battleField = (BattleField)getWorld();
                removeTouching(OpposingSoldier.class);
                boomHit.play();
                battleField.scoring(20);
                battleField.removeObject(this);
            }
            else if(getX() > getWorld().getWidth() - 150 )
            {
                BattleField battleField = (BattleField)getWorld();
                boomHit.play();
                battleField.removeObject(this); 
            } // end inner if/else
            
        }
        else if (currentWorldName.equals("WinterField")) // statement is used in STAGE 2
        {
            setLocation(getX()+6, getY());
            
            if(getX() > getWorld().getWidth() - 150 )
            {
                WinterField battleField = (WinterField)getWorld();
                boomHit.play();
                battleField.removeObject(this); 
            }
            else if(isTouching(OpposingSoldier.class)){
                WinterField battleField1 = (WinterField)getWorld();
                removeTouching(OpposingSoldier.class);
                boomHit.play();
                battleField1.scoring(40);
                battleField1.removeObject(this); 
            } // end inner if/else
            
        } // end if/else
    } // end method lookForEnemy 
} // end class Bullet
