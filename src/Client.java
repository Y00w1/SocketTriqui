import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private String name;
    public Client(Socket socket){
        try{
            this.socket = socket;
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            askName();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void askName() {
        System.out.println("Ingresa tu nombre: ");
        name = read();
        System.out.println("Bienvenido " + name);
    }
    public String read(){
        String line = "";
        boolean exit = false;
        while(!exit){
            try {
                if (inputStream.available()>0){
                    int d;
                    while ((d = inputStream.read()) != 38 ){
                        line += (char) d;
                    }
                    exit = true;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return line;
    }

    public void write(String message){
        try {
            outputStream.write(message.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void close(){
        try {
            socket.close();
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
