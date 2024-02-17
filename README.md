# Plan IT
In this project we have created 3 Scenarios.

   Scenarios 1:
          1. From the home page go to contact page
          2. Click submit button
          3. Verify error messages
          4. Populate mandatory fields
          5. Validate errors are gone
  Scenarios 2:
          1. From the home page go to contact page
          2. Populate mandatory fields
          3. Click submit button
          4. Validate successful submission message
          Note: Run this test 5 times to ensure 100% pass rate
Scenarios 3:
          1. Buy 2 Stuffed Frog, 5 Fluffy Bunny, 3 Valentine Bear
          2. Go to the cart page
          3. Verify the subtotal for each product is correct
          3. Verify the price for each product
          4. Verify that total = sum(sub totals)

Getting started with Serenity and Cucumber
Serenity BDD is a library that makes it easier to write high quality automated acceptance tests, with powerful reporting and living documentation features. It has strong support for both web testing with Selenium, Java and Webdrivers.

Serenity strongly encourages good test automation design, and supports several design patterns, including classic Page Objects, the newer Lean Page Objects/ Action Classes approach, and the more sophisticated and flexible Screenplay pattern.

The latest version of Serenity supports Cucumber 6.x.

The starter project
The best place to start with Serenity and Cucumber is to clone or download the starter project on Github (https://github.com/DishaKhilari/Tech_PlanIT/). This project gives you a basic project setup, along with some sample tests and supporting classes.

The project directory structure
The project has build scripts for both Maven and Gradle, and follows the standard directory structure used in most Serenity projects:

        src
        + main
        + test
          + java                        Test runners and supporting code
          + resources
                + features                  Feature files
                + search                  Feature file subdirectories
                      CartPage.feature
                      ContactPageCheckSubmission.feature
                   
Serenity 2.2.13 introduced integration with WebdriverManager to download webdriver binaries.

The sample scenario
Both variations of the sample project uses the sample Cucumber scenario. In this scenario, User is Contacting TAL Customer Service:

    Feature: Cart page and check price

    Scenario Outline: Cart page and check price
          Given Enter URL '<URL>'
          When land on main page and click start shopping
          Then Buy 2 Stuffed Frog, 5 Fluffy Bunny, 3 Valentine Bear
          When Verify the price for each product
          Then Verify the subtotal for each product is correct
          And Verify that total = sum(sub totals)
      
          Examples:
            | URL                                    |
            | http://jupiter.cloud.planittesting.com |

The Screenplay implementation
The sample code in the master branch uses the Screenplay pattern. The Screenplay pattern describes tests in terms of actors and the tasks they perform. Tasks are represented as objects performed by an actor, rather than methods. This makes them more flexible and composable, at the cost of being a bit more wordy. Here is an example of Scenario 2:

    @Given("Enter URL {string}")
    public void enterURL(String agr0) throws Throwable {
        //Setting system properties of FirefoxDriver
      
    }

    @When("land on main page and click start shopping")
    public void land_on_main_page_and_click_start_shopping() {
        //land on main page and Click on Start shopping
    }

    @Then("Select option as {string}")
    public void selectOptionAs(String agr0) throws Throwable {
        // click on dropdown and select option
    }

   @Then("Buy {int} Stuffed Frog, {int} Fluffy Bunny, {int} Valentine Bear")
    public void buy_stuffed_frog_fluffy_bunny_valentine_bear(Integer agr0, Integer agr0, Integer agr0) throws Throwable {
        // enter all required detaials and submit
    }

   @When("Verify the price for each product")
    public void verify_the_price_for_each_product(){
    }

    @Then("Verify the subtotal for each product is correct")
    public void verify_the_subtotal_for_each_product_is_correct() {
    }

    @And("Verify that total = sum\\(sub totals)")
    public void verify_that_total_sum_sub_totals() throws IOException {
    }
    
Executing the tests
To run the sample project, you can either just run the CucumberTestSuite test runner class, or run either mvn verify or gradle test from the command line.

By default, the tests will run using Chrome. You can run them in Firefox by overriding the driver system property, e.g.

$ mvn clean verify -Ddriver=firefox
Or

$ gradle clean test -Pdriver=firefox
The test results will be recorded in the target/site/serenity directory.

Generating the reports
Since the Serenity reports contain aggregate information about all of the tests, they are not generated after each individual test (as this would be extremenly inefficient). Rather, The Full Serenity reports are generated by the serenity-maven-plugin. You can trigger this by running mvn serenity:aggregate from the command line or from your IDE.

They reports are also integrated into the Maven build process: the following code in the pom.xml file causes the reports to be generated automatically once all the tests have completed when you run mvn verify?

             <plugin>
                <groupId>net.serenity-bdd.maven.plugins</groupId>
                <artifactId>serenity-maven-plugin</artifactId>
                <version>${serenity.maven.version}</version>
                <configuration>
                    <tags>${tags}</tags>
                </configuration>
                <executions>
                    <execution>
                        <id>serenity-reports</id>
                        <phase>post-integration-test</phase>
                        <goals>
                            <goal>aggregate</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
Simplified WebDriver configuration and other Serenity extras
The sample projects both use some Serenity features which make configuring the tests easier. In particular, Serenity uses the serenity.conf file in the src/test/resources directory to configure test execution options.

Webdriver configuration
The WebDriver configuration is managed entirely from this file, as illustrated below:

    webdriver {
    driver = firefox

        #// Add the WebDriver proxy capability.

        #   Proxy proxy = new Proxy();
        #   proxy.setHttpProxy("myhttpproxy:3337");
        #   options.setCapability("proxy", proxy);

        #  // Add a ChromeDriver-specific capability.
        #   options.addExtensions(new File("/path/to/extension.crx"));
        #   ChromeDriver driver = new ChromeDriver(options);
        #   driver = chromecapabilities {
                    browserName = "firefox"
                    acceptInsecureCerts = true
                    "goog:chromeOptions" {
                    args = ["remote-allow-origins=*","test-type", "no-sandbox", "ignore-certificate-errors", "--window-size=1000,800",
                    "incognito", "disable-infobars", "disable-gpu", "disable-default-apps", "disable-popup-blocking"]
                }

        #
        # Chrome options can be defined using the chrome.switches property
        #
        chrome.switches="""--start-maximized;--test-type;--no-sandbox;--ignore-certificate-errors;
                           --disable-popup-blocking;--disable-default-apps;--disable-extensions-file-access-check;
                           --incognito;--disable-infobars,--disable-gpu"""
        #
        # Define drivers for different platforms. Serenity will automatically pick the correct driver for the current platform
        #
        
        drivers {
          windows {
            webdriver.chrome.driver = "src/test/resources/webdrivers/windows/chromedriver.exe"
            webdriver.gecko.driver = "src/test/resources/webdrivers/windows/geckodriver.exe"
          }
          linux {
            webdriver.chrome.driver = "src/test/resources/webdrivers/linux/chromedriver"
            webdriver.gecko.driver = "src/test/resources/webdrivers/linux/geckodriver"
          }
        }


    }
Serenity uses WebDriverManager to download the WebDriver binaries automatically before the tests are executed.

Environment-specific configurations > specially used for high scale project
We can also configure environment-specific properties and options, so that the tests can be run in different environments. Here, we configure three environments, dev, staging and prod, with different starting URLs for each:

    environments {
        default {
            webdriver.base.url = "http://jupiter.cloud.planittesting.com"
        }
        dev {
            webdriver.base.url = "http://jupiter.cloud.planittesting.com"
        }
        staging {
            webdriver.base.url = "http://jupiter.cloud.planittesting.com"
        }
        prod {
            webdriver.base.url = "http://jupiter.cloud.planittesting.com"
        }
    }
You use the environment system property to determine which environment to run against. For example to run the tests in the staging environment, you could run:

    $ mvn clean verify -Denvironment=staging

You can see the Screenshot of Testcase Results saved in:

    src
        + main
        + test
            + java                        Test runners and supporting code
            + resources
                + features                  
                    + search                  
                        + OutputFile                Output files
                            + passedresult.png           < Scenario 1
                            + Result.png                 < Scenario 2

To Save Result in cucumber report:

when execution is done look for link in the bottom of your console result:

    This report will self-destruct in 24h.                                   
    Keep reports forever: https://reports.cucumber.io/profile

Click on that link login to save the result in Collection

    Publish reports to this collection by defining an environment variable before running Cucumber:
        CUCUMBER_PUBLISH_TOKEN=ee30fd9a-42ad-44f9-b4a8-005bb09fe3b2

    Please follow instructions about how to define environment variables.

Add this token in your 'Edit Run/Debug configurations' > in environment variable
        
    CUCUMBER_PUBLISH_TOKEN= <your cucumber token>

Run your project and you can see all result save in your collection
