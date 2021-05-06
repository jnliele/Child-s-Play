Team Nine: Tony Kha, Julie Le, Johnny Nguyen, Nehemie Mampaka

README
************************
Instructions to run the code:
1. Android Studio needs to be installed to run the code

2. Unzip the project file “Child-s-Play-master.zip” and import it into Android Studio via “Import Project (Gradle, Eclipse ADT, etc.)”

3. Ensure the SDK is “Android 11.0 (R)", or download it by going to File->Settings->System Settings->Android SDK, check only “Android 11.0 (R)", 
press OK and install the SDK if needed

4. Ensure the AVD is set to “Pixel_3a_API_30_x86”, or download it by going to Tools->AVD Manager, click “Create Virtual Device”, Phone->Pixel 3a, 
select “Next”, select x86 Images, select the row with “R 30 x86 Android 11.0 (Google APIs)” in its respective columns and download if needed, click “Next”, 
click “Finish”

5. Run the app by going to Run->Run ‘app’

6. If needed, our GitHub is https://github.com/jnliele/Child-s-Play

************************
This Android mobile application is a game called Child’s Play that teaches children in an interactive way. There are three lessons that the children can 
learn through the games: the English alphabet, counting, and addition. The child and guardian can view the child’s learning progress through the profile 
page.

Sample username: shorty.johnny78@gmail.com
Sample password: nguyen

(NOTE: create your own account to be able to test the forgot password function)

Alphabet Gameplay: For each level, 10 questions will be given. To pass the current level, at least 7 questions need to be answered correctly.

Counting Gameplay:  For each level, 10 questions will be given. To pass the current level, at least 7 questions need to be answered correctly.

Addition Gameplay: For each level, an infinite number of questions will be given, but there is a time limit of 30 seconds. To pass the first two levels, at 
least 5 questions need to be attempted, and a passing grade is required (70% or more, which can be determined by the ratio shown at the bottom of the 
screen). To pass the third level, at least 3 questions need to be attempted, and a passing grade is required (70% or more, which can be determined the same 
way as the first two levels).

************************
File(s) included:

accountCreation.java: Implements the child setup page

additionGame.java: Displays the game levels and retrieves information Firebase to either keep the levels locked, unlocked, or both

additionLevelOne.java: Implements the presets of the game: timer, disables the buttons before starting, and shows the start button to begin the game. Also 
keeps track of correct and incorrect answers while adding single digit numbers, updating the users information in Firebase

additionLevelThree.java: implements the presets of the game: timer, disables the buttons before starting, and shows the start button to begin the game. 
Also keeps track of correct and incorrect answers while adding triple digit numbers, updating the users information in Firebase

additionLevelTwo.java implements the presets of the game: timer, disables the buttons before starting, and shows the start button to begin the game. 
Also keeps track of correct and incorrect answers while adding triple digit numbers, updating the users information in Firebase

additionGameCode.java: Regulates the difficulty of each game mode and checks whether the user’s choices were correct or incorrect. Additionally, the 
file generates new questions for the user to answer

addQuestions.java: Generates 3 random answer choices along with the correct answer choice and shuffles the order of the choices for each question

AdminProfileFragment.java: Implements the other fragment for the tab of the top navigation bar in profilePage.java. Displays all the admin information 
inputted upon account registration

alphabetGame.java: Creates the alphabet game levels page. Displays three game levels, locked, unlocked, or both

alphabetGameLvl1.java: Generates 10 random level 1 alphabet questions one at a time from the alphabetQuestions file. Will keep track of how many questions 
were answered correctly

alphabetGameLvl2.java: Generates 10 random level 2 alphabet questions one at a time from the alphabetQuestions file. Will keep track of how many questions 
were answered correctly

alphabetGameLvl3.java: Generates 10 random level 3 alphabet questions one at a time from the alphabetQuestions file. Will keep track of how many questions 
were answered correctly

alphabetQuestions.java: Contains the questions and answers for each alphabet game level

ChildProfileFragment.java: Implements the default fragment for the tab of the top navigation bar in profilePage.java. Calculates and displays all the 
statistics for child profile page

countGame.java: Displays counting game page with three levels either locked, unlocked, or both

countGameLvl1.java: Generates a random level 1 counting question for the user to answer from countGameQuestions.java

countGameLvl2.java: Generates a random level 2 counting question for the user to answer from countGameQuestions.java 

countGameLvl3.java: Generates a random level 3 counting question for the user to answer from countGameQuestions.java 

countGameQuestions.java: Contains a bank of counting game questions and answers

forgotPassword.java: Implements the forgot password page to reset password

homePage.java: Implements the bottom navigation bar for the home page

homeScreen.java: Implements the default fragment for the bottom navigation bar in homePage.java

LoginPage.java: Implements the login page

MainActivity.java: Implements the team logo page

newAdmin.java: Implements the admin setup page

profilePage.java: Implements the other fragment for the bottom navigation bar in homePage.java. Creates the tabs to switch between child profile page and 
admin profile page

recyclerAdapter.java: Enables the lists for strengths, weaknesses, and favorite game to be implemented on the child profile page

SectionPagerAdapter.java: Basic structure to create tabs at the top of the screen

User.java: Stores user registration information and default statistics upon registration
