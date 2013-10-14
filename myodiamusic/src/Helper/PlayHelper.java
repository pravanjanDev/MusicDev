package Helper;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.PlayUtil;

import datamodel.PlayDatamodel;

public class PlayHelper {
	PlayDatamodel playModel = new PlayDatamodel();
	public String addSong(HttpServletRequest request){
	    String  responseString = null;
		HashMap<String ,String > songMap = new HashMap<String, String>();
		 try{
			 
			System.out.println("The song name "+request.getParameter("songTitle")); 
			songMap.put("songRelationTypeId",request.getParameter("songTitle"));
				
			songMap.put("songName", request.getParameter("songName"));
			songMap.put("songTitle", request.getParameter("songTitle"));
			songMap.put("songAlbumName", request.getParameter("songAlbumName"));
			songMap.put("songLink", request.getParameter("songLink"));
			songMap.put("songImage", request.getParameter("songImage"));
			songMap.put("songRelationTypeId", request.getParameter("songRelationTypeId"));
			responseString  = playModel.addSong(songMap);
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 return responseString;
	}
	public HashMap<String, Object> getSongList(HttpServletRequest request , HttpServletResponse response) {
		HashMap<String ,Object > songMap = new HashMap<String , Object>();
		try{
			songMap = playModel.getSongWithRelationTypeId(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return songMap;
	}

}
