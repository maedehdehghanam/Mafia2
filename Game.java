import java.util.*;
import java.util.regex.*;
//import project.mafia;
public class Game{
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		Mafia[] initial_mafias=new Mafia[50];
		Villager[] initial_villager=new Villager[50];
		//starting the game
		String starting_game=scan.nextLine();
		while(starting_game.equals("create_game")==false)
		{
			System.out.println("no game created");
			starting_game=scan.nextLine();
		}
		//adding players to the game
		System.out.println("How many players want to play?");
		int numberOfPlayers=scan.nextInt();
		String[] players_names=new String[numberOfPlayers];
		for (int i=0;i<numberOfPlayers;++i)
		{
			players_names[i]=(scan.next());
		}
		//creating an array of players
		Player[] players=new Player[numberOfPlayers];
		int numberOfMafias=0;
		int numberOfVillagers=0;
		//checking order
		String assigning_roles=scan.nextLine();
		while(assigning_roles.equals("assign_role")==false)
		{
			System.out.println("Now it's time to assign roles!please type'assign_role'");
			assigning_roles=scan.nextLine();
		}

		//assigning the roles
		String role=null;
		String names=null;
		int j=0;
		for(int k=0;k<numberOfPlayers;k++)
		{
			//checking if the player exists
			while(j==0)
			{	
				names=scan.next();
				for (int i=0;i<numberOfPlayers ;++i )
				{
					if(names.equals(players_names[i]))
					{
						j++;
						break;
					}
				}
				if(j==0)
				{
				System.out.println("User not found!");
				}
			}
			//checking if the role exists
			int z=0;
			while(z==0)
			{
				role=scan.next();
				if(role.equals("mafia"))
				{
					initial_mafias[numberOfMafias]=new Mafia(names);
					initial_mafias[numberOfMafias].godfather=false;
					initial_mafias[numberOfMafias].silencer=false;
					numberOfMafias++;
					z++;
				}
				else if(role.equals("godfather"))
				{
					initial_mafias[numberOfMafias]=new Mafia(names);
					initial_mafias[numberOfMafias].godfather=true;
					initial_mafias[numberOfMafias].silencer=false;
					numberOfMafias++;
					z++;					
				}
				else if(role.equals("silencer"))
				{
					initial_mafias[numberOfMafias]=new Mafia(names);
					initial_mafias[numberOfMafias].godfather=false;
					initial_mafias[numberOfMafias].silencer=true;	
					numberOfMafias++;
					z++;					
				}
				else if(role.equals("villager"))
				{
					initial_villager[numberOfVillagers]=new Villager(names);
					initial_villager[numberOfVillagers].doctor=false;
					initial_villager[numberOfVillagers].detective=false;
					initial_villager[numberOfVillagers].bulletproof=false;
					numberOfVillagers++;
					z++;
				}
				else if(role.equals("doctor"))
				{
					initial_villager[numberOfVillagers]=new Villager(names);
					initial_villager[numberOfVillagers].doctor=true;
					initial_villager[numberOfVillagers].detective=false;
					initial_villager[numberOfVillagers].bulletproof=false;
					numberOfVillagers++;
					z++;					
				}
				else if(role.equals("detective"))
				{
					initial_villager[numberOfVillagers]=new Villager(names);
					initial_villager[numberOfVillagers].doctor=false;
					initial_villager[numberOfVillagers].detective=true;
					initial_villager[numberOfVillagers].bulletproof=false;
					numberOfVillagers++;
					z++;					
				}	
				else if(role.equals("bulletproof"))
				{
					initial_villager[numberOfVillagers]=new Villager(names);
					initial_villager[numberOfVillagers].doctor=false;
					initial_villager[numberOfVillagers].detective=false;
					initial_villager[numberOfVillagers].bulletproof=true;
					numberOfVillagers++;
					z++;					
				}
				else if(role.equals("joker"))
				{
					Player joker=new Joker(names);
					z++;
				}
				else
				{
					System.out.println("role not found");
				}
			}
			j=0;
			z=0;
		}
		

		

	}
}