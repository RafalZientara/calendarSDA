package com.tools.apps.rzientara.sdaCalendar.interactors;

import java.util.Scanner;

public class ConsoleDecision {

    private Scanner scanner = new Scanner(System.in);

    public boolean userWantToContinue() {
        do {
            String output = scanner.next();
            if (output != null) {
                switch (output.toLowerCase()) {
                    case "y":
                    case "yes":
                        return true;
                    case "n":
                    case "no":
                        return false;
                }
            }
            System.out.println("Write \"y\" or \"n\"");
        } while (true);
    }
}
