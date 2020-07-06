//package vip.mate.oauth.factory;
//
//import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.*;
//import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class MatePasswordEncoderFactories {
//
//    public static PasswordEncoder createDelegatingPasswordEncoder() {
//        String idForEncode = "mate";
//        Map<String, PasswordEncoder> encoders = new HashMap<>(16);
//        encoders.put(idForEncode, new MatePasswordEncoder());
//        encoders.put("bcrypt", new BCryptPasswordEncoder());
//        encoders.put("noop", MateNoOpPasswordEncoder.getInstance());
//        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
//        encoders.put("scrypt", new SCryptPasswordEncoder());
//        return new DelegatingPasswordEncoder(idForEncode, encoders);
//    }
//
//    private MatePasswordEncoderFactories() {
//    }
//}
