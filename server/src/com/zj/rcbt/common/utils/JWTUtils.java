package com.zj.rcbt.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW";

    private static final String EXP = "exp";

    private static final String PAYLOAD = "payload";
    private static Logger log = LogManager.getLogger(JWTUtils.class.getName());
    public static String  createToken(String userid){
        long TTLMillis=Constants.tokenexpire;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        try {

            Algorithm algorithm = Algorithm.HMAC256(SECRET+userid);
            String token = JWT.create()
                    .withIssuer("rcbt")
                    .withExpiresAt(new Date(nowMillis+TTLMillis))
                    .withNotBefore(now)
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
            log.error(ExceptionUtils.getStackTrace(exception));
            return null;
        }


    }

    public static boolean verifyToken(String token,String userid)
    {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET+userid);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("rcbt")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception){
            //Invalid signature/claims
            log.error(ExceptionUtils.getStackTrace(exception));
            return false;
        }
    }

}
