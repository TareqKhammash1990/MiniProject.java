package MiniProject;


/**
 * Write a description of MiniProject here.
 * MiniProject Babies names Exercise
 * @author Tareq Khammash 
 * @version 25/11/2021
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class MiniProject 
{
    //----------------------------------------------------------
    public void totalBirths ()
    {
        int counter = 0;
        int counterF = 0;
        int counterM = 0;
        FileResource fr = new FileResource();
        
        for (CSVRecord record: fr.getCSVParser(false))
        {
            
            counter++;
            if (record.get(1).equals("F"))
            {
                counterF++;
            }
            else if (record.get(1).equals("M"))
            {
               counterM++; 
            }
        }
        System.out.println("The number of names is "+ counter);
        System.out.println("The number of girls names is "+ counterF);
        System.out.println("The number of boys names is "+ counterM);
    }
    //------------------------------------------------------------------------
    public int getRank(int year, String name, String gender, FileResource fr)
    {
        int rank= -1;
        int counterBoys = 0;
        int counterGirls = 0;
        int numBorn=0;
        //int tempGirls = 1;
        //int tempBoys = 1;
        //FileResource fr = new FileResource();
        for (CSVRecord record: fr.getCSVParser(false))
        {
            String nameInFile = record.get(0);
            String genderInFile = record.get(1); 
            numBorn = Integer.parseInt(record.get(2));
            if(record.get(1).equals("F"))
            {
                //if(numBorn != tempGirls)
                //{
                    counterGirls++;
                //}
                if(nameInFile.equals(name) && genderInFile.equals(gender))
                {
                    rank = counterGirls;
                }
                //tempGirls = numBorn;
            }
            if(record.get(1).equals("M"))
            {
                //if(numBorn != tempBoys)
                //{
                    counterBoys++;
                //}
                if(nameInFile.equals(name) && genderInFile.equals(gender))
                {
                    rank = counterBoys;
                }
                //tempBoys = numBorn;
            }
        }
        return rank;
    }
    
    public void testGetRank()
    {
        FileResource fr = new FileResource();
        int rank = getRank(1971,"Frank", "M", fr);
        System.out.println(rank);
    }
    //-----------------------------------------------------------------------    
    public String getName(int year, int rank, String gender)
    {
        String name = "NO NAME" ;
        FileResource fr = new FileResource();
        int countBoys = 0;
        int countGirls = 0;
        for (CSVRecord record: fr.getCSVParser(false))
        {
            //int numBorn = Integer.parseInt(record.get(2));
            if(record.get(1).equals("M"))
            {
                countBoys++;
                if(rank == countBoys && gender.equals("M"))
                {
                    name = record.get(0);
                }
            }
            else
            {
                countGirls++;
                if(rank == countGirls && gender.equals("F"))
                {
                    name = record.get(0);
                }
            }
        }
        return name;   
    }
    //-----------------------------------------------------------------------    
    public void whatIsNameInYear (String name, int year, int newYear, String gender)
    {
        FileResource fr = new FileResource();
        int rank = getRank(year, name, gender,fr);
        String newName = getName(newYear, rank, gender);
        System.out.println(name +" born in " + year +" would be " + newName + " in "+ newYear);
    }
    //-----------------------------------------------------------------------    
    public int yearOfHighestRank(String name, String gender)
    {
        int year = 0;
        int currRank;
        int highestRank = 1000000000; // random high number to overcome 
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles())
        {
            //FileResource fr = new FileResource(f);
            String fileName = f.getName();
            FileResource fr = new FileResource(f);
            //currRank = getRank(2012 , name , gender);
            //int yearTest = Integer.parseInt(fileName);
            String yearT = fileName.substring(3,7);
            //System.out.println(yearT);
            int yearIn = Integer.parseInt(yearT);
            //System.out.println(yearIn);
            currRank = getRank(yearIn, name, gender, fr);
            System.out.println(currRank);
            if(currRank < highestRank && currRank != -1)
            {
                highestRank = currRank;
                year = yearIn;
            }
            else
            {
                if(highestRank == -1)
                {
                     year = -1;
                }
            }
        }
        return year;
    }
    //----------------------------------------------------------------------- 
    public double getAverageRank(String name, String gender)
    {
        int currRank = 0;
        double averageRank = 0;
        DirectoryResource dr = new DirectoryResource();
        int sum =0;
        int counter= 0;
        
        for(File f : dr.selectedFiles())
        {
            String fileName = f.getName();
            FileResource fr = new FileResource(f);
            String yearT = fileName.substring(3,7);
            int yearIn = Integer.parseInt(yearT);
            currRank = getRank(yearIn, name, gender, fr);
            sum = sum + currRank;
            counter++;
        }
        averageRank = (double)sum / counter;
        return averageRank;
    }
    //----------------------------------------------------------------------- 
    public int getTotalBirthsRankedHigher(int year, String name, String gender)
    {
        int totalBirths = 0;
        FileResource fr = new FileResource();
        //int rank = getRank(year, name, gender,fr);
        //System.out.println(rank);
        
        for (CSVRecord record: fr.getCSVParser(false))
        {
            if(record.get(1).equals(gender))
            {
                if(record.get(0).equals(name))
                {
                   break;
                }
                else
                {
                   //System.out.println("Before " + totalBirths );
                   totalBirths = totalBirths + Integer.parseInt(record.get(2)); 
                   //System.out.println("after " + totalBirths );
                }
            }
        }
        //if(record.get(0)
        return totalBirths;
    }
    //------------------------------------------------------------------------- 
}   

