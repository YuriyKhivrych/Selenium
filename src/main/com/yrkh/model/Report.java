package com.yrkh.model;

import java.util.ArrayList;

/**
 * Created by Yuriy on 20.04.2016.
 */
public class Report {
    private ArrayList<String[]> report;

    public Report(){
        this.report = new ArrayList<String[]>();
        this.report.add(new String[]{
                "Result",
                "Step name",
                "Action",
                "Locator",
                "Parameter"
        });
    }

    public Report add(Action action, String message){
        report.add(new String[]{
                message,
                action.getStepName(),
                action.getAction().toString(),
                action.getLocatorName(),
                action.getParameter()
        });
        return this;
    }

    public ArrayList<String[]> getReport(){
        return this.report;
    }
}
