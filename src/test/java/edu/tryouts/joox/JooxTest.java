package edu.tryouts.joox;

import org.joox.Match;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.joox.JOOX.$;

/**
 * Class JooxTest.
 *
 * @author Erwin Dupont
 * @since 2014-02-04
 */
public class JooxTest {
    @Test
    public void testJoox() throws IOException, SAXException, URISyntaxException {
        Path pom = Paths.get(JooxTest.class.getResource("/").toURI()).resolve("../../pom.xml").normalize();
        System.out.printf("%s\n\n", pom);
//        $(pom.toUri())
//                .find("groupId")
//                .filter(ctx -> $(ctx).siblings("scope").isNotEmpty())
//                .filter(ctx -> $(ctx).siblings("scope").isEmpty() || $(ctx).siblings("scope").matchText("compile").isNotEmpty())
//                .each(ctx -> System.out.printf("%s:%s:%s%n", $(ctx).text(), $(ctx).siblings("artifactId").text(), $(ctx).siblings("version").text()));
//
//        $(pom.toUri())
//                .find("groupId")
//                .filter(ctx -> $(ctx).siblings("scope").isNotEmpty())
//                .content(ctx ->
//                        $(ctx).text() + ":" +
//                                $(ctx).siblings("artifactId").text() + ":" +
//                                $(ctx).siblings("version").text()
//                )
//                .rename("artifact")
//                .each(ctx -> System.out.println(ctx));


        Document document = $(pom.toUri()).document();
//        Document builder = JOOX.builder().newDocument();
//        Element dependency = builder.createElement("dependency");
//        dependency.appendChild(builder.createElement("groupId"));
        Match dependency = $("dependency").append($("groupId", "org.jooq")).append($("artifactId", "jooq")).append($("version", "1.2.0"));
        $(document).find("dependencies").append(dependency);
//        $(document).find("dependency").each(ctx -> System.out.println(ctx));
        //.filter(ctx -> $(ctx).isEmpty()).remove();
//        newDep.removeAttr("xmlns");
//        newDep.append("<groupId></groupId>");
        System.out.println("doc = " + $(document));

    }
}
