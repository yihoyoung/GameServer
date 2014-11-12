package com.yihoyoung.game.service;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yihoyoung.game.exception.ServiceException;
import com.yihoyoung.util.ByteUtils;

/**
 * 보안 관련 서비스를 제공하는 클래스.
 * 
 * @author 권중규
 */
public class SecuriService {
    private static final Log logger = LogFactory.getLog(SecuriService.class);

    private final String algorithm = "AES";
    private final String mode = "/CBC/PKCS5Padding";
    private final int keySize = 32;
    private final int ivSize = 16;
    private final String encoding = "UTF-8";

    /**
     * 새로운 키문자열을 생성하여 리턴함.
     */
    public String generateKeyString() {
        byte[] bytes = new byte[keySize * 2];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);

        try {
            return new String(Base64.encodeBase64(bytes, false), encoding);
        }
        catch(UnsupportedEncodingException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * 키 바이트를 리턴함.
     */
    public byte[] getKeyBytes(String keyText) {
        try {
            return Base64.decodeBase64(keyText.getBytes(encoding));
        }
        catch(UnsupportedEncodingException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * 주어진 키를 이용하여 {@link SecretKeySpec}를 생성하여 리턴함.
     */
    public SecretKeySpec getSecretKeySpec(byte[] keyBytes) {
        return getSecretKeySpec(keyBytes, algorithm, keySize);
    }

    /**
     * 주어진 키를 이용하여 {@link IvParameterSpec}를 생성하여 리턴함.
     */
    public IvParameterSpec getIvParameterSpec(byte[] keyBytes) {
        return getIvParameterSpec(keyBytes, ivSize);
    }

    /**
     * 주어진 평문을 암호화하여 리턴함.
     */
    public String encrypt(String keyText, String plainText) {
        byte[] keyBytes = getKeyBytes(keyText);
        SecretKeySpec keySpec = getSecretKeySpec(keyBytes);
        IvParameterSpec parameterSpec = getIvParameterSpec(keyBytes);

        return encrypt(keySpec, parameterSpec, plainText);
    }

    /**
     * 주어진 평문을 암호화하여 리턴함.
     */
    public String encrypt(SecretKeySpec keySpec, IvParameterSpec parameterSpec, String plainText) {
        String text = StringUtils.defaultString(plainText);
        try {
            Cipher cipher = Cipher.getInstance(algorithm + mode);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, parameterSpec);

            return new String(Base64.encodeBase64(cipher.doFinal(text.getBytes(encoding)), false), encoding);
        }
        catch(Exception e) {
            if(logger.isWarnEnabled()) {
                logger.warn(e.getMessage(), e);
            }

            return text;
        }
    }

    /**
     * 주어진 암호문을 복호화하여 리턴함.
     */
    public String decrypt(String keyText, String cipherText) {
        byte[] keyBytes = getKeyBytes(keyText);
        SecretKeySpec keySpec = getSecretKeySpec(keyBytes);
        IvParameterSpec parameterSpec = getIvParameterSpec(keyBytes);

        return decrypt(keySpec, parameterSpec, cipherText);
    }

    /**
     * 주어진 암호문을 복호화하여 리턴함.
     */
    public String decrypt(SecretKeySpec keySpec, IvParameterSpec parameterSpec, String cipherText) {
        String text = StringUtils.defaultString(cipherText);
        try {
            Cipher cipher = Cipher.getInstance(algorithm + mode);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, parameterSpec);

            return new String(cipher.doFinal(Base64.decodeBase64(text.getBytes(encoding))), encoding);
        }
        catch(Exception e) {
            if(logger.isWarnEnabled()) {
                logger.warn(e.getMessage(), e);
            }

            return cipherText;
        }
    }

    private SecretKeySpec getSecretKeySpec(byte[] keyBytes, String algorithm, int keySize) {
        return new SecretKeySpec(ByteUtils.getBytes(keyBytes, 0, keySize), algorithm);
    }

    private IvParameterSpec getIvParameterSpec(byte[] keyBytes, int keySize) {
        return new IvParameterSpec(ByteUtils.getBytes(keyBytes, this.keySize, keySize));
    }
}
