/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.business.utils;

/**

 * 
 */

public final class StringUtils {
    public static String toCamelCase(String c) {
        if (c == null || c.trim().length() == 0) {
            return c;
        }

        if (!c.trim().contains(" ")) {
           return capitalizeWord(c);
        }

        String[] arrayWords = c.split(" ");
        StringBuilder sb = new StringBuilder();

        for (String arrayWord : arrayWords) {
            sb.append(capitalizeWord(arrayWord.concat(" ")));
        }
        
        return sb.toString();
    }

    private static String capitalizeWord(String word) {

        if (word != null && !word.trim().equals("")) {
            return word.substring(0, 1).toUpperCase() + word.substring(1);
        } else {
            return word;
        }
    }
    
    public static String toCamelCase2(String inputString) {
       String result = "";
       if (inputString.length() == 0 || inputString.isEmpty()) {
           return result;
       }
       char firstChar = inputString.charAt(0);
       char firstCharToUpperCase = Character.toUpperCase(firstChar);
       result = result + firstCharToUpperCase;
       for (int i = 1; i < inputString.length(); i++) {
           char currentChar = inputString.charAt(i);
           char previousChar = inputString.charAt(i - 1);
           if (previousChar == ' ') {
               char currentCharToUpperCase = Character.toUpperCase(currentChar);
               result = result + currentCharToUpperCase;
           } else {
               char currentCharToLowerCase = Character.toLowerCase(currentChar);
               result = result + currentCharToLowerCase;
           }
       }
       return result;
   }
}
