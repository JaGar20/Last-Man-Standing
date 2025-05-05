import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * After passing the first stage, this class shows up. 
 * Telling you that winter is here and you are being targeted. Visuals by me.
 * 
 * @author jasoneg@email.uscb.edu
 * @version 2023.12.15 (Final Project ISATB145)
 */
public class FirstIntermission extends World
{
    /*FIELDS*/
    private GameManager myGameManager;
    
    /*CONSTRUCTOR(S)*/
    /**
     * Constructor for objects of class NextStage.
     */
    public FirstIntermission()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 500, 1); 
        myGameManager = new GameManager();
    } // end constructor FirstIntermission
    
    /**
     * This 1-arg constructor is called from the NfcStartScreen calss
     * and allows access to the GameManager object that helps
     * to maintain data from one world to the next
     */
    public FirstIntermission(GameManager myGameManager)
    {
        this();
        this.myGameManager = myGameManager;
    } // end constructor FirstIntermission (with args)
    
    /*METHODS*/
    /**
     * Once clicked, then the user will get to the next intermission.
     */
    public void act()
    {
        if ( Greenfoot.mouseClicked(this))
        {
            LastIntermission lastNextStage = new LastIntermission();
            Greenfoot.setWorld(lastNextStage);
        } // end if
    } // end method act
    
    /**
    * Enables an instance of another class (such as Crab or Lobster) to get a reference
    * to the current GameManager object (note that the return type
    * of this method is `GameManager`)
    * 
    * @return a reference to the current GameManager object 
    */
    public GameManager getReferenceToCurrentGameManager()
    {
        return myGameManager;
    } // end method getReferenceToCurrentGameManager  
} // end class FirstIntermission
