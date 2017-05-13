
public class HelloThread extends Thread{

    public void run(){
        System.out.println("Witam z wątku");
    }

    public static void main(String args[]){
        Thread t = new HelloThread();
        t.start();
        System.out.println("Witam z programu");
    }

}
