import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An intermission/transition, a guide on how to win Winter Field. 
 * Added sound effects to spice it up. Visuals by me.
 * 
 * @author jasoneg@email.uscb.edu
 * @version 2023.12.15 (Final Project ISATB145)
 */
public class LastIntermission extends World
{
    /*FIELDS*/
    private FirstIntermission myFirstIntermission;
    private GameManager myGameManager;
    private GreenfootSound uniqueClick;
    
    /*CONSTRUCTOR(S)*/
    /**
     * Constructor for objects of class LastNextStage.
     */
    public LastIntermission()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 500, 1);
        myFirstIntermission = new FirstIntermission();
        myGameManager = new GameManager();
        uniqueClick = new GreenfootSound("game_start.mp3");
    } // end constructor LastIntermission
     
    /**
     * This 1-arg constructor is called from the NfcStartScreen calss
     * and allows access to the GameManager object that helps
     * to maintain data from one world to the next
     */
    public LastIntermission(GameManager myGameManager)
    {
        this();
        this.myGameManager = myGameManager;
    } // end constructor LastIntermission (with args)
    
    /*METHODS*/
    /**
     * The act method calls from other methods
     */
    public void act()
    {
        nextIntermissionClick();
    } // end method act
    
    /**
     * The user clicks on the screen, it will be sent to the Winter Field (playable class)
     */
    public void nextIntermissionClick()
    {
        if ( Greenfoot.mouseClicked(this))
        {
            uniqueClick.play();
            WinterField winterField = new WinterField();
            Greenfoot.setWorld(winterField);
        } // end if
    } // end method nextIntermissionClick
    
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
} // end class LastIntermission
