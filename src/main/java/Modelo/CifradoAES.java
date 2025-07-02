package Modelo;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class CifradoAES {

    private static final String CLAVE_SECRETA = "Inge.Ju4n_M4nu3l";

    // Método para cifrar texto
    public static String cifrar(String textoPlano) {
        try {
            SecretKeySpec clave = new SecretKeySpec(CLAVE_SECRETA.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, clave);
            byte[] cifrado = cipher.doFinal(textoPlano.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(cifrado);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para descifrar texto
    public static String descifrar(String textoCifrado) {
        try {
            SecretKeySpec clave = new SecretKeySpec(CLAVE_SECRETA.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, clave);
            byte[] descifrado = cipher.doFinal(Base64.getDecoder().decode(textoCifrado));
            return new String(descifrado, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}