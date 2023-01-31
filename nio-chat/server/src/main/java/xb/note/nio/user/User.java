package xb.note.nio.user;

import java.nio.ByteBuffer;

public interface User{

    void listen(ByteBuffer message);

    void detach();

}
