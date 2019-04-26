package com.live.common.service;

public interface CodeGeneratorService {
    // Format a number on 6 digits
    public String sixDigitFormatter(long initialNumber);

    // Generate random small letter
    public char generateRandomLetterLowercase();

    // Generate random capital letter
    public char generateRandomLetterUpercase();

    // Generate a random digit
    public int generaterandomDigit();

    // Generate a random bill number (Fxxxxxx)
    public String generateBillNumber();

    // Generate a random account number (xx-xxxxx)
    public String generateAccountNumber();
}
