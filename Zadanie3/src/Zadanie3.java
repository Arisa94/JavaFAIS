
public class Zadanie3 {

    public static void main(String[] args) {
        Object a[] = new Object[10];
        Object b[] = new Object[10];
        a[0] = null;
        a[1] = null;
        a[2] = 3;
        a[3] = "u";
        a[4] = null;
        a[5] = 4;
        a[6] = null;
        a[7] = null;
        a[8] = null;
        a[9] = 10;
        
        b[0] = 1.3f;
        b[1] = 3;
        b[2] = 2;
        b[3] = 1.45;
        b[4] = null;
        b[5] = 4.5;
        b[6] = 1;
        b[7] = 2;
        b[8] = 4;
        b[9] = 5;
        
        SuperArrayParser kupa = new SuperArrayParser();
        kupa.parse(a, b);
    }
    
}
