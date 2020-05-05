package com.rough_edge.behaviourspecialization.Filters;

import com.rough_edge.behaviourspecialization.exceptions.MessageFilterException;
import lombok.Data;

import java.util.Map;
@Data
public class ContainsStringFilter implements IFilterBehavior {
    private final String filterKey;
    private final String filterString;
    private final boolean contains;

    ContainsStringFilter(String filterKey, String filterString) {
        this.filterKey = filterKey;
        this.filterString = filterString;
        this.contains = true;
    }

    ContainsStringFilter(String filterKey, String filterString,boolean contains) {
        this.filterKey = filterKey;
        this.filterString = filterString;
        this.contains = false;
    }

    @Override
    public boolean FilterMessage(Map<String, Object> messageInfo) throws MessageFilterException {
        return contains == ((String) messageInfo.getOrDefault(filterKey, false)).contains(filterString);
    }
}
