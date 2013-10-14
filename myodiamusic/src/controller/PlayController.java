package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Helper.PlayHelper;



@Controller
@RequestMapping("/PlayController")
public class PlayController {
	private static final Logger mLogger = Logger.getLogger(PlayController.class.getName());
	PlayHelper playHelper = new PlayHelper();
	
	@RequestMapping("/addEntryToPlayJDO")
	public  void  addEntryToPlayJDO(HttpServletRequest  request , HttpServletResponse response){
		String responseString = null;
		try{
			responseString = playHelper.addSong(request);
		}
		catch(Exception e){
			mLogger.log(Level.SEVERE,"\n An error has occured while persisting : " + "\n", e);
		}
		try {
			response.sendRedirect("/pages/UploadSong.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@RequestMapping("/getSongList")
	public @ResponseBody HashMap<String, Object>  getSongList(HttpServletRequest  request , HttpServletResponse response){
		HashMap<String, Object> responseString = null;
		try{
			responseString = playHelper.getSongList(request,response);
		}
		catch(Exception e){
			mLogger.log(Level.SEVERE,"\n An error has occured while persisting : " + "\n", e);
		}
		
		return responseString;
	}
}
