package com.napier.sem;

import java.sql.*;
import java.util.Scanner;

import static java.lang.System.exit;

public class HelloWorld
{
    /** ---------------------------------------------------------------------------------------
     * Initialise report classes
     */
    private final CountryReportsOne countryOne = new CountryReportsOne();
    private final CountryReportsTwo CountryTwo = new CountryReportsTwo();
    private final CityReportsOne cityOne = new CityReportsOne();
    private final CityReportsTwo cityTwo = new CityReportsTwo();
    private final CapCityReportsOne capOne = new CapCityReportsOne();
    private final CapCityReportsTwo capTwo = new CapCityReportsTwo();
    private final PopulationReports popRep = new PopulationReports();
    private final ExtraInformation exInfo = new ExtraInformation();
    private final LanguageReports LangRep = new LanguageReports();


    /** ---------------------------------------------------------------------------------------
     * Connection to MySQL database.
     */
    public Connection con = null;

    /** ---------------------------------------------------------------------------------------
     * Connect to the MySQL database.
     */
    public void connect(String location, int delay)
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(delay);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /** ---------------------------------------------------------------------------------------
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        // Create new Application
        HelloWorld app = new HelloWorld();

        // Connect to database
        if(args.length < 1){
            app.connect("localhost:33060", 30000);
        }else{
            app.connect(args[0], Integer.parseInt(args[1]));
        }

       // execute queries
        app.countryOne.World(app.con, -1);
        app.countryOne.Continent(app.con, -1);
        app.countryOne.Region(app.con, -1);

        // White Space
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");

        //int limit = app.CountryTwo.GetLimit(app.con);
        app.countryOne.World(app.con, 5);
        //limit = app.CountryTwo.GetLimit(app.con);
        app.countryOne.Continent(app.con, 5);
        //limit = app.CountryTwo.GetLimit(app.con);
        app.countryOne.Region(app.con, 5);

        // White Space
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");

        // execute CapCityOne queries
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Capital cities of The World by population in descending order");
        app.capOne.CapCityWorld(app.con, -1);
        System.out.println(" ");
        System.out.println(" ");

        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Capital cities of Europe by population in descending order");
        app.capOne.CapCityContinent(app.con, -1);
        System.out.println(" ");
        System.out.println(" ");

        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Capital cities of Western Europe by population in descending order");
        app.capOne.CapCityRegion(app.con, -1);
        System.out.println(" ");
        System.out.println(" ");

        // execute CapCityOne queries
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Top 5 Capital cities of The World by population in descending order");
        app.capOne.CapCityWorld(app.con, 5);
        System.out.println(" ");
        System.out.println(" ");

        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Top 5 Capital cities of Europe by population in descending order");
        app.capOne.CapCityContinent(app.con, 5);
        System.out.println(" ");
        System.out.println(" ");

        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Top 5 Capital cities of Western Europe by population in descending order");
        app.capOne.CapCityContinent(app.con, 5);
        System.out.println(" ");
        System.out.println(" ");

        System.out.println("\n\n");

            System.out.println("\t\t\t\t***********WELCOME IN THE PROGRAM OF POPULATION IN THE CITIES ***********");
            System.out.println("LOADING..........");
            Thread.sleep(1000);

            //CODE TO ORGANISED ALL THE POPULATION IN THE CITIES IN THE WORLD

        //CODE TO ORGANISED ALL THE POPULATION IN THE CITIES IN THE WORLD
        System.out.println("\t\t********************Program 1 *******************");
        System.out.println("As an organisation, we want to be able to list all the cities in the world in descending order, so that we can make a report on city population.");
        System.out.println("Processing......");
        Thread.sleep(1000);
        app.cityOne.CityWorld(app.con,-1);
        System.out.println("\n\n\n\n");
        System.out.println("");
        System.out.println("");


        // THIS CODE IS USED TO SHOW TOP N POPULATED CITIES IN THE WORLD
        System.out.println("\n\t\t*******************Program 2******************");
        System.out.println("As an organisation, we want to find the top N populated cities in the world where N is provided by the user, so that we can make a report on the based of top N populated cities.");
        Thread.sleep(1000);
        app.cityOne.CityWorld(app.con,6);

        //CODE TO ORGANISED ALL THE POPULATION IN THE CITIES IN A CONTINENT
        System.out.println("\t\t********************Program 3 *******************");
        System.out.println("As an organisation, we want to be able to list all the cities in a continent in descending order, so that we can make a report on city population.");
        System.out.println("Processing......");
        Thread.sleep(1000);
        app.cityOne.CityContinent(app.con,-1);
        System.out.println("");
        System.out.println("");
        System.out.println("");


        // THIS CODE IS USED TO SHOW TOP N POPULATED CITIES IN THE CONTINENT
        System.out.println("\t\t*********************Program 4********************");
        System.out.println("As an organisation, we want to find the top N populated cities in a continent where N is provided by the user, so that we can make a report on the based of top N populated cities.");
        Thread.sleep(1000);
        app.cityOne.CityContinent(app.con,6);

        // CODE TO ORGANISED ALL THE POPULATION OF THE CITIES IN A SPECIFIED REGION
        System.out.println("\t\t********************Program 5 *******************");
        System.out.println("As an organisation, we want to be able to list all the cities in a specified region in descending order, so that we can make a report on city population.");
        System.out.println("Processing......");
        Thread.sleep(1000);
        app.cityOne.CityRegion(app.con,-1);
        System.out.println("");
        System.out.println("");
        System.out.println("");


        // THIS CODE IS USED TO SHOW TOP N POPULATED CITIES IN THE REGION
        System.out.println("\t\t*************************Program 6***********************");
        System.out.println("As an organisation, we want to find the top N populated cities in a specified region where N is provided by the user, so that we can make a report on the based of top N populated cities.");
        Thread.sleep(1000);
        app.cityOne.CityRegion(app.con,6);
        System.out.println("");
        System.out.println("");
        System.out.println("");

        //CODE TO ORGANISED ALL THE POPULATION OF THE CITIES IN A COUNTRY
        System.out.println("\t\t********************Program 7 *******************");
        System.out.println("As an organisation, we want to be able to list all the cities in a country in descending order, so that we can make a report on city population.");
        System.out.println("Processing......");
        Thread.sleep(1000);
        app.cityOne.CityCountry(app.con,-1);
        System.out.println("");
        System.out.println("");
        System.out.println("");


        // THIS CODE IS USED TO SHOW TOP N POPULATED CITIES IN THE COUNTRY
        System.out.println("\t\t**********************Program 8************************");
        System.out.println("As an organisation, we want to find the top N populated cities in a country where N is provided by the user, so that we can make a report on the based of top N populated cities.");
        Thread.sleep(1000);
        app.cityOne.CityCountry(app.con,6);
        System.out.println("");
        System.out.println("");
        System.out.println("");

        //CODE TO ORGANISED ALL THE POPULATION OF THE CITIES IN A  DISTRICT
        System.out.println("\t\t********************Program 9 *******************");
        System.out.println("As an organisation, we want to be able to list all the cities in a district in descending order, so that we can make a report on city population.");
        System.out.println("Processing......");
        Thread.sleep(1000);
        app.cityOne.CityDistrict(app.con,-1);
        System.out.println("");
        System.out.println("");
        System.out.println("");


        // THIS CODE IS USED TO SHOW TOP N POPULATED CITIES IN THE DISTRICT
        System.out.println("\t\t**************************Program 10*******************");
        System.out.println("As an organisation, we want to find the top N populated cities in a district where N is provided by the user, so that we can make a report on the based of top N populated cities.");
        Thread.sleep(1000);
        app.cityOne.CityDistrict(app.con,4);
        app.popRep.PopContinent(app.con);
        app.popRep.PopCountry(app.con);
        app.popRep.PopRegion(app.con);
        System.out.println("");
        System.out.println("");
        System.out.println("");

        // White Space
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");

        //The code below is used to output the language report. It prints information only on the specified languages.
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Language Report");
        app.LangRep.LanguageReport(app.con,-1);
        System.out.println(" ");
        System.out.println(" ");


        // White Space
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");

        //The code below is used to output the Extra Information reports, which asked for the population of various parts of the world.
        //Below is the output for World Population
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Information on the World Population");
        app.exInfo.ExWorld(app.con,-1);
        System.out.println(" ");
        System.out.println(" ");

        //Below is the output for Continent Population, the specified continent is Europe.
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Information on Continent Population, with the continent being Europe");
        app.exInfo.ExContinent(app.con,-1);
        System.out.println(" ");
        System.out.println(" ");

        //Below is the output for Region Population, the specified region is Western Europe.
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Information on Region Population, with the region being Western Europe");
        app.exInfo.ExRegion(app.con,-1);
        System.out.println(" ");
        System.out.println(" ");

        //Below is the output for Country Population, the specified country is France.
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Information on Country Population, with the country being France");
        app.exInfo.ExCountry(app.con,-1);
        System.out.println(" ");
        System.out.println(" ");

        //Below is the output for District Population, the specified district is Utrecht
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Information on District Population, with the district being Utrecht in the Netherlands");
        app.exInfo.ExDistrict(app.con,-1);
        System.out.println(" ");
        System.out.println(" ");

        //Below is the output for City Population, the specified city is Berlin.
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Information on City Population, with the city being Berlin ");
        app.exInfo.ExCity(app.con,-1);
        System.out.println(" ");
        System.out.println(" ");


        // Disconnect from database
        app.disconnect();
    }
}

