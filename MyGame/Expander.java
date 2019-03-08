import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A circle that will expand in size until it's radius is 100 pixels, then disappear.
 * 
 * @author R. Gordon
 * @version Friday, March 8, 2019
 */
public class Expander extends Actor
{
    private int currentRadius;
    private Color myColor;

    /**
     * Constructor
     */
    Expander()
    {
        // Set starting size for target
        currentRadius = 0;

        // Set initial color
        myColor = Color.WHITE;

    }

    /**
     * Act - do whatever the Expander wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Get an object reference for the world
        Reflexes world = (Reflexes) getWorld();

        // Make the actor increase in size
        if (world.isGameOn())
        {
            expand();
            lookForHit();
        }
        else
        {
            removeTarget();
        }

        // Disappear after reaching 100 pixel radius
        if (currentRadius == 100)
        {
            removeTarget();
        }
    }    

    /**
     * Make the circle expand in size
     */
    private void expand()
    {
        currentRadius += 1;

        int diameter = currentRadius * 2;

        // Create an empty (transparent) image at current radius
        GreenfootImage costume = new GreenfootImage(diameter, diameter);

        // Set the colour
        costume.setColor(myColor);

        // Draw a circle on the costume
        costume.fillOval(0, 0, diameter , diameter);

        // Set this costume as the image for this actor
        setImage(costume);
    }

    /**
     * Remove the target from the world
     */
    private void removeTarget()
    {
        getWorld().removeObject(this);
    }

    /**
     * Check to see whether actor has been touched with mouse
     */
    private void lookForHit()
    {
        if (Greenfoot.mouseMoved(this))
        {
            // Achievement made!
            Reflexes world = (Reflexes) getWorld();
            world.playAchievementSound();

            // Remove this actor
            removeTarget();
        }

    }

}

