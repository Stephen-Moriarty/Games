import java.util.Scanner;
import java.util.Random;
public class ConnectFourPrototype {
    static Scanner k= new Scanner(System.in);
    static Random r = new Random();
    static char[][] playSpace = new char[7][7];
    static final char EMPTY='.';
    public static void main(String[] args){
        addLables();
        printPlaySpace();
        char player = '■';
        int turn=0;
        /*
            ■	
            □
        */
        // column is spelled: column
        do{
            player=(turn++%2==0) ? 'x' : 'o';
            System.out.println("Where would you like to go? [1-7]:");
            runTurn(player);
            printPlaySpace();
        }while(noWinner());
        System.out.println("The game has ended.");
    }
    public static void runTurn(char p){
        getUserCol(p);
    }
    public static void getUserCol(char p){
        int choice = isInputInvalid();
        boolean isFull= false;
        isFull = checkIfFull(choice, p);
        dropChip(choice, p, isFull);
        //checkForWins();
    }
    
    public static int isInputInvalid(){
        int choice = 1;
        boolean notAnOption = false;
        do{
            try{
                choice = k.nextInt();
                notAnOption=false;
            }catch(Exception e){
                k.next();
                notAnOption=true;
                System.out.println("That is not an integer...");
            }
            if(choice<8 && choice>0){
                notAnOption = false;
            }else{
                notAnOption=true;
                System.out.println("You can only choose 1 through 7.");
            }
        }while(notAnOption);
        return --choice;
    }
    public static void dropChip(int c, char p, boolean isFull){
        if(isFull){
            int choice = 0;
            System.out.println("That column is full choose a different one.");
            choice = isInputInvalid();
            dropChip(choice, p, false);
        }else{
            for(int i=playSpace.length-1; i>=0; i--){
                if(playSpace[i][c]==EMPTY){
                    playSpace[i][c]=p;
                    break;
                }
            }
        }      
    }
    public static boolean checkIfFull(int c, char p){
        int howManyAreFull = 0;
        for(int i=6; i>=0; i--){
            if(playSpace[i][c] != EMPTY){
                howManyAreFull++;
            }
        }
        if(howManyAreFull == 7){
            playSpace[0][c] = p;
            return true;
        }else{
           return false; 
        }
    }
    public static boolean noWinner(){
        //boolean starter = false;
        int a = 5;
        int b = 0;
        return diagonalWins(a,b);
        /*
        allFull
        diagonalWins1
        checkFourArr
        horizontalWins
        checkFourArr
        verticalWins
        checkFourArr
        */
    }
    public static boolean diagonalWins(int start, int end){
        if(end==0 && start==3){
            return true;
        }
        int c = 0;
        char[] four = new char[4];
        for(int i=start; i<=3; i++){
            for(int j=end; j<=3; j++){
                four[c++] = playSpace[i][j];
            }
        }
        int counter = 0;
        for(int i=0; i<=2; i++){
            for(int j=1; j<=3; j++){
                if(four[i]==four[j] && four[i]!=EMPTY){
                    counter++;
                }
            }
        }
        if(counter==4){
            return false;
        }else{
            return diagonalWins(--start, ++end);
        }
    }
    public static boolean checkForHorizontalWin(int c, char player){
        int counter = 0;
        for(int i=0; i<=c; i++){
            if(playSpace[c][i] == player){
                counter++;
            }
        }
        if(counter==4){
            return true;
        }

        counter = 0;
        for(int i=c; i<=0; i--){
            if(playSpace[c][i] == player){
                counter++;
            }
        }
        if(counter==4){
            return true;
        }else{
            return false;
        }
    }

    // TODO: make sure stuff works?
    public static boolean checkBoardFull(){
        int howManyAreFull = 0;
        for(int i=0; i<=6; i++){
            for(int j=0; j<=6; j++){
                if(playSpace[i][j] != EMPTY){
                    howManyAreFull++;
                }
            }
        }
        if(howManyAreFull==49){
            System.out.println("It was a tie.");
            return false;
        }else{
            return true;
        }
    }
    //Done
    public static void printPlaySpace(){
        for(char[] row : playSpace){
            for(char val : row){
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
    //Done
    public static void addLables(){
        for(int i=0; i<playSpace.length; i++){
            for(int j=0; j<playSpace.length; j++){
                if(i<=5){
                    playSpace[i][j] = EMPTY;
                }else{
                    char count = 49;
                    for(int k=0; k<=6; k++){
                        playSpace[i][k] = count++;
                    }
                } 
            }
        }
    }
}
