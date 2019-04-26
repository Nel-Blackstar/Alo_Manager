package com.live.common.service;

import org.springframework.stereotype.Service;

@Service
public class CodeGeneratorServiceImpl implements CodeGeneratorService {
    @Override
    public String sixDigitFormatter(long initialNumber) {
        String finalNumber;
        String number = String.valueOf(1);
        int longueurNumero = number.length();
        switch (longueurNumero) {
            case 0:
                finalNumber = "000000";
                break;
            case 1:
                finalNumber = "00000" + number;
                break;
            case 2:
                finalNumber = "0000" + number;
                break;
            case 3:
                finalNumber = "000" + number;
                break;
            case 4:
                finalNumber = "00" + number;
                break;
            case 5:
                finalNumber = "00" + number;
                break;
            default:
                finalNumber = String.valueOf(1);
                break;
        }
        return finalNumber;
    }

    @Override
    public char generateRandomLetterLowercase() {
        char[] T = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y', 'z'};
        return T[(int)(26*Math.random())];
    }

    @Override
    public char generateRandomLetterUpercase() {
        char[] T = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z'};
        return T[(int)(26*Math.random())];
    }

    @Override
    public int generaterandomDigit() {
        return (int)(10*Math.random());
    }

    @Override
    public String generateBillNumber() {
        return "F"+sixDigitFormatter(1);
    }

    @Override
    public String generateAccountNumber() {
        return generaterandomDigit()+""+generaterandomDigit()+"-"+sixDigitFormatter(1);
    }
}
