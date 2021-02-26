package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class VardenisiffrorPage {
	WebDriver driver;

	public VardenisiffrorPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@class='PrimaryButton-sc-1de7cpg-0 gJtkiM']")
	private WebElement btnCreateList;

	@FindBy(xpath = "//button[text()='Ok']")
	private WebElement btnAcceptCoockies;

	@FindBy(xpath = "//a[contains(@title, 'Min lista')]")
	private WebElement lnkCreatedLists;

	@FindBy(xpath = "//input[@id='unitlist-name']")
	private WebElement txtlistName;

	@FindBy(xpath = "//a[@class='Link-bq396v-0 fcounk']")
	private WebElement lnkGoBack;

	@FindBy(xpath = "//button[@class='PrimaryButton-sc-1de7cpg-0 esPrHU']")
	private WebElement btnDeleteList;

	@FindBy(xpath = "//input[@id='select-organizations-input']")
	private WebElement dropOrganizations;

	@FindAll(@FindBy(how = How.XPATH, using = "//ul[@id='select-organizations-listbox']/li"))
	private List<WebElement> organization_List;

	@FindBy(xpath = "//input[@id='select-multiple-units-checkbox-all-regions']")
	private WebElement chkallRegions;

	@FindBy(xpath = "//button[@id='select-color-for-0180']")
	private WebElement btnColor;

	@FindBy(xpath = "//button[@id='select-color-5']")
	private WebElement colorRed;

	@FindBy(xpath = "//button[@id='select-color-for-0180']")
	private WebElement unitColorIcon;

	@FindBy(xpath = "//h3[contains(text(),'Välj en färg')]")
	private WebElement colorPopupHeader;
	
@FindBy(xpath = "//button[@class='iconButtonstyles-x9983-0 dQWlHG']")
private WebElement btnRemoveUnit;

	public void clickCreateList() {
		btnCreateList.click();
	}

	public void clickAcceptCoockies() {
		btnAcceptCoockies.click();
	}

	public void clickGivenListName(String name) {
		driver.findElement(By.xpath("//a[contains(@title, '" + name + "')]")).click();
	}

	public boolean isGivenListNameAvailable(String name) {
		try {
			driver.findElement(By.xpath("//a[contains(@title, '" + name + "')]"));
			System.out.println("List is : " + "//a[contains(@title, '" + name + "')]");
			return true;
		} catch (NotFoundException e) {
			return false;
		}
	}

	public void clickFirstListName() {
		lnkCreatedLists.click();
	}

	public void clearListName() {
		txtlistName.clear();
	}

	public void clearOrganizationText() {
		dropOrganizations.clear();
	}

	public void checkedAllRegions() {
		chkallRegions.click();
	}

	public void setListName(String name) {
		txtlistName.sendKeys(name);
	}

	public void clickGoBack() {
		lnkGoBack.click();
	}

	public void clickDeleteList() {
		if (driver.getCurrentUrl() != "https://vardenisiffror.se/mina-enhetslistor") {
			btnDeleteList.click();
		}
	}

	public int getCountofListsCreated() {
		int counter = 0;
		try {
			List<WebElement> records = lnkCreatedLists.findElements(By.xpath("//a[contains(@title, 'Min lista ')]"));
			counter = records.size();
		} catch (Exception e) {
		}
		return counter;
	}

	public String getNameofTheListCreated(int i) {
		String listName = "";
		try {
			List<WebElement> records = lnkCreatedLists.findElements(By.xpath("//a[contains(@title, 'Min lista ')]"));
			listName = records.get(i - 1).getText();
		} catch (Exception e) {
		}

		return listName;
	}

	public void selectOrganizations(String orgName) {
		// Select se = new Select(dropOrganizations);
		// se.selectByVisibleText(orgName);
		dropOrganizations.click();
		dropOrganizations.sendKeys(orgName);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		for (WebElement organization : organization_List) {
			System.out.println(organization.getText());

			if (organization.getText().equals(orgName)) {
				organization.click();
			}
		}
	}

	public void setColor() {
		btnColor.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		colorRed.click();
	}

	public boolean isColorPlatterOpened() {
		try {
			System.out.println("Color platter header is : " + colorPopupHeader.getText());
			if (colorPopupHeader.getText().equals("Välj en färg")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}


	public boolean validateUnitColor(String colorCode) {
		if (unitColorIcon.getCssValue("color").equals(colorCode)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void removeUnit() {
		btnRemoveUnit.click();
	}

}
