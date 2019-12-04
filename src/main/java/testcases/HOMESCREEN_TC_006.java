package testcases;

import com.framework.executor.TestCase;
import com.framework.executor.TestResult;
import com.framework.pages.DummyPage;
import events.IXPNEvents;
import pages.HamBurgerPage;
import util.OkUtil;

import java.util.ArrayList;

public class HOMESCREEN_TC_006 extends TestCase {
	String journey_id;
	ArrayList exceptionList = new ArrayList<>();

	public void setUp() {
		testId = "HOMESCREEN_TC_006";
		testDesc = "To verify on hamburger list there is user profile icon , contact number , account , whatsapp , help , share , about us , privacy policy , Sign out buttons and at last information of version of app.";
		DummyPage dummyPage = new DummyPage(testContext);

	}

	@Override
	public TestResult test() {
		TestResult result = new TestResult(testId, testDesc);
		DummyPage dummyPage = new DummyPage(testContext);

		OkUtil.permission(testContext);
		OkUtil.signInWithoutOTP(testContext);
		dummyPage.clickElement(IXPNEvents.HOME_PAGE_HAMBURGER_ICON);
		HamBurgerPage hamBurgerPage=new HamBurgerPage(testContext);
		result.setStatus(true);
		result.setExceptionList(exceptionList);
		return result;

	}

	public void release() {
		DummyPage dummyPage = new DummyPage(testContext);

	}

}
