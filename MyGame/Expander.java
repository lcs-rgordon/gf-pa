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
        currentRadius = 0;
        myColor = Color.WHITE;
    }
    
    /**
     * Act - do whatever the Expander wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Make the actor increase in size
        expand();
        
        // Disappear after reaching 100 pixel radius
        if (currentRadius == 100)
        {
            getWorld().removeObject(this);
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
}
