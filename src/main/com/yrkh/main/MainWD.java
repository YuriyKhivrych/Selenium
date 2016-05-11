package com.yrkh.main;

import com.yrkh.model.Action;
import com.yrkh.utils.ExcelWriter;
import com.yrkh.wd.WebDriverEngine;
import com.yrkh.wd.WebDriverActionReader;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Yuriy on 14.11.2015.
 */
public class MainWD {

    public static void main(String ... args) throws Exception{
        String filePath = System.getProperty("user.dir")+ File.separator + "res" + File.separator + "actions.xlsx";

        ArrayList <Action> actions = new WebDriverActionReader().readAll(filePath, "actions");
        new WebDriverEngine().runAllWithReport(actions, filePath.replace(".xlsx", " (report).xlsx"));
    }


}
