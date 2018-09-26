package site.clzblog.security.sha;

import java.security.MessageDigest;

public class SecuritySHA1Utils {
    public static String shaEncode(String inStr) throws Exception {
        MessageDigest sha;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        common(md5Bytes, hexValue);
        return hexValue.toString();
    }

    public static void common(byte[] md5Bytes, StringBuffer hexValue) {
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
    }

    public static void main(String args[]) throws Exception {
        String str = new String("chengli.zou");
        System.out.println("Original  -> " + str);
        System.out.println("SHA later -> " + shaEncode(str));
    }
}
