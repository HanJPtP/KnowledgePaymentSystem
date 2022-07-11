package com.woniu.util;



import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;

import java.util.Map;

@Slf4j
public class JwtUtils {

    private static final String usernameKey = "username";
    private static final String useridKey = "userid";
    private static final String authoritiesKey = "authorities";

    public static final String secret = "hello world goodbye thank you very much see you next time";

    static {
        log.info("spring security jwt secret: {}", secret);
    }

    @SneakyThrows
    public static String createJWT(String username) {
        // jwt 荷载
        JSONObject obj = new JSONObject();
        obj.put(usernameKey, username);

        return createJWT(obj);
    }

    @SneakyThrows
    public static String createJWT(Long id) {
        // jwt 荷载
        JSONObject obj = new JSONObject();
        obj.put(useridKey, id);

        return createJWT(obj);
    }

    @SneakyThrows
    public static boolean verify(String jwtString) {
        JWSObject jwsObject = JWSObject.parse(jwtString);
        JWSVerifier jwsVerifier = new MACVerifier(secret);
        return jwsObject.verify(jwsVerifier);
    }

    @SneakyThrows
    public static String getUsernameFromJWT(String jwtString) {
        JWSObject jwsObject = JWSObject.parse(jwtString);
        Map<String, Object> map = jwsObject.getPayload().toJSONObject();
        return (String) map.get(usernameKey);
    }

    @SneakyThrows
    public static String getAuthoritiesFromJwt(String jwtString) {
        JWSObject jwsObject = JWSObject.parse(jwtString);
        Map<String, Object> map = jwsObject.getPayload().toJSONObject();
        return (String) map.get(authoritiesKey);
    }

    private static String createJWT(JSONObject payload) throws JOSEException {
        // jwt 头
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256).type(JOSEObjectType.JWT).build();

        // jwt 头 + 荷载 + 密钥 = 签名
        JWSSigner jwsSigner = new MACSigner(secret);
        JWSObject jwsObject = new JWSObject(jwsHeader, new Payload(payload));
        // 进行签名（根据前两部分生成第三部分）
        jwsObject.sign(jwsSigner);

        // 获得 jwt string
        return jwsObject.serialize();
    }

}