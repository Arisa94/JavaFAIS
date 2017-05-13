import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Anonymizer implements AnonymizerInterface{
    
    Set<String> mySet = new HashSet<>();
    Map<String, String> myMap = new HashMap<>();
    String newName = Helper.generate();;
    
    @Override
    public List<PhoneInterface> anonimize(List<PhoneInterface> phones){
        
        for(int i = 0; i < phones.size(); i++){
            
            if(!myMap.containsKey(phones.get(i).getPhoneNumber())){
    
                mySet.add(newName);                
                myMap.put(phones.get(i).getPhoneNumber(), newName);
                
                while(mySet.contains(newName)){
                    
                    newName = Helper.generate();
                    
                }
                
            }
            
        }
        
        for(int i = 0; i < phones.size(); i++){
            
            phones.get(i).setPhoneNumber(myMap.get(phones.get(i).getPhoneNumber()));
            
        }
        
        return phones;
        
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
