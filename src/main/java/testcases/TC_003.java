package testcases;

import com.framework.executor.TestCase;
import com.framework.executor.TestResult;
import com.framework.pages.DummyPage;
import events.IXPNEvents;
import util.OkUtil;

import java.util.ArrayList;

public class TC_003 extends TestCase {

	ArrayList exceptionList = new ArrayList<>();

	public void setUp() {
		testId = "TC_003";
		testDesc = "To verify user can Add credit or payment Transaction in giving amount and attachment.";
		DummyPage dummyPage = new DummyPage(testContext);
		OkUtil.navigateToHomeScreen(testContext);
	}

	@Override
	public TestResult test() {
		TestResult result = new TestResult(testId, testDesc);
		String name=null;
		String amount="00";

		DummyPage dummyPage = new DummyPage(testContext);
		if(dummyPage.isElementFound2("//android.widget.TextView[contains(@resource-id,\"desc\")]")==false) {
			name = dummyPage.returnSystemTime();
			String number = (long) (Math.random() * Math.pow(10, 10)) + "";
			dummyPage.clickElement(IXPNEvents.HOME_PAGE_ADD_CUSTOMER_BUTTON);
			dummyPage.clickElement(IXPNEvents.ADD_CUSTOMER_NAME_FIELD);
			dummyPage.elementSendText(IXPNEvents.ADD_CUSTOMER_NAME_FIELD, name);
			dummyPage.clickElement(IXPNEvents.ADD_CUSTOMER_SAVE_NAME_FIELD);
			dummyPage.clickElement(IXPNEvents.ADD_CUSTOMER_NUMBER_FIELD);
			dummyPage.elementSendText(IXPNEvents.ADD_CUSTOMER_NUMBER_FIELD, number);
			dummyPage.clickElement(IXPNEvents.ADD_CUSTOMER_SAVE_PHONE_NUMBER_FIELD);
			dummyPage.backButton();

			dummyPage.verifyElementFound2("//android.widget.TextView[contains(@resource-id,\"desc\") and contains(@text,\"" + name + "\")]");
		}
		else {
			name=dummyPage.getElementText2("//android.widget.TextView[contains(@resource-id,\"desc\")]");
			amount=dummyPage.getElementText2("//android.widget.TextView[contains(@resource-id,\"balance\")]");
		}

		amount=(dummyPage.getElementText2("//android.widget.TextView[contains(@resource-id,\"balance\")]"));
		String status=dummyPage.getElementText2("//android.widget.TextView[contains(@resource-id,\"balanceStatus\")]");
		String customerName=dummyPage.getElementText2("//android.widget.TextView[contains(@resource-id,\"desc\")]");

		dummyPage.clickElement2("//android.widget.TextView[contains(@resource-id,\"desc\") and contains(@text,\"" + name + "\")]");
		dummyPage.clickElement(IXPNEvents.CUSTOMER_PAGE_GIVE_CREDIT);
		dummyPage.clickElement(IXPNEvents.ADD_CREDIT_PAGE_AMOUNT_FIELD);

		dummyPage.clickElement(IXPNEvents.ADD_CREDIT_PAGE_AMOUNT_FIELD);
		dummyPage.clickElement2("//*[contains(@resource-id,\"btn_one\")]");
		dummyPage.clickElement2("//*[contains(@resource-id,\"btn_zero\")]");

		dummyPage.clickElement(IXPNEvents.ADD_CREDIT_PAGE_ADD_NOTE_FIELD);
		dummyPage.elementSendText(IXPNEvents.ADD_CREDIT_PAGE_ADD_NOTE_FIELD,"item bought for 10");

		dummyPage.clickElement(IXPNEvents.ADD_CREDIT_PAGE_IMAGE_ATTACHMENT_BUTTON);
		if(dummyPage.isElementFound(IXPNEvents.ADD_CREDIT_PAGE_ALLOW_PHOTO_BUTTON))
		{
			dummyPage.clickElement(IXPNEvents.ADD_CREDIT_PAGE_ALLOW_PHOTO_BUTTON);
			dummyPage.defaultWait(2000);
			while(dummyPage.isElementFound(IXPNEvents.DEVICE_PERMISSON_OK_BUTTON))
			{
				dummyPage.clickElement(IXPNEvents.DEVICE_PERMISSON_OK_BUTTON);
				dummyPage.defaultWait(1000);
			}

			dummyPage.clickElement(IXPNEvents.ADD_CREDIT_PAGE_USE_CAMERA_BUTTON);
			String currentActivity=dummyPage.currentActivity();
			if(currentActivity.toLowerCase().contains("okcredit"))
			{
				exceptionList.add("Attachment functionality for camera is not working");

			}
		}
		dummyPage.clickElement(IXPNEvents.ADD_CREDIT_PAGE_SAVE_BUTTON);
		dummyPage.backButton();
    	if(exceptionList.size()==0)
		result.setStatus(true);
		result.setExceptionList(exceptionList);
		return result;

	}

	public void release() {

		DummyPage dummyPage = new DummyPage(testContext);


	}

}
