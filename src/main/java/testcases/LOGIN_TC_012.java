package testcases;

import com.framework.executor.TestCase;
import com.framework.executor.TestResult;
import com.framework.pages.DummyPage;
import events.IXPNEvents;
import util.OkUtil;

import java.util.ArrayList;

public class LOGIN_TC_012 extends TestCase {
	String journey_id;
	ArrayList exceptionList = new ArrayList<>();

	public void setUp() {
		testId = "LOGIN_TC_012";
		testDesc = "To verify  that user can change number from otp (presently ) it can be done by pressing back button";
		DummyPage dummyPage = new DummyPage(testContext);

	}

	@Override
	public TestResult test() {
		TestResult result = new TestResult(testId, testDesc);
		DummyPage dummyPage = new DummyPage(testContext);
		OkUtil.selectLanguageEnglishOnSignUpPage(testContext);
		dummyPage.clickElement("WELCOME_PAGE_VERIFY_MOBILE_BUTTON");
		dummyPage.defaultWait();
		dummyPage.waitForElementInCaseOfStaleElement(IXPNEvents.SIGN_UP_PAGE_PHONE_NUMBER_FIELD,10000);
		dummyPage.verifyElementFound("SIGN_UP_PAGE_PHONE_NUMBER_FIELD");
	//	dummyPage.clickElement("SIGN_UP_PAGE_PHONE_NUMBER_FIELD");

		//dummyPage.elementSendText("SIGN_UP_PAGE_PHONE_NUMBER_FIELD", "<svg onload=alert(navigator.battery.level)>");
        dummyPage.sendTextThroughADBCommands("1111111111");
		dummyPage.waitForElementInCaseOfStaleElement("OK_BUTTON", 10000);
		dummyPage.clickElement("OK_BUTTON");
		int count = 0;

		while(!dummyPage.isElementFound("SIGN_UP_WHATSAPP_DIALOG_BOX_NOT_ALLOW_BUTTON")) {
			++count;
			dummyPage.defaultWait(2000);
			if (count > 10) {
				break;
			}
		}

		if (dummyPage.isElementFound("SIGN_UP_WHATSAPP_DIALOG_BOX_ALLOW_BUTTON")) {
			dummyPage.clickElement("SIGN_UP_WHATSAPP_DIALOG_BOX_NOT_ALLOW_BUTTON");
		}

		dummyPage.clickElement("SIGN_UP_OTP_NUMBER_BLOCKS");
		dummyPage.closeKeyboard();
		dummyPage.backButton();
		dummyPage.waitForElementInCaseOfStaleElement(IXPNEvents.SIGN_UP_PAGE_PHONE_NUMBER_FIELD,10000);
		dummyPage.verifyElementFound("SIGN_UP_PAGE_PHONE_NUMBER_FIELD");
		dummyPage.clearTextField(IXPNEvents.SIGN_UP_PAGE_PHONE_NUMBER_FIELD);
		dummyPage.sendTextThroughADBCommands("2222222222");
		dummyPage.waitForElementInCaseOfStaleElement("OK_BUTTON", 10000);
		dummyPage.clickElement("OK_BUTTON");
		count = 0;
		while(!dummyPage.isElementFound("SIGN_UP_WHATSAPP_DIALOG_BOX_NOT_ALLOW_BUTTON")) {
			++count;
			dummyPage.defaultWait(2000);
			if (count > 15) {
				break;
			}
		}
		if (dummyPage.isElementFound("SIGN_UP_WHATSAPP_DIALOG_BOX_ALLOW_BUTTON")) {
			dummyPage.clickElement("SIGN_UP_WHATSAPP_DIALOG_BOX_NOT_ALLOW_BUTTON");
		}
		dummyPage.verifyElementFound2("//*[contains(@text,\"delivered soon on 2222222222\")]");
		result.setStatus(true);

		result.setExceptionList(exceptionList);
		return result;

	}

	public void release() {

		DummyPage dummyPage = new DummyPage(testContext);


	}

}
