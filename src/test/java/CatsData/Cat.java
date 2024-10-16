package CatsData;
import java.util.List;

public class Cat {
    private String name;
    private int age;
    private String breed;
    private List<Toy> favoriteToys;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
    public List<Toy> getFavoriteToys() { return favoriteToys; }
    public void setFavoriteToys(List<Toy> favoriteToys) { this.favoriteToys = favoriteToys; }
}
