import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The class is what makes this games. The timer,
 * score, background, banner. All of this that are mention are created in this
 * class. 
 * 
 * @author jasoneg@email.uscb.edu
 * @version 2023.12.15 (Final Project ISATB145)
 */
public class BattleField extends World
{
    /*FIELDS*/
    private ArtillerySoldier artillerySoldier;
    private ScoreBanner scoreBanner;
    private OpposingSoldier addEnemy;
    private Health health;
    
    private int timer;
    private int score; 
    private int enemyCount; 
    private GreenfootSound backgroundMusic;
    private GreenfootSound countDownSound;
    private GameManager myGameManager;
    
    /*CONSTRUCTOR(S)*/
    /**
     * Constructor for objects of class BattleField.
     */
    public BattleField()
    {    
        super(800, 500, 1, false); 
        timer = 2500;
        score = 20;
        enemyCount = 0;
        backgroundMusic = new GreenfootSound("background_music.mp3");
        countDownSound  = new GreenfootSound("countdown.wav");
        myGameManager = new GameManager();
        setPaintOrder(ScoreBanner.class);
        prepare();
    } // end constructor BattleField
    
    /**
     * This 1-arg constructor is called from the NfcStartScreen calss
     * and allows access to the GameManager object that helps
     * to maintain data from one world to the next
     */
    public BattleField(GameManager myGameManager)
    {
        this();
        this.myGameManager = myGameManager;
    } // end contructor BattleField (with args)
    
    /*METHODS*/
    /**
     * Calls out from other void methods. In addition, it spawns the enemy count randomly along
     * with the health.
     */
    public void act()
    {
        backgroundMusic.playLoop();
        countDown();
        showScore();
        inOrderToWin();
        
        enemyCount++;
        if(enemyCount > 100)
        {
            addEnemy = new OpposingSoldier();
            addObject(addEnemy, 779, Greenfoot.getRandomNumber(200) + 140);
            enemyCount = 0;
        } // end if
    
        if (Greenfoot.getRandomNumber(2600) < 1)
        {
            health = new Health();
            addObject(health, 70, Greenfoot.getRandomNumber(200) + 140);
        } // end if
    } // end method act 
    
    /**
     * Prepares this world, sets the coordinates of each objects that that will
     * be used in this world.
     */
    private void prepare()
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
        score = score + points; // the points is the value we can add on a different class
        showScore();            // So the score will add to that value which is initially 20. 
                                // For an example scoring(20) will add 20 points when something hits something.
        
        if(score <= 0) // when the reaches to zero (or basically you're dead), then the game is over
        {
            backgroundMusic.stop();
            countDownSound.stop();
            beginGameOverSequence();
        } // end if
    } // end method scoring
    
    /**
     * Simply displays a score on the top right corner. String concatenation is used but
     * I left it blank since I designed a "SCORE" sign.
     */
    public void showScore()
    {
        showText("" + score, 740, 43);
    } // end method showScore
    
    /**
     * Creates a timer, It decrement the time by 1 with additional features
     * Whenever the timer reaches to 205, it starts the count down beep to let
     * the user know that the stage is almost done.
     */
    public void countDown(){
        timer -= 1;
        includeTime();
        
        if(timer == 205) // didn't took me a long time, when it reaches to 205, the countdown starts.
        {
            countDownSound.play();
        } // end if
    } // end method countDown

    /**
     * Displays a timer in the top left corner
     * String concatenation is used, but left it blank since I designed the "TIMER" sign.
     */
    public void includeTime()
    {
        showText("" + timer, 160, 43);
    } // end method includeTime 
    
    /**
     * This is the winning critera. In order to win stage 1, the user have to reach 400 pts and when the time runs
     * out. If the user did not make it, then it displays a Game Over
     */
    public void inOrderToWin()
    {
       if(timer == 0 && score >= 300) // winning critera
       {
           backgroundMusic.stop();
           beginNextLvlSequence();
       } // end if
       
       if(timer == 0 && score < 300) // losing critera
       {
           backgroundMusic.stop();
           beginGameOverSequence();
       } // end if
    } //end method inOrderToWin
    
    /**
     * The music pauses when the user pause the game.
     */
    public void stopped()
    {
        if(backgroundMusic.isPlaying())
        {
            backgroundMusic.pause();
        } // end if
    } // end method stopped
    
    /**
     * The music resumes when the user play the game after pausing.
     */
    public void started()
    {
        if(!backgroundMusic.isPlaying())
        {
            backgroundMusic.playLoop();
        } // end if 
    } // end method started   
    
    /**
     * When the user the game, this method displays a image to indicate that you lose.
     * casting from another class that has the image and bring to here when is called.
     */
    public void beginGameOverSequence()
    {
        GameOver gameover = new GameOver();
        Greenfoot.setWorld(gameover);
    } //end method beginGameOverSequence 

    /**
     * When the user the game, this method displays a image to indicate that you win.
     * casting from another class that has the image and bring to here when is called.
     */
    public void beginNextLvlSequence()
    {   
        FirstIntermission winner = new FirstIntermission();
        Greenfoot.setWorld(winner);
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
} // end class BattleField
