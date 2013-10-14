
var songList = null ;

function playFavSong(love){
	//alert("i am here "+love);
	callSameCatagorySong(love);

	

}

function callSameCatagorySong(love){	
	
	
	alert("I  am in same catagory"+love);
	$.ajax({type: 'GET', url:'/PlayController/getSongList' , success: function(data){
		
		
		if(data!=null){
			songList = null;
			songList=eval('(' + data + ')');	    	
			console.log("i have got the information:"+songList);
			structingSongInRowWithJtable(songList);
	    }
      }
   });
  }
	
	


	function structingSongInRowWithJtable(songDataList){
		var newRow = new Array();
		console.log("i am in StructuringinJtableBatchEventand found list is ::::"+songDataList)
		songDataTable = $('#songTable').dataTable( {
		   "bProcessing": true,		
			"bFilter": false,
		 	"sPaginationType": "full_numbers", 
			"bLengthChange": false,
			"bInfo": true,
			"bDestroy":true,
			"bSort":false,
			"aaSorting": [[ 0, "asc" ]],
			"bJQueryUI": false,
			"bAutoWidth" : false,
		   "aoColumns" :	[ 	
			        
		                	    {"sWidth": "50px"},
			                	{ "sWidth": "350px" },   
			                	{ "sWidth": "350px" },
			                	
			                	{ "sWidth": "350px" },
			                	{ "sWidth": "200px" },
		                	    {"sWidth": "50px"}

							]
		});
	
		songDataTable.fnClearTable();
		var dataIndex = 0;
		
	   $.each(songDataList, function(index, songDataList) {
		  
		   newRow[0]="<input name='sharebutton' type='button' id='" + songDataList.songId + "' value='shareWithSocial'onclick>";
		  
		   var songName = songDataList.songName;
		   newRow[1] =songName;
				
		   var songTitle= songDataList.songTitle;
		   newRow[2]=songTitle;
			
		   var  songAlbumName = songDataList.songAlbumName;
		   newRow[3]=songAlbumName;
		   
		   var songImage = songDataList.songImage;
		   newRow[4]=songImage;

		   newRow[5]="<input name='PlayButton' type='button' id='" + songDataList.songId + "' value='Play' onclick='playThisNow(\""+songDataList.songId+"\", " + dataIndex + ")'>";

		  
		   songDataTable.fnAddData(newRow, true); 				
		   dataIndex ++;
	});
	   
	}
	
	
	function playThisNow(playId, dataIndex){
		var playObject = null ;
		$('#player').show();
		
		var audio = document.getElementById("audio1");
		for(var index in songList){
			if(songList[index].songId == playId){
				playObject = songList[index];
				audio.src=playObject.songLink;
				audio.play();
			
			}
			
		}
		
		
	}
	
	