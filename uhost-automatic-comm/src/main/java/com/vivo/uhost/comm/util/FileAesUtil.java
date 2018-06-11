/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.comm.util;

import com.vivo.internet.vivocfg.client.VivoConfigManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;

/**
 * @author huangxiaoqun  2017/11/24 22:35
 * @version 1.0
 * @description
 */
public class FileAesUtil {


    private static final String ALGORITHM_AES_EBC_PKCS7Padding = "AES/ECB/PKCS5Padding";

    private static final String AES_PASSWORD = VivoConfigManager.getString("AES_PASSWORD","201801241532V");

    /**
     * init AES Cipher
     *
     * @param cipherMode
     * @return
     */
    private Cipher initAESCipher(int cipherMode) {
        Cipher cipher = null;
        try {
            SecretKey key = getKey(AES_PASSWORD);
            Security.addProvider(new BouncyCastleProvider());
            cipher = Cipher.getInstance(ALGORITHM_AES_EBC_PKCS7Padding);
            cipher.init(cipherMode, key);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("init aes cipher failed", e);
        } catch (InvalidKeyException ex) {
            throw new RuntimeException("invalid key exception", ex);
        } catch (NoSuchPaddingException ex) {
            throw new RuntimeException("no such padding exception", ex);
        }
        return cipher;
    }

    private SecretKey getKey(String password) {
        int keyLength = 128;
        byte[] keyBytes = new byte[keyLength / 8];
        SecretKeySpec key = null;
        try {
            Arrays.fill(keyBytes, (byte) 0x0);
            byte[] passwordBytes = password.getBytes("UTF-8");
            int length = passwordBytes.length < keyBytes.length ? passwordBytes.length : keyBytes.length;
            System.arraycopy(passwordBytes, 0, keyBytes, 0, length);

            for (byte b : keyBytes) {
                System.out.println(b);
            }

            key = new SecretKeySpec(keyBytes, "AES");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("get key failed", e);
        }
        return key;
    }


    /**
     * 文件AES加密
     *
     * @param originFilePath 原始文件
     * @param encryptPath    加密后的文件路径
     * @return 是否加密成功
     */
    public boolean encryptFile(String originFilePath, String encryptPath) {
        File originFile = null;
        File encryptfile = null;
        CipherOutputStream cipherOutputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            originFile = new File(originFilePath);
            if (!originFile.exists()) {
                throw new NullPointerException("Encrypt file is empty");
            }
            encryptfile = new File(encryptPath);
            if (encryptfile.exists() && !encryptfile.delete()) {
                throw new NullPointerException("delete existed decrypt file failed");
            }
            if (!encryptfile.createNewFile()) {
                throw new NullPointerException("create new decrypt file failed");
            }
            Cipher cipher = initAESCipher(Cipher.ENCRYPT_MODE);
            cipherOutputStream = new CipherOutputStream(new FileOutputStream(encryptfile), cipher);
            bufferedInputStream = new BufferedInputStream(new FileInputStream(originFile));
            IOUtils.copy(bufferedInputStream, cipherOutputStream);
        } catch (IOException e) {
            FileUtils.deleteQuietly(encryptfile);
            throw new RuntimeException("encrypt file {" + originFile + "} failed", e);
        } finally {
            IOUtils.closeQuietly(cipherOutputStream, bufferedInputStream);
        }
        return true;
    }

    /**
     * AES 解密
     *
     * @param encryptPath AES加密文件
     * @param decryptPath 解密后的文件
     * @return 是否解密成功
     */

    public boolean decryptFile(String encryptPath, String decryptPath) {
        File encryptFile = null;
        File decryptFile = null;
        BufferedOutputStream outputStream = null;
        CipherInputStream inputStream = null;
        try {
            encryptFile = new File(encryptPath);
            if (!encryptFile.exists()) {
                throw new NullPointerException("Decrypt file is not existed !!!");
            }
            decryptFile = new File(decryptPath);
            if (decryptFile.exists()) {
                FileUtils.forceDelete(decryptFile);
            }
            if (!decryptFile.createNewFile()) {
                throw new NullPointerException("create Decrypt file failed");
            }
            Cipher cipher = initAESCipher(Cipher.DECRYPT_MODE);
            outputStream = new BufferedOutputStream(new FileOutputStream(decryptFile));
            inputStream = new CipherInputStream(new FileInputStream(encryptFile), cipher);
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            throw new RuntimeException("decrypt file {" + encryptFile + "} failed", e);
        } finally {
            IOUtils.closeQuietly(inputStream, outputStream);
        }
        return true;
    }


}
