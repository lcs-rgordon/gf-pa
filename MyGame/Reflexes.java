import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Reflexes here.
 * 
 * @author R. Gordon
 * @version March 6, 2019
 */
public class Reflexes extends World
{
    /**
     * Instance variables (fields a.k.a. properties)
     */
    private boolean shouldBeShowingHint;
    private boolean gameOn;
    private int centreX;
    private int timeLeft;
    private int frames;

    /**
     * Constructor for objects of class Reflexes.
     * 
     */
    public Reflexes()
    {    
        // Create a world 500 pixels wide by 400 pixels high
        super(500, 400, 1); 

        // Make background black
        this.drawBlackBackground();

        // Game starts showing hint about how to play
        shouldBeShowingHint = true;
        
        // Game is not on to start
        gameOn = false;
        
        // Set centre point in the world
        centreX = this.getWidth() / 2;
    }

    /**
     * Update the action in the world
     */
    public void act()
    {
        if (shouldBeShowingHint)
        {
            showHint();
            lookForGameStart();
        }
        else if (gameOn)
        {
            trackTime();
        }
    }

    /**
     * Show a hint about how to play.
     */
    private void showHint()
    {
        this.showText("Welcome to Reflexes!", centreX, 75);
        this.showText("Click as many white circles as you can!", centreX, 175);
        this.showText("Don't click the red circles.", centreX, 225);
        this.showText("Press SPACE BAR to begin.", centreX, 325);
    }
    
    /**
     * Hide the hint
     */
    private void hideHint()
    {
        this.showText("", centreX, 75);
        this.showText("", centreX, 175);
        this.showText("", centreX, 225);
        this.showText("", centreX, 325);
    }

    /**
     * Start the game if the user wants to.
     */
    private void lookForGameStart()
    {
        if (Greenfoot.isKeyDown("space"))
        {
            this.shouldBeShowingHint = false;
            this.hideHint();
            this.startGame();
        }
    }
    
    /**
     * Set background to pure black.
     */
    private void drawBlackBackground()
    {
        // Make a new background image (transparent pixels)
        GreenfootImage background = new GreenfootImage(400, 400);

        // Draw a black rectangle over background
        background.setColor(Color.BLACK);
        background.fill();

        // Set the new background
        this.setBackground(background);
    }
    
    /**
     * Start a new game
     */
    private void startGame()
    {
        gameOn = true;
        timeLeft = 10;
        frames = 0;
        showTimeLeft();
    }
    
    /**
     * Displays the time left on the screen.
     */
    private void showTimeLeft()
    {
        this.showText("Time left: " + timeLeft, 100, 50);
    }
    

    /**
     * Track frames and reduce time left in the game (each game is 10 seconds long)
     */
    private void trackTime()
    {
        // Track frames (fps is about 60)
        frames += 1;
        
        // Every second (roughly) reduce the time left
        if (frames % 60 == 0)
        {
            timeLeft -= 1;
            showTimeLeft();
            
            // Look for end of game
            if (timeLeft == 0)
            {
                gameOn = false;
            }
        }
    }
}
