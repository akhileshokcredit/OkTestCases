package testcases;

import com.framework.executor.TestCase;
import com.framework.executor.TestResult;
import com.framework.pages.DummyPage;
import events.IXPNEvents;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import util.OkUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class API_TC_001 extends TestCase {
	String journey_id;
	ArrayList exceptionList = new ArrayList<>();

	public void setUp() {
		testId = "API_TC_001";
		testDesc = "";
		DummyPage dummyPage = new DummyPage(testContext);

	}

	@Override
	public TestResult test() {
		TestResult result = new TestResult(testId, testDesc);
		DummyPage dummyPage = new DummyPage(testContext);


		String apiResponse = OkUtil.requestOTP(testContext);
		JsonNode rootNode,otpId = null;
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			rootNode = objectMapper.readTree(apiResponse);
			otpId=rootNode.getPath("otp_id");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String otp="";
		OkUtil.verifyOTP(testContext,otpId.toString(),otp);
		String assertion="";
		OkUtil.authenticateOTP(testContext,assertion);

		result.setExceptionList(exceptionList);
		return result;

	}

	public void release() {
		DummyPage dummyPage = new DummyPage(testContext);

	}

}
