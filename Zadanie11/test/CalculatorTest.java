import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

    CalculatorInterface calculator;
    CalculatorInterface calculator2;
    CalculatorInterface calculator3;
    CalculatorInterface calculator4;

    @Before
    public void setUp() {

        calculator = new Calculator();
        calculator2 = new Calculator();
        calculator3 = new Calculator();
        calculator4 = new Calculator();

        calculator.setReg1(1);
        calculator.setReg2(2);
        calculator2.setReg1(0);
        calculator2.setReg2(0);
        calculator3.setReg1(-1);
        calculator3.setReg2(42);
        calculator4.setReg1(9);
        calculator4.setReg2(-8);

    }

    @Test
    public void testCreate() {

        Assert.assertNotNull(calculator);
        Assert.assertNotNull(calculator2);
        Assert.assertNotNull(calculator3);
        Assert.assertNotNull(calculator4);

    }

    @Test
    public void testSet() {
        
        //System.out.println("Setery");

        Assert.assertEquals("Niepoprawnie dzialajacy seter dla Register1 w przypadku: OBIE WARTOSCI DODATNIE!", 1, calculator.getReg1());
        calculator.swap();
        Assert.assertEquals("Niepoprawnie dzialajacy seter dla Register2 lub swap w przypadku: OBIE WARTOSCI DODATNIE!", 2, calculator.getReg1());
        calculator.swap();
        Assert.assertEquals("Niepoprawnie dzialajacy seter dla Register1 w przypadku: OBIE WARTOSCI ROWNE ZERO!", 0, calculator2.getReg1());
        calculator2.swap();
        Assert.assertEquals("Niepoprawnie dzialajacy seter dla Register2 lub swap w przypadku: OBIE WARTOSCI ROWNE ZERO!", 0, calculator2.getReg1());
        calculator2.swap();
        Assert.assertEquals("Niepoprawnie dzialajacy seter dla Register1 w przypadku: Register1 ujemna, Register2 dodatnia!", -1, calculator3.getReg1());
        calculator3.swap();
        Assert.assertEquals("Niepoprawnie dzialajacy seter dla Register2 lub swap w przypadku: Register1 ujemna, Register2 dodatnia!", 42, calculator3.getReg1());
        calculator3.swap();
        Assert.assertEquals("Niepoprawnie dzialajacy seter dla Register1 w przypadku: Register1 dodatnia, Register2 ujemna!", 9, calculator4.getReg1());
        calculator4.swap();
        Assert.assertEquals("Niepoprawnie dzialajacy seter dla Register2 lub swap w przypadku: Register1 dodatnia, Register2 ujemna!", -8, calculator4.getReg1());
        calculator4.swap();
    }
    
    @Test
    public void testSwap() {
        
        //System.out.println("Swap");
        
        calculator.swap();
        Assert.assertEquals("Niepoprawnie dzialajacy swap (lub seter dla Register2) w przypadku: OBIE WARTOSCI DODATNIE!", 2, calculator.getReg1());
        calculator.swap();
        calculator2.swap();
        Assert.assertEquals("Niepoprawnie dzialajacy swap (lub seter dla Register2) w przypadku: OBIE WARTOSCI ROWNE ZERO!", 0, calculator2.getReg1());
        calculator2.swap();
        calculator3.swap();
        Assert.assertEquals("Niepoprawnie dzialajacy swap (lub seter dla Register2) w przypadku: Register1 ujemna, Register2 dodatnia!", 42, calculator3.getReg1());
        calculator3.swap();
        calculator4.swap();
        Assert.assertEquals("Niepoprawnie dzialajacy swap (lub seter dla Register2) w przypadku: Register1 dodatnia, Register2 ujemna!", -8, calculator4.getReg1());
        calculator4.swap();
    }

    @Test
    public void testClear() {
        
        //System.out.println("Clear");
        
        calculator.clear();
        Assert.assertEquals("Niepoprawnie dzialajacy clear na Register1 w przypadku: OBIE WARTOSCI DODATNIE!", 0, calculator.getReg1());
        calculator.swap();
        Assert.assertEquals("Niepoprawnie dzialajacy clear na Register2 w przypadku: OBIE WARTOSCI DODATNIE!", 0, calculator.getReg1());
        calculator2.clear();
        Assert.assertEquals("Niepoprawnie dzialajacy clear na Register1 w przypadku: OBIE WARTOSCI ROWNE ZERO!", 0, calculator2.getReg1());
        calculator2.swap();
        Assert.assertEquals("Niepoprawnie dzialajacy clear na Register2 w przypadku: OBIE WARTOSCI ROWNE ZERO!", 0, calculator2.getReg1());
        calculator3.clear();
        Assert.assertEquals("Niepoprawnie dzialajacy clear na Register1 w przypadku: Register1 ujemna, Register2 dodatnia!", 0, calculator3.getReg1());
        calculator3.swap();
        Assert.assertEquals("Niepoprawnie dzialajacy clear na Register2 w przypadku: Register1 ujemna, Register2 dodatnia!", 0, calculator3.getReg1());
        calculator4.clear();
        Assert.assertEquals("Niepoprawnie dzialajacy clear na Register1 w przypadku: Register1 dodatnia, Register2 ujemna!", 0, calculator4.getReg1());
        calculator4.swap();
        Assert.assertEquals("Niepoprawnie dzialajacy clear na Register2 w przypadku: Register1 dodatnia, Register2 ujemna!", 0, calculator4.getReg1());
        calculator4.swap();
        
    }

    @Test
    public void testAdd() {
        
        //System.out.println("Add");
        
        calculator.add();
        calculator.swap();
        Assert.assertEquals("Niepoprawnie dzialajacy add w przypadku: OBIE WARTOSCI DODATNIE!", 3, calculator.getReg1());
        calculator.swap();
        
        calculator2.add();
        calculator2.swap();
        Assert.assertEquals("Niepoprawnie dzialajacy add w przypadku: OBIE WARTOSCI ROWNE ZERO!", 0, calculator2.getReg1());
        calculator2.swap();
        
        calculator3.add();
        calculator3.swap();
        Assert.assertEquals("Niepoprawnie dzialajacy add w przypadku: Register1 ujemna, Register2 dodatnia!", 41, calculator3.getReg1());
        calculator3.swap();
        
        calculator4.add();
        calculator4.swap();
        Assert.assertEquals("Niepoprawnie dzialajacy add w przypadku: Register1 dodatnia, Register2 ujemna!", 1, calculator4.getReg1());
        calculator4.swap();
        
    }

    @Test
    public void testSetIfMatchReturnOld() {
        
        //System.out.println("setIfMatchReturnOld");
        
        Assert.assertEquals("Niepoprawnie dzialajacy setIfMatchReturnOld, zwraca zla wartosc!", 1, calculator.setIfMatchReturnOld(1, 7));
        Assert.assertEquals("Niepoprawnie dzialajacy setIfMatchReturnOld, nie zmienia wartosci Register1!", 7, calculator.getReg1());
        
        Assert.assertEquals("Niepoprawnie dzialajacy setIfMatchReturnOld, zwraca zla wartosc!", 7, calculator.setIfMatchReturnOld(5, 9));
        Assert.assertEquals("Niepoprawnie dzialajacy setIfMatchReturnOld, zmienia wartosc Register1, gdy nie powienien!", 7, calculator.getReg1());
        
    }

}
