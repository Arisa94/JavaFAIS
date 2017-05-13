class ZadII{

    public static void main(String[] args){
        ArrayParser kupa = new ArrayParser();
        
        Integer[] a = {1, null, 4, null, null, null, null, 6, null, null, null, null};
        Integer[] b = {2, 3, null, null, null, null, 5, 7, null, 8, null, null};
        Integer[] result = new Integer[12];
        result = kupa.parse(a,b);
        
        for(int i =0; i<12;i++){
            System.out.println(result[i]);
        }
    }
    
}
