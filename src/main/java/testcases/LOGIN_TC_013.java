package testcases;

import com.framework.events.IClient;
import com.framework.executor.TestCase;
import com.framework.executor.TestResult;
import com.framework.pages.DummyPage;
import events.IXPNEvents;
import util.OkUtil;

import java.util.ArrayList;

public class LOGIN_TC_013 extends TestCase {
	String journey_id;
	ArrayList exceptionList = new ArrayList<>();

	public void setUp() {
		testId = "LOGIN_TC_013";
		testDesc = "Whenever user signs in/up on the phone number page , number keypad should open up automatically.";
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

	   String output= dummyPage.run("adb -s "+testContext.getClientProperties().getProperty(IClient.UDID_VALUE)+" shell dumpsys input_method | grep mInputShown\n");
	if(output.contains("mInputShown=true")==true) {
		result.setStatus(true);
	}
	else{
		exceptionList.add("Keyboard is not open ");
	}
		result.setExceptionList(exceptionList);
		return result;

	}

	public void release() {

		DummyPage dummyPage = new DummyPage(testContext);


	}

}
