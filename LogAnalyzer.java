/**
 * Read web server data and analyse
 * hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 2011.07.31
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }
    
    /**
     * Crea un objeto logAnalyzer y indicas que objeto quieres analizar.
     */
    public LogAnalyzer(String nameLog)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(nameLog);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        int hour = 0;
        System.out.println("Hr: Count");
        while (hour < hourCounts.length){
            System.out.println(hour + ": " + hourCounts[hour]);
            hour++;
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
     /**
     * Devuelve numero total de accesses del servidor
     */
    public int numberOfAccesses()
    {
        int accesses = 0 ;
        for (int cont = 0;cont < hourCounts.length; cont++)
        {
            accesses += hourCounts[cont];
        }
        return accesses;
    }
    
    /**
     * Devuelve la hora de mayor solicitudes
     */
    public int busiestHour()
    {
        int maxaccesses = 0;
        for (int cont = 0;cont < hourCounts.length; cont++)
        {
            if (hourCounts[cont] > hourCounts[maxaccesses]){
                maxaccesses = cont;
            }
        }
        return maxaccesses;
    }
    
    /**
     * Devuelve la hora de menor solicitudes
     */
    public int quietestHour()
    {
       int minaccesses = 0;
       for (int cont = 0;cont < hourCounts.length; cont++)
       {
           if (hourCounts[cont] < hourCounts[minaccesses]){
               minaccesses = cont;
           }
       }
       return minaccesses;
    }
    
    /**
     * Devuelve las horas consecutivas con mayor solicitudes
     */
    public int busiest2Hour()
    {
        int maxaccesses = 0;
        for (int cont = 0;cont < (hourCounts.length-1); cont++)
        {
            if ((hourCounts[cont]+hourCounts[cont+1]) > (hourCounts[maxaccesses]+hourCounts[maxaccesses+1])){
                maxaccesses = cont;
            }
        }
        return maxaccesses;
    }
}
