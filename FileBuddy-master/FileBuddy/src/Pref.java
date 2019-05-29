import java.util.Arrays;
import java.util.Iterator;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Pref{
	public Pref(String password) throws BackingStoreException {
		Preferences prefs = Preferences.userNodeForPackage(Runner.class);
	    prefs.put("key1", "value1");
	    prefs.put("key2", "value2");
	    prefs.putInt("intValue", 4);
	    prefs.putBoolean("booleanValue", true);
	    int usageCount = prefs.getInt("intValue", 0);
	    usageCount++;
	    prefs.putInt("UsageCount", usageCount);
	    Iterator it = Arrays.asList(prefs.keys()).iterator();
	    while (it.hasNext()) {
	      String key = it.next().toString();
	      System.out.println(key + ": " + prefs.get(key, null));
	    }
	    System.out.println(prefs.getInt("booleanValue", 0));
	}
	}