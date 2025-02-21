package tech.techsmp.core.Join;
import tech.techsmp.core.Main;
import tech.techsmp.core.Join.PlayerPreJoin;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;


public class PlayerPostJoin implements Listener{

    @EventHandler
	public void postJoinEvent(PlayerLoginEvent e){
        Player p = e.getPlayer();
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
            
            @Override
                public void run() {
            	if(p.isOnline() && PlayerPreJoin.bedrockWhitelist.contains(p.getName())) {
            		p.setWhitelisted(true);
            		String wlList = "";
        			try {
        		    	File bedrockWl = new File("/home/container/plugins/TechSMP/bedrock_whitelist.yml");

        				Scanner scanner = new Scanner(bedrockWl);
        				while (scanner.hasNextLine()) {
        					String Line = scanner.nextLine();
        					if(!Line.toLowerCase().startsWith(p.getName())) {
        						wlList = wlList + Line + "\n";
        					}
        				}
        				scanner.close();
        			}
        			catch (FileNotFoundException exception) {
        				exception.printStackTrace();
        			}
        				try {
        				    FileWriter fw = new FileWriter("/home/container/plugins/TechSMP/bedrock_whitelist.yml", false); //the true will append the new data
        				    fw.write(wlList);//appends the string to the file
        				    fw.close();
        				}
        				catch(IOException ioe)
        				{
        					ioe.printStackTrace();
        				}
        			
            	}
		    	File ranks = new File("/home/container/plugins/TechSMP/ranks.yml");

            	try {
    				Scanner scanner = new Scanner(ranks);
    				while (scanner.hasNextLine()) {
    					String Line = scanner.nextLine();
    					if(Line.startsWith(p.getUniqueId().toString() + "|")) {
    						String rankName = "";
    						for(int i = p.getUniqueId().toString().length() + 1; i < Line.length(); i++) {
    							rankName = rankName + Line.charAt(i);
    						}
    	                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rank " + p.getName() +  " " + rankName);

    					}
    				}
    				scanner.close();


    			} catch (FileNotFoundException exception) {
    				exception.printStackTrace();
    			}

                /*if(p.getName().equalsIgnoreCase("theencomputers"))
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rank theencomputers owner");
                if(p.getName().equalsIgnoreCase("c1ff"))
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rank c1ff trusted");
                if(p.getName().equalsIgnoreCase("Admirable1915"))
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rank Admirable1915 trusted");
                if(p.getName().equalsIgnoreCase("SkottSmith91"))
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rank SkottSmith91 trusted");
                if(p.getName().equalsIgnoreCase("Vash3490"))
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rank Vash3490 trusted");*/
                if(!p.isWhitelisted()){
                    p.sendMessage("§eWelcome! It looks like you are not verified but that's okay!\n§ePlease Join out discord to get verified at §ldiscord.ttumc.tech\n§r§aRules: §7be civil, no greifing, no cheating");
                }
                }
            }, 10L);
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {

                @Override
                    public void run() {
                        p.setPlayerListHeader("§eWelcome to the §5§lTN Tech §7Minecraft server");
                        p.setPlayerListFooter("§6Griefing is bannable (we have block logging)\n§eJoin our discord: discord.ttumc.tech\n§aCommands: §7§o/help /tpa /wl /bedtp");
                        
                    }
                }, 10L);

    }
    
}
