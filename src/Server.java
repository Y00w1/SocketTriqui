import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3000);
        Socket socket = null;
        while (true) {
            try{
                //Espera primer jugador
                System.out.println("Esperando jugador 1");
                socket = serverSocket.accept();
                Client client = new Client(socket);
                System.out.println("Jugador 1 se ha unido ");

                //Espera segundo jugador
                System.out.println("Esperando jugador 2");
                socket = serverSocket.accept();
                Client client2 = new Client(socket);
                System.out.println("Jugador 2 se ha unido ");

                //Empezar juego con los clientes
                System.out.println("Empezando juego");
                Game game = new Game(client, client2);
                game.run();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}