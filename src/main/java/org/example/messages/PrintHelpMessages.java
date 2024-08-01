package org.example.messages;

public class PrintHelpMessages {

    public static void printAllOptions() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        System.out.println("use --create <link> <alias> to create a link");
        System.out.println("use --delete <alias> to delete a shortcut");

        System.out.println("use --update <alias> to update a shortcut");

        System.out.println("use --list to show all saved shortcut");

        System.out.println("use --reset to delete all shortcut");

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

    }
}
