package stepDefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.VardenisiffrorPage;
import utilities.ReadConfig;
import utilities.ScreenshotCapture;

public class VardenisiffrorSteps {

	public WebDriver driver;
	public ReadConfig config;
	public ScreenshotCapture screenshot;
	ExtentReports extent;
	ExtentSparkReporter spark;
	ExtentTest tester;
	VardenisiffrorPage vardens;
	private int NoOflistsInit;
	private int NoOflistsNow;
	private boolean checked;
	private String lstName;
	private boolean colorplatterdisplayed;

	@Before
	public void setup() {
		config = new ReadConfig();
		screenshot = new ScreenshotCapture();
		extent = new ExtentReports();
		spark = new ExtentSparkReporter("target/test-report.html");
		extent.attachReporter(spark);
		tester = extent.createTest("VardenisiffrorTest");
		NoOflistsInit = 0;
		NoOflistsNow = 0;
		checked = false;
		colorplatterdisplayed = false;
	}

	@Given("User Launch {string} Webbrowser")
	public void user_launch_webbrowser(String webBrowser) {
		this.launchWebBrowser(webBrowser);
		vardens = new VardenisiffrorPage(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@When("User Opens URL {string}")
	public void user_opens_url(String url) {
		driver.get(url);
	}

	@When("User Click Accept Cookies")
	public void user_click_accept_cookies() {
		try {
			vardens.clickAcceptCoockies();
			Thread.sleep(1000);
		} catch (Exception e) {
		}
	}

	@Then("Page Title Should Be {string}")
	public void page_title_should_be(String title) {
		try {
			assertEquals(driver.getTitle(), (title));
			Thread.sleep(1000);
			tester.log(Status.PASS, "Web Page Title Validation is Success");
		} catch (AssertionError e) {
			tester.log(Status.FAIL, "Web Page Title Validation is Failure");
			screenshot.captureScreenshot(driver, "HomePage");
			tester.fail(MediaEntityBuilder.createScreenCaptureFromPath("/Screenshot/HomePage.png").build());
		} catch (Exception e) {
		}
	}

	@Then("Page body has {string}")
	public void page_body_has(String content) {
		try {
			assertTrue(driver.getPageSource().contains(content));
			Thread.sleep(1000);
			tester.log(Status.PASS, "Web Page Content Validation is Success");
		} catch (Exception e) {
			tester.log(Status.FAIL, "Web Page Content Validation is Failure");
			screenshot.captureScreenshot(driver, "HomePage-Body");
			tester.fail(MediaEntityBuilder.createScreenCaptureFromPath("/Screenshot/HomePage-body.png").build());

		}
	}

	@When("Click ones Skapa enhetslista")
	public void click_ones_skapa_enhetslista() {
		try {
			vardens.clickCreateList();
			Thread.sleep(1000);
			tester.log(Status.PASS, "Create Enhetslista is Pass");
		} catch (Exception e) {
			tester.log(Status.FAIL, "Create Enhetslista is Failure");
			screenshot.captureScreenshot(driver, "HomePage-OneList");
			tester.fail(MediaEntityBuilder.createScreenCaptureFromPath("/Screenshot/HomePage-OneList.png").build());

		}
	}

	@Then("Created Label name Should be {string}")
	public void created_label_name_should_be(String labelName) {
		try {
			if (vardens.getCountofListsCreated() > 0) {
				Thread.sleep(1000);
				tester.log(Status.PASS, "List Creation is Success");
			} else {
				tester.log(Status.FAIL, "List Creation is Failure");
				screenshot.captureScreenshot(driver, "HomePage-ListName");
				tester.fail(
						MediaEntityBuilder.createScreenCaptureFromPath("/Screenshot/HomePage-ListName.png").build());

			}
		} catch (Exception e) {
		}
	}

	@When("Click Skapa enhetslista {int} times")
	public void click_skapa_enhetslista_times(Integer noOfTimes) {
		try {
			this.createMultipleLists(noOfTimes);
			Thread.sleep(1000);
			tester.log(Status.PASS, "List Creation - Multiple Times is Success");
			Thread.sleep(1000);
		} catch (Exception e) {
			tester.log(Status.FAIL, "List Creation - Multiple Times is Failure");
			screenshot.captureScreenshot(driver, "HomePage-MultipleLists");
			tester.fail(
					MediaEntityBuilder.createScreenCaptureFromPath("/Screenshot/HomePage-MultipleList.png").build());

		}
	}

	@Then("Created Label count Should be {int}")
	public void created_label_count_should_be(Integer noOfTimes) {
		try {
			if ((NoOflistsNow - NoOflistsInit) == noOfTimes) {
				Thread.sleep(1000);
				tester.log(Status.PASS, "List Creation - Multiple Times Validation is Success");
			} else {
				tester.log(Status.FAIL, "List Creation - Multiple Times Validation is Failure");
				screenshot.captureScreenshot(driver, "HomePage-LabelCount");
				tester.fail(
						MediaEntityBuilder.createScreenCaptureFromPath("/Screenshot/HomePage-LabelCount.png").build());

			}
		} catch (Exception e) {
		}
	}

	@Then("Created Label Name Should be {string}")
	public void created_position_label_name_should_be(String listName) {
		int count = vardens.getCountofListsCreated();
		try {
			for (int i = 1; i < count; i++) {
				assertEquals(vardens.getNameofTheListCreated(i), listName + "(" + (i) + ")");
				Thread.sleep(1000);
				tester.log(Status.PASS, "Position " + i + " List Name Validation is Success");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("Created Lists available")
	public void created_lists_available() {
		if (vardens.getCountofListsCreated() > 0) {
			tester.log(Status.PASS, "Created lists are available");
			checked = true;
		}
	}

	@When("click on the First list in the grid")
	public void click_on_the_first_list_in_the_grid() {
		if (checked) {
			vardens.clickFirstListName();
			tester.info("First List in the grid Selected");
		}
	}

	@When("Clear the list name text box")
	public void clear_the_list_name_text_box() throws Exception {
		vardens.clearListName();
		Thread.sleep(1000);
		tester.info("Clear the list name text box");
	}

	@When("Type {string} to the name")
	public void type_to_the_name(String name) {
		try {
			vardens.setListName(name);
			lstName = name;
			tester.info("Rename the List");
			Thread.sleep(2000);
			tester.info("Rename the List as " + name);
		} catch (Exception e) {
		}

	}

	@When("Click Tillbaka till mina enhetslistor")
	public void click_tillbaka_till_mina_enhetslistor() {
		try {
			vardens.clickGoBack();
			Thread.sleep(1000);
			tester.info("Clicked Go back to Home Page");
		} catch (NotFoundException e) {
			tester.log(Status.FAIL, "Click Go back home is failure");
			screenshot.captureScreenshot(driver, "HomePage-GoBack");
			tester.fail(MediaEntityBuilder.createScreenCaptureFromPath("/Screenshot/HomePage-GoBack.png").build());

		} catch (Exception e) {
		}

	}

	@Then("Updated List is displayed")
	public void updated_list_is_displayed() throws Exception {
		try {
			Thread.sleep(1000);
			assertTrue(vardens.isGivenListNameAvailable(lstName));
			tester.log(Status.PASS, "Renamed List is Available in the grid");
		} catch (Exception e) {
			tester.log(Status.FAIL, "Renamed List is not Available in the grid");
			screenshot.captureScreenshot(driver, "HomePage-UpdatedList");
			tester.fail(MediaEntityBuilder.createScreenCaptureFromPath("/Screenshot/HomePage-UpdatedList.png").build());

		}
	}

	@When("Select and checked Alla regioner + Riket")
	public void select_and_checked_Alla_regioner_Riket() {
		try {
			vardens.checkedAllRegions();
			tester.info("Select All Regions is Success");
		} catch (NotFoundException e) {
			tester.fail("Select All Regions is Failure");
			screenshot.captureScreenshot(driver, "HomePage-AllRegion");
			tester.fail(MediaEntityBuilder.createScreenCaptureFromPath("/Screenshot/HomePage-AllRegion.png").build());

		}

	}

	@When("Enter key word in {string} text box and select Unit")
	public void enter_key_word_in_text_box_and_select_unit(String unitName) {
		try {
			vardens.clearOrganizationText();
			vardens.selectOrganizations(unitName);
			Thread.sleep(1000);
			tester.log(Status.PASS, unitName + " is Selected from Dropdown List");
			Thread.sleep(1000);
		} catch (Exception e) {
			tester.log(Status.FAIL, unitName + " is not Selected from Dropdown List");
			screenshot.captureScreenshot(driver, "HomePage-SelectUnit");
			tester.fail(MediaEntityBuilder.createScreenCaptureFromPath("/Screenshot/HomePage-SelectUnit.png").build());

		}

	}

	@When("Click Farg icon_label of the device row")
	public void click_f_rg_icon_label_of_the_device_row() {
		try {
			vardens.setColor();
			tester.info("Clicked Color icon of the unit ");
		} catch (Exception e) {
		}
	}

	@Then("Display a color platter pop up")
	public void display_a_color_platter_pop_up_with_colors() throws Exception {
		if (vardens.isColorPlatterOpened()) {
			colorplatterdisplayed = true;
			Thread.sleep(1000);
			tester.log(Status.PASS, "Color Platter Display is success");
		} else {
			colorplatterdisplayed = false;
			tester.log(Status.FAIL, "Color Platter Display is not success");
			screenshot.captureScreenshot(driver, "HomePage-ColorPopup");
			tester.fail(MediaEntityBuilder.createScreenCaptureFromPath("/Screenshot/HomePage-ColorPopup.png").build());

		}
	}

	@When("Select one color from the platter")
	public void select_one_color_from_the_platter() {
		if (colorplatterdisplayed) {
			vardens.setColor();
			tester.info("Select one Color from the Platter");
		}
	}

	@Then("Close the color platter pop up")
	public void close_the_color_platter_pop_up() {
		if (vardens.isColorPlatterOpened()) {
			tester.log(Status.FAIL, "Color Platter Pop up Close is Failure");
			screenshot.captureScreenshot(driver, "HomePage-closeColorPopup");
			tester.fail(
					MediaEntityBuilder.createScreenCaptureFromPath("/Screenshot/HomePage-CloseColorPopup.png").build());

		} else {
			tester.log(Status.PASS, "Color Platter Pop up Close is Success");
		}
	}

	@Then("Verify Applied selected color to the device Farg label")
	public void verify_applied_selected_color_to_the_device_farg_label() {
		if (vardens.validateUnitColor("#D17B0D")) {
			tester.log(Status.PASS, "Color selection is Success");
		} else {
			tester.log(Status.FAIL, "Color selection is Failure");
			screenshot.captureScreenshot(driver, "HomePage-ColorApply");
			tester.fail(MediaEntityBuilder.createScreenCaptureFromPath("/Screenshot/HomePage-ColorApply.png").build());

		}
	}

	@When("Click Delete Unit Button")
	public void click_delete_unit_button() {
		try {
			vardens.removeUnit();
			Thread.sleep(1000);
			tester.log(Status.PASS, "Remove item from list is success");
		} catch (Exception e) {
			tester.log(Status.FAIL, "Remove item from list is Failure");
			screenshot.captureScreenshot(driver, "HomePage-DeleteUnit");
			tester.fail(MediaEntityBuilder.createScreenCaptureFromPath("/Screenshot/HomePage-DeleteUnit.png").build());

		}
	}

	@When("Click Ta bort Listan button")
	public void click_ta_bort_listan_button() {
		try {
			NoOflistsInit = vardens.getCountofListsCreated();
			vardens.clickDeleteList();
			Thread.sleep(1000);
			tester.log(Status.PASS, "Deleted List is Success");
		} catch (Exception e) {
			tester.log(Status.FAIL, "Deleted List is Failure");
			screenshot.captureScreenshot(driver, "HomePage-DeleteList");
			tester.fail(MediaEntityBuilder.createScreenCaptureFromPath("/Screenshot/HomePage-DeleteList.png").build());

		}
	}

	@When("Verify List Deleted from the List")
	public void list_deleted_from_the_list() {
		NoOflistsNow = vardens.getCountofListsCreated();
		try {
			assertEquals(NoOflistsInit - 1, NoOflistsNow);
			Thread.sleep(1000);
			tester.log(Status.PASS, "List removed from the Grid id Success");
		} catch (Exception e) {
			tester.log(Status.FAIL, "List removed from the Grid id Failure");
			screenshot.captureScreenshot(driver, "HomePage-VerifyListDeleted");
			tester.fail(MediaEntityBuilder.createScreenCaptureFromPath("/Screenshot/HomePage-VerifyListDeleted.png")
					.build());

		}
	}

	@After()
	public void tearoff() {
		extent.flush();
		driver.close();
	}

	public void launchWebBrowser(String webBrowser) {
		switch (webBrowser) {
		case "Chrome": {
			System.setProperty("webdriver.chrome.driver", config.getChromePath());
			driver = new ChromeDriver();
			break;
		}
		case "Firefox": {
			System.setProperty("webdriver.gecko.driver", config.getFirefoxPath());
			driver = new FirefoxDriver();
			break;
		}
		case "IExplorer": {
			System.setProperty("webdriver.ie.driver", config.getIEPath());
			driver = new InternetExplorerDriver();
			break;
		}
		default:
			System.setProperty("webdriver.gecko.driver", config.getFirefoxPath());
			driver = new FirefoxDriver();
			break;
		}
	}

	public void createMultipleLists(int noOfLists) {
		NoOflistsInit = vardens.getCountofListsCreated();
		for (int i = 1; i <= noOfLists; i++) {
			vardens.clickCreateList();
			NoOflistsNow++;
		}
	}

	public void selectGivenList(String name) {
		try {
			vardens.clickGivenListName(name);
			tester.info(name + " List is available");
		} catch (Exception e) {
			tester.info(name + " List is not available");
		}
	}
}
