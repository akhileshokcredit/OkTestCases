package testcases;

import com.framework.executor.TestCase;
import com.framework.executor.TestResult;
import com.framework.pages.DummyPage;
import events.IXPNEvents;
import pages.SelectLanguagePage;
import util.OkUtil;

import java.util.ArrayList;

public class LOGIN_TC_004 extends TestCase {
	String journey_id;
	ArrayList exceptionList = new ArrayList<>();

	public void setUp() {
		testId = "TC_004";
		testDesc = "To verify user can sign up using valid phone number through otp.";
		DummyPage dummyPage = new DummyPage(testContext);

	}

	@Override
	public TestResult test() {
		TestResult result = new TestResult(testId, testDesc);

		DummyPage dummyPage = new DummyPage(testContext);

		dummyPage.applicationClose();
		dummyPage.uninstall();
		dummyPage.install();
		OkUtil.permission(testContext);
		dummyPage.defaultWait();
		OkUtil.signInWithOTP(testContext);
		result.setStatus(true);
		result.setExceptionList(exceptionList);
		return result;

	}

	public void release() {

		DummyPage dummyPage = new DummyPage(testContext);


	}

}
