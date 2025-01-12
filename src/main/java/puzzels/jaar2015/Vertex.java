package puzzels.jaar2015;

//import org.jetbrains.annotations.NotNull;

class Vertex {

    String name;
    Integer weight;
    Boolean visited;

    Vertex(String label) {
        this.name = label;
        this.weight = 0;
        this.visited=false;
    }

    public String getName() {
        return name;
    }
    public Integer getWeight() {
        return weight;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    public Boolean isVisited() {
        return visited;
    }
    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    // equals and hashCode
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj.getClass() == this.getClass())) return false;

        Vertex o =(Vertex) obj;
        return this.name.equals(o.getName());
    }

    @Override
    public String toString() {
        return getName() + " " + weight;
    }

}
