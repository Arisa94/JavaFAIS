
class SuperArrayParser{

    public Object lastFoundValue;

    private Object find(Object a, Object b){

        Object result = null;

        if(a instanceof Integer){

            System.out.println("Mam int!");

            if(b instanceof Integer){

                result = (int) a + (int) b;

            }

            else{

                result = lastFoundValue;

            }

        }

        else if(b instanceof Integer){

            System.out.println("Mam int!");

            if(a instanceof Integer){

                result = (int) a + (int) b;

            }

            else{

                result = lastFoundValue;

            }

        }
        
        else if(a instanceof Float){

            System.out.println("Mam float!");

            if(b instanceof Float){

                result = (float) a * (float) b;

            }

            else{

                result = lastFoundValue;

            }

        }

        else if(b instanceof Float){

            System.out.println("Mam float!");

            if(a instanceof Float){

                result = (float) a * (float) b;

            }

            else{

                result = lastFoundValue;

            }

        }
        
        else if(a instanceof Double){

            System.out.println("Mam double!");

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

            System.out.println("Mam double!");

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
        
        if(a instanceof String){

            System.out.println("Mam string!");

            if(b instanceof Byte){

                result = -(byte) b;

            }

            else if(b instanceof Double){

                result = -(double) b;

            }

            else if(b instanceof Float){

                result = -(float) b;

            }

            else if(b instanceof Integer){

                result = -(int) b;

            }

            else if(b instanceof Long){

                result = -(long) b;

            }

            else if(b instanceof Short){

                result = -(short) b;

            }

        }

        if(b instanceof String){

            System.out.println("Mam string!");

            if(a instanceof Byte){

                result = -(byte) a;

            }

            else if(a instanceof Double){

                result = -(double) a;

            }

            else if(a instanceof Float){

                result = -(float) a;

            }

            else if(a instanceof Integer){

                result = -(int) a;

            }

            else if(a instanceof Long){

                result = -(long) a;

            }

            else if(a instanceof Short){

                result = -(short) a;

            }

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