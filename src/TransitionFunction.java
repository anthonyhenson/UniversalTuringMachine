import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransitionFunction {

    private int numberOfTransitions;
    private int startState;
    private int finalState;
    private int rejectState;
    private List<State> stateTable;


    public TransitionFunction(int num, int start, int accept, int reject) {
        this.numberOfTransitions = num;
        this.startState = start;
        this.finalState = accept;
        this.rejectState = reject;
        this.stateTable = new ArrayList<>();
    }

    // Makes an ArrayList of states named stateTable
    public void buildTransitions(String input) {
        String[] inputHolder = input.split("1");
        State nullState = new State(0,"",0, "Empty State.");

        // Output message if the input is not a multiple of 5 (5 inputs per state)
        if (0 != inputHolder.length % 5) {
            System.out.println("Invalid input string.");
            System.out.println("Current input string: " + Arrays.toString(inputHolder) + " length: " + inputHolder.length);
        } else {
            int j = 0;
            for (int i = 0; i < numberOfTransitions; i++) {
                // Send inputs (5 at a time) to the the state
                nullState = new State(inputHolder[j].length(), inputHolder[j + 1], inputHolder[j + 2].length(), Integer.toString(inputHolder[j + 3].length()));
                j += 5;
                // Add the state to the stateTable
                stateTable.add(nullState);
            }
        }
    }

    // Prints out the stateTable
    public void printStateTable() {
        System.out.println("Transition Table:");
        for (int i = 0; i < stateTable.size(); i++)
            System.out.println("{" + stateTable.get(i).getCurrentState() + ", " + stateTable.get(i).getTransition() + "} -> {" + stateTable.get(i).getNextState()+ ", " + stateTable.get(i).getOutput() + ", -}");
        System.out.println("");
    }

    public String runMachine(String input) {
        List<State> cState = new ArrayList<>();
        String[] read = input.split("1");
        State tempState = new State();
        int stateHolder;

        // Get the start state from the stateTable
        for (int i = 0; i < stateTable.size(); i++) {
            if (startState == stateTable.get(i).getCurrentState())
                cState.add(stateTable.get(i));
        }

        // Iterates through the state table
        System.out.println("Start state: " + cState.get(0).getCurrentState());
        for (String checkTrans : read) {
            for (int h = 0; h <= 1; h+=0) {
                // Check if transition in current state matches transition from input
                if (cState.get(h).getTransition().equals(checkTrans)) {
                    stateHolder = cState.get(h).getNextState();
                    System.out.println("Transitioned to: " + cState.get(h).getNextState());
                    cState.clear();
                    for (int i = 0; i < stateTable.size(); i++) {
                        // Check if the current state matches the state in stateholder (makes sure the current state doesnt hold more than 2 transitions)
                        if (stateTable.get(i).getCurrentState() == stateHolder) {
                            cState.add(stateTable.get(i));
                            tempState = stateTable.get(i);
                            h = 2;
                        }
                        // If the state only has 1 transitions, it copies the first transition into the second spot to avoid NullPointerExceptions
                        if (cState.size() == 1 && i == stateTable.size() - 1)
                            cState.add(tempState);
                        // If the current state is empty, check if you are in the final state (empty when stateTable is finished)
                        if (cState.size() == 0 && stateHolder == finalState)
                            return "Accept!";
                    }
                }
                else
                    h++; // Iterate the main loop
            }
        }

        // lock the cState (currentState) into the given reject state.
        cState.get(0).setNextState(rejectState);
        cState.get(1).setNextState(rejectState);
        cState.get(0).setCurrentState(rejectState);
        cState.get(1).setCurrentState(rejectState);
        return "Reject. Invalid final state.";
    }
}
