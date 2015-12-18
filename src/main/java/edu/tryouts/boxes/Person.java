package edu.tryouts.boxes;

import static alex.mojaki.boxes.Boxes.box;

import alex.mojaki.boxes.Box;
import alex.mojaki.boxes.CommonBox;
import alex.mojaki.boxes.PowerBox;
import alex.mojaki.boxes.collections.BoxesMap;
import alex.mojaki.boxes.observers.change.ChangePrinter;

public class Person {
    public PowerBox<String> firstName = new CommonBox<String>(Person.class, "firstName").notNull();
    public Box<String> lastName = box();

    /**
     * Command line application execution.
     *
     * @param args Array of Strings referencing the command line arguments.
     */
    public static void main(String[] args) {
        Person person = new Person();
        person.firstName.addChangeObserver(ChangePrinter.I);

        person.firstName.set("John");
        person.lastName.set("Doe");

        assert person.firstName.get().equals("John");
        assert person.lastName.get().equals("Doe");

        // Set up the map
        BoxesMap<String, String> map = new BoxesMap<>();
        map.putBox("firstName", person.firstName);
        map.putBox("lastName", person.lastName);

// Now you can use either the boxes or the map as normal...
        map.put("firstName", "Jack");
        person.lastName.set("Jones");

// And the results appear seamlessly on the other side
        assert person.firstName.get().equals("Jack");
        assert map.get("lastName").equals("Jones");
    }
}
