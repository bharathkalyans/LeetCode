package learning;

interface Singable {
    void say();
}

public class MethodReferences {

    /*
    Method References are used for following only :::
    * 1.Static Method
    * 2. Instance Method
    * 3.Constructor Method
    * */

    public void singingSomething() {
        System.out.println("Happy Halloween");
    }

    public static void main(String[] args) {
        MethodReferences object = new MethodReferences();
        Singable singable = object::singingSomething;//Accessing non static methids
        singable.say();

        //Anonymous Object
        Singable singable2 = new MethodReferences()::singingSomething;
        singable2.say();

        /*
        1.Using STATIC METHOD// Method must be static
        Singable singable = MethodReferences::singingSomething;
        singable.say();
        */


    }
}
