import java.util.logging.Level;
import java.util.logging.Logger;

//=====================================================================
//Class:	vMinecraftChat
//Use:		Encapsulates all chat commands added by this mod
//Author:	nossr50, TrapAlice, cerevisiae
//=====================================================================
public class vminecraftChat {
    protected static final Logger log = Logger.getLogger("Minecraft");

	//=====================================================================
	//Function:	gmsg
	//Input:	String msg: The message to be broadcast to all players
	//Output:	None 
	//Use:		Outputs a message to everybody
	//=====================================================================
    public static void gmsg(String msg){
        for (Player p : etc.getServer().getPlayerList()) {
            if (p != null) {
                p.sendMessage(msg);
            }
        }
    }

	//=====================================================================
	//Function:	wordWrap
	//Input:	String msg: The message to be wrapped
	//Output:	String[]: The array of substrings 
	//Use:		Cuts the message apart into whole words short enough to fit
    //			on one line
	//=====================================================================
    public static String[] wordWrap(String msg){
    	//Split each word apart
    	String[] array = msg.split(" ");
    	//Create the output array
    	String[] out = new String[0];
    	
    	//While i is less than the length of the array of words
    	int i = 0;
    	while(i < array.length){
    		int len = 0;
    		int j = i;
    		//Loop through the words finding their length and increasing
    		//j, the end point for the sub string
    		while(len <= 316 && j < array.length)
    		{
    			len += msgLength(array[j]) + 4;
    			if( len <= 316)
    				j++;

    		}
    		String[] temp = new String[j - i];

    		//If it's not the end yet
    		if(j < array.length)
    		{
    			//Copy the words in the selection into a new array
        		System.arraycopy(array, i, temp, 0, j);

        		//Merge them and add them to the output array
    			String[] tempOut = new String[out.length + 1];
        		System.arraycopy(out, 0, tempOut, 0, out.length);
        		tempOut[tempOut.length - 1] = etc.combineSplit(0, temp, " ");
        		out = tempOut;
        		
    		}
    		else
    		{
    			//Merge the rest and add them to the output array
    			String[] tempOut = new String[out.length + 1];
        		System.arraycopy(out, 0, tempOut, 0, out.length);
        		tempOut[tempOut.length - 1] = etc.combineSplit(i, array, " ");
        		out = tempOut;
    		}
    		//Make the old front equal to the old end
    		i = j;
    	}
    	return out;
    }
    
	//=====================================================================
	//Function:	wordWrap
	//Input:	Player player: To get the player name
    //			String msg: The message to be wrapped
	//Output:	String[]: The array of substrings 
	//Use:		Cuts the message apart into whole words short enough to fit
    //			on one line
	//=====================================================================
    public static String[] wordWrap(Player player, String msg){
    	//Split each word apart
    	String[] array = msg.split(" ");
    	//Create the output array
    	String[] out = new String[0];
    	
    	//While i is less than the length of the array of words
    	int i = 0;
    	while(i < array.length){
    		int len = 0;
    		if(out.length == 0)
    			len = msgLength("<" + player.getName() + "> ");
    		int j = i;
    		//Loop through the words finding their length and increasing
    		//j, the end point for the sub string
    		while(len <= 316 && j < array.length)
    		{
    			len += msgLength(array[j]) + 4;
    			if( len <= 316)
    				j++;

    		}
    		String[] temp = new String[j - i];

    		//If it's not the end yet
    		if(j < array.length)
    		{
    			//Copy the words in the selection into a new array
        		System.arraycopy(array, i, temp, 0, j);

        		//Merge them and add them to the output array
    			String[] tempOut = new String[out.length + 1];
        		System.arraycopy(out, 0, tempOut, 0, out.length);
        		tempOut[tempOut.length - 1] = etc.combineSplit(0, temp, " ");
        		out = tempOut;
        		
    		}
    		else
    		{
    			//Merge the rest and add them to the output array
    			String[] tempOut = new String[out.length + 1];
        		System.arraycopy(out, 0, tempOut, 0, out.length);
        		tempOut[tempOut.length - 1] = etc.combineSplit(i, array, " ");
        		out = tempOut;
    		}
    		//Make the old front equal to the old end
    		i = j;
    	}
    	return out;
    }
    
    private static int msgLength(String str){
		int length = 0;
		for(int x = 0; x<str.length(); x++)
		{
			if("i;,.:|!".indexOf(str.charAt(x)) != -1)
			{
				length+=2;
			}
			else if("l'".indexOf(str.charAt(x)) != -1)
			{
				length+=3;
			}
			else if("tI[]".indexOf(str.charAt(x)) != -1)
			{
				length+=4;
			}
			else if("kf{}<>\"*()".indexOf(str.charAt(x)) != -1)
			{
				length+=5;
			}
			else if("hequcbrownxjmpsvazydgTHEQUCKBROWNFXJMPSVLAZYDG1234567890#\\/?$%-=_+&".indexOf(str.charAt(x)) != -1)
			{
				length+=6;
			}
			else if("@~".indexOf(str.charAt(x)) != -1)
			{
				length+=7;
			}
			else if(str.charAt(x)==' ')
			{
				length+=4;
			}
		}
		return length;
    }
    

    public static String rainbow(String msg){
    	String temp = "";
    	//The array of colors to use
		String[] rainbow = new String[] {Colors.Red, Colors.Rose,
				Colors.Yellow, Colors.Green, Colors.Blue,
				Colors.LightPurple, Colors.Purple};
		int counter=0;
		//Loop through the message applying the colors
		for(int x=0; x<msg.length(); x++)
		{
			temp+=rainbow[counter]+msg.charAt(x);
			
			if(msg.charAt(x)!=' ') counter++;
			if(counter==7) counter = 0;
		}
		return temp;
    }
	//=====================================================================
	//Function:	nameColor
	//Input:	Player player: The player to get name as color
	//Output:	String: The name colored 
	//Use:		Returns the colored name;
	//=====================================================================
    public static String nameColor(Player player){
        return player.getColor() + player.getName();
    }
    
	//=====================================================================
	//Function:	colorChange
	//Input:	char colour: The color code to find the color for
	//Output:	String: The color that the code identified 
	//Use:		Finds a color giving a color code
	//=====================================================================
	public static String colorChange(char colour)
	{
		String color = "";
		switch(colour)
		{
			case '0':
				color = Colors.Black;
				break;
			case '1':
				color = Colors.Navy;
				break;
			case '2':
				color = Colors.Green;
				break;
			case '3':
				color = Colors.Blue;
				break;
			case '4':
				color = Colors.Red;
				break;
			case '5':
				color = Colors.Purple;
				break;
			case '6':
				color = Colors.Gold;
					break;
			case '7':
				color = Colors.LightGray;
				break;
			case '8':
				color = Colors.Gray;
				break;
			case '9':
				color = Colors.DarkPurple;
				break;
			case 'a':
				color = Colors.LightGreen;
				break;
			case 'b':
				color = Colors.LightBlue;
				break;
			case 'c':
				color = Colors.Rose;
				break;
			case 'd':
				color = Colors.LightPurple;
				break;
			case 'e':
				color = Colors.Yellow;
				break;
			case 'f':
				color = Colors.White;
				break;
			case 'A':
				color = Colors.LightGreen;
				break;
			case 'B':
				color = Colors.LightBlue;
				break;
			case 'C':
				color = Colors.Rose;
				break;
			case 'D':
				color = Colors.LightPurple;
				break;
			case 'E':
				color = Colors.Yellow;
				break;
			case 'F':
				color = Colors.White;
				break;
			default:
				color = Colors.White;
				break;
		}
		return color;
	}
	
	//=====================================================================
	//Function:	adminChat
	//Input:	Player player: The player talking
    //			String message: The message to apply the effect to
	//Output:	boolean: If this feature is enabled
	//Use:		Sends messages only to admins
	//=====================================================================
	public static boolean adminChat(Player player, String message){
		
		//Check if the player can use this feature
		if(player.isAdmin() || player.canUseCommand("/adminchat"))
		{
			//Special formatting for adminchat {Username}
	        String adminchat = Colors.DarkPurple + "{" + nameColor(player)
	        +  Colors.DarkPurple +"}" + Colors.White + " ";
	        
	        String[] msg = wordWrap(player, message.substring(1, message.length()));
	        
	        //Get the player from the playerlist to send the message to.
			for (Player p: etc.getServer().getPlayerList()) {
				
				//If p is not null
				if (p != null) {
					
					//And if p is an admin or has access to adminchat
					if (p.isAdmin() || (p.canUseCommand("/adminchat"))) {

						//Output the first line
						p.sendMessage(adminchat + msg[0]);
						
						//Get the rest of the lines and display them.
						String[] tempOut = new String[msg.length - 1];
						System.arraycopy(msg, 1, tempOut, 0, tempOut.length);
						for(String str: tempOut)
							p.sendMessage(str);
					}
				}
			}

		    //So you can read adminchat from the server console
			log.log(Level.INFO, "@" + "<" + nameColor(player)
					+  Colors.White +"> " + message); 
			return true;
		}
		return false;
	}

	//=====================================================================
	//Function:	quote
	//Input:	Player player: The player talking
    //			String message: The message to apply the effect to
	//Output:	boolean: If this feature is enabled
	//Use:		Displays a message as a quote
	//=====================================================================
	public static boolean quote(Player player, String message)
	{
		//Format the name
		String playerName = "<" + nameColor(player) + Colors.White +"> ";
		if(vminecraftSettings.getInstance().greentext()) {
			//Log the chat
			log.log(Level.INFO, "<"+player.getName()+"> "+message);
	        
			//Get the multi line array
	        String[] msg = wordWrap(player, message);

			//Output the first line
			gmsg( playerName + Colors.LightGreen + msg[0]);
			
			//Get the rest of the lines and display them.
			String[] tempOut = new String[msg.length - 1];
			System.arraycopy(msg, 1, tempOut, 0, tempOut.length);
			for(String str: tempOut)
				gmsg(Colors.LightGreen + str);
			return true;
		}
		return false;
	}

	//=====================================================================
	//Function:	rage
	//Input:	Player player: The player talking
    //			String message: The message to apply the effect to
	//Output:	boolean: If this feature is enabled
	//Use:		Displays a message in red
	//=====================================================================
	public static boolean rage(Player player, String message)
	{
		//Format the name
		String playerName = "<" + nameColor(player) + Colors.White +"> ";
		if (vminecraftSettings.getInstance().FFF()) {
			log.log(Level.INFO, "<"+player.getName()+"> "+message);
	        
			//Get the multi line array
	        String[] msg = wordWrap(player, message);

			//Output the first line
			gmsg( playerName + Colors.Red + msg[0]);
			
			//Get the rest of the lines and display them.
			String[] tempOut = new String[msg.length - 1];
			System.arraycopy(msg, 1, tempOut, 0, tempOut.length);
			for(String str: tempOut)
				gmsg(Colors.Red + str);
			return true;
		}
		return false;
	}
    
    //=====================================================================
	//Function:	quakeColors
	//Input:	Player player: The player talking
    //			String message: The message to apply the effect to
	//Output:	boolean: If this feature is enabled
	//Use:		Displays a message in red
	//=====================================================================
	public static boolean quakeColors(Player player, String message)
	{
		//Format the name
		String playerName = "<" + nameColor(player) + Colors.White +"> ";
		if(vminecraftSettings.getInstance().quakeColors() && message.length()>2) {

			//Log the chat
			log.log(Level.INFO, "<"+player.getName()+"> "+message);
			
			//Get the multi line array
	        String[] msg = wordWrap(player, message);
	        //Apply colors to the lines
			applyColors(msg);

			//Output the first line
			gmsg( playerName + msg[0]);
			//Get the rest of the lines and display them.
			String[] tempOut = new String[msg.length - 1];
			System.arraycopy(msg, 1, tempOut, 0, tempOut.length);
			for(String str: tempOut)
				gmsg(str);

			//Loop through the string finding the color codes and inserting them
			return true;
		}
		return false;
	}

    
    //=====================================================================
	//Function:	applyColors
	//Input:	String[] message: The lines to be colored
	//Output:	String[]: The lines, but colorful
	//Use:		Colors each line
	//=====================================================================
	private static String[] applyColors(String[] message)
	{

		//The color to start the line with
		String recentColor = Colors.White;
		
		//Go through each line
		int counter = 0;
		for(String msg: message)
		{	
			//Start the line with the most recent color
			String temp = recentColor;
			
			//Loop through looking for a color code
			for(int x = 0; x< msg.length(); x++)
			{
				if(msg.charAt(x)=='^' && x != msg.length() - 1)
				{
					//Set the most recent color to the new color
					recentColor = vminecraftChat.colorChange(msg.charAt(x+1));
					temp += recentColor;
					x++;
				}
				else{
					temp += msg.charAt(x);
				}
			}
			//Replace the message with the colorful message
			message[counter] = temp;
			counter++;
		}
		return message;
	}
}