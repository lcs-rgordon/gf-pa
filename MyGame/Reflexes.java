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
    private boolean firstTimeRun;
    private boolean gameOn;
    private int centreX;
    private int timeLeft;
    private int frames;
    private int score;
    private GreenfootSound achievement;
    private GreenfootSound mistake;

    /**
     * Constructor for objects of class Reflexes.
     * 
     */
    public Reflexes()
    {    
        // Create a world 500 pixels wide by 400 pixels high
        super(500, 400, 1); 

        // Make background black
        drawBlackBackground();

        // Tracks if game is run for first time
        firstTimeRun = true;

        // Game is not on to start
        gameOn = false;

        // Set centre point in the world
        centreX = getWidth() / 2;

        // Load the achievement sound effect
        achievement = new GreenfootSound("short_coin.wav");

        // Load the mistake sound effect
        mistake = new GreenfootSound("oops.mp3");
    }

    /**
     * Update the action in the world
     */
    public void act()
    {
        lookForGameStart();

        if (firstTimeRun)
        {
            showGamePlayHint();
        }
        else if (gameOn)
        {
            trackTime();
            lookToAddTarget();
        }
    }

    /**
     * Show a hint about how to play.
     */
    private void showGamePlayHint()
    {
        showText("Welcome to Reflexes!", centreX, 75);
        showText("Click as many white targets as you can!", centreX, 175);
        showText("Don't click the red targets.", centreX, 225);
        showText("Press SPACE BAR to begin.", centreX, 325);
    }

    /**
     * Hide the hint about how to play
     */
    private void hideGamePlayHint()
    {
        showText("", centreX, 75);
        showText("", centreX, 175);
        showText("", centreX, 225);
        showText("", centreX, 325);
    }

    /**
     * Show results of a game.
     */
    private void showResults()
    {
        showText("Press SPACE BAR to play again.", centreX, 325);
    }

    /**
     * Hide results of a game.
     */
    private void hideResults()
    {
        showText("", centreX, 325);
    }

    /**
     * Start the game if the user wants to.
     */
    private void lookForGameStart()
    {
        if (Greenfoot.isKeyDown("space") && !gameOn)
        {
            startGame();
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
        setBackground(background);
    }

    /**
     * Start a new game
     */
    private void startGame()
    {
        firstTimeRun = false;
        hideGamePlayHint();
        hideResults();
        gameOn = true;
        timeLeft = 10;
        frames = 0;
        showTimeLeft();
        removeTargets();
        score = 0;
        showScore();
    }

    /**
     * Removes all existing targets from the world.
     */
    private void removeTargets()
    {
        java.util.List targets = getObjects(Expander.class);
        removeObjects(targets);
    }

    /**
     * Displays the time left on the screen.
     */
    private void showTimeLeft()
    {
        showText("Time left: " + timeLeft, 100, 50);
    }

    /**
     * Displays the score on the screen
     */
    private void showScore()
    {
        showText("Score: " + score, getWidth() - 100, 50);
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
                showResults();
            }
        }
    }

    /**
     * Regularly add a new target to the world
     */
    private void lookToAddTarget()
    {
        // Add a new target roughly every half second
        if (frames % 30 == 0)
        {
            addTarget();
        }
    }

    /**
     * Add a new target to the world
     */
    private void addTarget()
    {
        // Pick a random x position within the width of this world
        int x = Greenfoot.getRandomNumber(this.getWidth());

        // Pick a random y position within the height of this world
        int y = Greenfoot.getRandomNumber(this.getHeight());

        // Select the type of target (80% of time will be beneficial)
        // ... then add to the world
        if (Greenfoot.getRandomNumber(10) + 1 <= 8)
        {
            Beneficial target = new Beneficial();
            addObject(target, x, y);
        }
        else
        {
            Harmful target = new Harmful();
            addObject(target, x, y);
        }

    }

    /**
     * Allow other objects to check whether the game is on
     */
    boolean isGameOn()
    {
        return gameOn;
    }

    /**
     * Play the achievement sound effect (when beneficial target is hit)
     */
    public void playAchievementSound()
    {
        // Will stop playing sound effect if it's currently playing
        achievement.stop();

        // Will start playing sound effect again
        achievement.play();
    }

    /**
     * Play the mistake sound effect (when harmful target is hit)
     */
    public void playMistakeSound()
    {
        // Will stop playing sound effect if it's currently playing
        mistake.stop();

        // Will start playing sound effect again
        mistake.play();
    }
    
    /**
     * Change the score when a target is hit
     * 
     * @param   thisMuch    How much to change the score by
     */
    public void changeScoreBy(int thisMuch)
    {
        score += thisMuch;
        showScore();
    }
}
