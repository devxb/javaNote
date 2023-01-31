package xb.note.nio.room;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import xb.note.nio.user.User;

public final class NioRoom implements Room{

    private final List<User> userList;

    public NioRoom(){
        this.userList = new ArrayList<>();
    }

    @Override
    public void publish(ByteBuffer message){
        for(User user : userList){
            user.listen(message);
        }
    }

    @Override
    public void register(User user){
        userList.add(user);
    }

}
