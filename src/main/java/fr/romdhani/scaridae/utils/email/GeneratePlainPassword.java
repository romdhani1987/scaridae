package fr.romdhani.scaridae.utils.email;



import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Generate an encryption of password
 *
 * @author aromdhani
 */
public class GeneratePlainPassword {

    public static final String AES = "AES";
    private static final String tempkey = "DB99A2A8EB6904F492E9DF0595ED683C";

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
     * Generate plain password
     *
     * @param password
     * @return
     */
    public static String generate(String password) {
        String originalPassword = "";
        try {
            byte[] bytekey = hexStringToByteArray(tempkey);
            SecretKeySpec sks = new SecretKeySpec(bytekey, GeneratePlainPassword.AES);
            Cipher cipher = Cipher.getInstance(GeneratePlainPassword.AES);
            cipher.init(Cipher.DECRYPT_MODE, sks);
            byte[] decrypted = cipher.doFinal(hexStringToByteArray(password));
            originalPassword = new String(decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return originalPassword;
    }

    public static void main(String args[]) {
        String password = "CEB997EF209F4B816EFD2A2B075B8B6E";
        generate(password);
    }
}