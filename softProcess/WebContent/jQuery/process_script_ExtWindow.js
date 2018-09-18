

function windowExt(Urlwin,titlewin,fnHide) {
      var FFF;
	  var TTTTT_PICASSSSO = new Ext.Window({
		width        :1200,
        autoShow     :true, 
        height       :500,
        maximizable  :true,
        minimizable  :true,
        autoScroll   :true,
        maxHeight    :500,
        border       :true,
        closeAction  :'hide',
        modal        :true,
        session      :true,
        iconCls      :'information', 
        layout       :'fit',
        closable     : true,
        title:titlewin, 
		autoLoad     :{  
  				 url : Urlwin, 
   				 scripts: true  
   				      },
		listeners     :{
		
                 'close':function(win){
                         alert('bye');
                  },
                 'hide':function(win){
                 //alert();
                  TTTTT_PICASSSSO.destroy();
                  TTTTT_PICASSSSO=FFF;
                  }
          }
		}).show();						
}




 
  


var  TTTTT_HELP;
function doGetHelp() {
if(!TTTTT_HELP){
  TTTTT_HELP = new Ext.Window({
  width        : 1200,
  height       : 500,
  maximizable: true,
  minimizable :true,
  maxHeight: 500,
  modal   : true,
  closeAction: 'hide',
  items: {
            xtype: 'component',
            autoEl: {
                tag: 'iframe',
                style: 'height: 100%; width: 100%; border: none',
                src: path_Rootac+'/temp/help.pdf'
            }
        },
  title        : 'help',
  border       : true,
  closable: true,
  listeners: {close:function(){ doLoadTraceData(data);} }
}).show();
}else{
  TTTTT_HELP.show();
}
} 





