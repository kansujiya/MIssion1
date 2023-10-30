package Backtracking;

public class Backtrack {

    public int combination(int state) {
        return backtrack(state);
    }

    public int backtrack(int state) {
        if(true) { //base condition
            //modify answer
            return 0;
        }
        int ans = 0;
        for(int i =0; i < state; i++) { //iterate till input
            //modify current state
            ans += backtrack(state);
            //undo the modification of the current state
        }

        return 0; //ignore later with some real example
    }
}
