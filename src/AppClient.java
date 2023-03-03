import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class AppClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.0.19",2000);

        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();

        Thread read = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try{
                        if (inputStream.available() > 0){
                            int d = 0;
                            String line = "";
                            while ((d = inputStream.read()) != 38){
                                line += line + (char) d;
                            }
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        read.start();

        Thread write = new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String line = scanner.nextLine();
                    try{
                        outputStream.write((line + "&").getBytes());
                        outputStream.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        write.start();
    }
}
