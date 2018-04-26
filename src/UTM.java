import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UTM {

    private static String filename = "input.txt";
    private static String transitionFunction;
    private static String inputString;
    private static String touringMachine;

    public static void main(String[] args) {
        // Get the string from  input.txt
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String input;

            // Split the string by 111
            while ((input = br.readLine()) != null) {
                String[] inputSplitter = input.split("111");
                touringMachine = inputSplitter[0];                 // General Turing Machine information
                transitionFunction = inputSplitter[1];             // Transition information
                inputString = inputSplitter[2];                    // DFA input string
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not opened.");
        } catch (IOException e) {
            System.out.println("Error reading from file.");
        }

        // Useful output information
        System.out.println("Machine info: " + touringMachine + "\nTransition function(s): " + transitionFunction + "\nInput string: " + inputString + "\n");

        // Build turing machine (this did more stuff in my first draft)
        Machine tm = new Machine(touringMachine);

        // Build the transitions
        int[] startTransition = tm.transitions();
        TransitionFunction tmBuilder = new TransitionFunction(startTransition[0], startTransition[1], startTransition[2], startTransition[3]);
        tmBuilder.buildTransitions(transitionFunction);
        tmBuilder.printStateTable();

        // Output the Accept/Reject
        System.out.println(tmBuilder.runMachine(inputString));
    }
}

