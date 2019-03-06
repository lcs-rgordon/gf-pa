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

    /**
     * Constructor for objects of class Reflexes.
     * 
     */
    public Reflexes()
    {    
        super(500, 400, 1); 

        // Make background black
        this.drawBlackBackground();

        // Game starts showing hint about how to play
        shouldBeShowingHint = true;
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
    }

    /**
     * Show a hint about how to play.
     */
    private void showHint()
    {
        this.showText("Welcome to Reflexes!", 250, 75);
        this.showText("Click as many white circles as you can!", 250, 175);
        this.showText("Don't click the red circles.", 250, 225);
        this.showText("Press SPACE BAR to begin.", 250, 325);
    }
    
    /**
     * Hide the hint
     */
    private void hideHint()
    {
        this.showText("", 250, 75);
        this.showText("", 250, 175);
        this.showText("", 250, 225);
        this.showText("", 250, 325);
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

}
