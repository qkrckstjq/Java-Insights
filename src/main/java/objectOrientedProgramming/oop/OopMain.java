package objectOrientedProgramming.oop;

public class OopMain {
    public static void main(String[] args) {
        //캡슐화
        Capsule capsule = new Capsule();
//        capsule.privateMethod(); Capsule 내부가 아닌 외부 클래스인 OopMain 클래스안에서는 사용 불가능
        capsule.publicMethod();
        //====================================================================================

        //상속
        ChildEntitiy child = new ChildEntitiy();
        child.getCreatedAt(); //BaseEntitiy에서 생성자 메소드에 기술했기 때문에 날짜 출력

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        child.setModifiedAt(); //BaseEntitiy 상속 후 원하는 추가한 메소드
        child.getModifiedAt();
        //====================================================================================

        //추상화
        //Interface로 Animal 생성
        //abstract class로 Bird 생성
        //====================================================================================

        //다형성
        Hawk hawk = new Hawk("hawk"); //추상 클래스로부터 상속받은 생성자 함수 사용
        hawk.fly();                         //추상 클래스로부터 상속받은 fly 메소드
        hawk.sound();                       //Interface로부터 상속받고 Hawk 클래스에서 구현한 sound 메소드
    }


}
