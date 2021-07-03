

public class Player extends Agent {

    

    public Player(String name, int role) {
        super(name, role);
        opponent.setRow(9);
        opponent.setCol(9);
        self.setRow(0);
        self.setCol(0);
    }
   
    
}
