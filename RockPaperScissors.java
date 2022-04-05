import java.util.Scanner;
import java.util.Random;
public class RockPaperScissors {
    enum Choices{
        ROCK,
        PAPER,
        SCISSORS
    }
    public static Choices[] CHOICE = Choices.values();
    static Random r = new Random();
    public static void main(String[] args){
        Scanner k = new Scanner(System.in);
        int rounds = 0;
        boolean goAgain;
        System.out.println("In this program you play rock paper scissors against a computer.");
        System.out.println("Give me amount of rounds:");
        do{
            rounds = k.nextInt();
            goAgain = checkIfOdd(rounds);
        }while(goAgain);
        divider();
        int humanWins = 0;
        int compWins = 0;
        //CHOICE[0];
        goAgain = false;
        Choices a = Choices.PAPER;
        Choices b;
        double check1;
        double check2;
        boolean notAnOption = false;
        String input;
        char choice;
        System.out.println("1 is rock\n2 is paper\n3 is Scissors.");
        for(int i=0; i<rounds; i++){
            do{
                divider();
                do{
                    System.out.println("Give me 1 or 2 or 3:");
                    input = k.next();
                    choice = input.charAt(0);
                    if(choice!='1' && choice!='2' && choice!='3'){
                        System.out.println("NOT AN OPTION.");
                        notAnOption = true;
                    }else{
                        notAnOption=false;
                        
                    }
                }while(notAnOption);
                switch (choice){
                    case '1':
                    a = Choices.ROCK;
                    break;
                    case '2':
                    a = Choices.PAPER;
                    break;
                    case '3':
                    a = Choices.SCISSORS;
                    break;
                    default:
                    System.out.println("An error has occured");
                }
                divider();
                System.out.println("Human | Computer");
                //a = check(choice);
                b=getRandomChoice();
                System.out.println(a + "|" + b);
                if(isTie(a,b)==1){
                    System.out.println("That was a tie, we redo it.");
                    goAgain = true;
                }else{
                    goAgain = false;
                }
            }while(goAgain);

            if(isTie(a,b)==2){
                compWins++;
            }else if(isTie(a,b)==3){
                compWins++;
            }else if(isTie(a,b)==4){
                compWins++;
            }else if(isTie(a,b)==5){
                humanWins++;
            }else if(isTie(a,b)==6){
                humanWins++;
            }else if(isTie(a,b)==7){
                humanWins++;
            }else{
                //Nothing
            }
            check1 = compWins-0.5;
            check2 = humanWins-0.5;
            double roundsCheck = (double)rounds/2;
            System.out.println("Human amount of wins=" + humanWins + " Computer amount of wins=" + compWins);
            if(check1 == roundsCheck){
                divider();
                System.out.println("Computer wins by majority.");
                break;
            }else if(check2 == roundsCheck){
                divider();
                System.out.println("Human wins by majority.");
                break;
            }
        }
    }
    public static void divider(){
        for(int i=0; i<80; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    public static boolean checkIfOdd(int r){
        if(r<=0){
            System.out.println("I only accept odd positive numbers.");
            return true;
        }
        double check;
        check = r%2;
        if (check == 0){
            System.out.println("I only accept odd positive numbers.");
            return true;
        }else{
            return false;
        }
    }

    public static int isTie(Choices a, Choices b){
        if(a==b){
            return 1;
        }else{
           return whoBeat(a, b);
        }
    }
    //Paper beats rock
    //rock beats scissors
    //scissors beat Paper
    public static int whoBeat(Choices a, Choices b){
        if(a==Choices.ROCK && b==Choices.PAPER){
            return 2;//a lost to b
        }else if(a==Choices.PAPER && b==Choices.SCISSORS){
            return 3;//a lost to b
        }else if(a==Choices.SCISSORS && b==Choices.ROCK){
            return 4;//a lost to b
        }else if(a==Choices.ROCK && b==Choices.SCISSORS){
            return 5;//A has beaten b
        }else if(a==Choices.SCISSORS && b==Choices.PAPER){
            return 6;//A has beaten b
        }else if(a==Choices.PAPER && b==Choices.ROCK){
            return 7;//A has beaten b
        }else{
            return 8;//Error?
        }
    }

    public static Choices getRandomChoice(){
        int randomIndex = r.nextInt(CHOICE.length);
        return CHOICE[randomIndex];
    }
}
