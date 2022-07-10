package hello.core.singleton;

public class SingletonService {

    //자기 자신을 내부에 private static으로 가짐 - 클래스 레벨에 올라가므로 딱 하나만 존재
    //Java가 뜰 때 내부적으로 실행해서 자기 자신을 생성하고 instance의 참조로 넣어둔다.
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    //외부에서의 생성을 막기 위해 private 생성자 사용
    private SingletonService(){
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

    public static void main(String[] args) {
        SingletonService singletonService = new SingletonService();
    }
}
