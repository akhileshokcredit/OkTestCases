package testcases;

import com.framework.events.IClient;
import com.framework.executor.TestCase;
import com.framework.executor.TestResult;
import com.framework.pages.DummyPage;
import events.IXPNEvents;
import util.OkUtil;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

public class LOGIN_TC_027 extends TestCase {
	String journey_id;
	ArrayList exceptionList = new ArrayList<>();

	public void setUp() {
		testId = "LOGIN_TC_027";
		testDesc = "To verify resend otp button is clickable on OTP screen .";
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
		dummyPage.elementSendText("SIGN_UP_PAGE_PHONE_NUMBER_FIELD", "9123456599");
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


		dummyPage.waitForElementInCaseOfStaleElement(IXPNEvents.RESEND_OTP_BUTTON,10000);
		dummyPage.clickElement(IXPNEvents.RESEND_OTP_BUTTON);
		if(dummyPage.isElementFound(IXPNEvents.RESEND_OTP_BUTTON)==false)
		{
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
