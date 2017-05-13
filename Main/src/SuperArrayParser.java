
class SuperArrayParser{

    public Object lastFoundValue;

    private Object find(Object a, Object b){

        Object result = null;

        if(a instanceof String){

            if(b instanceof Byte){

                result = new Byte((byte) ((byte) b * (byte)(-1)));
                
            }

            else if(b instanceof Double){

                result = -(double) b;
                result = (double) result;

            }

            else if(b instanceof Float){

                result = -(float) b;
                result = (float) result;

            }

            else if(b instanceof Integer){

                result = (int) b * (int)(-1);

            }

            else if(b instanceof Long){

                result = -(long) b;

            }

            else if(b instanceof Short){

                result = new Short((short) ((short) b * (short)(-1)));

            }

        }

        else if(b instanceof String){
            
            if(a instanceof Byte){

                result = new Byte((byte) ((byte) a * (byte)(-1)));

            }

            else if(a instanceof Double){

                result = -(double) a;
                result = (double) result;

            }

            else if(a instanceof Float){

                result = -(float) a;
                result = (float) result;

            }

            else if(a instanceof Integer){

                result = (int) a * (int)(-1);

            }

            else if(a instanceof Long){

                result = -(long) a;

            }

            else if(a instanceof Short){

                result = new Short((short) ((short) a * (short)(-1)));
                
            }

        }
        
        else if(a instanceof Integer){

            if(b instanceof Integer){

                result = new Integer((int) a + (int) b);

            }

            else{

                result = lastFoundValue;

            }

        }

        else if(b instanceof Integer){

            if(a instanceof Integer){

                result = new Integer((int) a + (int) b);

            }

            else{

                result = lastFoundValue;

            }

        }
        
        else if(a instanceof Float){

            if(b instanceof Float){

                result = (float) a * (float) b;

            }

            else{

                result = lastFoundValue;

            }

        }

        else if(b instanceof Float){

            if(a instanceof Float){

                result = (float) a * (float) b;

            }

            else{

                result = lastFoundValue;

            }

        }
        
        else if(a instanceof Double){

            if(b instanceof Double){

                if((double) a > (double) b){

                    result = a;

                }

                else{

                    result = b;

                }

            }

            else{

                result = lastFoundValue;

            }

        }

        else if(b instanceof Double){

            if(a instanceof Double){

                if((double) a > (double) b){

                    result = a;

                }

                else{

                    result = b;

                }

            }

            else{

                result = lastFoundValue;

            }

        }
        
        else{
            
            result = lastFoundValue;
            
        }
        
        lastFoundValue = result;
        return result;

    }

    public Object[] parse(Object[] a, Object[] b){

        Object[] result = new Object[a.length];
        int tempStart;

        for(int i = 0; i < a.length; i++){

            tempStart = i;
            if(a[i] == null && b[i] == null){

                result[i] = null;
                lastFoundValue = null;

            }

            else if(a[i] == null || b[i] == null){

                while(true){

                    if(a[i + 1] == null && b[i + 1] == null){

                        while(tempStart <= i){

                            result[tempStart] = null;
                            tempStart++;

                        }
                        
                        lastFoundValue = null;
                        break;

                    }

                    if(a[i + 1] != null && b[i + 1] != null){

                        while(tempStart <= i){

                            result[tempStart] = find(a[i + 1], b[i + 1]);
                            tempStart++;

                        }

                        break;

                    }

                    else{

                        i++;

                    }

                }

            }

            else{

                result[i] = find(a[i], b[i]);

            }

        }

        return result;
        
    }

}