package filters;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

public class AIAKey {
    private static  Key key = null;
    public static  final SignatureAlgorithm signatureAlgorithm= SignatureAlgorithm.HS256;
    public static Key getKey(){
        return key;
    }
    static {
        byte[] barray = DatatypeConverter.parseBase64Binary("c2VjcmV0");
        key =new SecretKeySpec(barray,signatureAlgorithm.getJcaName());
    }
}
