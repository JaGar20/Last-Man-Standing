import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Game Over! This class shows up whenever the user losses all the points or didn't 
 * meet expectations (Timer reaches zero but the score is below the expectations). Visuals by me.
 * 
 * @author jasoneg@email.uscb.edu 
 * @version 2023.12.15 (Final Project ISATB145)
 */
public class GameOver extends World
{
    /*FIELDS*/
    private GameManager myGameManager;
    
    /*CONSTRUCTOR(S)*/
    /**
     * Constructor for objects of class GameOver.
     */
    public GameOver()
    {    
        super(800, 500, 1); 
        myGameManager = new GameManager();
    } // end GameOver constructor
    
    /**
     * This 1-arg constructor is called from the NfcStartScreen calss
     * and allows access to the GameManager object that helps
     * to maintain data from one world to the next
     */
    public GameOver(GameManager myGameManager)
    {
        this();
        this.myGameManager = myGameManager;
    } // end GameOver constructor (with args)
    
    /*METHODS*/
    /**
     * In this act method, whenever the user clicks the Gamer Over screen, then it will be sent back to the Title screen
     */
    public void act()
    {
        if ( Greenfoot.mouseClicked(this))
        {
            TitleScreen titleScreen = new TitleScreen();
            Greenfoot.setWorld(titleScreen);
        } // end if
    } // end act method
    
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
    } // end getReferenceToCurrentGameManager method    
} // end class GameOver
