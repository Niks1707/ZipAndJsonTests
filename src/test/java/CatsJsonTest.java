import CatsData.Cat;
import CatsData.Cats;
import CatsData.Toy;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class CatsJsonTest {

    private ClassLoader cl = CatsJsonTest.class.getClassLoader();

    @DisplayName("Проверка состава котов")
    @Test
    void jsonFileParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("cats.json")
        )) {
            ObjectMapper mapper = new ObjectMapper();
            Cats actualData = mapper.readValue(reader, Cats.class);
            List<Cat> cats = actualData.getCats();
            Cat firstCat = cats.get(0);
            assertThat(firstCat.getName()).isEqualTo("Ginger");
            assertThat(firstCat.getAge()).isEqualTo(3);
            assertThat(firstCat.getBreed()).isEqualTo("British Shorthair");
            List<Toy> firstCatToys = firstCat.getFavoriteToys();
            Toy firstToyFirstCat = firstCatToys.get(0);
            assertThat(firstToyFirstCat.getName()).isEqualTo("Mouse");
            assertThat(firstToyFirstCat.getType()).isEqualTo("plush");
            Cat secondCat = cats.get(1);
            assertThat(secondCat.getName()).isEqualTo("Cinnamon");
            assertThat(secondCat.getAge()).isEqualTo(5);
            assertThat(secondCat.getBreed()).isEqualTo("Siamese");
            List<Toy> secondCatToys = secondCat.getFavoriteToys();
            Toy firstToySecondCat = secondCatToys.get(0);
            assertThat(firstToySecondCat.getName()).isEqualTo("Laser pointer");
            assertThat(firstToySecondCat.getType()).isEqualTo("laser");
        }
    }
    }