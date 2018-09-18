package com.googlecode.exttag.tags.ext.dd;

import com.googlecode.exttag.Description;

@Description("A DragDrop implementation that inserts an empty, bordered div into the document that follows the cursor during drag operations. At the time of the click, the frame div is resized to the dimensions of the linked html element, and moved to the exact location of the linked element. References to the 'frame' element refer to the single proxy element that was created to be dragged in place of all DDProxy elements on the page. ")
public class DDProxy extends DD {

}
