
public class Calculator implements CalculatorInterface{

    private int Register1, Register2;
    
    @Override
    public void setReg1(int v) {
    
        Register1 = v;
        
    }

    @Override
    public void setReg2(int v) {
        
        Register2 = v;
    
    }

    @Override
    public int getReg1() {
    
        return Register1;
            
    }

    @Override
    public void clear() {
    
        Register1 = 0;
        Register2 = 0;
        
    }

    @Override
    public void swap() {
    
        int temp = Register1;
        Register1 = Register2;
        Register2 = temp;
        
    }

    @Override
    public void add() {
        
        Register2 += Register1;       
    
    }

    @Override
    public int setIfMatchReturnOld(int expected, int v) {
        
        int temp = Register1;
        
        if(temp == expected){
            
            Register1 = v;
            
        }
        
        return temp;
        
    }
    
}
