import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JerkSONParser<T> {
    private final String jerkSON;
    private final List<T> parsedObjects;
    private final Class<T> aClass;
    private Integer errors;

    public JerkSONParser(String jerkSON, Class<T> aClass) {
        this.jerkSON = jerkSON;
        this.parsedObjects = new ArrayList<>();
        this.aClass = aClass;
        this.errors = 0;
    }

    public void parseJerkSON() throws IllegalAccessException, InstantiationException {
        Matcher object = createMatcher("([^#])+", getJerkSON());
        while (object.find()) {
            if (createObject(object.group()) != null) {
                parsedObjects.add(createObject(object.group()));
            }
        }
    }

    public T createObject(String singleEntry) throws InstantiationException, IllegalAccessException {
        Matcher value = createMatcher(("(?<=:)([^;])([^;@!^*%:\\n])+"), singleEntry);
        List<String> entryValues = new ArrayList<>();
        while (value.find()) {
            entryValues.add(value.group());
        }
        return parseObject(entryValues);
    }

    public T parseObject(List<String> entryValues) throws IllegalAccessException, InstantiationException {
        T newObj = this.aClass.newInstance();
        Field[] fields = this.aClass.getDeclaredFields();
        int index = 0;
        if (entryValues.size() == fields.length) {
            for (Field field : fields) { //this is assuming that the fields are in order
                field.setAccessible(true);
                field.set(newObj, entryValues.get(index)); //will only set String values
                index++;
            }
        } else {
            errors += 1;
            return null;
        }
        return newObj;
    }

    public Matcher createMatcher(String pattern, String string) {
        return Pattern.compile(pattern).matcher(string);
    }

    public String getJerkSON() {
        return jerkSON;
    }

    public List<T> getParsedObjects() {
        return parsedObjects;
    }

    public Integer getErrors() {
        return errors;
    }
}