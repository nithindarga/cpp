import java.io.*;
import java.util.*;

public class UserIDGeneration {
    public static String generateUserID(String firstName, String lastName, int pin, int n) {
        // Step 1 - Determine the smaller name and the longer name
        String smallerName, longerName;
        if (firstName.length() < lastName.length()) {
            smallerName = firstName;
            longerName = lastName;
        } else if (firstName.length() > lastName.length()) {
            smallerName = lastName;
            longerName = firstName;
        } else {
            if (firstName.compareTo(lastName) < 0) {
                smallerName = firstName;
                longerName = lastName;
            } else {
                smallerName = lastName;
                longerName = firstName;
            }
        }

        // Step 2 - Generate the user ID
        char firstLetter = smallerName.charAt(0);
        int pinLength = (int) (Math.log10(pin) + 1);
        int leftDigit = getDigit(pin, n, pinLength);
        int rightDigit = getDigit(pin, pinLength - n + 1, pinLength);
        String userID = firstLetter + longerName + leftDigit + rightDigit;

        // Step 3 - Toggle the alphabets in the user ID
        StringBuilder toggledUserID = new StringBuilder();
        for (char c : userID.toCharArray()) {
            if (Character.isUpperCase(c)) {
                toggledUserID.append(Character.toLowerCase(c));
            } else if (Character.isLowerCase(c)) {
                toggledUserID.append(Character.toUpperCase(c));
            } else {
                toggledUserID.append(c);
            }
        }

        return toggledUserID.toString();
    }

    public static int getDigit(int number, int position, int length) {
        int divisor = (int) Math.pow(10, length - position);
        return (number / divisor) % 10;
    }

    public static void main(String[] args) {
        String firstName = "Rajiv";
        String lastName = "Roy";
        int pin = 560037;
        int n = 6;

        String userID = generateUserID(firstName, lastName, pin, n);
        System.out.println("Generated User ID: " + userID);
    }
}