import com.michaelsnowden.js2j.JS2J;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.nio.file.Path;

/**
 * @author michael.snowden on 7/24/15.
 */
public class JQueryTest {
    @Test
    public void testJQuery() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, URISyntaxException, ClassNotFoundException {
        RemoteWebDriver driver = new ChromeDriver();
        driver.get("https://www.glassdoor.com");
        JS2J.init(driver);
        final String jQueryClassName = "$";
        verifyGenerationIsSuccessful(jQueryClassName);
    }

    private void verifyGenerationIsSuccessful(String jsClassName) throws NoSuchMethodException, IOException, InstantiationException, URISyntaxException, IllegalAccessException, InvocationTargetException {
        final Path generatedFilePath = JS2J.generate(jsClassName);
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        final int compilationResult = compiler.run(null, null, null, generatedFilePath.toString());
        Assert.assertEquals(compilationResult, 0, "Verify that the compilation of the generated class was successful");
    }
}