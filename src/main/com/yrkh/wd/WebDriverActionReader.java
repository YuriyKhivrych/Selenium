package com.yrkh.wd;

import com.yrkh.model.ActionTypes;
import com.yrkh.utils.ConfigurationReader;
import com.yrkh.model.Action;
import com.yrkh.utils.ExcelReader;

import java.util.ArrayList;

/**
 * Created by Yuriy on 19.04.2016.
 */
public class WebDriverActionReader {

    //Indexes read action's params
    private int flagix, case_nameix, step_name, actionix, locatorix, id_stringix, parameterix;

    public WebDriverActionReader(){
        init();
    }

    private void init(){
        ConfigurationReader configurationReader = new ConfigurationReader();
        flagix = Integer.parseInt(configurationReader.getProperty("flag"));
        case_nameix = Integer.parseInt(configurationReader.getProperty("case_name"));
        step_name = Integer.parseInt(configurationReader.getProperty("step_name"));
        actionix = Integer.parseInt(configurationReader.getProperty("action"));
        locatorix = Integer.parseInt(configurationReader.getProperty("locator"));
        id_stringix = Integer.parseInt(configurationReader.getProperty("id_string"));
        parameterix = Integer.parseInt(configurationReader.getProperty("parameter"));
    }

    public ArrayList<Action> readAll(String path,String sheet) throws Exception{
        String[][] sActions = new ExcelReader(path).readData(sheet);
        ArrayList<Action> actionList = new ArrayList<Action>();

        for (int i = 1; i < sActions.length; i++){
            actionList.add(new Action()
                    .setFlag(readFlag(sActions[i][flagix]))
                    .setAction(readAction(sActions[i][actionix]))
                    .setCaseName(sActions[i][case_nameix])
                    .setLocator(sActions[i][locatorix],sActions[i][id_stringix])
                    .setStepName(sActions[i][step_name])
                    .setParameter(sActions[i][parameterix]));
        }
        return actionList;
    }

    // TODO: implement reading in boolean format from file
    private boolean readFlag(String flag){
        if(flag.equals("y")) return true;
        else return true;
    }

    private ActionTypes readAction(String actionName){
        return ActionTypes.valueOf(actionName);
    }
}
