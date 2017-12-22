package jpacontrol;

import java.util.HashMap;
import javax.persistence.Persistence;

/**
 *
 * @author xu
 */
public class Structure {
    public static void main(String[] args) {
        HashMap puproperties = new HashMap();
        
        puproperties.put("javax.persistence.sql-load-script-source", "script/ClearDB.sql");
        Persistence.generateSchema("PU", puproperties);
        
        puproperties.remove("javax.persistence.sql-load-script-source");
        Persistence.generateSchema("PU", puproperties);
    }
}
