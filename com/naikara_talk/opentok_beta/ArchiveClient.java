package com.naikara_talk.opentok_beta;

import java.util.HashMap;
import java.util.Map;

import com.naikara_talk.opentok.api.OpenTokSDK;
import com.naikara_talk.opentok.exception.OpenTokException;


public class ArchiveClient {

	//use the sdk to generate sessionId and token
	public static final String API_KEY = "100";
	public static final String API_SECRET = "19f149fdf697474f915f13de40e0ad53";
	private static String archiveId;
	private static String command, sessionId, archiveNum, name;

	//To do- generate a sessionId instead of passing it in

	public static void main(String[] args) {
    	//  String s = args[0];

		//If you want to use this to create a sessionId then pass in argument createSessionId.
		//Not necessary for now, session should already be up and running to even start archiving.
	/*	final OpenTokSDK api = new OpenTokSDK( API_Config.API_KEY,API_Config.API_SECRET );
		try {
			sessionId = createSession(api);
		} catch (OpenTokException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

    	  if(args.length > 3){
    		  System.out.println("invalid number of arguments, should not exceed 3");
    		  return;
    	  }
    	  else {
    		  if ( !(args[0].equals("listall"))){
    			  command = args[0];
    			  sessionId = args[1];
    			  name = args[2];
    		  }
    		  else{
    			  command = args[0];
    			  sessionId = args[1];

    		  }
    	  }
    	  System.out.println("Arguments are " + command + " " + sessionId + " " + archiveNum);
    	  ArchiveHelper helper = new ArchiveHelper(API_KEY, API_SECRET);
    	  helper.createClient(command, sessionId, name);

    	  if(command.equals("start")){
    		  helper.startArchiving(sessionId, name);
    	  }
    	  if(command.equals("stop")){
    		 // helper.stopArchiving(sessionId, archiveId);
    		  helper.stopArchiving(sessionId, name);
    	  }
    	  if(command.equals("listall")){
    		  helper.listAllArchives(sessionId);
    	  }
    	  if(command.equals("list")){
    		  //helper.listArchive(sessionId, archiveId);
    		  helper.listArchive(sessionId, name);
    	  }
    	  if(command.equals("delete")){
    		  //helper.deleteArchive(sessionId, archiveId);
    		  helper.deleteArchive(sessionId, name);
    	  }

	}

	 private static String createSession( OpenTokSDK api ) throws OpenTokException, Exception {
	    	Map<String,String> props = new HashMap<String, String>();
	        props.put( "p2p.preference", "enable" );
	        String sessionId = api.create_session( null, props ).getSessionId();
	        System.out.println("The session id generated is " + sessionId);
	        return sessionId;

	    }
}

           //************************************************

