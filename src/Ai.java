import java.util.ArrayList;

public class Ai extends Agent {

   
    public Ai(String name, int role) {
        super(name, role);
        opponent.setRow(0);
        opponent.setCol(0);
        self.setRow(9);
        self.setCol(9);
       // System.out.println(self.getRow()+ self.getCol());
        
    }

    public Location makeMove(Cave cave){
        //System.out.println(self.getRow()+ self.getCol());
        cell l = calcUtil(cave);
        //System.out.println(l.loc.getRow()+","+l.loc.getCol());
        return l.loc;
        
    }

   /* private cell maxVal(Cave cave, int depth,int Umana){
        cell maxT = new cell();
        maxT.utility = -100;
        //int winner = cave.winner;
        
        if(depth == 0)
            return maxT;

        if(self.getRow() == cave.idol.getRow() && self.getCol() == cave.idol.getCol()){
            maxT.utility = 10;
            return maxT;
        }

        else if(opponent.getRow() == cave.idol.getRow() && opponent.getCol() == cave.idol.getCol()){
            maxT.utility = -10;
            return maxT;
        }

        else if(self.getRow() == cave.POTION && self.getCol() == cave.POTION){
            maxT.utility = 1;
            return maxT;
        }

        else if(Umana == 0){
            maxT.utility = 0;
            return maxT;
        }
        
        // Check Up
        if(cave.isValid(new Location(self.getRow()+1, self.getCol()))){
            int x = cave.tiles[self.getRow()+1][self.getCol()];
            cave.tiles[self.getRow()+1][self.getCol()] = role;
            int v = minVal(cave, depth-1, Umana-1).utility;
            
            if(v > maxT.utility){
                System.out.println(maxT.utility);
                maxT.utility = v;
                maxT.loc.setRow(self.getRow());
                maxT.loc.setCol(self.getCol());
            }
            cave.tiles[self.getRow()+1][self.getCol()] = x;
        }

        // Check Down
        else if(cave.isValid(new Location(self.getRow()-1, self.getCol()))){
            int x = cave.tiles[self.getRow()-1][self.getCol()];
            cave.tiles[self.getRow()-1][self.getCol()] = role;
            int v = minVal(cave, depth-1, Umana-1).utility;

            if(v > maxT.utility){
                maxT.utility = v;
                maxT.loc.setRow(self.getRow());
                maxT.loc.setCol(self.getCol());
            }
            cave.tiles[self.getRow()-1][self.getCol()] = x;
        }

        // Check Left
        else if(cave.isValid(new Location(self.getRow(), self.getCol()+1))){
            int x = cave.tiles[self.getRow()][self.getCol()+1];
            cave.tiles[self.getRow()][self.getCol()+1] = role;
            int v = minVal(cave, depth-1, Umana-1).utility;

            if(v > maxT.utility){
                maxT.utility = v;
                maxT.loc.setRow(self.getRow());
                maxT.loc.setCol(self.getCol());
            }
            cave.tiles[self.getRow()][self.getCol()+1] = x;
        }

        // Check Right
        else if(cave.isValid(new Location(self.getRow(), self.getCol()-1))){
            int x = cave.tiles[self.getRow()][self.getCol()-1];
            cave.tiles[self.getRow()][self.getCol()-1] = role;
            int v = minVal(cave, depth-1, Umana-1).utility;

            if(v > maxT.utility){
                maxT.utility = v;
                maxT.loc.setRow(self.getRow());
                maxT.loc.setCol(self.getCol());
            }
            cave.tiles[self.getRow()][self.getCol()-1] = x;
        }

        return maxT;
    }
*/

    /*private cell minVal(Cave cave, int depth,int Umana){
        cell minT = new cell();
        minT.utility = 100;
        //int winner = cave.winner;
        if(depth == 0)
            return minT;

        if(self.getRow() == cave.idol.getRow() && self.getCol() == cave.idol.getCol()){
            minT.utility = 10;
            return minT;
        }

        else if(opponent.getRow() == cave.idol.getRow() && opponent.getCol() == cave.idol.getCol()){
            minT.utility = -10;
            return minT;
        }

        else if(self.getRow() == cave.POTION && self.getCol() == cave.POTION){
            minT.utility = 1;
            return minT;
        }

        else if(Umana == 0){
            minT.utility = 0;
            return minT;
        }

        // Check Up
        if(cave.isValid(new Location(self.getRow()+1, self.getCol()))){
            int x = cave.tiles[self.getRow()+1][self.getCol()];
            cave.tiles[self.getRow()+1][self.getCol()] = role;
            int v = maxVal(cave, depth-1, Umana-1).utility;

            if(v < minT.utility){
                minT.utility = v;
                minT.loc.setRow(self.getRow());
                minT.loc.setCol(self.getCol());
            }
            cave.tiles[self.getRow()+1][self.getCol()] = x;
        }

        // Check Down
        else if(cave.isValid(new Location(self.getRow()-1, self.getCol()))){
            int x = cave.tiles[self.getRow()-1][self.getCol()];
            cave.tiles[self.getRow()-1][self.getCol()] = role;
            int v = maxVal(cave, depth-1, Umana-1).utility;

            if(v < minT.utility){
                minT.utility = v;
                minT.loc.setRow(self.getRow());
                minT.loc.setCol(self.getCol());
            }
            cave.tiles[self.getRow()-1][self.getCol()] = x;
        }

        // Check Left
        else if(cave.isValid(new Location(self.getRow(), self.getCol()+1))){
            int x = cave.tiles[self.getRow()][self.getCol()+1];
            cave.tiles[self.getRow()][self.getCol()+1] = role;
            int v = maxVal(cave, depth-1, Umana-1).utility;

            if(v < minT.utility){
                minT.utility = v;
                minT.loc.setRow(self.getRow());
                minT.loc.setCol(self.getCol());
            }
            cave.tiles[self.getRow()][self.getCol()+1] = x;
        }

        // Check Right
        else if(cave.isValid(new Location(self.getRow(), self.getCol()-1))){
            int x = cave.tiles[self.getRow()][self.getCol()-1];
            cave.tiles[self.getRow()][self.getCol()-1] = role;
            int v = maxVal(cave, depth-1, Umana-1).utility;

            if(v < minT.utility){
                minT.utility = v;
                minT.loc.setRow(self.getRow());
                minT.loc.setCol(self.getCol());
            }
            cave.tiles[self.getRow()][self.getCol()-1] = x;
        }

        return minT;

    }*/

    private cell calcUtil(Cave cave){
        cell best = new cell();
        int util = 100;
        int x = 0;

        if(cave.isValid(new Location(self.getRow()+1, self.getCol()))){
            util = (Math.abs(cave.idol.getRow()-self.getRow()+1)+Math.abs(cave.idol.getCol()-self.getCol()));
            best.loc.setRow(self.getRow()+1);
            best.loc.setCol(self.getCol());
            System.out.println(x);
        }

        if(cave.isValid(new Location(self.getRow()-1, self.getCol()))){
            x = (Math.abs(cave.idol.getRow()-self.getRow()-1)+Math.abs(cave.idol.getCol()-self.getCol()));
            if (util > x || x == 0){
                util = x;
                best.loc.setRow(self.getRow()-1);
                best.loc.setCol(self.getCol()); 
            }
            System.out.println(x);
        }

        if(cave.isValid(new Location(self.getRow(), self.getCol()+1))){
            
            x = (Math.abs(cave.idol.getRow()-self.getRow())+Math.abs(cave.idol.getCol()-self.getCol()+1));
            if (util > x || x == 0){
                util = x;
                best.loc.setRow(self.getRow());
                best.loc.setCol(self.getCol()+1); 
                
            }
            System.out.println(x);
           
        }

        if(cave.isValid(new Location(self.getRow(), self.getCol()-1))){
            x = (Math.abs(cave.idol.getRow()-self.getRow())+Math.abs(cave.idol.getCol()-self.getCol()-1));
            if (util > x || x == 0){
                util = x;
                best.loc.setRow(self.getRow());
                best.loc.setCol(self.getCol()-1); 
               
                
            }
            System.out.println(x);
        }
        
        return best;
    }

    public Location bestMove(Cave cave){
        Location b[] = new Location[4];
        int sel = 0;
        for(int i = 0; i < 4; i++){
            b[i] = new Location();
        }
        b[0].setRow(self.getRow()+1);
        b[0].setCol(self.getCol());
        b[1].setRow(self.getRow()-1);
        b[1].setCol(self.getCol());
        b[2].setRow(self.getRow());
        b[2].setCol(self.getCol()+1);
        b[3].setRow(self.getRow());
        b[3].setCol(self.getCol()-1);
        int a[] = new int[4] ;
        int util = 1000;

        a[0] = (Math.abs(cave.idol.getRow()-self.getRow()+1)+Math.abs(cave.idol.getCol()-self.getCol()));
        a[1] = (Math.abs(cave.idol.getRow()-self.getRow()-1)+Math.abs(cave.idol.getCol()-self.getCol()));
        a[2] = (Math.abs(cave.idol.getRow()-self.getRow())+Math.abs(cave.idol.getCol()-self.getCol()+1));
        a[3] = (Math.abs(cave.idol.getRow()-self.getRow())+Math.abs(cave.idol.getCol()-self.getCol()-1));

        for(int i = 0; i < 4; i++){
            if(util > a[i]){
                util = a[i];
                sel = i;
            }
        }

        return b[sel];

    }

    class cell{
        int utility;
        Location loc;

        cell(){
            loc = new Location(-1,-1);
        }
    } 
   
    
}
