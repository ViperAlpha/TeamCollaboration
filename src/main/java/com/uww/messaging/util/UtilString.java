package com.uww.messaging.util;

/**
 * Created by horvste on 3/19/16.
 */
public class UtilString {
    public static String toValidFilePathString(String s) {
        return s.replaceAll("[^a-zA-Z0-9.-]", "_");
    }
}
