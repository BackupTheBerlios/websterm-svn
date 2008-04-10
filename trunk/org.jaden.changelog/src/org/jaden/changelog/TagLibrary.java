package org.jaden.changelog;

import org.generama.MetadataProvider;

public class TagLibrary {
    public static final String CHANGE_LOG = ChangeLogTagImpl.NAME;

    public TagLibrary(MetadataProvider metadataProvider) {
        metadataProvider.getDocletTagFactory().registerTag(ChangeLogTagImpl.NAME, ChangeLogTagImpl.class);
    }
}
