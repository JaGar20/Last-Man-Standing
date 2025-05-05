import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Instructions incase if a person dosen't read the "READ ME" text, like me. 
 * Not required, but is perferred (for me).
 * Added sound effect to spice it up. Visuals by me.
 * 
 * @author jasoneg@email.uscb.edu 
 * @version 2023.12.15 (Final Project ISATB145)
 */
public class HowToPlayDisplay extends World
{
    /*FIELDS*/
    private GreenfootSound howToPlaySound;
    private GameManager myGameManager;
    
    /*CONSTRUCTOR(S)*/
    /**
     * Constructor for objects of class InstructionDisplay.
     */
    public HowToPlayDisplay()
    {    
        super(800, 500, 1); 
        howToPlaySound = new GreenfootSound("game_start.mp3");
        myGameManager = new GameManager();
    } // end constructor HowToPlayDisplay 
    
    /**
     * This 1-arg constructor is called from the NfcStartScreen calss
     * and allows access to the GameManager object that helps
     * to maintain data from one world to the next
     */
    public HowToPlayDisplay(GameManager myGameManager)
    {
        this();
        this.myGameManager = myGameManager;
    } // end constructor HowToPlayDisplay (with args)
    
    /*METHODS*/
    /**
     * The user clicks on the screen, it will be sent to the Battle Field (playable class)
     * The song also plays when this class is being used.
     */
    public void act()
    {
        if ( Greenfoot.mouseClicked(this))
        {
            howToPlaySound.play();
            BattleField newDisplay = new BattleField();
            Greenfoot.setWorld(newDisplay);
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
} // end class HowToPlayDisplay