package cn.haitaoss.springboot;

public class TmpCode {


    public static void main(String[] args) throws Exception {
        try {
            // throw new RuntimeException("异常了");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.toString());
            e.printStackTrace();
        } finally {
        }
        System.out.println(test());

        System.out.println(Override.class.getSuperclass());
    }

    public static String test() {
        /*try {
            return invoke();
        } catch (Exception e) {
            e.printStackTrace();
            return "exception";
        } finally {
            System.out.println("hello");
        }*/
       /* try (FileInputStream fileInputStream = new FileInputStream("")){
        } catch (Exception e) {
            e.printStackTrace();
        }finally {}*/

        return "";
    }

    private static String invoke() {
        System.out.println("执行了");
        return "invoke";
    }


}
