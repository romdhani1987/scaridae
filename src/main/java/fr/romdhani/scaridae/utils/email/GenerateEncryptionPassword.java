package fr.romdhani.scaridae.utils.email;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Generate encryption of password
 *
 * @author aromdhani
 */
public class GenerateEncryptionPassword {

    public static final String AES = "AES";
    private static final String key = "DB99A2A8EB6904F492E9DF0595ED683C";

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }

    private static byte[] hexStringToByteArray(String s) {
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(s.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        return b;
    }

    /**
     * Generate an encrypted password
     *
     * @param password
     * @return
     */
    public static String generate(String password) {
        String encryptedpwd = "";
        try {
            byte[] bytekey = hexStringToByteArray(key);
            SecretKeySpec sks = new SecretKeySpec(bytekey, GenerateEncryptionPassword.AES);
            Cipher cipher = Cipher.getInstance(GenerateEncryptionPassword.AES);
            cipher.init(Cipher.ENCRYPT_MODE, sks, cipher.getParameters());
            byte[] encrypted = cipher.doFinal(password.getBytes());
            encryptedpwd = byteArrayToHexString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedpwd;
    }

    public static void main(String args[]) throws Exception {
        String password = "rs2d%rs2d";
        System.out.println(generate(password));
    }
}
