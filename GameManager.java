import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * This "helper class" (which is NOT a subclass of Actor or World) can be used 
 * to keep track of the state of the scenario even if a new World subclass
 * is loaded into memory. 
 * 
 * Please treat this version of GameManager as another example of how it can
 * be used in your game, in addition to the version of GameManager that was
 * included in the `greenfoot-pengu-gamemanager` scenario that you can 
 * download from Blackboard.
 * 
 * @author jasoneg@email.uscb.edu
 * @version 2023.12.08
 */
public class GameManager 
{
    /* FIELDS */
    private boolean nfcEnabled;
    
    /* CONSTRUCTORS */
    /**
     * This no-arg constructor should be used if you want to "reset" the
     * state of the scenario. Be sure to initialize *each* of the instance 
     * variables you have declared at the top of this class definition.
     */
    public GameManager()
    {
        nfcEnabled = false;
    } // end no-arg GameManager constructor
    
    /*
     * NOTE: The overloaded 1-arg GameManager constructor has been 
     *       REMOVED from this class because it is not used!
     */ 
    
    /* METHODS */
    /**
     * A public "getter" (accessor) method to enable
     * access to the GameManager's nfcEnabled state (true or false)
     * 
     * @return true if the game's additional feature(s) are enabled by a detected NFC tag or card
     */
    public boolean isNfcEnabled()
    {
        return nfcEnabled;
    } // end method isNfcEnabled;
    
    /**
     * A public "setter" (mutator) method to enable modification 
     * of the private `nfcEnabled` variable
     * 
     * @param the value of `nfcEnabled` to set
     */
    public void setNfcEnabled( boolean nfcEnabled )
    {
        this.nfcEnabled = nfcEnabled;
    } // end method setCurrentTime
    
} // end class GameManager