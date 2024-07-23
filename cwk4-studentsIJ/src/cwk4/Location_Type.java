package cwk4;

public class Location_Type {
    private String type;

    public Location_Type(String type) {

        this.type = type;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

    @Override
    public String toString() {

        return type;
    }
}
