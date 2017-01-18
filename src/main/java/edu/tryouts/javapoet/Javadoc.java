package edu.tryouts.javapoet;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.MethodSpec;

/**
 * Class Javadoc.
 *
 * @author Erwin Dupont
 * @since 2015-01-30
 */
public class Javadoc {
    /**
     * Command line application execution.
     *
     * @param args Array of Strings referencing the command line arguments.
     */
    public static void main(String[] args) {
        MethodSpec dismiss = MethodSpec.methodBuilder("dismiss")
                .addJavadoc("Hides {@code message} from the caller's history. Other\n"
                        + "participants in the conversation will continue to see the\n"
                        + "message in their own history unless they also delete it.\n")
                .addJavadoc("\n")
                .addJavadoc("<p>Use {@link #delete($T)} to delete the entire\n"
                        + "conversation for all participants.\n", Conversation.class)
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .addParameter(Message.class, "message")
                .build();

        System.out.println(dismiss);
    }

    private interface Conversation {
    }

    private interface Message {
    }
}
