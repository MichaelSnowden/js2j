package com.michaelsnowden.js2j;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author michael.snowden on 7/22/15.
 */
public class JavaScriptObjectModel {
    protected String staticName;
    protected String simpleClassName;
    protected List<JavaScriptFunctionModel> functions = new ArrayList<>();
    protected List<JavaScriptVariableModel> variables = new ArrayList<>();
    protected List<JavaScriptFunctionModel> staticFunctions = new ArrayList<>();
    protected List<JavaScriptVariableModel> staticVariables = new ArrayList<>();

    public JavaScriptObjectModel(String staticName, String className, Map jsMap) throws InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.staticName = staticName;
        simpleClassName = className;
        addList(jsMap, functions, "functions", JavaScriptFunctionModel.class);
        addList(jsMap, variables, "variables", JavaScriptVariableModel.class);
        addList(jsMap, staticFunctions, "staticFunctions", JavaScriptFunctionModel.class);
        addList(jsMap, staticVariables, "staticVariables", JavaScriptVariableModel.class);
    }

    protected void addList(Map outerMap, List toList, String key, Class c) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List fromList = (List) outerMap.get(key);
        for (Object o : fromList) {
            Map innerMap = (Map) o;
            toList.add(c.getConstructor(Map.class).newInstance(innerMap));
        }
    }

    public String getStaticName() {
        return staticName;
    }

    public String getSimpleClassName() {
        return simpleClassName;
    }

    public List<JavaScriptFunctionModel> getFunctions() {
        return functions;
    }

    public List<JavaScriptVariableModel> getVariables() {
        return variables;
    }

    public List<JavaScriptFunctionModel> getStaticFunctions() {
        return staticFunctions;
    }

    public List<JavaScriptVariableModel> getStaticVariables() {
        return staticVariables;
    }
}