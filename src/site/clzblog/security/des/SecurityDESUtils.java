package site.clzblog.security.des;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;

public class SecurityDESUtils {
    private static final String ENCODEING = "UTF-8";
    private static final String ALGORITHM = "DES";//加密算法

    /**
     * @param plaintext 需要加密的文本
     * @param secureKey 长度不能小于8
     *                  DES算法加密
     * @Author chengli.zou
     */
    public static String encrypt(String plaintext, String secureKey) {
        String encryptStr = "";
        try {
            byte[] datasource = plaintext.getBytes(ENCODEING);
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(secureKey.getBytes(ENCODEING));

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            SecretKey secretKey = keyFactory.generateSecret(desKey);

            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance(ALGORITHM);

            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, random);

            //现在，获取数据并加密
            //正式执行加密操作
            byte[] encryptSrc = cipher.doFinal(datasource);

            encryptStr = Base64.encodeBase64String(encryptSrc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptStr;
    }

    /**
     * @param encryptStr 已加密的字符串
     * @param secureKey  长度不能小于8
     *                   解密
     * @Author chengli.zou
     */
    public static String decrypt(String encryptStr, String secureKey) {
        String decryptStr = "";
        try {
            byte[] src = Base64.decodeBase64(encryptStr);
            // DES算法要求有一个可信任的随机数源
            SecureRandom random = new SecureRandom();
            // 创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(secureKey.getBytes(ENCODEING));

            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);

            // 将DESKeySpec对象转换成SecretKey对象
            SecretKey secretKey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, secretKey, random);
            // 真正开始解密操作
            byte[] decryptSrc = cipher.doFinal(src);
            decryptStr = new String(decryptSrc, ENCODEING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptStr;
    }

    public static void main(String[] arg) throws Exception {
        String src = "ninhao";
        String srKey = "40c7f529c177487bb3b03cf16e962c82";
        String enString = encrypt(src, srKey);
        System.out.println("加密：" + enString);

        System.out.println("解密：" + decrypt(enString, srKey));
    }
}
