package datamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JDOCursorHelper;


import jdo.PlayJDO;

import util.PersistenceManagerUtil;
import util.PlayUtil;

public class PlayDatamodel {
	private static final Logger mLogger = Logger.getLogger(PlayDatamodel.class.getName());
	
	public HashMap<String, Object> getSongWithRelationTypeId(HttpServletRequest  request , HttpServletResponse response ){
		String relationTypeId = "fb739f34-114a-475b-9fc5-fa2901a709eb";
		HashMap<String ,Object > songMap = new HashMap<String , Object>();
		String cursorStringSonglist =null;
		cursorStringSonglist = (String) request.getSession().getAttribute("cursorStringSonglist");
		System.out.println("The CURSOR VALUE "+cursorStringSonglist);
		if(cursorStringSonglist==null){
			mLogger.log(Level.SEVERE,"the logger inside is ");
			cursorStringSonglist="null";
			//cursorStringSonglist = request.getParameter("cursorStringSonglist");
		}
		try {
			
			songMap  = getpaginatedResultWithSongRelationTypeId(request ,relationTypeId,cursorStringSonglist);
		}
		catch(Exception e){
			mLogger.log(Level.SEVERE,
					"\n An error has occured while persisting : " + "\n", e);
		}
		return  songMap ;
	
	}
	
	private HashMap<String, Object> getpaginatedResultWithSongRelationTypeId(HttpServletRequest request ,String relationTypeId,String cursorStringSonglist) {
		Query query1 = null;
		Cursor cursor=null;
		PersistenceManager pm = null;
		HashMap<String ,Object > songMap = new HashMap<String , Object>();
		ArrayList<PlayJDO> resultSongList = new ArrayList<PlayJDO>();
		
		try {
			pm = PersistenceManagerUtil.getPersistanceManager();
			Query querySongRelation = pm.newQuery(PlayJDO.class,
					" songRelationTypeId == '" + relationTypeId.trim()+ "'");
			querySongRelation.setRange(0, 4);
			
			if(!cursorStringSonglist.equalsIgnoreCase("null")){
				System.out.println("inside if cursor having value having query value is::::");
				cursor = Cursor.fromWebSafeString(cursorStringSonglist);
				Map<String, Object> extensionMap = new HashMap<String, Object>();
				extensionMap.put(JDOCursorHelper.CURSOR_EXTENSION, cursor);
				querySongRelation.setExtensions(extensionMap);
			}
			List songList = (List<PlayJDO>) querySongRelation.execute();
			System.out.println("Song list size is "+songList);
			ArrayList<PlayJDO> tempSongList = (ArrayList<PlayJDO>) pm.detachCopyAll(songList);
			cursor = JDOCursorHelper.getCursor(songList);
			System.out.println("The cursor value is "+cursor);
			cursorStringSonglist = cursor.toWebSafeString();
			if(tempSongList.size()>0 && tempSongList!=null){
				for(int playIndex=0 ;playIndex <tempSongList.size() ;playIndex ++){
					
					PlayJDO playJDO = tempSongList.get(playIndex);
					songMap.put(playJDO.getSongId(), playJDO);
							
				}
				System.out.println("The CURSOR VALUE "+cursorStringSonglist);
				
				request.getSession().setAttribute("cursorStringSonglist", cursorStringSonglist);
				 System.out.println((String) request.getSession().getAttribute("cursorStringSonglist"));
			}
		}
		catch(Exception e){
			mLogger.log(Level.SEVERE,
					"\n An error has occured while persisting : " + "\n", e);
		}
		
		return songMap;
	}

	
	

	public String addSong(HashMap<String,String> songMap) {
		PlayJDO playJDO = new PlayJDO();
		PersistenceManager pm = null;
		
		UUID key = UUID.randomUUID();
		
		
	    String songId = key.toString();
		try{
			pm = PersistenceManagerUtil.getPersistanceManager();
			playJDO.setSongAlbumName(songMap.get("songAlbumName"));
			playJDO.setSongId(songId);
			playJDO.setSongImage(songMap.get("songImage"));
			playJDO.setSongLink(songMap.get("songLink"));
			playJDO.setSongTitle(songMap.get("songTitle"));
			playJDO.setSongType(songMap.get("songType"));
			playJDO.setSongName(songMap.get("songName"));
			playJDO.setSongRelationTypeId(PlayUtil.getResource().getString("OldHindiFilmSong"));
			pm.makePersistent(playJDO);
		}
		
		catch(Exception e){
			mLogger.log(Level.SEVERE,
					"\n An error has occured while persisting : " + "\n", e);
			
		}
		return "UploadSong" ;
		
		
		
	}	

}
