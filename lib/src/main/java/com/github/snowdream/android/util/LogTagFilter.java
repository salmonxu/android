package com.github.snowdream.android.util;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hui.yang on 2014/10/14.
 */
public class LogTagFilter extends LogFilter {
    private Set<String> whitelist = null;
    private Set<String> blacklist = null;

    /**
     * If white tag is added, then other tags will be filtered.
     *
     * @param tag
     */
    public void addWhiteTag(String tag) {
        if (whitelist == null) {
            whitelist = new HashSet<String>();
        }
        whitelist.add(tag);
    }

    /**
     * If white tags is added, then other tags will be filtered.
     *
     * @param tags
     */
    public void addWhiteTags(Set<String> tags) {
        whitelist = tags;
    }

    /**
     * If black tag is added, then it will be filtered.
     *
     * @param tag
     */
    public void addBlackTag(String tag) {
        if (blacklist == null) {
            blacklist = new HashSet<String>();
        }
        blacklist.add(tag);
    }

    /**
     * If black tags is added, then them will be filtered.
     *
     * @param tags
     */
    public void addBlackTags(Set<String> tags) {
        blacklist = tags;
    }

    @Override
    public boolean filter(String tag) {
        if (!whitelist.contains(tag)){
            return true;
        }

        if (blacklist.contains(tag)){
            return true;
        }

        return false;
    }
}
