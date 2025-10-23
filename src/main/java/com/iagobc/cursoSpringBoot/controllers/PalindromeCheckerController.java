package com.iagobc.cursoSpringBoot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PalindromeCheckerController {

    @GetMapping ("/validate-palindrome/{word}")
    public String palindromeChecker(@PathVariable String word) {

        // Check the minimal length
        if (word.length() < 2) {
            return "The word introduced must have at least 2 letters.";
        }

        // Check that every character is a letter
        for (int i = 0; i < word.length(); i++){
            if (!Character.isLetter(word.charAt(i))) {
                return "There isn't a letter in the character " + (i+1) + " of your word.";
            }
        }

        // Validate palindrome
        int i = 0;
        int j = word.length() - 1;

        do {
            if (Character.toLowerCase(word.charAt(i)) != Character.toLowerCase(word.charAt(j))) {
                return "The word " + word + " isn't a palindrome";
            }
            i++;
            j--;
        } while (i < j);

        return "The word " + word + " is a palindrome.";
    }
}
