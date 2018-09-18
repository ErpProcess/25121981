<%@    include file="/Aceuil/esProcess.jsp"     %>
<script  type="text/javascript">
$(document).ready(function (){
LoadAutoCompletAjax("paycod","paylib","usr_ville","listCountry");
LoadAutoCompletAjax("soc_id","soc_lib","pk_etab.etab_id","listSocioTa");
LoadAutoCompletAjax("prf_id","prf_libelle","usr_etat","listProfile_for_utlisateur");

LoadOtherAutocompletesAjax("soc_id","i$_ACT_LOAD_ETAB","pk_etab.etab_id","etab_lib","usr_obs");

selectOptionvalue('usr_sexe' ,'${detailBean.usr_sexe}') ;
checkedRadioGroup('usr_cath' ,'${detailBean.usr_cath}');
selectOptionvalue('usr_piece','${detailBean.usr_piece}');
selectOptionvalue('usr_etat' ,'${detailBean.usr_etat}') ;
selectOptionvalue('usr_type' ,'${detailBean.usr_type}') ;
selectOptionvalue('usr_civil','${detailBean.usr_civil}');
selectOptionvalue('usr_lang' ,'${detailBean.usr_lang}');          
});
</script>
<style>
.tableStyleContent{
width: 97%;margin-top: 1.2%;margin-left: 2%;margin-bottom: 2%;margin-right: 8%;background:none;
} 
</style>
<ext:body>
  <ext:panel  renderTo="ThePageJsp"        bodyStyle="background: none;"        border="false"               >
    <table    class="tableStyleContent"    cellpadding="5" cellspacing="10"     border="0"  id="tblData"     >
      <tr>
        <td width="100%" colspan="6" height="20px;"  align="center"    style="border: 0.7px double;padding-bottom: 10px;">
          <c:forEach var="beanCath" varStatus="soduter" items="${listUsr_cath}">
            <input required type="radio"    name="usr_cath"   value="${beanCath.data_id}"   >
            &nbsp;
            <label>${beanCath.data_libelle}</label>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </c:forEach>
        </td>
      </tr>
      
      <!-- ******************************************************************************************************************************************** -->
      <tr>  
        <td width="10%" ><label>${usr_nom} </label></td>
        <td width="29%" ><input id="usr_nom"  name="usr_nom"   libre   type="text"     size="30"   nextElement="usr_pre"   value="${detailBean.usr_nom}"    autofocus   required   />
        </td>
        <td width="8%"><label >${usr_pre} </label></td>
        <td width="27%"><input id="usr_pre" name="usr_pre"      type="text"     size="35"  nextElement="usr_date_naiss"   value="${detailBean.usr_pre}"                required  />
        </td>
        <td width="10%"><label >${usr_date_naiss} </label></td>
        <td  width="20%" >
        <script type="text/javascript">
        
         
        
        $(document).ready(function () {
        $(":input[type=datepicker]").each(function (cnt, item) {  
	     var ELTsXD=$(item).val();  
	     
	     
	    
	    
	     <fmt:formatDate  pattern="dd/MM/yyyy"   value="${detailBean.usr_date_naiss}"    var="dateConverti"/>
	 

	 $(item).val("${dateConverti}");
	 
});
});
        </script>
       
        <input id="usr_date_naiss"   name="usr_date_naiss"   type="datepicker"  nextElement="usr_login"  value="${detailBean.usr_date_naiss}"       required   />
        </td>
      </tr>
      <!-- ******************************************************************************************************************************************** -->
      <tr> 
      
          <td width="11%"><label>Login</label></td>
        <td ><input id="usr_login" name="usr_login" type="text"    maxlength="4"    nextElement="usr_pwd"  value="${detailBean.usr_login}"   size="10"  required    />
        </td>
        
        <td  ><label>${usr_pwd}</label></td>
        <td  ><input id="usr_pwd" name="usr_pwd" type="password"    nextElement="usr_pwd2"     value="${detailBean.usr_pwd}"   size="15"     required  />
        </td>
        <td  ><label >${usr_pwd}</label></td>
        <td ><input id="usr_pwd2" name="usr_pwd2" type="password"    nextElement="usr_photo"   value="${detailBean.usr_pwd}"   size="15"    required  onblur="check(this)"   />
        </td>
        
      
        <!-- <td width="11%"><label> ${usr_photo}</label></td>
        <td ><input id="usr_photo" name="usr_photo" type="text"      nextElement="usr_piece"  value="${detailBean.usr_photo}"   size="10"    />
        </td>
         -->
      </tr>
      <!-- ******************************************************************************************************************************************** -->
      <tr>
        <td  ><label>${usr_piece}</label></td>
        <td  colspan="5"><table>
            <tr>
              <td  ><select  id="usr_piece" name="usr_piece"  nextElement="usr_num_piece"     required >
                  <option  value=""      >-------</option>
                  <c:forEach var="typ_piecebean"   items="${listUsr_piece}">
                    <option  value="${typ_piecebean.data_id}">${typ_piecebean.data_libelle}</option>
                  </c:forEach>
                </select>
              </td>
              <td ><label >${usr_num_piece}</label></td>
              <td ><input id="usr_num_piece" name="usr_num_piece"   nextElement="paycod"    type="number"  required value="${detailBean.usr_num_piece}" size="12" maxlength="10" />
              </td>
            </tr>
          </table></td>
      </tr>
      <!-- ******************************************************************************************************************************************** -->
      <tr>
        <td  ><label>${paycod}</label></td>
        <td  ><table>
            <tr>
              <td><input id="paycod" name="paycod"     type="text"  nextElement="usr_ville"       value="${detailBean.paycod}"            size="10" /></td>
              <td><input id="paylib" name="paylib"     type="text"  nextElement="usr_ville"       value="${detailBean.bean_pays.paylib}" size="29" />
              </td>
            </tr>
          </table></td>
        <td ><label >${usr_ville}</label></td>
        <td ><table>
            <tr>
              <td><input id="usr_ville" name="usr_ville" type="text"     nextElement="code_postal"     value="${detailBean.usr_ville}" size="15"   /></td>
              <td>${code_postal}</td>
              <td><input id="code_postal" name="code_postal" type="number"  nextElement="usr_adr"    value="${detailBean.code_postal}" size="5" style="width: 80px;"  maxlength="5" /></td>
            </tr>
          </table></td>
        <td ><label > </label></td>
        <td ></td>
      </tr>
      <!-- ******************************************************************************************************************************************** -->
      <tr>
        <td   >${usr_adr}</td>
        <td  colspan="5" ><textarea id="usr_adr" name="usr_adr"     nextElement="usr_sexe"    cols="35"  rows="3" >${detailBean.usr_adr}</textarea>
        </td>
      </tr>
      <!-- ******************************************************************************************************************************************** -->
      <tr>
        <td  ><label>${usr_sexe}</label></td>
        <td  ><select  id="usr_sexe" name=""    nextElement="usr_civil"    >
            <option  value=""      >-------</option>
            <c:forEach var="usr_sexeBean"   items="${listUsr_sexe}">
              <option  value="${usr_sexeBean.data_id}">${usr_sexeBean.data_libelle}</option>
            </c:forEach>
          </select>
        </td>
        <td ><label >${usr_civil}</label></td>
        <td ><select  id="usr_civil" name="usr_civil"    nextElement="usr_lang"   >
            <option  value=""      >-------</option>
            <c:forEach var="listUsr_civilBean"   items="${listUsr_civil}">
              <option  value="${listUsr_civilBean.data_id}">${listUsr_civilBean.data_libelle}</option>
            </c:forEach>
          </select>
        </td>
        <td ><label >${usr_lang}</label></td>
        <td ><select  id="usr_lang" name="usr_lang"   nextElement="usr_tel"  >
            <c:forEach var="lanGueBean"   items="${listLanG}">
              <option  value="${lanGueBean.lang_id}">${lanGueBean.lang_libelle}</option>
            </c:forEach>
          </select>
        </td>
      </tr>
      <!-- ******************************************************************************************************************************************** -->
      <tr>
        <td  ><label>${usr_tel}</label></td>
        <td  ><input id="usr_tel" name="usr_tel" type="number" value="${detailBean.usr_tel}"    nextElement="usr_gsm"  />
        </td>
        <td ><label >${usr_gsm}</label></td>
        <td ><input id="usr_gsm" name="usr_gsm" type="number" value="${detailBean.usr_gsm}"     nextElement="usr_mail" />
        </td>
        <td  ><label>${usr_mail}</label></td>
        <td  ><input id="usr_mail" name="usr_mail" type="email" value="${detailBean.usr_mail}"     nextElement="usr_type"   />
        
        </td>
      </tr>
      <!-- ******************************************************************************************************************************************** -->
      <tr>
        <td  ><label>${usr_type} </label></td>
        <td  ><select  id="usr_type" name="usr_type"  nextElement="prf_id"   >
            <option  value=""      >-------</option>
            <c:forEach var="listUsr_typeBean"   items="${listUsr_type}">
              <option  value="${listUsr_typeBean.data_id}">${listUsr_typeBean.data_libelle}</option>
            </c:forEach>
          </select>
        </td>
        <td ><label >${prf_id}</label></td>
        <td >
        <input id="prf_id" name="pfrBean.prf_id" type="text"     value="${detailBean.pfrBean.prf_id}"      size="7"               maxlength="4"    nextElement="usr_etat"     />
        <input id="prf_libelle" name="pfrBean.prf_libelle" type="text"     value="${detailBean.pfrBean.prf_libelle}"     size="22"        nextElement="usr_etat"     />
        </td>
        <td  ><label>${usr_etat} </label></td>
        <td><select  id="usr_etat" name="usr_etat"     nextElement="soc_id"    >
            <c:forEach var="listUsr_etatBean"   items="${listUsr_etat}">
              <option  value="${listUsr_etatBean.data_id}">${listUsr_etatBean.data_libelle}</option>
            </c:forEach>
          </select>
        </td>
      </tr>
      <!-- ******************************************************************************************************************************************** -->
      <tr>
        <td  ><label>${soc_id}</label></td>
        <td  ><input id="soc_id"   name="etab_bean.pk_etab.soc_bean.soc_id"     type="text"  size="7"   value="${detailBean.etab_bean.pk_etab.soc_bean.soc_id}"           maxlength="3"       />
              <input id="soc_lib"  name="soc_lib"    type="text"             value="${detailBean.etab_bean.pk_etab.soc_bean.soc_lib}"  maxlength="3"      />
        </td>
        <td ><label >${etab_id}</label></td>
        <td ><input id="pk_etab.etab_id"  name="etab_bean.pk_etab.etab_id" type="text"      size="7"    value="${detailBean.etab_bean.pk_etab.etab_id}"  maxlength="7" />
          <input    id="etab_lib" name="etab_lib" type="text"     size="15"                     value="${detailBean.etab_bean.etab_lib}"  maxlength="25" />
        </td>
      </tr>
      <!-- ******************************************************************************************************************************************** -->
      <tr valign="top"  >
        <td><label>${usr_obs}</label>
        </td>
        <td     colspan="6"    ><textarea rows="3" cols="35"  name="usr_obs" id="usr_obs"    >${detailBean.usr_obs}</textarea>
        </td>
      </tr>
      <!-- ******************************************************************************************************************************************** -->
    </table>
  </ext:panel>
</ext:body>
