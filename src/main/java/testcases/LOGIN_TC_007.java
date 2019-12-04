package testcases;

import com.framework.events.IClient;
import com.framework.executor.TestCase;
import com.framework.executor.TestResult;
import com.framework.pages.DummyPage;
import events.IXPNEvents;
import util.OkUtil;

import java.util.ArrayList;

public class LOGIN_TC_007 extends TestCase {
	String journey_id;
	ArrayList exceptionList = new ArrayList<>();

	public void setUp() {
		testId = "LOGIN_TC_007";
		testDesc = "To verify user cannot sign up using wrong OTP. ";
		DummyPage dummyPage = new DummyPage(testContext);

	}

	@Override
	public TestResult test() {
		TestResult result = new TestResult(testId, testDesc);
		DummyPage dummyPage = new DummyPage(testContext);
		OkUtil.navigateToLanguageSelectionPage(testContext);
		dummyPage.clickElement("WELCOME_PAGE_VERIFY_MOBILE_BUTTON");
		dummyPage.defaultWait();
		dummyPage.waitForElementInCaseOfStaleElement(IXPNEvents.SIGN_UP_PAGE_PHONE_NUMBER_FIELD,10000);
		dummyPage.verifyElementFound("SIGN_UP_PAGE_PHONE_NUMBER_FIELD");
	//	dummyPage.clickElement("SIGN_UP_PAGE_PHONE_NUMBER_FIELD");
		dummyPage.elementSendText("SIGN_UP_PAGE_PHONE_NUMBER_FIELD", "1414141414");
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
		dummyPage.clickElement(IXPNEvents.SIGN_UP_OTP_NUMBER_BLOCKS);
		dummyPage.clickElement2("//android.widget.LinearLayout[contains(@resource-id,\"otp\")]/android.widget.EditText");
		//dummyPage.clickElement2("//*[contains(@resource-id,\"btn_one\")]");
		//dummyPage.clickElement2("//*[contains(.,'1')]");
		dummyPage.run("adb -s "+testContext.clientProperties.getProperty(IClient.UDID_VALUE)+" shell input keyevent 8");
		dummyPage.run("adb -s "+testContext.clientProperties.getProperty(IClient.UDID_VALUE)+" shell input keyevent 91");
		dummyPage.run("adb -s "+testContext.clientProperties.getProperty(IClient.UDID_VALUE)+" shell input keyevent 9");
		dummyPage.run("adb -s "+testContext.clientProperties.getProperty(IClient.UDID_VALUE)+" shell input keyevent 9");
		dummyPage.run("adb -s "+testContext.clientProperties.getProperty(IClient.UDID_VALUE)+" shell input keyevent 9");
		dummyPage.run("adb -s "+testContext.clientProperties.getProperty(IClient.UDID_VALUE)+" shell input keyevent 9");
		dummyPage.defaultWaitTillElementAppear(IXPNEvents.SIGN_UP_INCORRECT_OTP_MESSAGE,10000);
		dummyPage.verifyElementFound(IXPNEvents.SIGN_UP_INCORRECT_OTP_MESSAGE);
		result.setStatus(true);
		result.setExceptionList(exceptionList);
		return result;

	}

	public void release() {

		DummyPage dummyPage = new DummyPage(testContext);


	}

}
