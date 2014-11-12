package com.yihoyoung.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.ArrayUtils;

public class ByteUtils {
    /**
     * 주어진 소스 바이트에 주어진 바이트를 추가하여 새로운 바이트를 생성함.
     * 
     * @param src 소스 바이트
     * @param b 추가할 바이트
     * @return 소스에 주어진 바이트가 추가된 새로운 바이트를 리턴함.
     */
    public static byte[] append(byte[] src, byte b) {
        return append(src, new byte[] {b});
    }

    /**
     * 주어진 소스 바이트에 주어진 바이트를 추가하여 새로운 바이트를 생성함.
     * 
     * @param src 소스 바이트
     * @param b 추가할 바이트
     * @return 소스에 주어진 바이트가 추가된 새로운 바이트를 리턴함.
     */
    public static byte[] append(byte[] src, byte[] b) {
        return ArrayUtils.addAll(src, b);
    }

    /**
     * 소스에서 주어진 위치의 바이트를 리턴함.
     * 
     * @param src 소스 바이트
     * @param offset 찾을 위치
     * @return 소스에서 주어진 위치의 바이트, 위치가 소스 길이보다 크면 맨 마지막 바이트 리턴함.
     */
    public static byte getByte(byte src[], int offset) {
        if(src == null) {
            return 0;
        }

        return src[offset >= src.length ? src.length - 1 : offset];
    }

    /**
     * 소스에서 주어진 위치부터 길이만큼의 바이트를 리턴함.
     * 
     * @param src 소스 바이트
     * @param offset 시작 위치
     * @param length 찾는 길이
     * @return 소스에서 주어진 위치부터 길이만큼의 바이트를 리턴함.
     */
    public static byte[] getBytes(byte[] src, int offset, int length) {
        return ArrayUtils.subarray(src, offset, offset + length);
    }

    /**
     * 소스 바이트가 찾는 바이트로 시작하는지 여부를 리턴함.
     * 
     * @param src 소스 바이트
     * @param prefix 찾는 바이트
     * @return 소스 바이트가 찾는 바이트로 시작하면 <code>true</code>, 그외에는 <code>false</code>
     */
    public static boolean startWith(byte[] src, byte prefix) {
        if(src == null || src.length < 1) {
            return false;
        }
        if(src[0] != prefix) {
            return false;
        }
        return true;
    }

    /**
     * 소스 바이트가 찾는 바이트로 끝나는지 여부를 리턴함.
     * 
     * @param src 소스 바이트
     * @param suffix 찾는 바이트
     * @return 소스 바이트가 찾는 바이트로 끝나면 <code>true</code>, 그외에는 <code>false</code>
     */
    public static boolean endWith(byte[] src, byte suffix) {
        if(src == null || src.length < 1) {
            return false;
        }
        if(src[src.length - 1] != suffix) {
            return false;
        }
        return true;
    }

    /**
     * 소스 바이트내의 찾는 바이트의 첫 위치를 리턴함.
     * 
     * @param src 소스 바이트
     * @param prefix 찾는 바이트
     * @return 소스 바이트내의 찾는 바이트의 첫 위치를 리턴함. 존재하지 않으면 -1를 리턴함.
     */
    public static int indexOf(byte[] src, byte prefix) {
        return ArrayUtils.indexOf(src, prefix);
    }

    /**
     * 소스 바이트내의 찾는 바이트의 마지막 위치를 리턴함.
     * 
     * @param src 소스 바이트
     * @param prefix 찾는 바이트
     * @return 소스 바이트내의 찾는 바이트의 마지막 위치를 리턴함. 존재하지 않으면 -1를 리턴함.
     */
    public static int lastIndexOf(byte[] src, byte prefix) {
        return ArrayUtils.lastIndexOf(src, prefix);
    }

    /**
     * 소스 바이트내의 찾는 바이트가 있는지 여부를 리턴함.
     * 
     * @param src 소스 바이트
     * @param prefix 찾는 바이트
     * @return 소스 바이트가 있으면 <code>true</code>, 그외에는 <code>false</code>
     */
    public static boolean contains(byte[] src, byte prefix) {
        return ArrayUtils.contains(src, prefix);
    }

    /**
     * 주어진 두 개의 바이트가 같은 값을 가지는지 여부를 리턴함.
     * 
     * @param src 소스 바이트
     * @param b 비교 대상 바이트
     * @return 소스 바이트와 대상 바이트의 값이 같으면 <code>true</code>, 그외에는 <code>false</code>
     */
    public static boolean isEquals(byte[] src, byte[] b) {
        return ArrayUtils.isEquals(src, b);
    }

    /**
     * 주어진 바이트를 문자열로 변환함.
     * 
     * @param b 변환할 바이트
     * @return 변환된 문자열, 인코딩은 시스템 인코딩을 따름.
     */
    public static String toString(byte b) {
        return toString(new byte[] {b});
    }

    /**
     * 바이트를 주어진 인코딩의 문자열로 변환함. 주어진 인코딩을 지원하지 않으면 공백<code>""</code> 문자열을 리턴함.
     * 
     * @param b 변환할 바이트
     * @param charsetName 인코딩명
     * @return 변환된 문자열.
     */
    public static String toString(byte b, String charsetName) {
        return toString(new byte[] {b}, charsetName);
    }

    /**
     * 주어진 바이트를 문자열로 변환함.
     * 
     * @param b 변환할 바이트
     * @return 변환된 문자열, 인코딩은 시스템 인코딩을 따름.
     */
    public static String toString(byte[] b) {
        return new String(b);
    }

    /**
     * 바이트를 주어진 인코딩의 문자열로 변환함. 주어진 인코딩을 지원하지 않으면 공백<code>""</code> 문자열을 리턴함.
     * 
     * @param b 변환할 바이트
     * @param charsetName 인코딩명
     * @return 변환된 문자열.
     */
    public static String toString(byte[] b, String charsetName) {
        try {
            return new String(b, charsetName);
        }
        catch(UnsupportedEncodingException e) {
            return "";
        }
    }
}
