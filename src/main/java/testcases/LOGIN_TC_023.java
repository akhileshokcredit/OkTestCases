package testcases;

import com.framework.executor.TestCase;
import com.framework.executor.TestResult;
import com.framework.pages.DummyPage;
import events.IXPNEvents;
import util.OkUtil;

import java.util.ArrayList;

public class LOGIN_TC_023 extends TestCase {
	String journey_id;
	ArrayList exceptionList = new ArrayList<>();

	public void setUp() {
		testId = "LOGIN_TC_023";
		testDesc = "To verify RETRY button on No Internet Connection works and gets removed if there is internet connection.";
		DummyPage dummyPage = new DummyPage(testContext);

	}

	@Override
	public TestResult test() {
		TestResult result = new TestResult(testId, testDesc);
		DummyPage dummyPage = new DummyPage(testContext);

		OkUtil.permission(testContext);
		dummyPage.defaultWait();
		dummyPage.disableMobileData();
		dummyPage.disableWifi();

		OkUtil.selectLanguageEnglishOnSignUpPage(testContext);
		dummyPage.clickElement("WELCOME_PAGE_VERIFY_MOBILE_BUTTON");
		dummyPage.defaultWait();
		dummyPage.waitForElementInCaseOfStaleElement(IXPNEvents.SIGN_UP_PAGE_PHONE_NUMBER_FIELD,10000);
		dummyPage.verifyElementFound("SIGN_UP_PAGE_PHONE_NUMBER_FIELD");
	//	dummyPage.clickElement("SIGN_UP_PAGE_PHONE_NUMBER_FIELD");
		dummyPage.elementSendText("SIGN_UP_PAGE_PHONE_NUMBER_FIELD", "9082748436");
		dummyPage.waitForElementInCaseOfStaleElement(IXPNEvents.OK_BUTTON,10000);
		dummyPage.clickElement("OK_BUTTON");

		int count=0;
		while(dummyPage.isElementFound(IXPNEvents.SIGN_UP_WHATSAPP_DIALOG_BOX_NOT_ALLOW_BUTTON)==false) {
			count++;
			dummyPage.defaultWait(2000);
			if(count>10)
				break;
		}
		if(dummyPage.isElementFound(IXPNEvents.SIGN_UP_WHATSAPP_DIALOG_BOX_ALLOW_BUTTON)){
			dummyPage.clickElement(IXPNEvents.SIGN_UP_WHATSAPP_DIALOG_BOX_NOT_ALLOW_BUTTON);
		}
		dummyPage.verifyElementFound(IXPNEvents.NO_INTERNET_CONNECTION_ERROR);
		dummyPage.verifyElementFound(IXPNEvents.RETRY_BUTTON_ON_NO_INTERNET_CONNECTION_ERROR);
		dummyPage.clickElement(IXPNEvents.RETRY_BUTTON_ON_NO_INTERNET_CONNECTION_ERROR);
		dummyPage.verifyElementFound(IXPNEvents.RETRY_BUTTON_ON_NO_INTERNET_CONNECTION_ERROR);
		dummyPage.enableMobileData();
		dummyPage.enableWifi();
		dummyPage.defaultWait(1000);
		dummyPage.clickElement(IXPNEvents.RETRY_BUTTON_ON_NO_INTERNET_CONNECTION_ERROR);

		if(dummyPage.isElementFound(IXPNEvents.RETRY_BUTTON_ON_NO_INTERNET_CONNECTION_ERROR)==false) {
			result.setStatus(true);
		}
		result.setExceptionList(exceptionList);
		return result;

	}

	public void release() {

		DummyPage dummyPage = new DummyPage(testContext);
		dummyPage.enableMobileData();
		dummyPage.enableWifi();
	}

}
