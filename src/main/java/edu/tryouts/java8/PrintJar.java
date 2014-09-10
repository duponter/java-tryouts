package edu.tryouts.java8;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Print text based resource file inside a jar file. (eg: META-INF/MANIFEST.MF)
 *
 * @author Zemian Deng
 */
public class PrintJar {
    public static void main(String[] args) throws Exception {
        // Search given name in a jar
        JarFile jarFile = new JarFile(args[0]);
        final String searchName = (args.length >= 2) ? args[1] : "META-INF/MANIFEST.MF";
        Optional<JarEntry> searchResult = jarFile
                .stream()
                .filter(e -> e.getName().equals(searchName))
                .findFirst();

        // Print the JarEntry
        JarEntry entry = searchResult.orElseThrow(() -> new RuntimeException(searchName + " not found!"));
        try (InputStream instream = jarFile.getInputStream(entry)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
            reader.lines().forEach(System.out::println);
        }
    }
}