public class State {

    private int currentState;
    private String transition;
    private int nextState;
    private String output;

    public State() {
        currentState = 0;
        transition = "0";
        nextState = 0;
        output = "0";
    }

    //Holds state and transition values
    public State (int cs, String t, int ns, String op) {
        currentState = cs;
        transition = t;
        nextState = ns;
        output = op;
    }

    public int getCurrentState() {
        return currentState;
    }

    public String getTransition() {
        return transition;
    }

    public int getNextState() {
        return nextState;
    }

    public String getOutput() {
        return output;
    }

    public void setNextState(int ns) {
        this.nextState = ns;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }
}
