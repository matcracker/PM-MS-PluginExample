package com.PMMSPlugins.PluginTest;

import com.matcracker.PMManagerServers.API.PluginInformation;
import com.matcracker.PMManagerServers.API.PluginStarter;
import com.matcracker.PMManagerServers.API.StatusAPI;
import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.Utility.Utility;
import com.matcracker.PMManagerServers.Utility.UtilityColor;


public class Main implements PluginStarter, PluginInformation{
	@Override
	public void onDisable() { //When plugin is unloaded
		System.out.println(UtilityColor.COLOR_PURPLE + this.getName() + " successfully disabled!");
		
	}
	@Override
	public void onEnable() { //When plugin is loaded
		System.out.println(UtilityColor.COLOR_CYAN + this.getName() + " successfully enabled!");
		
	}
	@Override
	public String getAPIVersion() {
		return "1.0";
	}
	
	@Override
	public String getAuthor(){
		return "matcracker";
	}
	
	@Override
	public String getName() {
		return "Plugin Example";
	}
	
	@Override
	public String getVersion() {
		return "1.0";
	}
	
	@Override
	public void execute() { //When plugin is used
		Utility.cleanScreen();
		System.out.println(Utility.softwareName);
		System.out.println("---------------------<" + this.getName() + ">---------------------");
		System.out.println("&eThis plugin is made by &2" + this.getAuthor());
		System.out.println("&11- Show servers");
		System.out.println("&b2- Get version of servers");
		System.out.println("&a3- Back&f");
		int opt = Utility.readInt("Select option: ", null);
		
		/*
		 * Various API's use and color
		 */
		if(opt == 1){
			Utility.showServers();
			Utility.waitConfirm("Press ENTER to continue");
		}
		
		if(opt == 2){
			Utility.showServers();
			int server = Utility.readInt("Select server for know your installed version", null);
			System.out.println(UtilityServersAPI.getNameServer(server-1) + " = " + StatusAPI.getVersion(server-1));
			Utility.waitConfirm("Press ENTER to continue");
		}
			
		if(opt == 3)
			return;
		
		this.execute();
		
	}
	
}
