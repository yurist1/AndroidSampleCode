package com.example.yrchoi.yurist.util;

import java.security.MessageDigest;

/**
 * Created by neozen on 2018-04-04.
 */

public class Utils {

    public static String getSHA256String(String sText) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] digest = md.digest(sText.getBytes());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < digest.length; i++) {
            sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
        }

        String sResult = sb.toString();

        return (sResult);
    }
}
