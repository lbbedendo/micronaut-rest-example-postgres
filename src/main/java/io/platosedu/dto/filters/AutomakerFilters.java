package io.platosedu.dto.filters;

import io.micronaut.core.annotation.Introspected;
import io.platosedu.jooq.tables.Automaker;
import org.jooq.Condition;
import org.jooq.impl.DSL;

@Introspected
public class AutomakerFilters {
    private String name;
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Condition toCondition() {
        Condition condition = DSL.trueCondition();
        if (name != null) {
            condition = condition.and(Automaker.AUTOMAKER.NAME.eq(name));
        }
        if (country != null) {
            condition = condition.and(Automaker.AUTOMAKER.COUNTRY.eq(country));
        }
        return condition;
    }
}
