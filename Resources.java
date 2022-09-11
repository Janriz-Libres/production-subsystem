public class Resources {
    
    private int supply;

    public Resources(int s) {
        this.supply = s;
    }

    public boolean isEnough(int required) {
        return required <= supply;
    }
}
