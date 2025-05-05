import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the second stage. It sets the timer, score, classes, and objects. 
 * All of this that are mention are created in this class. 
 * 
 * @author jasoneg@email.uscb.edu 
 * @version 2023.12.15 (Final Project ISATB145)
 */
public class WinterField extends World
{
    /*FIELDS*/
    private ArtillerySoldier artillerySoldier;
    private ScoreBanner scoreBanner;
    private OpposingSoldier newEnemy;
    private Health iceHealth;
    
    private int timer;
    private int score;
    private int snowEnemyCount;
    private GreenfootSound countDownSound;
    private GreenfootSound backgroundSound;
    private GameManager myGameManager;
    
    /*CONSTRUCTOR(S)*/
    /**
     * Constructor for objects of class WinterField.
     * 
     */
    public WinterField()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 500, 1, false); 
        timer = 2500;
        score = 20;
        snowEnemyCount = 0;
        countDownSound  = new GreenfootSound("countdown.wav");
        backgroundSound = new GreenfootSound("stage2.mp3");
        myGameManager = new GameManager();
        prepare();
    } // end constructor WinterField 
    
    /**
     * This 1-arg constructor is called from the NfcStartScreen calss
     * and allows access to the GameManager object that helps
     * to maintain data from one world to the next
     */
    public WinterField(GameManager myGameManager)
    {
        this();
        this.myGameManager = myGameManager;
    } // end constructor WinterField (with args)
    
    /*METHODS*/
    /**
     * Calls out from other void methods. In addition, it spawns the enemy count randomly along
     * with the health.
     */
    public void act()
    {
        backgroundSound.playLoop();
        winterFieldShowScore();
        winterFieldCountDown();
        inOrderToWinWinterField();
        
        snowEnemyCount++;
        if(snowEnemyCount > 110)
        {
            newEnemy = new OpposingSoldier();
            addObject(newEnemy, 779, Greenfoot.getRandomNumber(200) + 140);
            snowEnemyCount = 0;
        } // end if
        
        if (Greenfoot.getRandomNumber(2200) < 1)
        {
            iceHealth = new Health();
            addObject(iceHealth, 70, Greenfoot.getRandomNumber(200) + 150);
        } // end if
    } // end method act 
    
    /**
     * Prepares this world, sets the coordinates of each objects that that will
     * be used in this world.
     */
    public void prepare()
    {
        artillerySoldier = new ArtillerySoldier();
        addObject(artillerySoldier, 70, getHeight() / 2);
        
        scoreBanner = new ScoreBanner();
        addObject(scoreBanner, 400, 30);
    } // end method prepare 
    
     /**
     * Updates the score. We can change any score from other clases with an value inside
     * of the parameter. In this case if the other class is using this method and set the points 
     * to a different value, then the score will set to that value. Like scoring(20);
     * 
     * @param points being used for adding value
     */
    public void scoring(int points)
    {
        score = score + points;
        winterFieldShowScore();
        
        if(score <= 0)
        {
            backgroundSound.stop();
            countDownSound.stop();
            beginGameOverSequence();
        } // end if
    } // end method scoring (with args)
    
    /**
     * Simply displays a score on the top right corner. String concatenation is used but
     * I left it blank since I designed a "SCORE" sign.
     */
    public void winterFieldShowScore()
    {
        showText("" + score, 740, 43);
    } // end method winterFieldShowScore
    
    /**
     * Creates a timer, It decrement the time by 1 with additional features
     * Whenever the timer reaches to 205, it starts the count down beep to let
     * the user know that the stage is almost done.
     */
    public void winterFieldCountDown(){
        timer -= 1;
        includeTime();
   
        if(timer == 205){
            countDownSound.play();
        } // end if
        
        if(timer == 0){
            backgroundSound.stop();
            beginWinningSequence();
        } // end if
    } // end method winterFieldCountDown 
    
    /**
     * Displays a timer in the top left corner
     * String concatenation is used, but left it blank since I designed the "TIMER" sign.
     */
    public void includeTime()
    {
        showText("" + timer, 160, 43);
    } // end method includeTime 

    /**
     * This is the winning critera. In order to win stage 2, the user have to reach 600 pts and when the time runs
     * out. If the user did not make it, then it displays a Game Over
     */
    public void inOrderToWinWinterField()
    {
       if(timer == 0 && score >= 600)
       {
           backgroundSound.stop();
           beginWinningSequence();
       } // end if
       
       if(timer == 0 && score < 600)
       {
           backgroundSound.stop();
           beginGameOverSequence();
       } // end if
    } // end method inOrderToWinWinterField 

    /**
     * The music pauses when the user pause the game.
     */
    public void stopped()
    {
        if(backgroundSound.isPlaying())
        {
            backgroundSound.pause();
        } // end if
    } // end method stopped 
    
    /**
     * The music resumes when the user play the game after pausing.
     */
    public void started()
    {
        if(!backgroundSound.isPlaying())
        {
            backgroundSound.playLoop();
        } // end if 
    } // end method started     
    
    /**
     * When the user the game, this method displays a image to indicate that you lose.
     * casting from another class that has the image and bring to here when is called.
     */
    public void beginGameOverSequence(){
        GameOver gameover = new GameOver();
        Greenfoot.setWorld(gameover);
    } //end method beginGameOverSequence 

    /**
     * When the user the game, this method displays a image to indicate that you win.
     * casting from another class that has the image and bring to here when is called.
     */
    public void beginWinningSequence(){
        WinDisplay winnerWinterField = new WinDisplay();
        Greenfoot.setWorld(winnerWinterField);
    } // end method beginNextLvlSequence 
    
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
} // end class WinterField
