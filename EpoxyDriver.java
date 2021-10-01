public class EpoxyDriver {
    public static void main(String[] args) {
        EpoxyFlooring epoxyFlooring = new EpoxyFlooring();

        epoxyFlooring.prepare("graveled");
        epoxyFlooring.prepare("metallic");
        epoxyFlooring.prepare("flaked");
    }
}
class Application implements EpoxyState{
    public void process(Epoxy epoxy){
        System.out.println("Applying "+ epoxy.toString());
        epoxy.setState(this);
    }
    public String toString(){
        return "Applying Epoxy";
    }
}
class Epoxy {
    private EpoxyTypes type;
    private EpoxyState state;

    public Epoxy(String type){
        this.type = EpoxyTypes.valueOf(type.toUpperCase());
        state = null;
    }

    public void setState(EpoxyState state){
        this.state = state;
    }

    public EpoxyState getState(){
        return state;
    }

    @Override
    public String toString(){
        return type.toString();
    }

}
class EpoxyFlooring {

    public void prepare(String flooringtype){
        Epoxy epoxy = new Epoxy(flooringtype);

        processState(epoxy, new SurfacePreparation());
        processState(epoxy, new SurfacePriming());
        processState(epoxy, new Mixing());
        processState(epoxy, new Application());

        System.out.println("Epoxy Flooring completed\n");
    }
    public void processState(Epoxy epoxy, EpoxyState state){
        epoxy.setState(state);
        epoxy.getState().process(epoxy);
        System.out.println(epoxy.getState().toString());
    }
}
interface EpoxyState {
    public void process(Epoxy epoxy);
}
enum EpoxyTypes {
    GRAVELED{
        @Override
        public String toString() {
            return "Graveled Epoxy Coatings";
        }
    },
    FLAKED{
        @Override
        public String toString() {
            return "Epoxy Flaked Floor Coatings";
        }
    },
    METALLIC{
        @Override
        public String toString() {
            return "Metallic Epoxy Floor Coatings";
        }
    }
}
class Mixing implements EpoxyState{

    public void process(Epoxy epoxy){
        System.out.println("Mixing epoxy ingredients");
        epoxy.setState(this);
    }
    
    public String toString(){
        return "Ingredient Mixing";
    }

}
class SurfacePreparation implements EpoxyState{
    
    public void process(Epoxy epoxy){
        System.out.println("Preparing Floor for "+ epoxy.toString());
        epoxy.setState(this);
    }

    public String toString(){
        return "Floor preparation state";
    }
}
class SurfacePriming implements EpoxyState{
    public void process(Epoxy epoxy){
        System.out.println("Priming of Surface");
        epoxy.setState(this);
    }
    public String toString(){
        return "Surface priming state";
    }
}