package com.yrkh.wd;

import com.yrkh.model.Action;
import com.yrkh.model.Report;
import com.yrkh.utils.ExcelWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Yuriy on 08.12.2015.
 */
public class WebDriverEngine {
    private WebDriver webDriver;
    private WebDriverWait wait;
    private Report report;

    public WebDriverEngine(){
    }

    public void runAll(ArrayList<Action> actionArrayList) throws Exception {
        Iterator<Action> actionIterator = actionArrayList.iterator();
        while (actionIterator.hasNext()) {
            execute(actionIterator.next());
        }
    }

    public void runAllWithReport(ArrayList<Action> actionArrayList, String reportFile) throws Exception {
        this.report = new Report();
        Iterator<Action> actionIterator = actionArrayList.iterator();
        while (actionIterator.hasNext()) {
            execute(actionIterator.next());
        }
        writeReport(this.report, reportFile, "1");
    }

    private void execute(Action action){
        try {
            System.out.println(action.getStepName());
            switch (action.getAction()) {
                case open_browser:
                    //TODO open different browsers
                    FirefoxProfile customProfile = new FirefoxProfile();
                    customProfile.setPreference("dom.disable_beforeunload", true);
                    webDriver = new FirefoxDriver(customProfile);
                    break;
                case navigate_to:
                    webDriver.get(action.getParameter());
                    break;
                case verify_element:
                    verifyElement(action.getLocator());
                    break;
                case click_element:
                    webDriver.findElement(action.getLocator()).click();
                    break;
                case send_keys:
                    sendKeys(action.getLocator(), action.getParameter());
                    break;
                case store_text:
                    //TODO Write result somewhere
                    String str = webDriver.findElement(action.getLocator()).getText();
                    System.out.println("Text is: " + str);
                    break;
                case close_browser:
                    webDriver.close();
                    break;
                default:
                    System.out.println("Wrong action.");
                    report.add(action, "Wrong action");
            }
            report.add(action, "Success");
        }
        catch (Exception e){
            report.add(action, "Failure");
        }
    }

    protected void sendKeys(By byLocator, String keys){
        webDriver.findElement(byLocator).click();
        webDriver.findElement(byLocator).clear();
        webDriver.findElement(byLocator).sendKeys(keys);
    }

    private void verifyElement(By locator){
        wait = new WebDriverWait(this.webDriver,60);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private void writeReport(Report report1, String path, String sheetName){
        new ExcelWriter().writeToFile(report1.getReport(), path, sheetName);
    }

}
