package com.googlecode.exttag.tags.ext.data;

import com.googlecode.exttag.Description;
import com.googlecode.exttag.tags.ext.util.Observable;

/**
 * 
 * @author oscar.xie
 * 
 */
@Description("This class is an abstract base class for implementations which provide retrieval of unformatted data objects."

		+ "DataProxy implementations are usually used in conjunction with an implementation of Ext.data.DataReader (of the appropriate type which knows how to parse the data object) to provide a block of Ext.data.Records to an Ext.data.Store."

		+ "Custom implementations must implement the load method as described in Ext.data.HttpProxy.load.")
public class DataProxy extends Observable {

}
