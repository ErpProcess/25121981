function  alertExt(titra,messagio,TYPIO){

    var ELY;
    var tit="";
    if(TYPIO=="1") {ELY= Ext.MessageBox.INFO;     tit=titra==""?"INFO":titra; }
    if(TYPIO=="2") {ELY= Ext.MessageBox.QUESTION; tit=titra==""?"QUESTION":titra; }
    if(TYPIO=="3") {ELY= Ext.MessageBox.WARNING;  tit=titra==""?"ATTENTION":titra; }
    if(TYPIO=="4") {ELY= Ext.MessageBox.ERROR;    tit=titra==""?"ERREUR":titra; }

 Ext.MessageBox.show({
           title:tit,
           msg: messagio ,
           width:300,
           buttons: Ext.Msg.OK,
           animEl: 'mb4',
           icon: ELY
});
}






function  mayBox_al(messagio,type){

var  TYPIO=Ext.MessageBox.INFO;
 Ext.MessageBox.show({
           title:'Enregistrement Valider',
           msg: messagio ,
           width:300,
           //buttons: Ext.MessageBox.YES,
           buttons: Ext.Msg.OK,
           animEl: 'mb4',
           icon: TYPIO
});
} 

 


$(document).ready(function () {
titreMessageBox="ERREUR";
if( MyMessageBoxo != undefined && MyMessageBoxo!=""){
	var iconMessageBox = Ext.MessageBox.INFO;
	var titreMessageBox="INFO";
	if(MyMessageBoxo.startsWith("ERREUR") ||   MyMessageBoxo.startsWith("ERROR") ){
		iconMessageBox = Ext.MessageBox.ERROR;
		titreMessageBox="ERREUR";
} 
 Ext.MessageBox.show({
           title:titreMessageBox,
           msg: MyMessageBoxo ,
           //buttons: Ext.MessageBox.YES,
           buttons:   Ext.Msg.OK ,
           width:600,
           animEl: "mb4",
           icon: iconMessageBox
});
}  
});