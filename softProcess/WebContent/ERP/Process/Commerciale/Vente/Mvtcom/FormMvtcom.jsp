 <%@include file="/Aceuil/esProcess.jsp" %>  
  
	 <style>
	#menuGes ul {
	  margin: 0;
	  padding: 0;
	}

	#menuGes .main-menuGes {
	  display: none;
	}

	#tm:checked + .main-menuGes {
	  display: block;
	}

	#menuGes input[type="checkbox"], 
	#menuGes ul span.drop-icon {
	  display: none;
	}

	#menuGes li, 
	#toggle-menuGes, 
	#menuGes .sub-menuGes {
	  border-style: solid;
	  border-color: rgba(0, 0, 0, .05);
	}

	#menuGes li, 
	#toggle-menuGes {
	  border-width: 0 0 1px;
	}

	#menuGes .sub-menuGes {
	  background-color: #444;
	  border-width: 1px 1px 0;
	  margin: 0 1em;
	}

	#menuGes .sub-menuGes li:last-child {
	  border-width: 0;
	}

	#menuGes li, 
	#toggle-menuGes, 
	#menuGes a {
	  position: relative;
	  display: block;
	  color: #000000;
	  /*text-shadow: 1px 1px 0 rgba(0, 0, 0, .125);*/
	}

	#menuGes, 
	#toggle-menuGes {
	  background-color: #09c;
	}

	#toggle-menuGes, 
	#menuGes a {
	  padding: 1em 1.5em;
	}

	#menuGes a {
	  transition: all .125s ease-in-out;
	  -webkit-transition: all .125s ease-in-out;
	}

	#menuGes a:hover {
	  background-color: white;
	  color: #09c;
	}

	#menuGes .sub-menuGes {
	  display: none;
	}

	#menuGes input[type="checkbox"]:checked + .sub-menuGes {
	  display: block;
	}

	#menuGes .sub-menuGes a:hover {
	  color: #444;
	}

	#toggle-menuGes .drop-icon, 
	#menuGes li label.drop-icon {
	  position: absolute;
	  right: 1.5em;
	  top: 1.25em;
	}

	#menuGes label.drop-icon, #toggle-menuGes span.drop-icon {
	  border-radius: 50%;
	  width: 1em;
	  height: 1em;
	  text-align: center;
	  background-color: rgba(0, 0, 0, .125);
	  text-shadow: 0 0 0 transparent;
	  color: rgba(255, 255, 255, .75);
	}

	#menuGes .drop-icon {
	  line-height: 1;
	}

@media only screen and (max-width: 64em) and (min-width: 52.01em) {
  #menuGes li {
    width: 33.333%;
  }

  #menuGes .sub-menuGes li {
    width: auto;
  }
}

@media only screen and (min-width: 52em) {
  #menuGes .main-menuGes {
    display: block;
  }

  #toggle-menuGes, 
  #menuGes label.drop-icon {
    display: none;
  }

  #menuGes ul span.drop-icon {
    display: inline-block;
  }

  #menuGes li {
    float: left;
    /*border-width: 0 1px 0 0;*/
  }

  #menuGes .sub-menuGes li {
    float: none;
  }

  #menuGes .sub-menuGes {
    border-width: 0;
    margin: 0;
    position: absolute;
    top: 100%;
    left: 0;
    width: 12em;
    z-index: 3000;
  }

  #menuGes .sub-menuGes, 
  #menuGes input[type="checkbox"]:checked + .sub-menuGes {
    display: none;
  }

  #menuGes .sub-menuGes li {
    border-width: 0 0 1px;
  }

  #menuGes .sub-menuGes .sub-menuGes {
    top: 0;
    left: 100%;
  }

  #menuGes li:hover > input[type="checkbox"] + .sub-menuGes {
    display: block;
  }
}

</style>
   
 
  
<ext:body  >  
<ext:panel  border="false"    bodyStyle="background: none;"    renderTo="ThePageJsp"  height="500"  >  
 

 
 	<nav id="menuGes">
	  <label for="tm" id="toggle-menuGes">Navigation <span class="drop-icon">▾</span></label>
	  <input type="checkbox" id="tm">
	  <ul class="main-menuGes clearfix">
	    <li><a href="#">Parent menuGes 1</a></li>
	    <li><a href="#">Parent menuGes 2
	        <span class="drop-icon">▾</span>
	        <label title="Toggle Drop-down" class="drop-icon" for="sm1">▾</label>
	      </a>
	      <input type="checkbox" id="sm1">
	      <ul class="sub-menuGes">
	        <li><a href="#">Item 2.1</a></li>
	        <li><a href="#">Item 2.2
	            <span class="drop-icon">></span>
	            <label title="Toggle Drop-down" class="drop-icon" for="sm2">▾</label>
	          </a>
	          <input type="checkbox" id="sm2">
	          <ul class="sub-menuGes">
	            <li><a href="#">Item 2.2.1</a></li>
	            <li><a href="#">Item 2.2.2</a></li>
	            <li><a href="#">Item 2.2.3</a></li>
	          </ul>
	        </li>
	        <li><a href="#">Item 3.4</a></li>
	      </ul>
	    </li>
	    <li><a href="#">Parent menuGes 3</a></li>
	  </ul>
	</nav>

 
 
 
    
 
</ext:panel>
</ext:body>
