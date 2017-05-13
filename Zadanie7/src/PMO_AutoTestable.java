import java.util.Map;
import java.util.Set;

public interface PMO_AutoTestable {
	void setString(String value);

	String getOldString();
	String getNewString();

	void autotest(Set<String> set, Map<String, String> map);
}