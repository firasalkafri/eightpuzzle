import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class Main {
    private Queue<String> solutionNodes = new LinkedList<String>();    
    private Map<String,Integer> movesDone = new HashMap<String, Integer>(); 
    private Map<String,String> movesHistory = new HashMap<String,String>();

    public String optimalSolution= 	"123"+
    								"8X4"+
    								"765";
    
    public static void main(String args[]){
    	String board = "123"+
    			       "856"+
    				   "7X4";
    	
    	//Recursive calls
    	Main solution = new Main();
        
        //Original board
        solution.add(board, null);
        
    	//Start doing the moves
        while(!solution.solutionNodes.isEmpty()){
            String currentMove = solution.solutionNodes.remove();
            
            solution.moveUp(currentMove);                                     
            solution.moveDown(currentMove); 
            solution.moveLeft(currentMove); 
            solution.moveRight(currentMove);
        }
        
        System.out.println("Done building the solutions tree, and no solution was found");
      }

    
    
    void add(String newState, String oldState){
        if(!movesDone.containsKey(newState)){
            int newValue = oldState == null ? 0 : movesDone.get(oldState) + 1;
            movesDone.put(newState, newValue);
            solutionNodes.add(newState);
            movesHistory.put(newState, oldState);
        }
    }

    void moveUp(String currentMove){
        int a = currentMove.indexOf("X");
        if(a>2){
            String nextMove = currentMove.substring(0,a-3)+
            		"X"+
            		currentMove.substring(a-2,a)+
            		currentMove.charAt(a-3)+
            		currentMove.substring(a+1);
            checkCompletion(currentMove, nextMove);
        }
    }

    void moveDown(String currentMove){
        int a = currentMove.indexOf("X");
        if(a<6){
            String nextMove = currentMove.substring(0,a)+
            		currentMove.substring(a+3,a+4)+
            		currentMove.substring(a+1,a+3)+
            		"X"+
            		currentMove.substring(a+4);
            checkCompletion(currentMove, nextMove);
        }
    }
    void moveLeft(String currentMove){
        int a = currentMove.indexOf("X");
        if(a!=0 && a!=3 && a!=6){
            String nextMove = currentMove.substring(0,a-1)+
            		"X"+
            		currentMove.charAt(a-1)+
            		currentMove.substring(a+1);
            checkCompletion(currentMove, nextMove);
        }
    }
    void moveRight(String currentMove){
        int a = currentMove.indexOf("X");
        if(a!=2 && a!=5 && a!=8){
            String nextMove = currentMove.substring(0,a)+
            		currentMove.charAt(a+1)+
            		"X"+
            		currentMove.substring(a+2);
            checkCompletion(currentMove, nextMove);
        }
    }

    private void checkCompletion(String oldState, String newState) {
        add(newState, oldState);
        if(newState.equals(optimalSolution)) {
            System.out.println("Solution Exists at Level "+movesDone.get(newState)+" of the solutions tree");
            String traceState = newState;
            
            while (traceState != null) {
            	drawBoard(traceState);
            	traceState = movesHistory.get(traceState);
            }
            System.exit(0);
        }
    }

    public void drawBoard(String board){
    	String[] boardArr = board.split("");
    	
    	System.out.println("At level" + movesDone.get(board));
    	System.out.println("=========");
    	System.out.println("|" + boardArr[1] + "|" + "|" + boardArr[2] + "|" + "|" + boardArr[3] + "|\n"+
    			           "|" + boardArr[4] + "|" + "|" + boardArr[5] + "|" + "|" + boardArr[6] + "|\n"+
    		               "|" + boardArr[7] + "|" + "|" + boardArr[8] + "|" + "|" + boardArr[9] + "|");
    	System.out.println("=========");


    }
}
