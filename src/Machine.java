public class Machine {

    private int numberOfStates;
    private int numberOfTapeSymbols;
    private int inputSymbols;
    private int transitionNum;
    private int start;
    private int accept;
    private int reject;
    private String blankSymbol;
    private String endMarker;

    //Splits the input from input.txt into different variables.
    public Machine(String utm) {
        String[] utmHolder = utm.split("1");

        if (utmHolder.length != 9)
            System.out.println("Invalid machine input.");
        else {
            this.numberOfStates = utmHolder[0].length();
            this.numberOfTapeSymbols = utmHolder[1].length();
            this.inputSymbols = utmHolder[2].length();
            this.endMarker = utmHolder[3];
            this.blankSymbol = utmHolder[4];
            this.transitionNum = utmHolder[5].length();
            this.start = utmHolder[6].length();
            this.accept = utmHolder[7].length();
            this.reject = utmHolder[8].length();
        }
    }

    //Sends out useful information for the TransitionFunction
    public int[] transitions() {
        int[] tempTransitions = new int[4];
        tempTransitions[0] = transitionNum;
        tempTransitions[1] = start;
        tempTransitions[2] = accept;
        tempTransitions[3] = reject;

        return tempTransitions;
    }
}
