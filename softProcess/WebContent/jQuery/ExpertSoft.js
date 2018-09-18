$(document).ready(function(){
		$("a.tab").click(function () {
			// switch all tabs off
			
			 
			
			if($(this).attr("class")=="tab active")
			return;
			
			$(".active").removeClass("active");
			// switch this tab on
			
			$(this).addClass("active"); 
			// slide all content up
			
			$(".content").slideUp();
			// slide this content up
			var content_show = $(this).attr("title");
			$("#"+content_show).slideDown(); 
			
		});
});

function aHrefRedirect(rforaire,path) {
        Ext.get(foraire).on('click', function(){
        Ext.MessageBox.show({
           title: 'Please wait',
           msg: 'Loading items...',
           progressText: 'Initializing...',
           width:300,
           progress:true,
           closable:false,
           animateTarget: foraire
       });

       // this hideous block creates the bogus progress
       var f = function(v){
            return function(){
                if(v == 12){
                    Ext.MessageBox.hide();
                    Ext.example.msg('Done', 'Your fake items were loaded!');
                }else{
                    var i = v/11;
                    Ext.MessageBox.updateProgress(i, Math.round(100*i)+'% completed');
                }
           };
       };
       for(var i = 1; i < 13; i++){
           setTimeout(f(i), i*500);
       }
    });
     
}


function doActionLoad(rforaire) {
        Ext.get(rforaire).on('click', function(){
        Ext.MessageBox.show({
           title: 'Please wait',
           msg: 'Loading items...',
           progressText: 'Initializing...',
           width:300,
           progress:true,
           closable:false,
           animateTarget: rforaire
       });

       // this hideous block creates the bogus progress
       var f = function(v){
            return function(){
                if(v == 12){
                    Ext.MessageBox.hide();
                    Ext.example.msg('Done', 'Your fake items were loaded!');
                }else{
                    var i = v/11;
                    Ext.MessageBox.updateProgress(i, Math.round(100*i)+'% completed');
                }
           };
       };
       for(var i = 1; i < 13; i++){
           setTimeout(f(i), i*500);
       }
    });
     
}

function doActionSave(thevard) {

Ext.get(thevard).on('click', function(){
        Ext.MessageBox.show({
           msg: 'Saving your data, please wait...',
           progressText: 'Saving...',
           width:300,
           wait:true,
           waitConfig: {interval:200},
           icon:'ext-mb-download', //custom class in msg-box.html
           animateTarget:thevard
       });
        setTimeout(function(){
            Ext.MessageBox.hide();
            Ext.example.msg('Done', 'Your fake data was saved!');
        }, 1000);
  });

}

function doActionSaveParam(thevard,vario) {

Ext.get(thevard).on('click', function(){
        Ext.MessageBox.show({
           msg: 'Saving your data, please wait...',
           progressText: 'Saving...',
           width:300,
           wait:true,
           waitConfig: {interval:200},
           icon:'ext-mb-download', //custom class in msg-box.html
           animateTarget:thevard
       });
        setTimeout(function(){
            Ext.MessageBox.hide();
            Ext.example.msg('Done', 'Your fake data was saved!');
        }, vario);
  });

}

$(function() {
	    var menu_ul = $('.menu > li > ul'),
	           menu_a  = $('.menu > li > a');
	    //menu_ul.hide();	
	   /* menu_a.click(function(e) {
	        e.preventDefault();
	        if(!$(this).hasClass('active')) {
	            menu_a.removeClass('active');
	            menu_ul.filter(':visible').slideUp('normal');
	            $(this).addClass('active').next().stop(true,true).slideDown('normal');
	        } else {
	            $(this).removeClass('active');
	            $(this).next().stop(true,true).slideUp('normal');
	        }
	    });*/
	
	});

	
	