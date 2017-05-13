
public class Helper{

    public Point2D move(Point2D startPosition, int length, int angle){

        int dx = (int) (length * Math.cos(Math.PI * angle / 180.0));
        int dy = (int) (length * Math.sin(Math.PI * angle / 180.0));

        return new Point2D(startPosition.getX() + dx, startPosition.getY() + dy);

    }

    public static void main(String[] argv){

        Collision X = new Collision();
        Point2D A = new Point2D(1, 1);
        Point2D B = new Point2D(2, 2);
        Point2D C = new Point2D(3, 3);
        int[] tab;
        int test_index = 4;

        X.setNumberOfObjects(9);//_____________________________________________1

        //______________________________________________________________________2
        X.setInitialPosition(0, A); //1 - pokryje sie
        X.setInitialPosition(1, A); //2 - pokryje sie 
        X.setInitialPosition(2, C); //3
        X.setInitialPosition(3, B); //4
        X.setInitialPosition(4, A); //5 - Bedzie wyjsciem@@@@@
        X.setInitialPosition(5, A); //6 - pokryje sie
        X.setInitialPosition(6, A); //7 - pokryje sie
        X.setInitialPosition(7, A); //8
        X.setInitialPosition(8, A); //8

        System.out.println("Pozycje wejsciowe");
        System.out.println("0 = " + X.getPosition(0));
        System.out.println("1 = " + X.getPosition(1));
        System.out.println("2 = " + X.getPosition(2));
        System.out.println("3 = " + X.getPosition(3));
        System.out.println("4 = " + X.getPosition(4)+" To jest obiekt bazowy");
        System.out.println("5 = " + X.getPosition(5));
        System.out.println("6 = " + X.getPosition(6));
        System.out.println("7 = " + X.getPosition(7));
        System.out.println("8 = " + X.getPosition(8));
        System.out.println("");
        //______________________________________________________________________3
        /*(int objectID, int stepLength, int angle)*/
        X.moveObject(0, 50, 22); //1 -  OK
        X.moveObject(1, 50, 22); //2 -  OK
        X.moveObject(2, 60, 30); //3 -  inne: wyjscie, krok, kat
        X.moveObject(3, 60, 22); //4 -  inne: wyjsice
        X.moveObject(4, 50, 22); //5  - @@START@@
        X.moveObject(5, 50, 22); //6  - OK
        X.moveObject(6, 50, 22); //7  - OK
        X.moveObject(7, 60, 22); //8  - inne : krok
        X.moveObject(8, 50, 130); //9  - inne : kat

        //______________________________________________________________________4
        tab = X.getCollictionsOfObject(test_index);

        System.out.println("Pozycje wyjsciowe");
        System.out.println("0 = " + X.getPosition(0));
        System.out.println("1 = " + X.getPosition(1));
        System.out.println("2 = " + X.getPosition(2));
        System.out.println("3 = " + X.getPosition(3));
        System.out.println("4 = " + X.getPosition(4)+" To jest obiekt bazowy");
        System.out.println("5 = " + X.getPosition(5));
        System.out.println("6 = " + X.getPosition(6));
        System.out.println("7 = " + X.getPosition(7));
        System.out.println("8 = " + X.getPosition(8));
         
        System.out.println("________________");
 for(int i : tab){
            System.out.println(i);
        }
        int[] wynik = {0, 1, 5, 6};

        int x = tab.length;
        System.out.println("x= " + x);

        int licznik = 0;

        for(int k = 0; k < x; k++){
            
            if(tab[k] == wynik[k]){
                
                licznik++;
                
            }
            

        }

        //jak wywali b³êdy zanim tu dojdzie to wczesniejsze funkcje nie dzia³aj¹
        if(licznik == 4){
            System.out.println("getCollictionsOfObject sie zgadza");
        }
        if(x == 4){
            System.out.println("Dlugosc tablicy wynikowej sie zgadza");
        }

        Point2D start = new Point2D(0,0);
	
	   Helper h = new Helper();
	   
	   System.out.println( "start = " + start );
	   System.out.println( "start = " + h.move( start, 10, 0 ) );
	   System.out.println( "start = " + h.move( start, 10, 45 ) );
	   System.out.println( "start = " + h.move( start, 10, 90 ) );
	   System.out.println( "start = " + h.move( start, 10, 180 ) );
           System.out.println(X.getPosition(test_index));
    }

}
