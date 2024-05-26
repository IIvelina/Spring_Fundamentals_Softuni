package bg.softuni.intro1.ioc;

public class Dog implements Animal{

    private boolean superDog;

    public Dog(){
        this(false);
    }

    public Dog(boolean superDog) {
        this.superDog = superDog;
    }

    @Override
    public void makeNoise() {
        if (superDog){
            System.out.println("Super Bark Super bark");
        }else {
            System.out.println("Bark bark");
        }
    }
}
