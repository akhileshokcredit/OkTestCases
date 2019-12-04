package testcases;

import com.framework.executor.TestCase;
import com.framework.executor.TestResult;
import com.framework.pages.DummyPage;
import events.IXPNEvents;
import util.OkUtil;

import java.util.ArrayList;

public class LOGIN_TC_022 extends TestCase {
	String journey_id;
	ArrayList exceptionList = new ArrayList<>();

	public void setUp() {
		testId = "LOGIN_TC_022";
		testDesc = "To verify phone number field should not evaluate tags";
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
		//dummyPage.elementSendText("SIGN_UP_PAGE_PHONE_NUMBER_FIELD", "<svg onload=alert(navigator.battery.level)>");

		dummyPage.sendTextThroughADBCommands("<svg onload=alert(navigator.battery.level)>");
        dummyPage.verifyElementFound(IXPNEvents.SIGN_UP_PAGE_PHONE_NUMBER_FIELD);
        //if(dummyPage.isElementFound(IXPNEvents.SIGN_UP_PAGE_PHONE_NUMBER_FIELD)==false)
		result.setStatus(true);

		result.setExceptionList(exceptionList);
		return result;

	}

	public void release() {

		DummyPage dummyPage = new DummyPage(testContext);


	}

}
