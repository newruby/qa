package com.ty.qa.util;

/**
 * created by TY on 2019/5/18.
 */
public class CharUtils {
    public static final char LF = 10;
    public static final char CR = 13;

    public static String unicodeEscaped(char ch)
    {
        if (ch < '\16')
            return "\\u000" + Integer.toHexString(ch);
        if (ch < 256)
            return "\\u00" + Integer.toHexString(ch);
        if (ch < 4096)
            return "\\u0" + Integer.toHexString(ch);

        return "\\u" + Integer.toHexString(ch);
    }

    public static String unicodeEscaped(Character ch)
    {
        if (ch == null)
            return null;

        return unicodeEscaped(ch.charValue());
    }

    public static boolean isAscii(char ch)
    {
        return (ch < 128);
    }

    public static boolean isAsciiPrintable(char ch)
    {
        return ((ch >= ' ') && (ch < ''));
    }

    public static boolean isAsciiControl(char ch)
    {
        return ((ch < ' ') || (ch == ''));
    }

    public static boolean isAsciiAlpha(char ch)
    {
        return (((ch >= 'A') && (ch <= 'Z')) || ((ch >= 'a') && (ch <= 'z')));
    }

    public static boolean isAsciiAlphaUpper(char ch)
    {
        return ((ch >= 'A') && (ch <= 'Z'));
    }

    public static boolean isAsciiAlphaLower(char ch)
    {
        return ((ch >= 'a') && (ch <= 'z'));
    }

    public static boolean isAsciiNumeric(char ch)
    {
        return ((ch >= '0') && (ch <= '9'));
    }

    public static boolean isAsciiAlphanumeric(char ch)
    {
        return (((ch >= 'A') && (ch <= 'Z')) || ((ch >= 'a') && (ch <= 'z')) || ((ch >= '0') && (ch <= '9')));
    }
}
