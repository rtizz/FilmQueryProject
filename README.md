# Film Query App

## Description
This application allows the user to query specific criteria within an SQL database containing a variety of movie rental criteria. 

## Intructions and Expected Outcomes
Once started, the application will prompt user with three options.
1. Find film by ID - User enters arbitrary film ID to see corresponding film, displaying Title, description, release year, rating, and language. 
Additionally a list of all actors within said film will be displayed under film data.
2. Find film containing Keyword - User can enter any keyword in which the database will search through all film titles and descriptions to display respective films.
Additionally a list of all actors within said film will be displayed under film data.
3. Exit the program - Exits program.


## Code Breakdown
The following classes are what encompasses the program. This README will provide a succinct overview of the purposes of the class'. Additional notes can be found commented in the code.


### Entities
1. Actor - Class
Contains parameters to create an "actor" from the database utilizing the respective CTOR. Along with all getters, setters, toString, and .equals/Hashcode required.
2. Film - Class
Contains all parameters to create a film from database. Additionally holds 2 CTORs- 1st is tailored for this application specifically requiring only the parameters explicitly outlined. 2nd carried over from lab and usable if stretch goals attempted.
### DataBase
1. DatabaseAccessor - Interface
Contains all unimplemented methods which is implemented by DatabaseAccessorObject. findActorById(int actorId) is commented out as it is not directly utilized
2. DatabaseAccessorObject - Implements DatabaseAccessor Interface
The variables initialized from liked 16-19 are used throughout the methods. With 16,17, and 18 holding private static and final variables strictly for database access. 19 is used for return criteria of searches within the methods. 

#### DatabaseAccessorObject()CTOR
Provides access to required drivers for application to talk to database.<br>
***The following all contain similar logic so the steps below apply to all.***<br>
#### findFilmBykKeywordSearch(String keyword) List<Film>
#### findActorsByFilmId(int filmId) List<Actor>
#### findFilmById(int filmId) Film
1. Creates connection to via DriverManager using the final variables to access database.
2. Create search statement for necessary criteria and binds it using set prepared statement/setInt.
3. Cycles through what is returned and assigns to respective object to be called via app.

#### findActorById(int actorId) Actor - Functional - Not used/Commented

### POM.xml<br>
Generated onces project converted to Maven project. Holds the required dependancies for drivers to verify against for accessing database. 

### APP<br>
#### main() - Initiates launch()
#### launch() - Provides welcome message, scanner and while loop for startUserInterface(Scanner) to use
#### startUserInterface(Scanner) - Provides switch statement and logic to read user input and call corresponding method to get search results.


## Technologies Used
Java
SQL
Maven
Eclipse


## Lessons learned# FilmQueryProject
1. As always, read the error messaged. You dont know as much as you think you do.
2. While SQL is intuitive, small things like forgetting to add spaces in concatenation will eat up a lot of troubleshooting time. 
3. A lot of SQL is just rinse and repeat. However small logic such as reading an empty query confused me a bit. Stack overflow was there to help.
4. Make CTOR only as big as you need it. If more parameters are added than needed, that is just more work.

