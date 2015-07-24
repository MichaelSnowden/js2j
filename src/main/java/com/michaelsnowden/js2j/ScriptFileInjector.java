package com.michaelsnowden.js2j;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;

/**
 * @author michael.snowden on 7/22/15.
 */
class ScriptFileInjector {
    public static void injectScriptFile(String pathToResource) throws IOException {
        String script = Resources.toString(Resources.getResource(pathToResource), Charsets.UTF_8);
        Javascript.execFile("inject.js.vm", script);
    }

    public static void injectAllNecessaryJavaScript() throws IOException {
        String[] files = {"selectorator.min.js", "main.js", "annotate.js", "function-name.js"};
        for (String file : files) {
            injectScriptFile(file);
        }
    }
}