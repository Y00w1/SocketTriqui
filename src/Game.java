import java.io.IOException;

public class Game implements Runnable {
    Client player1;
    Client player2;
    Triqui TGame;
    public Game(Client player1, Client player2) throws IOException {
        this.player1 = player1;
        this.player2 = player2;
        TGame = new Triqui();
    }


    @Override
    public void run() {
        player1.write("Estado inicial \n" + TGame.toString());
        player2.write("Estado inicial \n" + TGame.toString());

        boolean exit = false;
        boolean turn = true;

        String p1Mark = "x";
        String p2Mark = "o";

        while (!exit) {
            if (turn) {
                exit = play(player1, player2, p1Mark);
                turn = false;
            }else{
                exit = play(player2, player1, p2Mark);
                turn = true;
            }
        }
        player1.close();
        player2.close();
    }

    private boolean play(Client player1, Client player2, String mark) {
        String msg;
        try{
            boolean marked = false;
            while (!marked) {
                try {
                    player1.write("Ingresa la posicion para marcar la X");
                    msg = player1.read();
                    TGame.cardinal(msg, mark);
                    marked = true;
                }catch (NotValidPositionException nvp){
                    player1.write("Posicion no valida");
                }
            }
            player1.write(TGame.toString());
            player2.write(TGame.toString());

            player1.write("Esperando la jugada del oponente");
            player2.write("Tu turno! ");
        }catch (PlayerWonException pw){
            player1.write("Felicitaciones ganaste!!!");
            player2.write("Perdiste");
            player1.write(TGame.toString());
            player2.write(TGame.toString());
            return true;
        }catch (GameDrawException gd){
            player1.write("Juego empatado");
            player2.write("Juego empatado");
            player1.write(TGame.toString());
            player2.write(TGame.toString());
            return true;
        }
        return false;
    }
}
