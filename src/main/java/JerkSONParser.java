import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JerkSONParser<T> {
    private final String jerkSON;
    private final Class<T> aClass;
    private Integer errors;

    public JerkSONParser(String jerkSON, Class<T> aClass) {
        this.jerkSON = jerkSON;
        this.aClass = aClass;
        this.errors = 0;
    }

    public List<T> parseJerkSON() throws IllegalAccessException, InstantiationException {
        List<T> parsedObjects = new ArrayList<>();
        Matcher object = MatcherBuilder.createMatcher("([^#])+", getJerkSON());
        while (object.find()) {//gets each individual object string in JerkSON
            if (createObject(object.group()) != null) {//null come from incomplete objects
                parsedObjects.add(createObject(object.group()));//turns object string into object and adds to list
            }
        }
        return parsedObjects;
    }

    public T createObject(String singleEntry) throws InstantiationException, IllegalAccessException {
        Matcher value = MatcherBuilder.createMatcher(("(?<=:)([^;])([^;@!^*%:\\n])+"), singleEntry);
        List<String> entryValues = new ArrayList<>();
        while (value.find()) {
            entryValues.add(value.group());//adds each value found by regex to entryValues
        }
        return parseObject(entryValues);//creates and returns new object, fields are set by entryValues found
    }

    public T parseObject(List<String> entryValues) throws IllegalAccessException, InstantiationException {
        T newObj = this.aClass.newInstance();//creates a new class instance
        Field[] fields = this.aClass.getDeclaredFields();//gets all fields for that class
        int index = 0;//sets index for entryValues
        if (entryValues.size() == fields.length) {//checks if entryValues holds all values
            for (Field field : fields) { //this is assuming that the fields are in order
                field.setAccessible(true);//allows private fields to be set
                field.set(newObj, entryValues.get(index)); //will only set String values
                index++;
            }
        } else {
            errors += 1;//if entryValues don't have all necessary values, increments errors count
            return null;
        }
        return newObj;//return object with all fields set
    }

    public void parseField(Field field) {

    }

    public String getJerkSON() {
        return jerkSON;
    }

    public Integer getErrors() {
        return errors;
    }
}