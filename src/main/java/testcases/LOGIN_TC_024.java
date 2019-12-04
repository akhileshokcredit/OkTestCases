package testcases;

import com.framework.executor.TestCase;
import com.framework.executor.TestResult;
import com.framework.pages.DummyPage;
import events.IXPNEvents;
import util.OkUtil;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

public class LOGIN_TC_024 extends TestCase {
	String journey_id;
	ArrayList exceptionList = new ArrayList<>();

	public void setUp() {
		testId = "LOGIN_TC_024";
		testDesc = "To verify that mobile number should be 10 digit number .";
		DummyPage dummyPage = new DummyPage(testContext);
	}

	@Override
	public TestResult test() {
		TestResult result = new TestResult(testId, testDesc);
		DummyPage dummyPage = new DummyPage(testContext);
		OkUtil.permission(testContext);
		OkUtil.selectLanguageEnglishOnSignUpPage(testContext);
		dummyPage.clickElement("WELCOME_PAGE_VERIFY_MOBILE_BUTTON");
		dummyPage.defaultWait();
		dummyPage.waitForElementInCaseOfStaleElement(IXPNEvents.SIGN_UP_PAGE_PHONE_NUMBER_FIELD,10000);
		dummyPage.verifyElementFound("SIGN_UP_PAGE_PHONE_NUMBER_FIELD");
	//	dummyPage.clickElement("SIGN_UP_PAGE_PHONE_NUMBER_FIELD");
		dummyPage.elementSendText("SIGN_UP_PAGE_PHONE_NUMBER_FIELD", "90827484");
		dummyPage.waitForElementInCaseOfStaleElement(IXPNEvents.OK_BUTTON,10000);
		dummyPage.clickElement("OK_BUTTON");
		dummyPage.clickElement("OK_BUTTON");
        Map image=dummyPage.createImages();

		BufferedImage bfImage= (BufferedImage) image.keySet().stream().findFirst().get();
		System.out.println(bfImage);
		System.out.println(image.values().stream().findFirst().get());
		String command="tesseract "+image.values().stream().findFirst().get() +" stdout";
        String content=dummyPage.run(command);
		result.setExceptionList(exceptionList);
		return result;
	}

	public void release() {
		DummyPage dummyPage = new DummyPage(testContext);
	}
}
