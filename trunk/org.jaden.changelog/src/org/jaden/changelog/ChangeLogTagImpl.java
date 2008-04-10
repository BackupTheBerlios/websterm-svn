package org.jaden.changelog;

public class ChangeLogTagImpl extends org.xdoclet.XDocletTag implements org.jaden.changelog.ChangeLogTag {
    public static final String NAME = "change.log";
    private static final java.util.List ALLOWED_PARAMETERS = java.util.Arrays.asList( new String[] {
		"date",
		"message",
    	""
    });
    
    private static final java.util.List ALLOWED_VALUES = java.util.Arrays.asList( new String[] {
        ""
    });
    public ChangeLogTagImpl(String name, String value, com.thoughtworks.qdox.model.AbstractJavaEntity entity, int lineNumber, org.xdoclet.QDoxPropertyExpander expander) {
        super(name, value, entity, lineNumber, expander);
    }
    public ChangeLogTagImpl(String name, String value, com.thoughtworks.qdox.model.AbstractJavaEntity entity, int lineNumber) {
        super(name, value, entity, lineNumber);
    }

    public java.lang.String getDate() {
		boolean required = true;
        String result = getNamedParameter("date");
        if(required && result == null) {
            bomb("date=\"???\" must be specified.");
        }

		java.lang.String retVal = null;


		if (result != null) {
	            retVal = result;
		}
		
		return retVal;
    }
    public java.lang.String getMessage() {
		boolean required = true;
        String result = getNamedParameter("message");
        if(required && result == null) {
            bomb("message=\"???\" must be specified.");
        }

		java.lang.String retVal = null;


		if (result != null) {
	            retVal = result;
		}
		
		return retVal;
    }

    protected void validateLocation() {
        if(isOnField) {
            bomb("is not allowed on fields");
        }
		if(isOnConstructor) {
        	bomb("is not allowed on constructors");
		}
		if(isOnMethod) {
        	bomb("is not allowed on methods");
		}
        
        // check uniqueness

        // warn deprecation
        
        // check for allowed values for whole tag
        if( ALLOWED_VALUES.size() > 1 && !ALLOWED_VALUES.contains(getValue())) {
            bomb( "\"" + getValue() +"\" is not a valid value. Allowed values are ");
        }        
        // Verify that all parameters are known.
        final java.util.Collection parameterNames = getNamedParameterMap().keySet();
        for (java.util.Iterator iterator = parameterNames.iterator(); iterator.hasNext();) {
            String parameterName = (String) iterator.next();
            if (!ALLOWED_PARAMETERS.contains(parameterName)) {
                bomb(parameterName + " is an invalid parameter name.");
            }
        }
        
        // Get all the parameters to validate their contents
        getDate();
        getMessage();
    }

    public void validateModel() {
        // check uniqueness
    }
}