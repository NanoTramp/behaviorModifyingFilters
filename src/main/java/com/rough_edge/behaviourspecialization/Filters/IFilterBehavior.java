package com.rough_edge.behaviourspecialization.Filters;

import com.rough_edge.behaviourspecialization.exceptions.MessageFilterException;

import java.util.Map;

public interface IFilterBehavior {
    public boolean FilterMessage(Map<String,Object> message) throws MessageFilterException;
}
