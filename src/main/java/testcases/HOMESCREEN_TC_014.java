package testcases;

import com.framework.executor.TestCase;
import com.framework.executor.TestResult;
import com.framework.pages.DummyPage;
import events.IXPNEvents;
import util.OkUtil;

import java.util.ArrayList;

public class HOMESCREEN_TC_014 extends TestCase {

	ArrayList exceptionList = new ArrayList<>();

	public void setUp() {
		testId = "HOMESCREEN_TC_014";
		testDesc = "To verify merchant can add customers";
		DummyPage dummyPage = new DummyPage(testContext);
		OkUtil.navigateToHomeScreen(testContext);
	}

	@Override
	public TestResult test() {
		TestResult result = new TestResult(testId, testDesc);

		String number = null;
		DummyPage dummyPage = new DummyPage(testContext);
		String name="";
		try {
			name=dummyPage.returnTimein24Hour(0);
			System.out.println(name);
			 number=9+(long) (Math.random()*Math.pow(9,9)) +"";
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		dummyPage.clickElement(IXPNEvents.HOME_PAGE_ADD_CUSTOMER_BUTTON);
		dummyPage.defaultWait(1000);
		dummyPage.clickElement(IXPNEvents.ADD_CUSTOMER_NAME_FIELD);
		dummyPage.elementSendText(IXPNEvents.ADD_CUSTOMER_NAME_FIELD,name);
		dummyPage.clickElement(IXPNEvents.OK_BUTTON);
		dummyPage.clickElement(IXPNEvents.ADD_CUSTOMER_NUMBER_FIELD);
		dummyPage.elementSendText(IXPNEvents.ADD_CUSTOMER_NUMBER_FIELD,number);
		dummyPage.clickElement(IXPNEvents.OK_BUTTON);
		dummyPage.backButton();
		String xpath="//android.widget.TextView[contains(@resource-id,\"tvName\") and contains(@text,\""+name+"\")]";
		int count =0;
		while(dummyPage.isElementFound2(xpath)==false)
		{   count++;
		    if(count>5)
		    	break;
		//	dummyPage.swipe(500,300	,200,200,200);
			//dummyPage.swipe1("//android.widget.TextView[contains(@resource-id,\"tvName\")]",10,0,500);
		    dummyPage.swipe1("//android.widget.LinearLayout[contains(@index,\"5\")]",0,10,1000);
		}
		dummyPage.verifyElementFound2(xpath);
		result.setStatus(true);
		result.setExceptionList(exceptionList);
		return result;
	}

	public void release() {

		DummyPage dummyPage = new DummyPage(testContext);
	}

}
