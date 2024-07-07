package cn.yinsel.GodzillaCrypto.util;

import javax.crypto.Cipher;
import java.io.*;
import java.util.Base64;
import java.util.zip.GZIPInputStream;

public class CryptoUtil {

    // AES加解密函数
    public static byte[] x(String key, byte[] s, int m) {
        try {
            javax.crypto.Cipher c = javax.crypto.Cipher.getInstance("AES");
            c.init(m, new javax.crypto.spec.SecretKeySpec(key.getBytes(), "AES"));
            return c.doFinal(s);
        } catch (Exception e) {
            return null;
        }
    }

    // Base64解密函数
    public static byte[] base64DecodeToBytes(String str) {
        Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(str);
    }

    // Base64解密函数
    public static byte[] base64EncodeToBytes(byte[] bytes) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encode(bytes);
    }

    // GZIP解压
    public static byte[] decompress(byte[] compressedData) throws IOException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressedData);
            GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int len;
            while ((len = gzipInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }

            gzipInputStream.close();
            byteArrayOutputStream.close();

            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            return compressedData;
        }
    }

    // 哥斯拉解密函数
    public static byte[] decrypt(String req, String key, int mode) throws IOException {
        byte[] decoded = base64DecodeToBytes(req);
        return decompress(x(key,decoded,mode));
    }

    public static boolean isValidClassFile(byte[] classData) {
        if (classData == null || classData.length < 4) {
            return false;
        }
        try (DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(classData))) {
            int magic = dataInputStream.readInt();
            return magic == 0xCAFEBABE;
        } catch (IOException e) {
            return false;
        }
    }


    public static void main(String[] args) throws IOException {

    }
}