package testcases;

import com.framework.events.IClient;
import com.framework.executor.TestCase;
import com.framework.executor.TestResult;
import com.framework.pages.DummyPage;
import events.IXPNEvents;
import util.OkUtil;

import java.util.ArrayList;

public class LOGIN_TC_030 extends TestCase {
	String journey_id;
	ArrayList exceptionList = new ArrayList<>();

	public void setUp() {
		testId = "LOGIN_TC_030";
		testDesc = "To verify user can change language by going back to the Launch Screen .";
		DummyPage dummyPage = new DummyPage(testContext);

	}

	@Override
	public TestResult test() {
		TestResult result = new TestResult(testId, testDesc);
		DummyPage dummyPage = new DummyPage(testContext);

		OkUtil.permission(testContext);


		OkUtil.selectLanguageEnglishOnSignUpPage(testContext);
		dummyPage.backButton();
		dummyPage.clickElement(IXPNEvents.SELECT_LANGUAGE_HINDI_BUTTON);
         String text=dummyPage.getElementText(IXPNEvents.WELCOME_PAGE_VERIFY_MOBILE_BUTTON);
		if(text.contains("मोबाइल वेरिफिकेशन"))
		{
			dummyPage.backButton();
			dummyPage.clickElement(IXPNEvents.SELECT_LANGUAGE_GUJARATI_BUTTON);
			text=dummyPage.getElementText(IXPNEvents.WELCOME_PAGE_VERIFY_MOBILE_BUTTON);
			if(text.contains("મોબાઇલ ચકાસણી કરો"))
			{
				dummyPage.backButton();
				dummyPage.clickElement(IXPNEvents.SELECT_LANGUAGE_MARATHI_BUTTON);
				text=dummyPage.getElementText(IXPNEvents.WELCOME_PAGE_VERIFY_MOBILE_BUTTON);
				if(text.contains("भ्रमणध्वनी सत्यापित करा"))
				{
					dummyPage.backButton();
					dummyPage.clickElement(IXPNEvents.SELECT_LANGUAGE_PUNJABI_BUTTON);
					text=dummyPage.getElementText(IXPNEvents.WELCOME_PAGE_VERIFY_MOBILE_BUTTON);
					if(text.contains("ਮੋਬਾਇਲ ਨੰਬਰ ਵੇਰਿਫਿਕੇਸ਼ਨ"))
					{
						dummyPage.backButton();
						dummyPage.clickElement(IXPNEvents.SELECT_LANGUAGE_TAMIL_BUTTON);
						text=dummyPage.getElementText(IXPNEvents.WELCOME_PAGE_VERIFY_MOBILE_BUTTON);
						if(text.contains("மொபைலை சரிபார்க்கவும்"))
						{
							dummyPage.backButton();
							dummyPage.clickElement(IXPNEvents.SELECT_LANGUAGE_MALAYALAM_BUTTON);
							text=dummyPage.getElementText(IXPNEvents.WELCOME_PAGE_VERIFY_MOBILE_BUTTON);
							if(text.contains("മൊബൈൽ പരിശോധിച്ചുറപ്പിക്കുക"))
							{   dummyPage.backButton();
								dummyPage.clickElement(IXPNEvents.SELECT_LANGUAGE_TELUGU_BUTTON);
								text=dummyPage.getElementText(IXPNEvents.WELCOME_PAGE_VERIFY_MOBILE_BUTTON);
								if(text.contains("మొబైల్ నంబర్\u200Cను ధృవపరుచుకోండి"))
								{
									result.setStatus(true);
								}
							}
						}
					}
				}
			}
		}

		result.setExceptionList(exceptionList);
		return result;

	}

	public void release() {
		DummyPage dummyPage = new DummyPage(testContext);

	}

}
