package objectOrientedProgramming.oop;

public class Capsule {
    private String privateString;

    public void setPrivateString(String privateString) {
        this.privateString = privateString;
    }

    public String getPrivateString() {
        return privateString;
    }

    private void privateMethod() {
        System.out.println("private Method");
    }

    public void publicMethod() {
        System.out.println("public Method");
    }
}
