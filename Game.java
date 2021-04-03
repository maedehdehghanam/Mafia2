import java.util.*;
import java.util.regex.*;
//import project.mafia;

public class Game{
	public static int findPlayer(String name,Player[] playersArray,int number)
	{
		int index=0;
		for (int i=0;i<number;++i)
		{
			if(playersArray[i].getName().equals(name))
			{
				index=i;
				break;
			}
		}
		return index;
	}
	public static String playerRole(String name,Mafia[] mafias,Villager[] villagers,int numMafias,int numVillagers,Joker joker)
	{
		for(int i=0;i<numMafias;i++)
		{
			if(mafias[i].getName().equals(name))
			{
				if(mafias[i].godfather)
				{
					return "godfather";
				}
				else if(mafias[i].silencer)
				{
					return"silencer";
				}
				else
				{
					return "mafia";
				}
			}
		}
		for(int i=0;i<numVillagers;i++)
		{
			if(villagers[i].getName().equals(name))
			{
				if(villagers[i].doctor)
				{
					return "doctor";
				}
				else if(villagers[i].detective)
				{
					return "detective";
				}
				else if(villagers[i].bulletproof)
				{
					return "bulletproof";
				}
				else 
					return "villager";
			}
		}
		if(name.equals(joker.getName()))
			return "joker";
		else 
			return "user not found";
	}
	public static void zeroVotes(Player[] playersArray,int numOfPlayers)
	{
		for(int i=0;i<numOfPlayers;++i)
		{
			playersArray[i].votes=0;
		}
	}
	//tabe baraye vroodi gereftan ta dastoor ejra she
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		Mafia[] initial_mafias=new Mafia[50];
		Villager[] initial_villager=new Villager[50];
		Joker joker=null;	
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
					joker=new Joker(names);
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
		//starting the game
		System.out.println("Well done!Whenever you were ready to start the game type'start_game'");
		//showing the roles
		System.out.println("here are the roles:");
		for (int i=0;i<numberOfMafias;++i)
		{
			System.out.print(initial_mafias[i].getName());
			if(initial_mafias[i].silencer==true)
			{
				System.out.println(":silencer");
			}
			else if(initial_mafias[i].godfather==true)
			{
				System.out.println(":godfather");
			}	
			else
			{
				System.out.println(":mafia");
			}
		}
		for (int i=0;i<numberOfVillagers ;++i )
		{
			System.out.print(initial_villager[i].getName());
			if(initial_villager[i].doctor==true)
				System.out.println(":doctor");
			else if(initial_villager[i].detective==true)
			{
				System.out.println(":detective");
			}
			else if(initial_villager[i].bulletproof==true)
			{
				System.out.println(":bulletproof");
			}
			else
			{
				System.out.println(":villager");
			}
		}
		System.out.print(joker.getName());
		System.out.println(":joker");
		//starting the game
		Day day=new Day();
		Night night=new Night();
		String game_state="not finished";
		int alives=numberOfPlayers;
		int numberOfVillagers_alive=numberOfVillagers;
		int numberOfMafias_alive=numberOfMafias;
		//list of players which has been worked on at night
		String got_silenced=null;
		String saved=null;
		//game
		while(true)
		{
			//day satate
			System.out.print(day.name+day.day_number);
			//a voter wants to vote
			String voter=null;
			String votee=null;
			int max_vote=0;
			int maxnum=0;
			String suspected=null;
			boolean is_sus_mafia=false;
			//checking if the voter exists or not
			int p=0;
			int p2=0;
			while(p==0 || p2==0)
			{	
				voter=scan.next();
				for (int i=0;i<numberOfPlayers ;++i )
				{
					if(voter.equals(players_names[i]))
					{
						p++;
						break;
					}
				}
				if(p==0)
				{
					System.out.println("User not found!");
				}
				//checking if voter is alive 
				if(p!=0)
				{
					for (int i=0;i<numberOfMafias;i++)
					{	
						if(voter.equals(initial_mafias[i].getName()))
						{
							if(initial_mafias[i].state)
							{
								p2++;
								if(voter.equals(got_silenced))
								{
									p2++;
								}
								break;
							}
						}	
			
					}
					for (int i=0;i<numberOfVillagers;i++)
					{
						if(voter.equals(initial_villager[i].getName()))
						{
							if(initial_villager[i].state)
							{
								p2++;
								if(voter.equals(got_silenced))
								{
									p2++;
								}
								break;
							}
						}	
					}
					if(voter.equals(joker.getName()))
					{
						if(joker.state)
						{
							p2++;
							if(voter.equals(got_silenced))
							{
								p2++;
							}
						}
					}
					if(p2==0 || p2==2)
					{
						if(p2==0)
							System.out.println("the voter is deaed!");
						else {
							System.out.println("the voter is silenced!");
							p2=0;
						}
					}
				}
			}
			//checking if the votee exists or not
			int x=0;
			int x2=0;
			while(x==0 || x2==0)
			{	
				votee=scan.next();
				for (int i=0;i<numberOfPlayers ;++i )
				{
					if(votee.equals(players_names[i]))
					{
						x++;
						break;
					}
				}
				if(x==0)
				{
					System.out.println("User not found!");
				}
				//Checking if the votee is alive
				if(x!=0)
				{
					for (int i=0;i<numberOfMafias;i++)
					{	
						if(votee.equals(initial_mafias[i].getName()))
						{
							if(initial_mafias[i].state)
							{
								initial_mafias[i].votes++;
								if(initial_mafias[i].votes >max_vote)
								{
									max_vote=initial_mafias[i].votes;
									maxnum=1;
									suspected=initial_mafias[i].getName();
									is_sus_mafia=true;
								}
								else if(initial_mafias[i].votes==max_vote)
								{
									maxnum++;
								}
								x2++;
								break;
							}
						}	
			
					}
					for (int i=0;i<numberOfVillagers;i++)
					{
						if(votee.equals(initial_villager[i].getName()))
						{
							if(initial_villager[i].state)
							{
								initial_villager[i].votes++;
								if(initial_villager[i].votes>max_vote)
								{
									max_vote=initial_villager[i].votes;
									maxnum=1;
									suspected=initial_villager[i].getName();
									is_sus_mafia=false;
								}
								else if(initial_villager[i].votes==max_vote)
								{
									maxnum++;
								}
								x2++;
								break;
							}
						}	
					}
					if(votee.equals(joker.getName()))
					{
						if(joker.state)
						{
							joker.votes++;
							if(joker.votes>max_vote)
							{
								max_vote=joker.votes;
								maxnum=1;
								suspected=joker.getName();
							}
							else if(joker.votes==max_vote)
							{
								maxnum++;
							}
							x2++;
						}
					}
					if(x2==0)
					{
						System.out.println("the votee is already dead!");
					}
				}
			}
			//the voting is ok!

			//who dies?
			if(maxnum>1)
			{
				System.out.println("nobody died!");
			}
			else if(maxnum==1 && suspected.equals(joker.getName()))
			{
				joker.state=false;
				System.out.println("Joker won!");
				break;
			}
			else
			{
				System.out.println(suspected+"died!");
				if(is_sus_mafia)
				{
					int index=findPlayer(suspected,initial_mafias,numberOfMafias);
					initial_mafias[index].state=false;
					numberOfMafias_alive--;
				}
				else
				{
					int index=findPlayer(suspected,initial_villager,numberOfVillagers);
					initial_villager[index].state=false;
					numberOfVillagers_alive--;
				}

			}
			//day is over
			day.day_number++;
			//the votes for every players return to zero
			zeroVotes(initial_villager,numberOfVillagers);
			zeroVotes(initial_mafias,numberOfMafias);
			joker.votes=0;
			//night is here
			System.out.println(night.name+night.number_night);
			night.number_night++;
			//printing the nighters:-)
			for(int i=0;i<numberOfMafias;++i)
			{
				if(initial_mafias[i].state)
				{
					System.out.print(initial_mafias[i].getName());
					if(initial_mafias[i].godfather)
						System.out.println(":godfather");
					else if(initial_mafias[i].silencer)
						System.out.println(":silencer");
					else
						System.out.println(":mafia");
				}
			}
			for(int i=0;i<numberOfVillagers;i++)
			{
				if(initial_villager[i].doctor && initial_villager[i].state)
				{
					System.out.println(initial_villager[i].getName()+":doctor");
				}
				else if(initial_villager[i].detective && initial_villager[i].state)
				{
					System.out.println(initial_villager[i].getName()+":detective");
				}

			}
			//the night works starts
			String doer=null;
			String done=null;
			int check=0;
			String doer_role=null;
			int doer_index=0;
			//if the doer is ok
			while(check==0){
				doer=scan.next();
				doer_role=playerRole(doer,initial_mafias,initial_villager,numberOfMafias,numberOfVillagers,joker);
				doer_index=findPlayer(doer,initial_mafias,numberOfMafias);
				if(doer_role.equals("villager") || doer_role.equals("joker"))
				{
					System.out.println("the user is not awake during the night!");
					check++;
				}
				else if((doer_role.equals("doctor") || doer_role.equals("detective")) && initial_villager[findPlayer(doer,initial_villager,numberOfVillagers)].state==false)
				{
					System.out.println("the user is dead!");
					check++;
				}
				else if(doer_role.equals("mafia") && initial_mafias[doer_index].state==false)
				{
					System.out.println("the user is dead!");
					check++;
				}
			}
			//if the done is ok
			int check2=0;
			while(check2==2)
			{	done=scan.next();
				String done_role=playerRole(done,initial_mafias,initial_villager,numberOfMafias,numberOfVillagers,joker);
				int done_index=findPlayer(done,initial_villager,numberOfVillagers);
				if(done_role.equals("user not found"))
				{
					System.out.println("user did not join!");
				}
				else if(initial_villager[done_index].state==false)
				{
					System.out.println("votee is already dead!");
				}
				else if(doer_role.equals("doctor"))
				{
					saved=done;
					check2++;
				}
				else if(doer_role.equals("detective"))
				{
					if(done_role.equals("godfather") || done_role.equals("villager"))
					{
						System.out.println("No");
					}
					else if(done_role.equals("mafia") || done_role.equals("silencer"))
					{
						System.out.println("Yes");
					}
					check2++;
				}
				else if(doer_role.equals("silencer"))
				{
					if(got_silenced==null)
					{
						got_silenced=done;
					}
					else
					{
						initial_mafias[doer_index].votee=done;
					}
					check2++;
				}
				else 
				{
					initial_mafias[doer_index].votee=done;
				}
			}
			//lets assume every body voted
			int max_mafias_votes=0;
			int number_of_maxvotes=0;
			String max_mafias_name=null;
			for (int i=0;i<numberOfMafias;i++)
			{
				if(initial_mafias[i].votee!=null)
				{
					int j2=findPlayer(initial_mafias[i].votee,initial_villager,numberOfVillagers);
					initial_villager[j2].votes++;
					if(initial_villager[j2].votes>max_mafias_votes)
					{
						number_of_maxvotes=1;
						max_mafias_votes=initial_villager[j2].votes;
						max_mafias_name=initial_mafias[i].votee;
					}
					else if(initial_villager[j2].votes==max_mafias_votes)
					{
						number_of_maxvotes++;
					}

				}
			}
			//lets see who gets killed

			if(number_of_maxvotes>1)
			{
				System.out.println("mafias tried to kill"+number_of_maxvotes+"players,but could't");
			}
			else
			{
				if(initial_villager[findPlayer(max_mafias_name,initial_villager,numberOfVillagers)].bulletproof && initial_villager[findPlayer(max_mafias_name,initial_villager,numberOfVillagers)].shot==0)
				{
					initial_villager[findPlayer(max_mafias_name,initial_villager,numberOfVillagers)].shot=1;
					System.out.println("mafias tried to kill"+max_mafias_name+"but couldn't");
				}
				else if(max_mafias_name.equals(saved))
				{
					System.out.println("mafias tried to kill"+max_mafias_name+"but doctor saved them!");
				}
				else 
				{
					System.out.println("mafias killed"+max_mafias_name);
					initial_villager[findPlayer(max_mafias_name,initial_villager,numberOfVillagers)].state=false;
				}
				if(got_silenced!=null)
				{
					System.out.println(got_silenced+"is silenced!");
				}
			}
			//null kardan votee barye round jadid
			for (int i=0;i<numberOfMafias;++i) {
				initial_mafias[i].votee=null;
			}

		}

	}
}