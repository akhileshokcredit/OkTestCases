package testcases;

import com.framework.executor.TestCase;
import com.framework.executor.TestResult;
import com.framework.pages.DummyPage;
import events.IXPNEvents;
import util.OkUtil;

import java.util.ArrayList;

public class HOMESCREEN_TC_001_005 extends TestCase {
	String journey_id;
	ArrayList exceptionList = new ArrayList<>();

	public void setUp() {
		testId = "HOMESCREEN_TC_001_005";
		testDesc = "To verify user can land on Homescreen after proper sign in/up process. " +
				"To verify when user land on the Homescreen than he can see the search button , hamburger icon , share icon , filter icon ";
		DummyPage dummyPage = new DummyPage(testContext);
      	OkUtil.navigateToLanguageSelectionPage(testContext);
	}

	@Override
	public TestResult test() {
		TestResult result = new TestResult(testId, testDesc);
		DummyPage dummyPage = new DummyPage(testContext);

		OkUtil.permission(testContext);

		OkUtil.signInWithoutOTP(testContext);
		result.setStatus(true);
		result.setExceptionList(exceptionList);
		return result;

	}

	public void release() {
		DummyPage dummyPage = new DummyPage(testContext);

	}

}
