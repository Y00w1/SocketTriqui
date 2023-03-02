public class Triqui {
    int[] table;
    int count;
    public Triqui() {
        table = new int[9];
        count = 0;
    }
    public void cardinal(String msg, String player) throws NotValidPositionException, PlayerWonException, GameDrawException {
        int position = Integer.parseInt(msg);
        int marks = player.equals("x") ? 1 : -1;
        if(table[position - 1] == 0){
            table[position - 1] = marks;
            count++;
        }else{
            throw new NotValidPositionException();
        }
        if(count == 9){
            throw new GameDrawException();
        }else{
            winCond(marks == 1 ? 3 : -3);
        }
    }

    private void winCond(int sum) throws PlayerWonException{
        if (table[0] + table[1] + table[2] == sum)
            throw new PlayerWonException();
        else if (table[0] + table[3] + table[6] == sum)
            throw new PlayerWonException();
        else if (table[2] + table[5] + table[8] == sum)
            throw new PlayerWonException();
        else if (table[6] + table[7] + table[8] == sum)
            throw new PlayerWonException();
        else if (table[0] + table[4] + table[8] == sum)
            throw new PlayerWonException();
        else if (table[1] + table[4] + table[7] == sum)
            throw new PlayerWonException();
        else if (table[2] + table[4] + table[6] == sum)
            throw new PlayerWonException();
        else if (table[3] + table[4] + table[5] == sum)
            throw new PlayerWonException();
    }

    public String toString() {
        String msg = "";
        for (int i = 0; i < 10; i++) {
            String mark = table[i-1] == 0 ? " " : table[i-1] == -1 ? "O" : "X";
            msg += String.format("%2s",mark);
            if(i % 3 == 0){
                msg += "\n";
            }else {
                msg += "|";
            }
        }
        msg += "\n";
        return msg;
    }
}
