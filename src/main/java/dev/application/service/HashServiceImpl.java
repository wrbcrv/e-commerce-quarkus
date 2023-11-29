package dev.application.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HashServiceImpl implements HashService {

  private String salt = "IgW>n+8*04";
  private Integer iterationCount = 405;
  private Integer keyLength = 512;

  @Override
  public String getHashSenha(String senha) {
    if (senha == null)
      throw new IllegalArgumentException("A senha não pode ser nula.");

    try {
      byte[] result = SecretKeyFactory
          .getInstance("PBKDF2WithHmacSHA512")
          .generateSecret(new PBEKeySpec(senha.toCharArray(), salt.getBytes(), iterationCount, keyLength))
          .getEncoded();

      return Base64.getEncoder().encodeToString(result);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
  }

  public static void main(String[] args) {
    HashServiceImpl hashServiceImpl = new HashServiceImpl();

    System.out.println(hashServiceImpl.getHashSenha("12345678"));
  }
}