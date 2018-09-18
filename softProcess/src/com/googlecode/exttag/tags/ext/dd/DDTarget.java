package com.googlecode.exttag.tags.ext.dd;

import com.googlecode.exttag.Description;

@Description("A DragDrop implementation that does not move, but can be a drop target. You would get the same result by simply omitting implementation for the event callbacks, but this way we reduce the processing cost of the event listener and the callbacks. ")
public class DDTarget extends DragDrop {

}
