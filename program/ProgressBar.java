package program;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/*
 * ProgressBar creates and controls the loading  bar
 */
class ProgressBar extends Thread
{
    private final StringBuilder bar = new StringBuilder();
    private final Random random = new Random();

    private final int SCALE = 80;
    private final String TITLE;
    private final int DELAY;
    private final int RANDOM; 

    private boolean isDone = false;
    private boolean isStuck = false;

    /**
    * Constructor that takes three parameters
    * @title is the name of the loading bar
    * @delay is the amount of second between each time the bar progress increases
    * @random is random value between 1 and the parameter that is added to the bar with each progress
    */
    public ProgressBar(String title, int delay, int random)
    {
        this.TITLE = title;
        this.DELAY = delay;
        this.RANDOM = random;
    }

    @Override
    public void run() 
    {
        try 
        {
            int currentLevel = 0;  
            
            while (currentLevel < SCALE) 
            {
                currentLevel = updateProgressNormal(currentLevel);

                //Small change for the loading bar to hit an error
                if(random.nextInt(1, 200) == 1 && ! isStuck)
                {
                    updateProgressError();
                    break;
                }

                //Small change for the loading bar to get stuck at 99%
                if(random.nextInt(1, 200) == 2)
                {
                    isStuck = true;
                }          
                
                TimeUnit.MILLISECONDS.sleep(500 * DELAY);
            }

            if(isStuck)
            {
               updateProgressStuck();
            }
        } 
        catch (InterruptedException exception) 
        {
            exception.printStackTrace();
        }

        isDone = true;
    }

    //Update the bar output normally
    private int updateProgressNormal(int currentLevel)
    {
        int progress = random.nextInt(1, RANDOM + 1);
        currentLevel = Math.min(currentLevel + progress , SCALE);
        bar.setLength(0);
        bar.append(TITLE).append(":").append(" ".repeat(11 - TITLE.length()));
        bar.append("[").append("|".repeat(currentLevel)).append("-".repeat(SCALE - currentLevel)).append("] ");
        bar.append(String.format("%.2f" , ((double) currentLevel / SCALE) * 100)).append("%");
        return currentLevel;
    }
  
    //Update the bar output to be stuck at 99%
    private void updateProgressStuck()
    {
        bar.setLength(0);
        bar.append(TITLE).append(":").append(" ".repeat(11 - TITLE.length()));
        bar.append("[").append("|".repeat(SCALE - 1)).append("] 99.00%");
    }
  
    //Update the bar output to error out with a random code
    private void updateProgressError()
    {
         bar.setLength(0);
        bar.append(TITLE).append(":").append(" ".repeat(11 - TITLE.length()));
        bar.append("[").append("X".repeat(SCALE)).append("]");
        bar.append(" ERROR_").append(random.nextInt(124 , 482));
    }

    //Return the current output of the bar
    public String getProgress()
    {
        return bar.toString();
    }

    //Return whether the bar is done loading or not
    public boolean isDoneLoading()
    {
        return isDone;
    }
}
