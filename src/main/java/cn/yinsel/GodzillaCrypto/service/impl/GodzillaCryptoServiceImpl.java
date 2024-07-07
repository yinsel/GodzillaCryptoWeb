package cn.yinsel.GodzillaCrypto.service.impl;

import cn.yinsel.GodzillaCrypto.service.GodzillaCryptoService;
import cn.yinsel.GodzillaCrypto.util.CryptoUtil;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Cipher;

public class GodzillaCryptoServiceImpl implements GodzillaCryptoService {

    @Override
    public byte[] decrypt(String cipherText, String key) {
        try {
            return CryptoUtil.decrypt(cipherText, DigestUtils.md5Hex(key).substring(0,16),Cipher.DECRYPT_MODE);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
