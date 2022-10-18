package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;
import pageObjects.AddcustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass {
	

	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\prajeshp\\eclipse-workspace\\CucumberDemo\\Drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		lp= new LoginPage(driver);
		driver.manage().window().maximize();
		
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		driver.get(url);
		
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String userName, String passWord) {
		lp.setUserName(userName);
		lp.setPassword(passWord);
		
	}

	@When("Click on Login")
	public void click_on_login() {
		lp.clickLogin();
		
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) {
		
		if (driver.getPageSource().contains("Login was unsuccessful.")) {
			driver.close();
			Assert.assertTrue(false);
			} else {
			Assert.assertEquals(title, driver.getTitle());
			}
		
	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() throws InterruptedException {
		lp.clickLogout();
		Thread.sleep(3000);
	}
	

	@Then("close browser")
	public void close_browser() {
		
		driver.close();
		
	}
	
	//Customer StepDefinition
	
	@Then("User can view Dashboad")
	public void user_can_view_dashboad() {
		addCust=new AddcustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
		
	    
	}
	@When("User click on customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
		Thread.sleep(3000);
		addCust.clickOnCustomersMenu();
	   
	}
	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() throws InterruptedException {
		Thread.sleep(3000);
		addCust.clickOnCustomersMenuItem();
	    
	}
	@When("click on Add new button")
	public void click_on_add_new_button() throws InterruptedException {
		Thread.sleep(3000);
		addCust.clickOnAddnew();
	    
	}
	@Then("User can view Add new customer page.")
	public void user_can_view_add_new_customer_page() throws InterruptedException {
		Thread.sleep(3000);
	    Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}
	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		String email=randomestring()+"@gmail.com";
		addCust.setEmail(email);
		addCust.setPassword("test123");
		//Registered - default
		// The cx cannot be in both Guests and Registered in cx roles
	   //Add cx to Guests or Registered customer role
		Thread.sleep(3000);
		addCust.setCustomerRoles("Guests");
		Thread.sleep(3000);
		
		addCust.setManagerOfVendor("Vendor 2");
		addCust.setGender("Male");
		addCust.setFirstName("James");
		addCust.setLastName("Smith");
		addCust.setDob("7/05/1985"); //Format: D/MM/YYYY
		addCust.setCompanyName("CapG");
		addCust.setAdminContent("Testing Automation.......");
		
		
	}
	@When("click on Save button")
	public void click_on_save_button() throws InterruptedException {
		addCust.clickOnSave();
		Thread.sleep(3000);
	    
	}
	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg) {
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains(msg));
	    
	}
	
	///search cx using EmailID
	
	@When("Enter customer EMail")
	public void enter_customer_e_mail() {
	    searchCust = new SearchCustomerPage(driver);
	    searchCust.setEmail("FrqcC@gmail.com");
	}
	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
	    searchCust.clickSearch();
	    Thread.sleep(3000);
	}
	@Then("User should found Email in the Search table")
	public void user_should_found_email_in_the_search_table() {
	    boolean status = searchCust.searchCustomerByEmail("FrqcC@gmail.com");
	    Assert.assertEquals(true,status);

	}
	
	//Search cx by Name - first and last
	@When("Enter customer FirstName")
	public void enter_customer_first_name() {
		searchCust = new SearchCustomerPage(driver);
	    searchCust.setFirstName("John");
	}
	@When("Enter customer LastName")
	public void enter_customer_last_name() {
	    searchCust.setLastName("Snow");
	}
	@Then("User should found Name in the Search table")
	public void user_should_found_name_in_the_search_table() {
		
		boolean status1 =searchCust.searchCustomerByName("John Snow");
		Assert.assertEquals(true, status1);
	    
	}

}
