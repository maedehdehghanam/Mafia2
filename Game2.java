public class Game2{
	public final int numberOfPlayers;
	public  Mafia[] initial_mafias=new Mafia[50];
	public 	Villager[] initial_villager=new Villager[50];
	public 	Joker joker=null;
	public String[] players_names=new String[numberOfPlayers];	
	public static int numberOfMafias=0;
	public static int numberOfVillagers=0;
	public static Day day=new Day;
	public static Night night=new Night;
	public static int numberOfVillagers_alive=0;
	public static int numberOfMafias_alive=0; 
	public Game2(int numberOfPlayers,String[] players_names)
	{
		this.numberOfPlayers=numberOfPlayers;
		for (int i=0;i<numberOfPlayers;++i)
		{
			this.players_names[i]=players_names[i];
		}
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

}