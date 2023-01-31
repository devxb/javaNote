package xb.note.nio.room;

import java.nio.ByteBuffer;
import xb.note.nio.user.User;

public interface Room{

    void publish(ByteBuffer message);

    void register(User user);

}
