import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A circle that will expand in size until it's radius is 100 pixels, then disappear.
 * 
 * @author R. Gordon
 * @version Friday, March 8, 2019
 */
public abstract class Expander extends Actor
{
    private int currentRadius;
    private Color myColor;

    /**
     * Constructor
     */
    Expander(Color color)
    {
        // Set starting size for target
        currentRadius = 0;

        // Set initial color
        myColor = color;

        // Expand to one pixel in size
        expand();
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
    public void expand()
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
    public void removeTarget()
    {
        getWorld().removeObject(this);
    }
    
    /**
     * Get current radius of the target
     */
    public int getRadius()
    {
        return currentRadius;
    }

}

