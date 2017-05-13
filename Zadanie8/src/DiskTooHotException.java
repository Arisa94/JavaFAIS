
public class DiskTooHotException extends Exception{
    
    private long coolingTime; 
	
	public DiskTooHotException( long coolingTime ) {
		this.coolingTime = coolingTime;
	}
	
	public long getCoolingTime() {
		return coolingTime;
	}
        
}
