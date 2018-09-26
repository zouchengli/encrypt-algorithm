package site.clzblog.security.md5;

import site.clzblog.security.sha.SecuritySHA1Utils;

import java.security.MessageDigest;

public class SecurityMD5Utils {
    public static String md5Encode(String inStr) throws Exception {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        SecuritySHA1Utils.common(md5Bytes, hexValue);
        return hexValue.toString();
    }

    public static void main(String args[]) throws Exception {
        String str = new String("chengli.zou");
        System.out.println("原始：" + str);
        System.out.println("MD5后：" + md5Encode(str));
    }
}
