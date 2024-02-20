package starter.StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import starter.Pages.CartPage;
import starter.Pages.ContactPage_checksubmission;
import starter.StartBrowser.BaseClass;
import starter.StartBrowser.RunDriver;

import java.io.IOException;
import java.util.Random;

public class Main extends BaseClass {

    CartPage cartPage = new CartPage();
    RunDriver rundriver = new RunDriver();
    ContactPage_checksubmission contactPageChecksubmission = new ContactPage_checksubmission();


    //-------- To Rundriver and access URL ----------------//

    @Given("Enter URL {string}")
    public void enter_url(String URL) throws InterruptedException {
        rundriver.setUp(URL);
    }

//-------- Scneario 1 and 2: To validate valid and invalid message on Contact Page ----------------//
    @When("I go from the home page to contact page")
    public void i_go_from_the_home_page_to_contact_page() {
        contactPageChecksubmission.clickedOnContactUs();
    }

    @When("I click submit button")
    public void i_click_submit_button() {
        contactPageChecksubmission.submitButton();
    }

    @When("Verify messages")
    public void verify_messages() throws InterruptedException {
        contactPageChecksubmission.setErrorMessage();
    }

    @Then("I populate all mandatory fields {string} {string} {string}")
    public void i_populate_all_mandatory_fields(String Forename, String Email, String Message) {
        contactPageChecksubmission.setMandatoryFields(Forename,Email,Message);

    }

    @Then("I close the Browser")
    public void i_close_the_browser() throws IOException {
        Random random = new Random();
        int randomNumber = random.nextInt(10);
        BaseClass.screenShot("Result_Scneario" +randomNumber); //will take screen shot of your last step
        BaseClass.Close();
    }



    //-------- Scneario 3: To validate price on a Cart page and check price ----------------//
    @When("land on main page and click start shopping")
    public void land_on_main_page_and_click_start_shopping() {
        cartPage.cartPage();
    }

    @Then("Buy {int} Stuffed Frog, {int} Fluffy Bunny, {int} Valentine Bear")
    public void buy_stuffed_frog_fluffy_bunny_valentine_bear(Integer two, Integer five, Integer three) {
        cartPage.addItemsToCart(2,5,3);
    }

    @When("Verify the price for each product")
    public void verify_the_price_for_each_product() {
        cartPage.verifyItemsPrice();
    }

    @Then("Verify the subtotal for each product is correct")
    public void verify_the_subtotal_for_each_product_is_correct() {
        cartPage.verifyItemsSubtotal();
    }

    @And("Verify that total = sum\\(sub totals)")
    public void verify_that_total_sum_sub_totals() throws IOException {
        cartPage.verifyTotal_OfItems();
        BaseClass.screenShot("Result_Scenario 3"); //will take screen shot of your last step
        BaseClass.Close();
    }

}
