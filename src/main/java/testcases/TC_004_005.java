package testcases;

import com.framework.executor.TestCase;
import com.framework.executor.TestResult;
import com.framework.pages.DummyPage;
import events.IXPNEvents;
import util.OkUtil;


import java.util.ArrayList;

public class TC_004_005 extends TestCase {

	ArrayList exceptionList = new ArrayList<>();

	public void setUp() {
		testId = "TC_004_005";
		testDesc = "To verify user can Verify the balances with added amount in home page and customer transaction page.\n" +
				"TC 005 :In home page print list of all customers and verify whether the added amount is due or advance\n";
		DummyPage dummyPage = new DummyPage(testContext);
		OkUtil.navigateToHomeScreen(testContext);
	}

	@Override
	public TestResult test() {
		TestResult result = new TestResult(testId, testDesc);

		DummyPage dummyPage = new DummyPage(testContext);

		String amount=(dummyPage.getElementText2("//android.widget.TextView[contains(@resource-id,\"balance\")]"));
		String status=dummyPage.getElementText2("//android.widget.TextView[contains(@resource-id,\"balanceStatus\")]");
		String customerName=dummyPage.getElementText2("//android.widget.TextView[contains(@resource-id,\"desc\")]");

		dummyPage.clickElement2("//android.widget.TextView[contains(@resource-id,\"desc\") and contains(@text,\""+customerName+"\")]");

		dummyPage.clickElement(IXPNEvents.CUSTOMER_PAGE_ACCEPT_PAYMENT);

		dummyPage.clickElement(IXPNEvents.ADD_CREDIT_PAGE_AMOUNT_FIELD);
		dummyPage.clickElement2("//*[contains(@resource-id,\"btn_one\")]");
		dummyPage.clickElement2("//*[contains(@resource-id,\"btn_zero\")]");

		dummyPage.clickElement(IXPNEvents.ADD_CREDIT_PAGE_ADD_NOTE_FIELD);
		dummyPage.elementSendText(IXPNEvents.ADD_CREDIT_PAGE_ADD_NOTE_FIELD,"made payment of 10");

		dummyPage.clickElement(IXPNEvents.ADD_CREDIT_PAGE_IMAGE_ATTACHMENT_BUTTON);
		if(dummyPage.isElementFound(IXPNEvents.ADD_CREDIT_PAGE_ALLOW_PHOTO_BUTTON))
		{
			dummyPage.clickElement(IXPNEvents.ADD_CREDIT_PAGE_ALLOW_PHOTO_BUTTON);
			dummyPage.defaultWait(2000);
			while(dummyPage.isElementFound(IXPNEvents.DEVICE_PERMISSON_OK_BUTTON))
			{
				dummyPage.clickElement(IXPNEvents.DEVICE_PERMISSON_OK_BUTTON);
			}
		}
		dummyPage.clickElement(IXPNEvents.ADD_CREDIT_PAGE_SAVE_BUTTON);
		dummyPage.backButton();


			String newAmount=(dummyPage.getElementText2("//android.widget.TextView[contains(@resource-id,\"balance\")]"));

			int amountTemp=Integer.parseInt(amount.substring(1,amount.length()));
			int newAmountTemp=Integer.parseInt(newAmount.substring(1,newAmount.length()));


		if(status.toLowerCase().equals("due")&& amountTemp<10)
		{if(Math.abs(-amountTemp+(newAmountTemp))!=10)
		{
			exceptionList.add("The transaction changes are not reflecting on home page. It should be amount - " + Math.abs(amountTemp-10) );
		}

			status=dummyPage.getElementText2("//android.widget.TextView[contains(@resource-id,\"balanceStatus\")]");
			if(status.toLowerCase().equals("advance")==false)
			{
				exceptionList.add("TC_005 failed , as the status should be advance and it is due");
			}

		}
		else
		{
			if(status.toLowerCase().equals("advance")==false)
			{
				exceptionList.add("TC_005 failed , as the status should be advance and it is " + status);
			}
		}
		if (exceptionList.size() == 0) {
			result.setStatus(true);
		}

		result.setExceptionList(exceptionList);
		return result;

	}

	public void release() {

		DummyPage dummyPage = new DummyPage(testContext);


	}

}
