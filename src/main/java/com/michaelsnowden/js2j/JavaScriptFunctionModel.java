package com.michaelsnowden.js2j;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author michael.snowden on 7/22/15.
 */
public class JavaScriptFunctionModel {
    protected String name;
    protected List<String> args;
    protected List<String> params = new ArrayList<String>();

    public JavaScriptFunctionModel(Map map) {
        this((String) map.get("name"), (List) map.get("params"));
    }

    public JavaScriptFunctionModel(String name, List<String> args) {
        this.name = name;
        this.args = args;
        for (String arg : args) {
            params.add("Object " + arg);
        }
    }

    public String getScript() {
        if (args.size() == 0) {
            return name + "()";
        }
        String script = name + "(";
        for (int i = 0; i < args.size() - 1; ++i) {
            script += "\" + " + args.get(i) + " + \", ";
        }
        script += "\" + " + args.get(args.size() - 1) + " + \")";
        return script;
    }

    public List<String> getArgs() {
        return args;
    }

    public List<String> getParams() {
        return params;
    }

    public String getParamString() {
        return Joiner.on(", ").join(params);
    }

    public String getName() {
        return name;
    }

    public String getArgString() {
        return Joiner.on(", ").join(args);
    }
}
