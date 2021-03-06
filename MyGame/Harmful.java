import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * These are the targets you want to avoid!
 * 
 * @author R. Gordon
 * @version Friday, March 8, 2019
 */
public class Harmful extends Expander
{
    /**
     * Constructor
     */
    Harmful()
    {
        super(Color.RED);
    }

    /**
     * Act - do whatever the Harmful wants to do. This method is called whenever
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
                // Move toward the mouse pointer
                moveToPlayer();

                // Let the superclass do the expanding work
                super.act();
            }
        }
    }

    /**
     * Move toward the mouse pointer to make game a bit more challenging
     */
    private void moveToPlayer()
    {
        // Get info about the mouse
        MouseInfo mouse = Greenfoot.getMouseInfo();

        // If the mouse pointer is off the edge of the world, the object
        // will be null. So, we need to check before trying to use it.
        if (mouse != null)
        {
            turnTowards(mouse.getX(), mouse.getY());
            move(5);
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
            world.playMistakeSound();

            // Decrease score
            world.changeScoreBy((100 - getRadius()) * (-2));

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
