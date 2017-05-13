
import java.util.ArrayList;
import java.util.List;

public class Collision implements CollisionInterface{

    int numberOfValues;
    List<Point2D> objects = new ArrayList<>();

    @Override
    public void setNumberOfObjects(int value){

        numberOfValues = value;

    }

    @Override
    public void setInitialPosition(int objectID, Point2D ip){

        objects.add(objectID, ip);

    }

    @Override
    public void moveObject(int objectID, int stepLength, int angle){

        int dx = (int) (stepLength * Math.cos(Math.PI * angle / 180.0));
        int dy = (int) (stepLength * Math.sin(Math.PI * angle / 180.0));

        objects.set(objectID, new Point2D(objects.get(objectID).getX() + dx, objects.get(objectID).getY() + dy));

    }

    @Override
    public int[] getCollictionsOfObject(int objectID){
        
        List<Integer> collisionIDs = new ArrayList();
        
        for(int i = 0; i < objects.size(); i++){
            
            if(objects.get(i).getX() == objects.get(objectID).getX() && objects.get(i).getY() == objects.get(objectID).getY()){
                
                if(objects.get(i) !=  objects.get(objectID)){
                    
                    collisionIDs.add(i);
                    
                }
                
            }
      
        }
        
        int[] IDs = new int[collisionIDs.size()];
        
        for(int i = 0; i < collisionIDs.size(); i++){
            
            IDs[i] = collisionIDs.get(i);
            
        }
        
        return IDs;
        
    }

    @Override
    public Point2D getPosition(int objectID){
        
        return objects.get(objectID);
        
    }

}
