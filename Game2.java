import java.util.*;
public class Game2{
	public final int numberOfPlayers;
	public  Mafia[] initial_mafias=new Mafia[50];
	public 	Villager[] initial_villager=new Villager[50];
	public 	Joker joker=null;
	public String[] players_names=new String[100];	
	public int numberOfMafias=0;
	public int numberOfVillagers=0;
	public Day day=new Day();
	public Night night=new Night();
	public int numberOfVillagers_alive=0;
	public int numberOfMafias_alive=0; 
	public boolean game_state=true;
	public int alives=0;
	public String got_silenced="";
	public String saved="";
	private Scanner scan=new Scanner(System.in);
	public Game2(int numberOfPlayers,String[] players_names)
	{
		this.numberOfPlayers=numberOfPlayers;
		alives=numberOfPlayers;
		for (int i=0;i<numberOfPlayers;++i)
		{
			this.players_names[i]=players_names[i];
		}
	}
	public static void zeroVotes(Player[] playersArray,int numOfPlayers)
	{
		for(int i=0;i<numOfPlayers;++i)
		{
			playersArray[i].votes=0;
		}
	}
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




	public void givingRoles()
	{
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
		numberOfMafias_alive=numberOfMafias;
		numberOfVillagers_alive=numberOfVillagers;
	}
	public void rolesCheck()
	{
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
		System.out.println("READY?SET,GO!");
	}
	public void dayPhase()
	{
		System.out.print(day.name+day.day_number);
		String voter=null;
		String votee=null;
		int max_vote=0;
		int maxnum=0;
		String suspected=null;
		boolean is_sus_mafia=false;
		for(int n=0;n<alives;++n)
		{
			//checking if the voter exists
			int p=0;
			while(p==0)
			{
				voter=scan.next();
				String voter_role=playerRole(voter,initial_mafias,initial_villager,numberOfMafias,numberOfVillagers,joker);
				if(voter_role.equals("user not found"))
				{
					System.out.println("user not found");
				}
				else
				{
					if(voter_role.equals("villager") || voter_role.equals("doctor") || voter_role.equals("detective") || voter.equals("bulletproof"))
					{
						int voter_index=findPlayer(voter,initial_villager,numberOfVillagers);
						if(initial_villager[voter_index].state==false)
						{
							System.out.println("user is dead!");
						}
						else if(initial_villager[voter_index].getName().equals(got_silenced))
						{
							System.out.println("the voter is silenced!");
						}
						else
						{
							p++;
						}
					}
					else if (voter_role.equals("mafia") || voter_role.equals("silencer") || voter_role.equals("godfather"))
					{
						int voter_index=findPlayer(voter,initial_mafias,numberOfMafias);
						if(initial_mafias[voter_index].state==false)
						{
							System.out.println("user is dead!");
						}
						else if(initial_mafias[voter_index].getName().equals(got_silenced))
						{
							System.out.println("the voter is silenced!");
						}
						else
						{
							p++;
						}
					}
					else
					{
						if(joker.state==false)
						{
							System.out.println("user is dead!");
						}
						else if(joker.getName().equals(got_silenced))
						{
							System.out.println("the voter is silenced!");
						}
						else
						{
						p++;
						}
					}
				}	

			}
			//the voter is ok!
			int x=0;
			while(x==0)
			{
				votee=scan.next();
				String votee_role=playerRole(votee,initial_mafias,initial_villager,numberOfMafias,numberOfVillagers,joker);
				if(votee_role.equals("user not found"))
				{
					System.out.println("user not found");
				}
				else 
				{
					if(votee_role.equals("villager") || votee_role.equals("doctor") || votee_role.equals("detective") || votee_role.equals("bulletproof"))
					{
						int votee_index=findPlayer(votee,initial_villager,numberOfVillagers);
						if(initial_villager[votee_index].state==false)
						{
							System.out.println("user is dead!");
						}
						else
						{
							initial_villager[votee_index].votes++;
							if(initial_villager[votee_index].votes>max_vote)
							{
								max_vote=initial_villager[votee_index].votes;
								maxnum=1;
								suspected=initial_villager[votee_index].getName();
								is_sus_mafia=false;
							}
							else if(initial_villager[votee_index].votes==max_vote)
							{
								maxnum++;
							}
							x++;
						}
					}
					else if (votee_role.equals("mafia") || votee_role.equals("silencer") || votee_role.equals("godfather"))
					{
						int votee_index=findPlayer(votee,initial_mafias,numberOfMafias);
						if(initial_mafias[votee_index].state==false)
						{
							System.out.println("user is dead!");
						}
						else
						{
							initial_mafias[votee_index].votes++;
							if(initial_mafias[votee_index].votes>max_vote)
							{		
								max_vote=initial_mafias[votee_index].votes;
								maxnum=1;
								suspected=initial_mafias[votee_index].getName();
								is_sus_mafia=true;
							}
							else if(initial_mafias[votee_index].votes==max_vote)
							{
								maxnum++;
							}
							x++;
						}
					}
					else
					{
						if(joker.state==false)
						{
							System.out.println("user is dead!");
						}
						else
						{
							joker.votes++;
							if(joker.votes>max_vote)
							{
								suspected=joker.getName();
								max_vote=joker.votes;
								is_sus_mafia=false;
								maxnum=1;
							}
							else if(joker.votes==max_vote)
							{
								maxnum++;
							}
							x++;
						}
					}
				}
			}
		}
		//who dies?
			if(maxnum>1)
			{
				System.out.println("nobody died!");
			}
			else if(maxnum==1 && suspected.equals(joker.getName()))
			{
				joker.state=false;
				System.out.println("Joker won!");
				alives--;
				game_state=false;
			}
			else
			{
				System.out.println(suspected+"died!");
				if(is_sus_mafia)
				{
					int index=findPlayer(suspected,initial_mafias,numberOfMafias);
					initial_mafias[index].state=false;
					numberOfMafias_alive--;
					alives--;
				}
				else
				{
					int index=findPlayer(suspected,initial_villager,numberOfVillagers);
					initial_villager[index].state=false;
					numberOfVillagers_alive--;
					alives--;
				}

			}
		if(numberOfMafias_alive>=numberOfVillagers_alive)
		{
			System.out.println("Mafias WON!");
			game_state=false;
		}
		else if(numberOfMafias==0)
		{
			System.out.println("villagers WON!");
			game_state=false;
		}
		day.day_number++;
		//the votes for every players return to zero
		zeroVotes(initial_villager,numberOfVillagers);
		zeroVotes(initial_mafias,numberOfMafias);
		joker.votes=0;

	}
	public void nightPhase()
	{
		int nighters=0;
		System.out.println(night.name+night.number_night);
		night.number_night++;
		//printing the nighters:-)
		for(int i=0;i<numberOfMafias;++i)
		{
			if(initial_mafias[i].state)
			{
				nighters++;
				System.out.print(initial_mafias[i].getName());
				if(initial_mafias[i].godfather)
					System.out.println(":godfather");
				else if(initial_mafias[i].silencer)
				{	System.out.println(":silencer");
					nighters++;
				}
				else
					System.out.println(":mafia");
			}
		}
		for(int i=0;i<numberOfVillagers;i++)
		{
			if(initial_villager[i].doctor && initial_villager[i].state)
			{
				System.out.println(initial_villager[i].getName()+":doctor");
				nighters++;
			}
			else if(initial_villager[i].detective && initial_villager[i].state)
			{
				System.out.println(initial_villager[i].getName()+":detective");
				nighters++;
			}

		}
		System.out.println(nighters);
		String doer=null;
		String done=null;
		int check=0;
		String doer_role=null;
		int doer_index=0;
		for (int n=0;n<nighters;++n){
			//doer is ok
			while(check==0){
				doer=scan.next();
				doer_role=playerRole(doer,initial_mafias,initial_villager,numberOfMafias,numberOfVillagers,joker);
				doer_index=findPlayer(doer,initial_mafias,numberOfMafias);
				if(doer_role.equals("villager") || doer_role.equals("joker"))
				{
					System.out.println("the user is not awake during the night!");
				}
				else if((doer_role.equals("doctor") || doer_role.equals("detective")) && initial_villager[findPlayer(doer,initial_villager,numberOfVillagers)].state==false)
				{
					System.out.println("the user is dead!");
				}
				else if(doer_role.equals("mafia") && initial_mafias[doer_index].state==false)
				{
					System.out.println("the user is dead!");
				}
				else
				{
					check++;
				}
			}
			//done is ok
			int check2=0;
			while(check2==0)
			{	done=scan.next();
				String done_role=playerRole(done,initial_mafias,initial_villager,numberOfMafias,numberOfVillagers,joker);
				int done_index=findPlayer(done,initial_villager,numberOfVillagers);
				if(done_role.equals("user not found"))
				{
					System.out.println("user did not join!");
				}
				else if(done_role.equals("villager") && initial_villager[done_index].state==false)
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
					if(got_silenced.equals(""))
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
					check2++;
				}
			}
			
		}
		//lets assume every body voted
		int max_mafias_votes=0;
		int number_of_maxvotes=0;
		String max_mafias_name="";
		for (int i=0;i<numberOfMafias;i++)
		{
			if(initial_mafias[i].votee!=null)
			{
				String vrole=playerRole(initial_mafias[i].votee,initial_mafias,initial_villager,numberOfMafias,numberOfVillagers,joker);
				if(vrole.equals("villager"))
				{
					int indexv=findPlayer(initial_mafias[i].votee,initial_villager,numberOfVillagers);
					initial_villager[indexv].votes++;
					if(initial_villager[indexv].votes>max_mafias_votes)
					{
						max_mafias_name=initial_villager[indexv].getName();
						max_mafias_votes=initial_villager[indexv].votes;
						number_of_maxvotes=1;
					}
					else if(initial_villager[indexv].votes==max_mafias_votes)
					{
						number_of_maxvotes++;
					}
				}
				else if(vrole.equals("joker"))
				{
					joker.votes++;
					if(joker.votes>max_mafias_votes)
					{
						max_mafias_name=joker.getName();
						max_mafias_votes=joker.votes;
						number_of_maxvotes=1;
					}
					else if (joker.votes==max_mafias_votes) {
						number_of_maxvotes++;
					}
				}
			}
		}
			//lets see who gets killed

			if(number_of_maxvotes>1)
			{
				System.out.println("mafias tried to kill "+number_of_maxvotes+" players,but could't");
			}
			else
			{
				if(initial_villager[findPlayer(max_mafias_name,initial_villager,numberOfVillagers)].bulletproof && initial_villager[findPlayer(max_mafias_name,initial_villager,numberOfVillagers)].shot==0)
				{
					initial_villager[findPlayer(max_mafias_name,initial_villager,numberOfVillagers)].shot=1;
					System.out.println("mafias tried to kill "+max_mafias_name+"but couldn't");
				}
				else if(max_mafias_name.equals(saved))
				{
					System.out.println("mafias tried to kill "+max_mafias_name+"but doctor saved them!");
				}
				else 
				{
					System.out.println("mafias killed "+max_mafias_name);
					initial_villager[findPlayer(max_mafias_name,initial_villager,numberOfVillagers)].state=false;
					alives--;
					numberOfVillagers_alive--;
				}
				if(got_silenced.equals("")==false)
				{
					System.out.println(got_silenced+"is silenced!");
				}
			}
		//null kardan votee barye round jadid
		for (int i=0;i<numberOfMafias;++i) {
			initial_mafias[i].votee=null;
		}
		if(numberOfMafias_alive>=numberOfVillagers_alive)
		{
			System.out.println("Mafias WON!");
			game_state=false;
		}
		else if(numberOfMafias==0)
		{
			System.out.println("villagers WON!");
			game_state=false;
		}	

	}

}