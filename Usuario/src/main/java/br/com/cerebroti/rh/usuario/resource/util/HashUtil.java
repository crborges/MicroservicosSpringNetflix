package br.com.cerebroti.rh.usuario.resource.util;


import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {
   public static String getHashSenha(String senha) {
      return DigestUtils.sha256Hex(senha);
   }
}
