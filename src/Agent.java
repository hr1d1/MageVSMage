public abstract class Agent {
	String name;
	int role; 
    int mana;
    Location opponent;
    Location self;
	
	public Agent(String name, int role) {
		this.name = name;	
        this.role = role;	
        this.mana = 10;
        opponent = new Location();
        self = new Location();
	}
	
	//public abstract void makeMove(Cave cave);

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void resetMana() {
        this.mana = 10;
    }
    
}