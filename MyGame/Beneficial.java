import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * These are the targets you want to hit!
 * 
 * @author R. Gordon
 * @version Friday, March 8, 2019
 */
public class Beneficial extends Expander
{    
    /**
     * Constructor
     */
    Beneficial()
    {
        super(Color.WHITE);
    }

    /**
     * Act - do whatever the Beneficial wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Get an object reference for the world
        Reflexes world = (Reflexes) getWorld();

        // When the game is on...
        if (world.isGameOn())
        {
            // ... look to see if hit
            boolean hit = lookForHit();

            // When the target was not hit, continue expanding in size
            if (!hit)
            {
                // Let the superclass do the expanding work
                super.act();
            }
        }
    }  

    /**
     * Check to see whether target has been touched with mouse
     * 
     * @return  Whether the target was hit or not
     */
    private boolean lookForHit()
    {
        if (Greenfoot.mouseMoved(this))
        {
            // Achievement made!
            Reflexes world = (Reflexes) getWorld();
            world.playAchievementSound();
            
            // Increase score
            world.changeScoreBy(100 - getRadius());

            // Remove this expander
            removeTarget();

            // Target was hit
            return true;
        }
        else
        {
            // Target not hit
            return false;
        }

    }
}
