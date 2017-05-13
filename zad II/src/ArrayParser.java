
class ArrayParser{

    public Integer[] parse(Integer[] a, Integer[] b){

        Integer[] result = new Integer[a.length];
        Integer lastOne = null;

        for(int i = 0; i < a.length; i++){

            if(a[i] != null && b[i] != null){

                if(a[i] < b[i]){

                    result[i] = a[i];

                }

                else{

                    result[i] = b[i];

                }
                
                lastOne = result[i];

            }

            if(a[i] == null && b[i] != null){

                result[i] = b[i];
                lastOne = result[i];

            }

            if(a[i] != null && b[i] == null){

                result[i] = a[i];
                lastOne = result[i];

            }

            if(a[i] == null && b[i] == null){

                result[i] = lastOne;
                lastOne = null;
                
            }

        }

        return result;
    }

}
