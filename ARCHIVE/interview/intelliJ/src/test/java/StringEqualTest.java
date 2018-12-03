class StringEqualTest {

    public static void main(String[] args) {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2);  //false
        System.out.println(s1 == s5);//true
        System.out.println(s1 == s6);//false
        System.out.println(s1 == s6.intern());//true
        System.out.println(s2 == s2.intern());//false
        System.out.println("s1.hashcode="+s1.hashCode());
        System.out.println("s2.hashcode="+s2.hashCode());
        System.out.println("s3.hashcode="+s3.hashCode());
        System.out.println("s4.hashcode="+s4.hashCode());
        System.out.println("s5.hashcode="+s5.hashCode());
        System.out.println("s6.hashcode="+s6.hashCode());
//        s1.hashcode=-1615787847
//        s2.hashcode=-1615787847
//        s3.hashcode=1355265636
//        s4.hashcode=3351637
//        s5.hashcode=-1615787847
//        s6.hashcode=-1615787847
    }
}