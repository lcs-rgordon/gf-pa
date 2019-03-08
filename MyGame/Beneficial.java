import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Beneficial here.
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

        // Look to see if target hit
        if (world.isGameOn())
        {
            boolean hit = lookForHit();

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

            // Remove this expander
            removeTarget();

            return true;
        }
        else
        {
            return false;
        }

    }
}
