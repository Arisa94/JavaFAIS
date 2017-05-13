import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObjectAnonymizer implements ObjectAnonymizerInterface{

    Set<String> mySet = new HashSet<>();
    Map<String, String> myMap = new HashMap<>();
    String newName = Helper.generate();
    ToAnonimize toAnonimize;

    @Override
    public List<Object> anonimize(List<Object> objects){

        for(Object object : objects){

            Field[] fields = object.getClass().getDeclaredFields();

            for(Field field : fields){

                toAnonimize = field.getAnnotation(ToAnonimize.class);
                if(field.isAnnotationPresent(ToAnonimize.class) && toAnonimize.readyToAnonimize()){
                    if(!myMap.containsKey(field)){

                        do{

                            newName = Helper.generate();

                        }while(mySet.contains(newName));

                        
                        try{
                            myMap.put((String)field.get(object), newName);
                            mySet.add(newName);
                        }
                        catch(IllegalArgumentException | IllegalAccessException ex){
                            Logger.getLogger(ObjectAnonymizer.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }

                }

            }
           
            for(Field field : fields){

                toAnonimize = field.getAnnotation(ToAnonimize.class);
                if(field.isAnnotationPresent(ToAnonimize.class)){

                    if(field.isAnnotationPresent(ToAnonimize.class) && toAnonimize.readyToAnonimize()){

                        try{
                            
                            field.set(object, myMap.get((String)field.get(object)));

                        }

                        catch(IllegalArgumentException | IllegalAccessException ex){

                            Logger.getLogger(ObjectAnonymizer.class.getName()).log(Level.SEVERE, null, ex);

                        }

                    }

                }

            }
        }

        return objects;

    }

    @Override
    public Set<String> getUsedStrings(){

        return mySet;

    }

    @Override
    public Map<String, String> getMapping(){

        return myMap;

    }

}