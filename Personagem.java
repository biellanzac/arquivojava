import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Personagem {
    private String id;
    private String name;
    private List<String> alternateNames;
    private String house;
    private String ancestry;
    private String species;
    private String patronus;
    private boolean hogwartsStaff;
    private boolean hogwartsStudent;
    private String actorName;
    private boolean alive;
    private String eyeColour;
    private String gender;
    private String hairColour;
    private boolean wizard;

    public Personagem(String id, String name, List<String> alternateNames, String house, String ancestry,
            String species, String patronus, boolean hogwartsStaff, boolean hogwartsStudent, String actorName,
            boolean alive, String eyeColour, String gender, String hairColour, boolean wizard) {
        this.id = id;
        this.name = name;
        this.alternateNames = alternateNames;
        this.house = house;
        this.ancestry = ancestry;
        this.species = species;
        this.patronus = patronus;
        this.hogwartsStaff = hogwartsStaff;
        this.hogwartsStudent = hogwartsStudent;
        this.actorName = actorName;
        this.alive = alive;
        this.eyeColour = eyeColour;
        this.gender = gender;
        this.hairColour = hairColour;
        this.wizard = wizard;
    }

    // Getters and setters...

    public static List<Personagem> carregarDadosDoCSV(String arquivo) {
        List<Personagem> personagens = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            // Ignorar a primeira linha (cabeçalho)
            br.readLine();
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                String id = dados[0];
                String name = dados[1];
                List<String> alternateNames = extrairNomesAlternativos(dados[2]);
                String house = dados[3];
                String ancestry = dados[4];
                String species = dados[5];
                String patronus = dados[6];
                boolean hogwartsStaff = Boolean.parseBoolean(dados[7]);
                boolean hogwartsStudent = Boolean.parseBoolean(dados[8]);
                String actorName = dados[9];
                boolean alive = Boolean.parseBoolean(dados[10]);
                String eyeColour = dados[12];
                String gender = dados[13];
                String hairColour = dados[14];
                boolean wizard = Boolean.parseBoolean(dados[15]);

                Personagem personagem = new Personagem(id, name, alternateNames, house, ancestry, species, patronus,
                        hogwartsStaff, hogwartsStudent, actorName, alive, eyeColour, gender, hairColour, wizard);
                personagens.add(personagem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return personagens;
    }

    private static List<String> extrairNomesAlternativos(String texto) {
        List<String> nomes = new ArrayList<>();
        Pattern pattern = Pattern.compile("'(.*?)'");
        Matcher matcher = pattern.matcher(texto);
        while (matcher.find()) {
            nomes.add(matcher.group(1));
        }
        return nomes;
    }

    // Método para imprimir os dados do personagem (opcional)
    @Override
    public String toString() {
        return id + " ## " +
                name + " ## " +
                "{" + String.join(", ", alternateNames) + "} ## " +
                house + " ## " +
                ancestry + " ## " +
                species + " ## " +
                patronus + " ## " +
                hogwartsStaff + " ## " +
                hogwartsStudent + " ## " +
                actorName + " ## " +
                alive + " ## " +
                eyeColour + " ## " +
                gender + " ## " +
                hairColour + " ## " +
                wizard + " ## ";
    }
    

    public static void main(String[] args) {
        List<Personagem> personagens = carregarDadosDoCSV("characters.csv");
        for (Personagem personagem : personagens) {
            System.out.println(personagem);
        }
    }
}
