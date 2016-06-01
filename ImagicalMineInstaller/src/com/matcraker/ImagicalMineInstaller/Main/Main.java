package com.matcraker.ImagicalMineInstaller.Main;

import java.io.File;
import java.io.IOException;

import com.matcracker.PMManagerServers.API.PluginInformation;
import com.matcracker.PMManagerServers.API.PluginStarter;
import com.matcracker.PMManagerServers.API.StatusAPI;
import com.matcracker.PMManagerServers.API.UtilityServersAPI;
import com.matcracker.PMManagerServers.installer.ManagerInstaller;
import com.matcracker.PMManagerServers.lang.BaseLang;
import com.matcracker.PMManagerServers.settings.PluginManager;
import com.matcracker.PMManagerServers.utility.Utility;
import com.matcracker.PMManagerServers.utility.UtilityColor;

public class Main implements PluginStarter, PluginInformation{
	
	public String title = "&3============================&6<&bImagicalMine Installer&6>&3============================&f";
	private String link = "http://imagicalcorp.github.io/ImagicalMine/service-download/ImagicalMine.phar";

	@Override
	public void execute() {
		menu();
	}
	
	private void menu(){
		Utility.cleanScreen();
		System.out.println(title);
		System.out.println("1- Download ImagicalMine");
		System.out.println("2- Install ImagicalMine");
		System.out.println("3- Back");
		int sel = Utility.readInt("Select option: ", null);
		
		try{
			if(sel == 1)
				download();
			
			if(sel == 2)
				install();
			
			if(sel == 3)
				PluginManager.plugMenu();
			
		}catch(IOException e){}
	}

	private void download() throws IOException {
		Utility.cleanScreen();
		System.out.println(title);
		System.out.println("1- Download latest version {MCPE: 0.14.3}");
		System.out.println("2- " + BaseLang.translate("pm.standard.back"));
		int sel = Utility.readInt("Select version: ", null);
		
		if(sel == 1){
			int server = 0;
			
			Utility.showServers();
			server = Utility.readInt("Select server: ", null);
			
			if(server <= UtilityServersAPI.getNumberServers()){
					System.out.println("Downloading latest phar...");
					Utility.downloadFile(link, "Utils");
					ManagerInstaller.renameDownloadedFile("ImagicalMine.phar", "IMAGICALMINE");
					StatusAPI.setStatus(BaseLang.translate("pm.status.download"), server);
					Utility.waitConfirm("ImagicalMine downloaded! Now install it!");
			}else
				Utility.waitConfirm("Select an avaiable server");
			
			download();
		}
		menu();
	}

	private void install() {
		Utility.cleanScreen();
		System.out.println(title);
		System.out.println("1- Install latest version {MCPE: 0.14.3}");
		System.out.println("2- " + BaseLang.translate("pm.standard.back"));
		int sel = Utility.readInt("Select version: ", null);
		
		if(sel == 1){
			int server = 0;
			
			Utility.showServers();
			server = Utility.readInt("Select server: ", null);
			
			if(server <= UtilityServersAPI.getNumberServers()){
				File imagical = new File("Utils" + File.separator + "PocketMine-MP_IMAGICALMINE.phar");
				if(imagical.exists()){
					if(UtilityServersAPI.checkServersFile("Path", "path_", server)){
						String pathContent = UtilityServersAPI.getPath(server);
						if(pathContent != null){
							System.out.println("Installing ImagicalMine...");
							try{
								ManagerInstaller.changeInstallationsFile(pathContent, "IMAGICALMINE");
							}catch (IOException e){
								e.printStackTrace();
							}
							StatusAPI.setVersion("ImagicalMine", server);
							Utility.waitConfirm("ImagicalMine installed!");
						}else
							Utility.waitConfirm(BaseLang.translate("pm.installer.pharNotFound"));
					}else
						Utility.waitConfirm(UtilityColor.COLOR_RED + BaseLang.translate("pm.errors.pathNotFound"));	
				}else
					Utility.waitConfirm("You don't download ImagicalMine! First download it!");
				
			}else
				Utility.waitConfirm("Select an avaiable server");
			
			 install();
		}
		menu();
	}

	@Override
	public void onDisable() {
		System.out.println(UtilityColor.COLOR_GREEN + this.getName() + " " + "v" + this.getVersion() + " is disabled successfully!");
		
	}

	@Override
	public void onEnable() {
		System.out.println(UtilityColor.COLOR_GREEN + this.getName() + " " + "v" + this.getVersion() + " is enabled successfully!");
		
	}

	@Override
	public String getAPIVersion() {
		return "1.1";
	}

	@Override
	public String getAuthor() {
		return "matcracker";
	}

	@Override
	public String getName() {
		return "ImagicalMine-Installer";
	}

	@Override
	public String getVersion() {
		return "1.0.1";
	}

}
