package com.wd.education.core.cache;

import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.interceptor.KeyGenerator;

@Slf4j
public class JwtKeyGenerator implements KeyGenerator {

  @SuppressWarnings("NullableProblems")
  @Override
  public Object generate(final Object object, final Method method, final Object... params) {
    final String key = (String) params[0];
    try {
      final MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(key.getBytes());
      return DatatypeConverter.printHexBinary(md.digest());
    } catch (NoSuchAlgorithmException exception) {
      log.warn("Cannot take MD5 hash from jwt", exception);
      log.debug("jwt {}", key);
      return key;
    }
  }
}
