package program;

/*
 * TerminalDisplay handles displaying the bars progress to the terminal
 */
import java.util.ArrayList;

class TerminalDisplay extends Thread
{
    private ArrayList<ProgressBar> progressBars = new ArrayList<>();

    /**
    * Constructor that takes three parameters
     * @progressBars is an array list with the bars to be displayed in the terminal
     */
    public TerminalDisplay(ArrayList<ProgressBar> progressBars)
    {
        this.progressBars = progressBars;
    }

    @Override
    public void run() 
    {
         try 
        {
            while (isScreenLoading()) 
            {
                System.out.print("\033[H\033[2J");
                System.out.flush();

                StringBuilder output = new StringBuilder();

                for (ProgressBar progressBar : progressBars) 
                {
                    output.append("\n" + progressBar.getProgress());
                }

                System.out.println(output.toString());

                Thread.sleep(500);
            }
        } 
        catch (InterruptedException ie) 
        {
            Thread.currentThread().interrupt();
        }
    }

    //Check if all the bars in the list are done loading or not
    public boolean isScreenLoading()
    {
        for(ProgressBar progressBar : progressBars)
        {
            if(!progressBar.isDoneLoading())
            {
                return true;
            }
        }

        return false;
    }
}