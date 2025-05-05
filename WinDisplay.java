import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * You Win! This is a win class that will be display if you finished the level. It includes the title of the art
 * and the QR code if you want to learn more about it. When you click, then it goes back
 * to title screen. I added sound effects and background to spice it up. Visuals by me.
 * 
 * @author jasoneg@email.uscb.edu 
 * @version 2023.12.15 (Final Project ISATB145)
 */
public class WinDisplay extends World
{
    /*FIELDS*/
    private GameManager myGameManager;
    private GreenfootSound winningSound;
    
    /*CONSTRUCTOR(S)*/
    /**
     * Constructor for objects of class WinDisplay.
     */
    public WinDisplay()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 500, 1); 
        myGameManager = new GameManager();
        winningSound = new GreenfootSound("winning.mp3");
    } // end constructor WinDisplay
     
    /*METHODS*/
    /**
     * This 1-arg constructor is called from the NfcStartScreen calss
     * and allows access to the GameManager object that helps
     * to maintain data from one world to the next
     */
    public WinDisplay(GameManager myGameManager)
    {
        this();
        this.myGameManager = myGameManager;
    } // end constructor WinDisplay (with args)
    
    /**
     * Simply plays music and goes back to the title screen when the user click the screen.
     */
    public void act()
    {
        winningSound.playLoop();
        
        if ( Greenfoot.mouseClicked(this))
        {
            //howToPlaySound.play();
            winningSound.stop();
            TitleScreen goingBackToGame = new TitleScreen();
            Greenfoot.setWorld(goingBackToGame);
        } // end if
    } // end method act
    
    /**
     * Whenever the user press the pause button to start the game
     * then the song stops at the same time.
     */
    public void stopped()
    {
        if(winningSound.isPlaying())
        {
            winningSound.pause();
        } // end if
    } // end method stopped 
    
    /**
     * When the game is paused and the user decided to click the play
     * then the song starts back again at the same time.
     */
    public void started()
    {
        if(!winningSound.isPlaying())
        {
            winningSound.playLoop(); 
        } // end if 
    } // end method started 
    
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
} // end class WinDisplay
