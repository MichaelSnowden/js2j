package com.michaelsnowden.js2j;

import java.util.Map;

/**
 * @author michael.snowden on 7/23/15.
 */
public class JavaScriptVariableModel {
    protected String name;

    public JavaScriptVariableModel(Map map) {
        this((String) map.get("name"));
    }

    public JavaScriptVariableModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
