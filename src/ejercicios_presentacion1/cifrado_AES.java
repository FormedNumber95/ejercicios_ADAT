package ejercicios_presentacion1;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;

/**
 * Clase cifrado_AES.
 */
public class cifrado_AES {
    
    /**
     * Metodo que cifra y descifra archivos segun el el cifrado AES
     *
     * @param args los argumentos que se pasan por consola, nada
     * @throws Exception Cualquier excepcion
     */
    public static void main(String[] args) throws Exception {
        // Generar clave AES
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);  // AES-256
        SecretKey secretKey = keyGen.generateKey();

        // Generar un vector de inicialización (IV)
        SecureRandom random = new SecureRandom();
        byte[] iv = new byte[16]; // AES usa un IV de 16 bytes
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Archivos de entrada y salida
        File inputFile = new File("carpeta/pako.txt");
        File encryptedFile = new File("carpeta/pakoEncriptado.aes");
        File decryptedFile = new File("carpeta/pakoDesencriptado.txt");

        // Cifrar el archivo
        encryptFile(secretKey, ivSpec, inputFile, encryptedFile);
        System.out.println("Archivo cifrado guardado en: "+
        encryptedFile.getAbsolutePath());

        // Descifrar el archivo
        decryptFile(secretKey, ivSpec, encryptedFile, decryptedFile);
        System.out.println("Archivo descifrado guardado en: "+ 
        decryptedFile.getAbsolutePath());
    }

    /**
     * Encrita el archivo.
     *
     * @param key la key
     * @param iv el iv
     * @param inputFile el archivo a encriptar
     * @param outputFile es archivo encriptado
     * @throws Exception Cualquier excepcion
     */
    // Método para cifrar un archivo
    public static void encryptFile(SecretKey key, IvParameterSpec iv, File 
    		inputFile, File outputFile) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        try (FileInputStream inputStream = new FileInputStream(inputFile);
             FileOutputStream outputStream = new FileOutputStream(outputFile)){

            byte[] buffer = new byte[64];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byte[] output = cipher.update(buffer, 0, bytesRead);
                if (output != null) {
                    outputStream.write(output);
                }
            }

            byte[] outputBytes = cipher.doFinal();
            if (outputBytes != null) {
                outputStream.write(outputBytes);
            }
        }
    }

    /**
     * Metodo que desencripta.
     *
     * @param key la key
     * @param iv el iv
     * @param inputFile el archivo encriptado
     * @param outputFile el archivo desencriptado
     * @throws Exception Cualquier excepcion
     */
    // Método para descifrar un archivo
    public static void decryptFile(SecretKey key, IvParameterSpec iv, File
    		inputFile, File outputFile) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, iv);

        try (FileInputStream inputStream = new FileInputStream(inputFile);
             FileOutputStream outputStream = new FileOutputStream(outputFile)){

            byte[] buffer = new byte[64];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byte[] output = cipher.update(buffer, 0, bytesRead);
                if (output != null) {
                    outputStream.write(output);
                }
            }

            byte[] outputBytes = cipher.doFinal();
            if (outputBytes != null) {
                outputStream.write(outputBytes);
            }
        }
    }
}
