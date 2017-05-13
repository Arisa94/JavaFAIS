
import java.util.ArrayList;
import java.util.List;

public class StartMe{

    public static void main(String[] args){
        List<String> list1 = new ArrayList<>();
        List<String> listTemp = new ArrayList<>();
        list1.add("AA");
        list1.add("AB");
        list1.add("BA");
        list1.add("BB");

        //Iterator<String>
        for(String a : list1){
            //list1.remove(0);
            if(a.startsWith("A")){
                //System.out.println(a);
                //remove tutaj
                listTemp.add(a);

            }
        }
        for(int i = 0; i < listTemp.size(); i++){

            list1.remove(listTemp.get(i));
        }

        System.out.println(list1);

    }

}
