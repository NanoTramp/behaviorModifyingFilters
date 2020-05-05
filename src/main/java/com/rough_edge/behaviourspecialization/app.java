package com.rough_edge.behaviourspecialization;

import com.rough_edge.behaviourspecialization.Filters.Filter;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

public class app {

    public static void main(String[] args) throws Exception {
        Map<String, Object> message1 = new HashMap<>();
        message1.put("product_category", "grocery");
        message1.put("shipping_location", "uk");

        Map<String, Object> message2 = new HashMap<>();
        message2.put("product_category", "electrical");
        message2.put("shipping_location", "uk");


        var containsFilterDefinitionKey = "product_category";
        var containsFilterDefinitionValue = new String[]{"grocery"};
        var notContainsFilterDefinitionNestedValue = new HashMap<String, Object[]>();
        notContainsFilterDefinitionNestedValue.put("anything_but", new String[]{"grocery"});
        var notContainsFilterDefinitionValue = new Object[]{notContainsFilterDefinitionNestedValue};

        Filter containsFilter = new Filter(containsFilterDefinitionKey, containsFilterDefinitionValue);
        Filter notContainsFilter = new Filter(containsFilterDefinitionKey, notContainsFilterDefinitionValue);

        Filter combinationFilter = new Filter(containsFilterDefinitionKey, notContainsFilterDefinitionValue);


        containsFilterDefinitionKey = "shipping_location";
        containsFilterDefinitionValue = new String[]{"uk"};

        combinationFilter.addFilter(containsFilterDefinitionKey, containsFilterDefinitionValue);


        System.out.println(format("running contains filter, %s, on %s", containsFilter, message1));
        System.out.println(format("Allowed Through? %s", containsFilter.doFilter(message1)));
        System.out.println(format("running contains filter, %s, on %s", containsFilter, message2));
        System.out.println(format("Allowed Through? %s", containsFilter.doFilter(message2)));
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.println(format("running not contains filter, %s, on %s", notContainsFilter, message1));
        System.out.println(format("Allowed Through? %s", notContainsFilter.doFilter(message1)));
        System.out.println(format("running not contains filter, %s, on %s", notContainsFilter, message2));
        System.out.println(format("Allowed Through? %s", notContainsFilter.doFilter(message2)));
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.println(format("running combination filter, %s, on %s", combinationFilter, message1));
        System.out.println(format("Allowed Through? %s", combinationFilter.doFilter(message1)));
        System.out.println(format("running combination filter, %s, on %s", combinationFilter, message2));
        System.out.println(format("Allowed Through? %s", combinationFilter.doFilter(message2)));
    }
}
