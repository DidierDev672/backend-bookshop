package org.acme.infrastructure.driven.shared;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {
    //! Método para encriptar contraseñas
    public static String hashPassword(String plainPassword){
        String salt = BCrypt.gensalt(12); //! Generar un salt con coste de 12
        return BCrypt.hashpw(plainPassword, salt);
    }

    //! Méétodo para verificar contraseñas.
    public static boolean verifyPassword(String plainPassword, String hashedPassword){
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
