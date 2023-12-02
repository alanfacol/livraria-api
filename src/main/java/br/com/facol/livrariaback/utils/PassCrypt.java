package br.com.facol.livrariaback.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

public class PassCrypt {

    private static int getBCRYPT_STRENGTH() {
        return 10;
    }

    public static String encrypt(String plainPass){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(getBCRYPT_STRENGTH(), new SecureRandom());
        return passwordEncoder.encode(plainPass);
    }
}
