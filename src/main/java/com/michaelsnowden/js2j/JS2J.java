package com.michaelsnowden.js2j;

import org.openqa.selenium.JavascriptExecutor;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Map;

/**
 * Facade class for JS2J.
 *
 * @author michael.snowden on 7/24/15.
 */
public class JS2J {
    public static void init(JavascriptExecutor executor) throws IOException {
        Javascript.setJavascriptExecutor(executor);
        ScriptFileInjector.injectAllNecessaryJavaScript();
    }

    public static Path generate(String jsVarName) throws NoSuchMethodException, IOException, InstantiationException,
            URISyntaxException, IllegalAccessException, InvocationTargetException {
        return generate(jsVarName, jsVarName);
    }

    public static Path generate(String jsVarName, String generatedClassName) throws IOException, URISyntaxException,
            NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Map map = (Map) Javascript.execFileAndReturn("jso.js.vm", jsVarName);
        JavaScriptObjectModel jsoModel = new JavaScriptObjectModel(jsVarName, generatedClassName, map);
        return Templatizer.toClasspath(jsoModel, generatedClassName + ".java", "jso.java.vm");
    }
}