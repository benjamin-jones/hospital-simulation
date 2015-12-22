import java.util.ArrayList;


public class UpgradeManager 
{
	ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
	
	//upgrade counters and flags
	static int xtratime_count = 0;		//patient hp increases giving the user more time to diagnose
	static int superdoc_count = 0;		//bonus points for quick diagnoses
	static int secondchance_count = 0;	//second changed at failed diagnoses
	static int mal_ins_count = 0;		//malpractice insurance; prevent negative effects from failed diagnoses
	static int multisym_count = 0; 		//patients begin with multiple symptoms
	static int stable_count = 0; 		//patients hp stops decreasing for a certain period of time	
	static boolean use_xtratime = false;		//patient hp increases giving the user more time to diagnose
	static boolean use_superdoc = false;		//bonus points for quick diagnoses
	static boolean use_secondchance = false;	//second changed at failed diagnoses
	static boolean use_mal_ins = false;		//malpractice insurance; prevent negative effects from failed diagnoses
	static boolean use_multisym = false; 		//patients begin with multiple symptoms
	static boolean use_stable = false; 		//patients hp stops decreasing for a certain period of time
	
	static int upgrade_count[] = {0, 0, 0, 0, 0, 0};
	static boolean use_upgrade[] = {false, false, false, false, false, false};
	
	//temporary fix
	static int upgrade_cost[] = {500,500,500,500,500,1500};
	static String upgrade_name[] = {"Extra Time",
									"Quick Diagnoses Bonus",
									"Second Chance",
									"Malpractice Insurance",
									"Multi-symptoms",
									"Stable"};

	public UpgradeManager()
	{
		int cost = 500;
		
		upgrades.add(new Upgrade("Extra Time",cost));
		upgrades.add(new Upgrade("Bonus Points for Quick Diagnoses",cost));
		upgrades.add(new Upgrade("Second Chance at failed diagnoses",cost));
		upgrades.add(new Upgrade("Malpractice Insurance",cost));
		upgrades.add(new Upgrade("Multi-Symptoms",cost));
		upgrades.add(new Upgrade("Stable",cost));
	}
}
