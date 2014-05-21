package edu.tryouts.devoxx.java8;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.InputStreamReader;

/**
 * Class Nashorn.
 *
 * @author Erwin Dupont
 * @since 2014-04-04
 */
public class Nashorn {
        public static void main(String... args) throws Exception {
            ScriptEngineManager factory = new ScriptEngineManager();
            ScriptEngine nashornEngine = factory.getEngineByName("nashorn");
            nashornEngine.eval("print('hello world');");

            nashornEngine.eval(new InputStreamReader(Nashorn.class.getResourceAsStream("nashorn.js")));
            System.out.println(nashornEngine.get("version"));
            Invocable invocable = (Invocable) nashornEngine;
            Object result = invocable.invokeFunction("hello", "soroosh");
            System.out.println(result);
        }
}
