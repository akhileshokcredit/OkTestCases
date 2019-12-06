package testcases;

import java.io.IOException;
import java.util.List;
import java.util.Map;


import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

import com.framework.device.AppClient;
import com.framework.device.AppClientFactory;
import com.framework.device.TestContext;
import com.framework.events.IClient;
import com.framework.executor.Executor;
import com.framework.executor.TestCaseDTO;
import com.framework.pages.DummyPage;
import com.framework.reports.ReportAppium;
import com.framework.reports.ReportExcel;
import com.framework.utils.AppiumStartStopLinux;
import com.framework.utils.AppiumStartStopWindow;
import com.framework.utils.CommonUtilities;
import com.framework.utils.EmailUtilities;
import util.OkUtil;

//import android.net.ParseException;

public class TestRunnerAppium {

    private final static String appName = "test";


    public static void main(String[] args) {


    //  String testCaseFileName ="/src/main/java/testsuite/UITestCase.properties";
        String testCaseFileName ="/src/main/java/testsuite/ApiTestCases.properties";
      String xpathFileName = "/src/main/java/config/WebAppXPATH.properties";
      String clientFileName = "/src/main/java/config/ClientAndroidPropertyWebApp.properties";

        String tool = "appium";

        TestContext testContext = new TestContext(xpathFileName, clientFileName);
        System.out.println(testContext.getClientProperties().getProperty(IClient.DEVICE_ENVIRONMENT));

        System.out.println(testContext.getClientProperties().getProperty(IClient.DEVICE_ENVIRONMENT));
        if (testContext.getClientProperties().getProperty(IClient.DEVICE_ENVIRONMENT).equals("TESTOBJECT") == false) {
            if (!testContext.getClientProperties().getProperty(IClient.PROJECT_TYPE).equals("API")) {
                if (!testContext.getClientProperties().getProperty(IClient.PROJECT_TYPE).equals("WEB")) {
                    if ((testContext.getClientProperties().getProperty(IClient.PROJECT_TYPE).equals("LINUX"))
                            || (testContext.getClientProperties().getProperty(IClient.PROJECT_TYPE)
                            .equals("MAC")) == false) {
                        AppiumStartStopWindow.startAppiumServer(testContext);
                    } else {
                        AppiumStartStopLinux.startAppiumServer(testContext);
                    }
                }
            }
        }
        try {
            AppClient appClient = AppClientFactory.getAppClient(testContext, "appium");
            testContext.setAppClient(appClient);
            // testContext.getAppClient().turnGPSOn();
            // Properties props = LoadProperty.loadProperties(testCaseFileName);
            List<TestCaseDTO> testCaseList = CommonUtilities.getTestCaseList(testCaseFileName);
            if (!testContext.getClientProperties().getProperty(IClient.PROJECT_TYPE).equals("API")) {

                if (!testContext.getClientProperties().getProperty(IClient.PROJECT_TYPE).equals("WEB")) {
                    if (testContext.getClientProperties().getProperty(IClient.UNINSTALL_OLD_APP).equals("YES")
                            || testContext.getClientProperties().getProperty(IClient.UNINSTALL_OLD_APP).equals("Yes")) {
                        try {
                            testContext.getAppClient().applicationClose();
                            OkUtil.uninstall(testContext);
                            OkUtil.install(testContext);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            String folderName = ReportAppium.setReport(true,
                    testContext.getClientProperties().getProperty(IClient.REPORT_FOLDER_PATH), "pdf");

            // wait is needed for jars
            try {
                Thread.sleep(25000);// wait needed for jars is 25 seconds
            } catch (Exception e) {
            }
            Map<String, Object> executorMap = Executor.execute(testContext, testCaseList);

            try {
                String pathName = testContext.getClientProperties().getProperty(IClient.REPORT_FOLDER_PATH);
                ReportExcel.generateReport(executorMap, appName, pathName);
                String path[] = ReportExcel.generateReport(executorMap, appName, pathName);
                ///// CommonUtilities.copyCompleteReports(folderName,testContext.getClientProperties().getProperty(IClient.REPORT_FOLDER_PATH));
                if (testContext.getClientProperties().getProperty(IClient.EMAIL_SEND).equals("Yes")
                        || testContext.getClientProperties().getProperty(IClient.EMAIL_SEND).equals("YES")) {
                    EmailUtilities.sendEmail(executorMap, path[0], path[1], folderName);
                }

            } catch (IOException e) {
                System.out.println("Exception while generating report : " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Exception while Sending Email : " + e.getMessage());
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (testContext.getClientProperties().getProperty(IClient.DEVICE_ENVIRONMENT)
                    .equals("TESTOBJECT") == false) {
                if (!testContext.getClientProperties().getProperty(IClient.PROJECT_TYPE).equals("API")) {
                    if (!testContext.getClientProperties().getProperty(IClient.PROJECT_TYPE).equals("WEB")) {
                        if ((testContext.getClientProperties().getProperty(IClient.PROJECT_TYPE).equals("LINUX"))
                                || (testContext.getClientProperties().getProperty(IClient.PROJECT_TYPE)
                                .equals("MAC")) == false) {
                            AppiumStartStopWindow.stopAppiumServer(testContext);
                        } else {
                            AppiumStartStopLinux.stopAppiumServer(testContext);
                        }
                    }
                }
            } else {
                testContext.getAppClient().TestObjectKillAppium();
            }
        }

        if (testContext.getClientProperties().getProperty(IClient.PROJECT_TYPE).equals("WEB")) {
            testContext.getAppClient().killWebDriver();
        }
        try {
            DummyPage dummyPage = new DummyPage(testContext);
            if (testContext.getClientProperties().getProperty(IClient.REBOOT).equals("Yes")
                    || testContext.getClientProperties().getProperty(IClient.REBOOT).equals("YES")) {
                dummyPage.reboot();
            }
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.exit(1);
    }
}
