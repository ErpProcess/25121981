package com.googlecode.exttag.tags.ext.util;

import com.googlecode.exttag.Description;
import com.googlecode.exttag.tags.AbstractMapTag;

@Description("Provides a convenient method of performing setTimeout where a new timeout cancels the old timeout. An example would be performing validation on a keypress. You can use this class to buffer the keypress events for a certain number of milliseconds, and perform only if they stop for that amount of time.")
public class DelayedTask extends AbstractMapTag {

}
