package com.googlecode.exttag;

public class Configuration {

	private static String home;
	
	private static String styles;
	
	private static String scripts;

	public static void init(String home, String styles, String scripts) {
        if (home == null || home.trim().length() == 0) {
            //home = "ext-3.0.0/";
        	 home = "ext-3.0.0/";
        } else {
            home = home.trim();
            if (! home.endsWith("/")) {
                home += "/";
            }
        }
        if (styles == null || styles.trim().length() == 0) {
            styles = "resources/css/ext-all.css";
        } else {
            styles = styles.trim();
        }
        if (scripts == null || scripts.trim().length() == 0) {
            scripts = "adapter/ext/ext-base.js,ext-all.js";
        } else {
            scripts = scripts.trim();
        }
		Configuration.home = home;
		Configuration.styles = parseStyles(styles, true);
		Configuration.scripts = parseScripts(scripts, true);
	}

	public static final String parseStyles(String styles, boolean isInExtHome) {
		StringBuilder buf = new StringBuilder();
		if (styles != null && styles.trim().length() > 0) {
			for (String css : styles.split("\\,")) {
				if (css != null) {
					String str = css.trim();
					if (str.length() > 0) {
						buf
								.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""
										+ (isInExtHome ? (Configuration.getHome() + str) : str) + "\" />");
					}
				}
			}
		}
		return buf.toString();
	}

	public static final String parseScripts(String scripts, boolean isInExtHome) {
		StringBuilder buf = new StringBuilder();
		if (scripts != null && scripts.trim().length() > 0) {
			for (String js : scripts.split("\\,")) {
				if (js != null) {
					String str = js.trim();
					if (str.length() > 0) {
						buf.append("<script type=\"text/javascript\" src=\""
								+ (isInExtHome ? (Configuration.getHome() + str) : str) + "\"></script>");
					}
				}
			}
		}
		return buf.toString();
	}

	public static String getHome() {
		return home;
	}

    public static String getStyles() {
        return styles;
    }

    public static String getScripts() {
        return scripts;
    }

}
