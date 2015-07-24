package com.michaelsnowden.js2j;

import org.openqa.selenium.JavascriptExecutor;

import java.io.IOException;

/**
 * @author michael.snowden on 7/22/15.
 */
public class Javascript {
    protected static JavascriptExecutor executor;

    public static void setJavascriptExecutor(JavascriptExecutor executor) {
        Javascript.executor = executor;
    }

    public static void execFile(String file, Object model) throws IOException {
        execScript(Templatizer.toString(model, file).trim());
    }

    public static Object execFileAndReturn(String file, Object model) throws IOException {
        return execScriptAndReturn(Templatizer.toString(model, file).trim());
    }

    public static void execScript(String script) {
        executor.executeScript(script);
    }

    public static Object execScriptAndReturn(String script) {
        return executor.executeScript("return " + script);
    }

    public static String store(String construction) {
        Long id = (Long) Javascript.execScriptAndReturn("SeleniumJSO.var_id++");
        String varName = "seleniumJSO" + id;
        Javascript.execScript(varName + " = " + construction);
        return varName;
    }
}