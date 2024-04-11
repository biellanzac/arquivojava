import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

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
        return "[" + id + " ## " +
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
                wizard + "]";
    }

    public static void main(String[] args) {
        List<Personagem> personagens = carregarDadosDoCSV("characters.csv");
        Scanner scanner = new Scanner(System.in);

        String idDesejado;
        do {
            idDesejado = scanner.nextLine();

            if (!idDesejado.equals("FIM")) {
                boolean encontrado = false;
                for (Personagem personagem : personagens) {
                    if (personagem.getId().equals(idDesejado)) {
                        System.out.println(personagem);
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    System.out.println("Personagem não encontrado.");
                }
            }
        } while (!idDesejado.equals("FIM"));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAlternateNames() {
        return alternateNames;
    }

    public void setAlternateNames(List<String> alternateNames) {
        this.alternateNames = alternateNames;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getAncestry() {
        return ancestry;
    }

    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getPatronus() {
        return patronus;
    }

    public void setPatronus(String patronus) {
        this.patronus = patronus;
    }

    public boolean isHogwartsStaff() {
        return hogwartsStaff;
    }

    public void setHogwartsStaff(boolean hogwartsStaff) {
        this.hogwartsStaff = hogwartsStaff;
    }

    public boolean isHogwartsStudent() {
        return hogwartsStudent;
    }

    public void setHogwartsStudent(boolean hogwartsStudent) {
        this.hogwartsStudent = hogwartsStudent;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public String getEyeColour() {
        return eyeColour;
    }

    public void setEyeColour(String eyeColour) {
        this.eyeColour = eyeColour;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHairColour() {
        return hairColour;
    }

    public void setHairColour(String hairColour) {
        this.hairColour = hairColour;
    }

    public boolean isWizard() {
        return wizard;
    }

    public void setWizard(boolean wizard) {
        this.wizard = wizard;
    }
}
