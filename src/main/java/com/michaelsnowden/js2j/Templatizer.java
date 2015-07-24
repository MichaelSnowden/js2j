package com.michaelsnowden.js2j;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.tools.generic.DisplayTool;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author michael.snowden on 7/22/15.
 */
class Templatizer {
    public static void templatize(Object model, Writer writer, String templatePath) throws IOException {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();

        VelocityContext vc = new VelocityContext();
        vc.put("model", model);
        vc.put("display", new DisplayTool());

        Template vt = ve.getTemplate("templates/" + templatePath);
        vt.merge(vc, writer);
    }

    public static Path toClasspath(Object model, String simpleClassName, String templatePath) throws IOException, URISyntaxException {
        // TODO: find a way to do this with/without processingEnv
        final ClassLoader classLoader = Templatizer.class.getClassLoader();
        final Path path = Paths.get(Paths.get(classLoader.getResource("").getPath()).getParent().toString(),
                "generated-sources", "com", "michaelsnowden", "generated", simpleClassName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()));
        templatize(model, writer, templatePath);
        writer.close();
        return path.toAbsolutePath();
    }

    public static String toString(Object model, String templatePath) throws IOException {
        final StringWriter writer = new StringWriter();
        templatize(model, writer, templatePath);
        final String result = writer.toString();
        writer.close();
        return result;
    }
}