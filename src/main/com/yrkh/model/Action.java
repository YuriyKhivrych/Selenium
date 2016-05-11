package com.yrkh.model;

import org.openqa.selenium.By;

/**
 * Created by Yuriy on 19.04.2016.
 */
public class Action {
    private boolean flag;
    private String caseName;
    private String stepName;
    private ActionTypes action;
    private By locator;
    private LocatorTypes locatorType;
    private String idString;
    private String parameter;

    public Action(){
        init();
    }

    private void init(){
        this.flag = false;
        this.caseName = new String();
        this.stepName = new String();
        this.action = ActionTypes.open_browser;
        this.locator = By.id("");
        this.locatorType = LocatorTypes.id;
        this.idString = new String();
        this.parameter = new String();
    }

    public boolean getFlag(){
        return this.flag;
    }

    public String getCaseName (){
        return this.caseName;
    }

    public String getStepName(){
        return this.stepName;
    }

    public By getLocator (){
        return this.locator;
    }

    public String getLocatorName (){
        if (this.locator.equals(By.id(""))) return "";
        else return this.locator.toString();
    }

    public ActionTypes getAction(){
        return this.action;
    }

    public String getParameter (){
        return this.parameter;
    }

    public Action setFlag (boolean flag1){
        this.flag = flag1;
        return this;
    }

    public Action setCaseName (String caseName1){
        this.caseName = caseName1;
        return this;
    }

    public Action setStepName (String stepName1){
        this.stepName = stepName1;
        return this;
    }

    public Action setAction(ActionTypes action1){
        this.action = action1;
        return this;
    }

    public Action setParameter(String parameter1){
        this.parameter = parameter1;
        return this;
    }

    public Action setLocator (String locatorType1, String idString1){
        if(!locatorType1.isEmpty()) {
            LocatorTypes type = LocatorTypes.valueOf(locatorType1);
            switch (type) {
                case id:
                    this.locator = By.id(idString1);
                    break;
                case name:
                    this.locator = By.name(idString1);
                    break;
                case css:
                    this.locator = By.cssSelector(idString1);
                    break;
                case xpath:
                    this.locator = By.xpath(idString1);
                    break;
                default:
                    this.locator = By.id("");
                    break;
            }
        }
        return this;
    }


}
