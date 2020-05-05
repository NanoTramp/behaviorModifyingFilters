package com.rough_edge.behaviourspecialization.Filters;

import com.rough_edge.behaviourspecialization.exceptions.MessageFilterException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Data
public class Filter {

    private List<IFilterBehavior> filters = new ArrayList<>();

    public Filter(String filterTopic, Object[] filterDefinition) throws Exception {
        addFilter(filterTopic, filterDefinition);
    }


    public void addFilter(String filterTopic, Object[] filterDefinition) throws Exception {
        for (Object object : filterDefinition) {
            if (object instanceof String) {
                String filterValue = (String) object;
                filters.add(new ContainsStringFilter(filterTopic, filterValue));
            } else if (object instanceof Map) {
                Map<String, Object[]> filterMap = (Map<String, Object[]>) object;
                if (filterMap.keySet().size() > 1) {
                    System.out.println("keyset for filter is more than one: " + filterMap.keySet());
                }
                String filterCondition = (String) filterMap.keySet().toArray()[0];
                SupportedFilterTypes filterType = SupportedFilterTypes.valueOf(filterCondition.replace('-', '_').toUpperCase());
                System.out.println(filterType);
                switch (filterType) {
                    case ANYTHING_BUT:
                        filters.add(new ContainsStringFilter(filterTopic, (String) filterMap.get(filterCondition)[0], false));
                        break;
                    case NUMERIC:
                        System.out.println("Build numberic and add to list");
                        break;

                }
            }
        }


    }


    public boolean doFilter(Map<String, Object> messageFilterInfo) throws MessageFilterException {
        boolean sendMessage = true;
        for (IFilterBehavior filter : filters) {
            if (!filter.FilterMessage(messageFilterInfo)) {
                sendMessage = false;
                break;
            }
        }
        return sendMessage;
    }
}
