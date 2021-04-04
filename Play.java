import java.util.*;
public class Play{
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);

		System.out.println("*    Hi!Wellcome to MAFIA    *");
		System.out.println("for creating a game please enter(create_game)");
		String starting_game=scan.nextLine();
		while(starting_game.equals("create_game")==false)
		{
			System.out.println("no game created");
			starting_game=scan.nextLine();
		}
		System.out.println("Great!the game is created.now please tell me how many players want to play?");
		int numberOfPlayers=scan.nextInt();
		System.out.println("Now in one line tell me players names=)");
		String[] players_names=new String[numberOfPlayers];
		for (int i=0;i<numberOfPlayers;++i)
		{
			players_names[i]=(scan.next());
		}
		Game2 game=new Game2(numberOfPlayers,players_names);
		String assigning_roles=scan.nextLine();
		while(assigning_roles.equals("assign_role")==false)
		{
			System.out.println("Now it's time to assign roles!please type'assign_role'");
			assigning_roles=scan.nextLine();
		}
		//giviving roles!
		game.givingRoles();
		//
		System.out.println("Well done!Whenever you were ready to start the game type'start_game'.");
		String starting_game2=scan.nextLine();
		while(starting_game2.equals("start_game")==false)
		{
			starting_game2=scan.nextLine();
		}
		game.rolesCheck();
		while(game.game_state)
		{
			game.dayPhase();
			if(game.game_state)
			{
				game.nightPhase();
			}
		}

	}
}
