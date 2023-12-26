package program;
import java.util.ArrayList;
import java.util.Random;
/*
 * Created by KareemShahatta on 12/26/2023
 * LoadingScreen is the application's main class for starting the simulation on the terminal
 */
public class LoadingScreen 
{
    //Array list data strucutre to keep track of the bars that we want to display
    private static ArrayList<ProgressBar> progressBars = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException 
    {  
        startCustomSimulation();    
        //startBasicSimulation(9); 

        new TerminalDisplay(progressBars).start();
    }

    //Create a new progress bar, add it to the list, and start loading
    public static void createProgressBar(String title, int delay, int random)
    {
        ProgressBar progressBar = new ProgressBar(title, delay , random);
        progressBars.add(progressBar);
        progressBar.start();
    }
    
    //Custom simulation with custom loading bars
    public static void startCustomSimulation()
    {
        createProgressBar("RENDER", 2 , 5);
        createProgressBar("DISPLAY", 5 , 15);
        createProgressBar("AUDIO",2 , 5);
        createProgressBar("NETWORK I",2 , 9);
        createProgressBar("NETWORK II",2 , 5);
        createProgressBar("FAN SYS 1",1 , 3);
        createProgressBar("FAN SYS 2",2 , 5);
        createProgressBar("FAN SYS 3",3 , 10);
        createProgressBar("MOUSE",1 , 6);
        createProgressBar("KEYBOARD",1 , 9);
        createProgressBar("SSD",1 , 5);
    }
    
    //Basic simulation with standard loading bars
    public static void startBasicSimulation(int bars)
    {
        Random random = new Random();

        for(int i = 1 ; i <= bars ; i ++)
        {
            createProgressBar("CORE-" + i  , random.nextInt(1 , 4) , random.nextInt(1 , 11));
        }
    }
}

