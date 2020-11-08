package com.vytrack.step_definitions;
import com.vytrack.pages.LoginPage;
import com.vytrack.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginStepDefinitions {
    LoginPage loginPage = new LoginPage();

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        Driver.getDriver().get("http://qa1.vytrack.com");
    }

    @When("user logs in")
    public void user_logs_in() throws InterruptedException {
        loginPage.login();
        Thread.sleep(3000);
    }

    @Then("user should see dashboard page")
    public void user_should_see_dashboard_page() {
        String expected = "Dashboard";
        String actual = loginPage.getPageSubTitleText().trim();

        Assert.assertEquals("Title is not correct!", expected, actual);
        System.out.println("I see the Dashboard page!");
        Driver.closeDriver();
    }

    @Then("user should see {string} page")
    public void user_should_see_page(String string) {
        String actual = loginPage.getPageSubTitleText().trim();
        Assert.assertEquals("Page title is not correct!", string, actual);
    }

    //When user logs in as a "driver" --> public void user_logs_in_as_a(String string) -> loginPage.login(string); -> public void login(String role) { if role == "" do this..}
    //When user logs in as a "sales manager"
    //When user logs in as a "store manager"
    @When("user logs in as a {string}")
    public void user_logs_in_as_a(String string) {
        loginPage.login(string);
    }

    //  String string = "storemanager85";
//  String string2 =  "wrong";
    //When user logs in with "storemanager85" username and "wrong" password
    @When("user logs in with {string} username and {string} password")
    public void user_logs_in_with_username_and_password(String string, String string2) {
        loginPage.login(string, string2);
    }

    //    String expected = "Invalid user name or password."
//    Then user verifies that "Invalid user name or password." message is displayed
    @Then("user verifies that {string} message is displayed")
    public void user_verifies_that_message_is_displayed(String expected) {
        String actualResult = loginPage.getWarningMessageText();
        Assert.assertEquals(expected, actualResult);
    }

}/*
Today is October 18, 2020
 Agenda: Introduction to Cucumber BDD
########################################
BDD scenario:
####################
Feature: As user, I want to be able to create calendar events
Scenario: 1. Create Calendar event
Given user is on the landing page
When user logs in as a driver
And user navigates to Calendar Events
And creates new calendar event
Then new calendar event should be displayed
####################
DSL - domain specific language
public boolean isValid(String str1, String str2){
    if(str1.equals(str2)){
        return true;
    }else {
        return;
    }
}
public boolean isValid(String str1, String str2){
    return str1.equals(str2));
}
################
Steps:
 1. Open Intellij IDEA
 2. Click "Create new Project"
 3. Make sure it's maven project
 4. Select latest JDK version (at least 8/1.8);
 5. Summer2020CucumberTestAutomationProject
    Project name: Summer2020CucumberTestAutomationProject
    Group id: com.vytrack
    Artifact id: Summer2020CucumberTestAutomationProject
 6. Add dependencies:
    <dependencies>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>3.141.59</version>
        </dependency>
        <dependency>
            <groupId>io.github.bonigarcia</groupId>
            <artifactId>webdrivermanager</artifactId>
            <version>4.2.2</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-java -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>6.8.1</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-junit -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit</artifactId>
            <version>6.8.1</version>
            <scope>test</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.github.javafaker/javafaker -->
        <dependency>
            <groupId>com.github.javafaker</groupId>
            <artifactId>javafaker</artifactId>
            <version>1.0.2</version>
        </dependency>
    </dependencies>
7. right click on src --> test --> java -- new package
    package name: com.vytrack.pages
NOTE: . (dot) is a delimiter. Whenver you put . in package name, it creates 2 separate folder:
    com.vytrack.pages
    com - package/folder
    vytrack - package/folder
    pages package/folder
    pages inside vytrack, and vytrack is inside com.
8. Right click on src --> test --> new directory --> resources
9. Right click on src --> test --> resources --> new directory --> features
10. Right click on src --> test --> resources --> features --> new --> file --> Login.feature
11. Add package runners
12. Create class "CucumberRunner"
package com.vytrack.runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com/vytrack/step_definitions",
        dryRun = true
)
public class CucumberRunner {
}
features = "src/test/resources/features", - to tell where are the features
 glue = "com/vytrack/step_definitions",
        dryRun = true - to tell where are the step definitions
  dryRun = true - not run the tests but ensure that every test step (phrase) has a code implementation. If some code implementation is missing, cucumber will generate it for you and print into the console:

  io.cucumber.junit.UndefinedStepException: The step "user is on the login page" is undefined. You can implement it using the snippet(s) below:
    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    Some other steps were also undefined:
    @When("user logs in")
    public void user_logs_in() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("user should see dashboard page")
    public void user_should_see_dashboard_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
NOTE: Extension of feature file is always .feature (not .features, .featurekldfmgklpsmdgf;l)
 Login.feature
 AddToCart.feature
 CreateCalendarEvent.feature
 ........
Sometimes, feature files are named after pages. Sometimes you can meet very strange implementation.
features - folder/directory under resources
Steps:
1. Create Feature file and add scenario.
2. Perform dry run to get missing step definitions
3. Copy and paste from the console step definitions
4. Delete default message and provide code implementation.
5. Go to CucumberRunner class and set dryRun to false and run tests.
Q: Why my resources folder has a different icon?
A:  You need mark this folder (resources) as a "Test Resources Root" folder
    For this, right click on the resources folder --> mark as --> "Test Resources Root"
Q: What is the purpose of resources folder?
    In this folder, we gonna store .feature files (used to write BDD test scenarios), test data (.xlsx, .csv, json. .txt, etc...)
We need to add cucumber plugin/gherkin to intellij in order to work with feature files.
    1. Click "shift" twice for quick search
    2. Enter "Plugins"
    3. Go to Marketplace tab and look for Cucumber
    NOTE: If you already installed this plugin, it will be under installed tab.
    We need cucumber for java and gherkin plugins.
NOTE: please make sure to have latest/near latest Intellij version (as of today, at least 2020.1)
https://mkyong.com/maven/how-to-tell-maven-to-use-java-8/
Q: Why version of my dependency is red?
A: Reload/Load maven libraries
Q: Why do we need this?

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.6.1</version>
                <configuration>
                    <source>8</source>
                    <target>8</target>
                </configuration>
            </plugin>
1.8 = 8 (not in math but in terms of java versions it's the same thing)
A: This is compiler plugin. It helps to set java version for our project. By default maven uses Java 5. As you know, many features that we use were introduced much later.
Q: What is target folder? Why do/don't I see it see it?
A: target folder contains results of the build (compiled code (.class files), jar/war file, test report, etc.). Once you run maven project, it automatically creates target folder (on a fly) and puts there results of build. There is a chance that this folder is hidden, but in 99% it will show up once you run something.
Q: What is plugin? What's the difference between plugin and dependency?
A: Both plugins&dependencies are some java libraries. The main difference is that plugin is used during the build process (at some maven lifecyle).
Q: How to create .gitignore?
A: Right click on the project name --> new -- file --> .gitignore
    add these for now:
        .idea
        target
        *.iml
    Save and close (if you don't have an auto save)
NOTE: if version is still red, delete version, press control + space to see available versions for you.
I think if your recourses file is green color , it does not show the Directory option when right click on new.
we need to right click and mark as Test Resources Root just change the color green then it gives Directory option

 */
