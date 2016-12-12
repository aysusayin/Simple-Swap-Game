/*This program is the SWAP game where user gives the coordinates of 2 letters 
 * he or she wants to swap.User gets score if same letters are side by side. */
import java.util.Scanner;
public class SwapGame {
	public static void main(String[] args) {
		//In order to get user input I created scanner. 
		//(2 scanner to prevent an error that nextLine statement causes.)
		Scanner console = new Scanner(System.in);
		Scanner console2 = new Scanner(System.in);

		//Introduction to game.
		System.out.println("Welcome to this weird game of SWAP");


		String line1 = "", line2 = "", line3 = "", line4 = ""; //line1, line2, line3 and line4 are the lines of board.

		//i is for checking if user answered correctly. If not, i is initialized to 0 at the end and goes into loop again.
		for(int i=1; i<=1;i++){  
			System.out.print("Do you want to use the default board configuration?");
			String answer = console.next();

			//If user answers no, asks for a custom board configuration. 
			if(answer.equalsIgnoreCase("no")){
				System.out.print("Enter row 1 of the board: ");
				line1= console.next();
				System.out.print("Enter row 2 of the board: ");
				line2= console.next();
				System.out.print("Enter row 3 of the board: ");
				line3= console.next();
				System.out.print("Enter row 4 of the board: ");
				line4= console.next();

				//If user answers yes, default board configuration is set.
			}else if(answer.equalsIgnoreCase("yes")){
				/*Lines of default board configuration. line1 is the first line of default board configuration, line2 is second,
		line3 is the third and line4 is fourth.*/
				line1 = "ABGE";
				line2 = "RTFF";
				line3 = "AKEM";
				line4 = "GVJA";	
				//If user doesn't answer properly, it goes back to loop.
			}else{
				System.out.println("\nPlease answer YES or NO.\n");
				i=0;
			}
		}

		String board = line1 + line2 + line3 + line4;	//board is the sum of all lines. (Lines of table side by side.)
		board = board.toUpperCase();

		System.out.println("\nThis is the board configuration now:");
		boardConfiguration(board);

		System.out.print("\nHow many moves do you want to make? ");


		int movenum = console.nextInt();	//movenum is the number of movements that user wants to do.
		System.out.println("Make a move and press enter. After each move, the board configuration and your total points will");
		System.out.println("be printed. Input the coordinates to be swapped.\n");

		//This for loop provides to make moves according to desired move number. 
		//i is for counting loop. When i exceeds the movenum, for loop ends. 
		for(int i=1;i<=movenum;i++){
			board = boardAfterMove(console2,board);
			System.out.println("This is the board configuration now:");
			//This if statement prints hint if user will continue playing.(If his or her move number is not exceeded)
			if(i != movenum){
				System.out.println("\n"+hint(board)+"\n");
			}
			boardConfiguration(board);
			System.out.println("\nYour total score is "+ score(board)+".\n");
		}
		System.out.println("\nThank you for playing this game.");	
	}

	/*This method gets the move that user make and board configuration at start as a String and returns the board configuration 
	 * after the movement as a String. console2 is to get user input , board is String of board configuration(sum of lines).*/
	public static String boardAfterMove(Scanner console2, String board){

		String move = console2.nextLine();

		/*When the program gets user input(move), it assigns first number of move to horizontal coordinate of first letter(x1),
		 *second number of move to vertical coordinate of first letter(y1), third number of move to horizontal coordinate of 
		 *second letter(x2) and fourth number of move to vertical coordinate of second letter(y2).
		 *To get the integer value, '0' character is subtracted from coordinates. Because for example integer value of '3' is 32.
		 *When '0' is subtracted from '3', it gives 3 since the 0,1,2,3,...9 is consecutive in ASCII table.  */
		int x1 = move.charAt(0)-'0';
		int y1 = move.charAt(2)-'0';
		int x2 = move.charAt(4)-'0';
		int y2 = move.charAt(6)-'0';

		//If all the coordinates are same, it returns the sama board configuration.
		if(x1 == x2 && y1 == y2){
			return board;
		}

		//If coordinate of letter 1 is bigger, this statement changes their values with each other. 
		if(x1==x2 && y1>y2){
			int temporary = x2;	//Temporary is to hold other variables temporarily.
			x2 = x1;
			x1 = temporary;
			temporary = y2;
			y2 = y1;
			y1 = temporary;
		}
		//If coordinate of letter 1 is bigger, this statement changes their values with each other. 
		if(x1 > x2){
			int temporary = x2;	//Temporary is to hold other variables temporarily.
			x2 = x1;
			x1 = temporary;
			temporary = y2;
			y2 = y1;
			y1 = temporary;
		}

		String b= "";	//b is the blank String that the program will add up the lines to form the new board configuration.
		char letter1 = board.charAt(4*(x1-1)+y1-1);	//letter1 is the first letter that we want to change.
		char letter2 = board.charAt(4*(x2-1)+y2-1);	//letter2 is the second letter that we want to change.

		/*This for loop adds characters at board in order, until it reaches the character of first letter which is 
		 * (4*(x1-1)+y1-1). */
		for(int i=0; i<4*(x1-1)+y1-1;i++){	//All i's in for loops are for counting the loops.
			b+=board.charAt(i);
		}
		/*Then the second letter(the one we want to change the first letter with) is added. After 
		 *that for loop adds characters at board to b in order again until it reaches the 
		 * character of second letter. */
		b+=letter2;
		for(int i = 4*(x1-1)+y1; i<4*(x2-1)+y2-1;i++){
			b+=board.charAt(i);
		}
		/*First letter is added to b and for loop continues adding the characters till it reaches the end of String board.*/
		b+=letter1;
		for(int i = 4*(x2-1)+y2; i < board.length();i++){
			b+=board.charAt(i);
		}
		return b;
	}

	/*This method gets the board configuration as a String and returns the score of that configuration. 
	 * board is String of board configuration(sum of lines)*/
	public static int score(String board){

		int score = 0;	//score is the score that the board configuration has.

		//This nested for loop checks if two letter that are side to side are same and increments score for each pair.
		for(int i=1; i<=4;i++){	// i is the x coordinate of the letter. j is the y coordinate of the letter. 
			for(int j=4*(i-1) ; j < 4*i-1; j++){
				if( board.charAt(j)== board.charAt(j+1))
					score++;
			}
		}
		return score;
	}

	//This method gets board configuration as String prints the board configuration as a table.
	public static void boardConfiguration(String board){

		//This for loop prints the board made up of cells that contain letters.
		for(int i=0; i<4; i++){	//i is horizontal line number and m is vertical letter number.
			for(int m= 4*i; m<4*i+4; m++){
				System.out.print("|"+board.charAt(m));
			}
			System.out.println("|");
		}
	}

	/*This method returns a hint(String) according to the board configuration. board is String of board configuration(sum of lines)*/
	public static String hint(String board){

		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;	//(x1, y1) and (x2, y2) are the coordinates of letters that will be given by the hint.

		int control = 0;	//control is for controlling if there is a possible movement for (x1,y1) and (x2,y2).

		for(int i=0; i<board.length(); i++){	// i and k are numbers of the characters in String board.
			for(int k=i+2; k<board.length(); k++){
				/*This if statement checks  if there is 3 same characters align next to each other and there is another one that is not next to them.
				 *For example: .AAA....A... (Dots represent different letters)
				 *It gives the coordinates of first letter and the one which is seperate from the group.(first and last one)*/
				if(board.charAt(i)==board.charAt(k)&& board.charAt(k)==board.charAt(i+1)&& board.charAt(k)==board.charAt(i+2) && i/4 != k/4){
					control = 3;
					y1 = i%4 + 1;
					y2 = k%4 + 1;
					x1 = i/4 + 1;
					x2 = k/4 + 1;
					/*This if statement checks  if there is 2 same characters align next to each other and there is another one that is not next to them.
					 *For example: ..AA....A... (Dots represent different letters)
					 *It gives the coordinates of first letter and the one which is seperate from the group.(first and last one)*/
				}else if (board.charAt(i)==board.charAt(k)&& board.charAt(k)==board.charAt(i+1)&& k != i+2 ){
					control=2;
					y1 = i%4 + 1;
					y2 = k%4 + 1;
					x1 = i/4 + 1;
					x2 = k/4 + 1;
					/*This statement checks if there is to same letter that are away from each other
					 * For example ......A......A..(Dots represent different letters) */
				}else if(board.charAt(i)==board.charAt(k)&& board.charAt(i+1)!=board.charAt(k)){
					control=1;
					y1 = i%4 + 1;
					y2 = k%4 + 1;
					x1 = i/4 + 1;
					x2 = k/4 + 1;
				}
			}
			/*This if statements check lots of things. It is very complicated so I will try to explain it briefly. They check the situations according to control value,
			 * if the letter we want to change is different from the one we want to change with, if the letter we want to change is already in a pair etc. Thus,
			 * they give the most suitable coordinates player can change.By that, the letter is transported next to the other one.*/
			if(control==1 && y1 != 1 && y1 !=2 && board.charAt(4*(x1-1)+y1-2)!=board.charAt(4*(x2-1)+y2-1)&& board.charAt(4*(x1-1)+y1-2)!= board.charAt(4*(x1-1)+y1-3)){
				return "HINT: You can change (" + x1 + " , " + (y1-1) + ") with ("+ x2 + " , " + y2 + ").";
			}else if(control==1 && y1 != 1 && y1 !=2 && y2 ==2 && board.charAt(4*(x1-1)+y1-1)!=board.charAt(4*(x2-1)+y2-2)&& board.charAt(4*(x1-1)+y1-2)== board.charAt(4*(x1-1)+y1-3)){
				return "HINT: You can change (" + x1 + " , " + y1 + ") with ("+ x2 + " , " + (y2-1) + ").";
			}else if(control==1 && y1 != 1 && y1 !=2 && y2 ==1 && board.charAt(4*(x1-1)+y1-1)!=board.charAt(4*(x2-1)+y2)&& board.charAt(4*(x1-1)+y1-2)== board.charAt(4*(x1-1)+y1-3)){
				return "HINT: You can change (" + x1 + " , " + y1 + ") with ("+ x2 + " , " + (y2+1) + ").";	
			}else if(control==1 &&y1 != 1 && y1 !=2 && y2 !=1 && y2 !=2 && board.charAt(4*(x2-1)+y2-2)!= board.charAt(4*(x2-1)+y2-3) &&board.charAt(4*(x1-1)+y1-1)!=board.charAt(4*(x2-1)+y2-2)&& board.charAt(4*(x1-1)+y1-2)== board.charAt(4*(x1-1)+y1-3)){
				return "HINT: You can change (" + x1 + " , " + y1 + ") with ("+ x2 + " , " + (y2-1) + ").";
			}else if(control==1 &&y1 != 1 && y1 !=2 && y2 ==3 && board.charAt(4*(x2-1)+y2-2)== board.charAt(4*(x2-1)+y2-3) &&board.charAt(4*(x1-1)+y1-1)!=board.charAt(4*(x2-1)+y2)&& board.charAt(4*(x1-1)+y1-2)== board.charAt(4*(x1-1)+y1-3)){
				return "HINT: You can change (" + x1 + " , " + y1 + ") with ("+ x2 + " , " + (y2+1) + ").";
			}else if(control==1 && y1 == 1 && board.charAt(4*(x1-1)+y1)!=board.charAt(4*(x2-1)+y2-1)&& board.charAt(4*(x1-1)+y1)!=board.charAt(4*(x1-1)+y1+1)){
				return "HINT: You can change (" + x1 + " , " + (y1+1) + ") with ("+ x2 + " , " + y2 + ").";
			}else if(control==1 && y1 == 2 && board.charAt(4*(x1-1)+y1)!=board.charAt(4*(x2-1)+y2-1)&& board.charAt(4*(x1-1)+y1)!=board.charAt(4*(x1-1)+y1+1)){
				return "HINT: You can change (" + x1 + " , " + (y1+1) + ") with ("+ x2 + " , " + y2 + ").";
			}else if(control==1 && y2 ==1 && board.charAt(4*(x1-1)+y1-1)!=board.charAt(4*(x2-1)+y2)){
				return "HINT: You can change (" + x1 + " , " + y1 + ") with ("+ x2 + " , " + (y2+1) + ").";
			}else if(control==1 && y2 ==2 && board.charAt(4*(x1-1)+y1-1)!=board.charAt(4*(x2-1)+y2-2)){
				return "HINT: You can change (" + x1 + " , " + y1 + ") with ("+ x2 + " , " + (y2-1) + ").";
			}else if(control==1 && board.charAt(4*(x1-1)+y1-1)!=board.charAt(4*(x2-1)+y2-2)&& board.charAt(4*(x2-1)+y2-2)!=board.charAt(4*(x2-1)+y2-2)){
				return "HINT: You can change (" + x1 + " , " + y1 + ") with ("+ x2 + " , " + (y2-1) + ").";
			}else if(control==1 && y2 ==3 && board.charAt(4*(x1-1)+y1-1)!=board.charAt(4*(x2-1)+y2) && board.charAt(4*(x2-1)+y2-2)==board.charAt(4*(x2-1)+y2-2)) {
				return "HINT: You can change (" + x1 + " , " + y1 + ") with ("+ x2 + " , " + (y2+1) + ").";
			}else if(control == 2 && y1 == 4 && board.charAt(4*(x1-1)+y1-3)!= board.charAt(4*(x2-1)+y2-1)){
				return "HINT: You can change (" + x1 + " , " + (y1-2) + ") with ("+ x2 + " , " + y2 + ").";
			}else if(control == 2 && y1 == 4 && board.charAt(4*(x1-1)+y1-3)!= board.charAt(4*(x2-1)+y2-1)){
				return "HINT: You can change (" + x1 + " , " + (y1-4) + ") with ("+ x2 + " , " + y2 + ").";
			}else if(control == 2 && y1 == 3 && board.charAt(4*(x1-1)+y1-2)!= board.charAt(4*(x2-1)+y2-1)){
				return "HINT: You can change (" + x1 + " , " + (y1-1) + ") with ("+ x2 + " , " + y2 + ").";
			}else if(control == 2 && y1 != 4 && y1 != 3 && board.charAt(4*(x1-1)+y1+1)!=board.charAt(4*(x2-1)+y2-1)){
				return "HINT: You can change (" + x1 + " , " + (y1+2) + ") with ("+ x2 + " , " + y2 + ").";
			}else if(control == 3 && y1 == 1 && board.charAt(4*(x1-1)+y1+2)!=board.charAt(4*(x2-1)+y2-1)){
				return "HINT: You can change (" + x1 + " , " + (y1+3) + ") with ("+ x2 + " , " + y2 + ").";
			}else if(control == 3 && y1 == 2 && board.charAt(4*(x1-1)+y1-2)!=board.charAt(4*(x2-1)+y2-1)) {
				return "HINT: You can change (" + x1 + " , " + (y1-1) + ") with ("+ x2 + " , " + y2 + ").";
			}
		}
		return "HINT: No moves left :(";		
	}
}
