package filters;

import io.jsonwebtoken.impl.TextCodec;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.crypto.Mac;
import java.security.Key;

public class AIAKey {
    private static final Key key = MacProvider.generateKey();
    public static Key getKey(){
        return key;
    }

}
