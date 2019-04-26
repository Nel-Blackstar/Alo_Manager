package com.live.core.service;

import com.live.core.entities.Users;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;

@Service
public class ILiveManagerImpl implements ILiveManager {
    private UsersService iUsers;

    @Override
    public Users userConnecte() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return iUsers.findByLogin(auth.getName());
    }

    public String formatterNumero4(long numeroInitial) {
        String numeroFinal;
        String numero = String.valueOf(numeroInitial);
        int longueurNumero  =numero.length();
        switch (longueurNumero) {
            case 0:
                numeroFinal = "0000";
                break;
            case 1:
                numeroFinal = "000" + numero;
                break;
            case 2:
                numeroFinal = "00" + numero;
                break;
            case 3:
                numeroFinal = "0" + numero;
                break;
            default:
                numeroFinal = String.valueOf(numeroInitial);
                break;
        }
        return numeroFinal;
    }

    public String formatterNumero2(long numeroInitial) {
        String numeroFinal;
        String numero = String.valueOf(numeroInitial);
        int longueurNumero = numero.length();
        switch (longueurNumero) {
            case 0:
                numeroFinal = "11";
                break;
            case 1:
                numeroFinal = "1" + numero;
                break;
            default:
                numeroFinal = String.valueOf(numeroInitial);
                break;
        }
        return numeroFinal;
    }

    @Override
    public char getRandomLetterUppercase() {
        char[] T = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        return T[(int)(26*Math.random())];
    }

    @Override
    public char getRandomLetterLowercase() {
        char[] T = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        return T[(int)(26*Math.random())];
    }

    @Override
    public String genererLoginUser() {
        char partie_1 = getRandomLetterUppercase();
        int partie_2 = (int)(10*Math.random());
        String partie_3 = formatterNumero4(iUsers.utilisateursCount()+1);
        String login = partie_1+""+partie_2+""+partie_3;
        return login;
    }

    @Override
    public String genererPasswordUser() {
        String password = getRandomLetterLowercase()+""+getRandomLetterLowercase()+""+getRandomLetterLowercase()+""+getRandomLetterLowercase();
        return password;
    }

    @Override
    public String crypterPasswordUser(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());

            byte byteData[] = md.digest();

            //convert the byte to hex format
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Le cryptage du mot de passe a échoué");
        }
    }
}
