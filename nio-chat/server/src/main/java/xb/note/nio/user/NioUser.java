package xb.note.nio.user;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public final class NioUser implements User{

    private final String name;
    private final SocketChannel clientChannel;

    public NioUser(String name, SocketChannel clientChannel){
        this.name = name;
        this.clientChannel = clientChannel;
    }

    @Override
    public void listen(ByteBuffer message){
        try{
            clientChannel.write(message);
        } catch (IOException IOE){
            throw new IllegalStateException("Wrong during listen(\"" + message + "\") client-name \"" + name + "\"");
        }
    }

    @Override
    public void detach(){
        try{
            clientChannel.close();
        } catch (IOException IOE){
            throw new IllegalStateException("Wrong during close() client-name \"" + name + "\"");
        }
    }

}
