package jdo;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

@PersistenceCapable(detachable = "true")
public class PlayJDO {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key id;
	private String songId ;
	
	private String songName;
	private String songTitle;
	private String  songAlbumName;
	private String songLink;
	private String songImage;
	private String songType;
	private String songRelationTypeId;
	private String specialInstruction;
	private Text specialInstructionText;
	
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public String getSongTitle() {
		return songTitle;
	}
	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}
	public String getSongAlbumName() {
		return songAlbumName;
	}
	public void setSongAlbumName(String songAlbumName) {
		this.songAlbumName = songAlbumName;
	}
	public String getSongLink() {
		return songLink;
	}
	public void setSongLink(String songLink) {
		this.songLink = songLink;
	}
	public String getSongImage() {
		return songImage;
	}
	public void setSongImage(String songImage) {
		this.songImage = songImage;
	}
	public String getSongType() {
		return songType;
	}
	public void setSongType(String songType) {
		this.songType = songType;
	}
	public String getSongRelationTypeId() {
		return songRelationTypeId;
	}
	public void setSongRelationTypeId(String songRelationTypeId) {
		this.songRelationTypeId = songRelationTypeId;
	}
	

	public String getSongId() {
		return songId;
	}
	public void setSongId(String songId) {
		this.songId = songId;
	}
	public String getSpecialInstruction() {
		return specialInstruction;
	}
	public void setSpecialInstruction(String specialInstruction) {
		this.specialInstruction = specialInstruction;
	}
	public Text getSpecialInstructionText() {
		return specialInstructionText;
	}
	public void setSpecialInstructionText(Text specialInstructionText) {
		this.specialInstructionText = specialInstructionText;
	}
	
	
	

}
