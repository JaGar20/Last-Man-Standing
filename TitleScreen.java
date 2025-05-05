import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The first thing that show when starting the game, it displays a title screen with an image 
 * When is clicked, then it head to the next level. There are a nice sound effect and background music
 * to spice it up a bit. Visuals made by Me.
 * 
 * @author jasoneg@email.uscb.edu 
 * @version 2023.12.15 (Final Project ISATB145)
 */
public class TitleScreen extends World
{
    /*FIELDS*/
    private GreenfootSound titleMusic;
    private GreenfootSound startSound;
    private GameManager myGameManager;
    
    /*CONSTRUCTOR(S)*/
    /**
     * Constructor for objects of class TitleScreen.
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 500, 1); 
        titleMusic = new GreenfootSound("intro_song.mp3");
        startSound = new GreenfootSound("game_start.mp3");
        myGameManager = new GameManager();
        Greenfoot.start(); // On the webpage, this class will start first
    } // end constructor TitleScreen 
    
    /**
     * This 1-arg constructor is called from the NfcStartScreen calss
     * and allows access to the GameManager object that helps
     * to maintain data from one world to the next
     */
    public TitleScreen(GameManager myGameManager)
    {
        this();
        this.myGameManager = myGameManager;
    } // end constructor TitleScreen  (with args)
     
    /*METHODS*/ 
    /**
     * The song starts playing when the user runs the game. In addition, when the user clicks
     * then it will be sent to the next screen which is the instructions.
     */
    public void act()
    {
        titleMusic.playLoop();
        
        if ( Greenfoot.mouseClicked(this))
        {
            startSound.play();
            titleMusic.stop();
            HowToPlayDisplay howToPlay = new HowToPlayDisplay();
            Greenfoot.setWorld(howToPlay);
        } // end if
    } // end method act 
    
    /**
     * Whenever the user press the pause button to start the game
     * then the song stops at the same time.
     */
    public void stopped()
    {
        if(titleMusic.isPlaying())
        {
            titleMusic.pause();
        } // end if
    } // end method stopped 
    
    /**
     * When the game is paused and the user decided to click the play
     * then the song starts back again at the same time.
     */
    public void started()
    {
        if(!titleMusic.isPlaying())
        {
            titleMusic.playLoop(); // plays the background music
        } // end if 
    } // end method started 
    
    /**
    * Enables an instance of another class to get a reference
    * to the current GameManager object (note that the return type
    * of this method is `GameManager`)
    * 
    * @return a reference to the current GameManager object 
    */
    public GameManager getReferenceToCurrentGameManager()
    {
        return myGameManager;
    } // end getReferenceToCurrentGameManager method    
} // end class TitleScreen
