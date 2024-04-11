#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_ALTERNATE_NAMES 10
#define MAX_NAME_LENGTH 50
#define MAX_LINE_LENGTH 1024

struct Personagem {
    char id[MAX_NAME_LENGTH];
    char name[MAX_NAME_LENGTH];
    char alternateNames[MAX_ALTERNATE_NAMES][MAX_NAME_LENGTH];
    int alternateNamesCount;
    char house[MAX_NAME_LENGTH];
    char ancestry[MAX_NAME_LENGTH];
    char species[MAX_NAME_LENGTH];
    char patronus[MAX_NAME_LENGTH];
    int hogwartsStaff;
    int hogwartsStudent;
    char actorName[MAX_NAME_LENGTH];
    int alive;
    char eyeColour[MAX_NAME_LENGTH];
    char gender;
    char hairColour[MAX_NAME_LENGTH];
    int wizard;
};

void extrairNomesAlternativos(char *texto, char alternateNames[][MAX_NAME_LENGTH], int *alternateNamesCount) {
    char *token = strtok(texto, "'");
    *alternateNamesCount = 0;
    while (token != NULL) {
        strcpy(alternateNames[*alternateNamesCount], token);
        (*alternateNamesCount)++;
        token = strtok(NULL, "'");
    }
}

void carregarDadosDoCSV(const char *arquivo, struct Personagem personagens[], int *num_personagens) {
    FILE *file = fopen(arquivo, "r");
    if (file == NULL) {
        printf("Erro ao abrir o arquivo.\n");
        exit(1);
    }
    else {
        printf("Arquivo aberto com sucesso!\n");
    }

    // Ignorar a primeira linha (cabeçalho)
    char line[MAX_LINE_LENGTH];
    fgets(line, sizeof(line), file);

    // Processar os dados do arquivo
    while (fgets(line, sizeof(line), file) != NULL) {
        char id[MAX_NAME_LENGTH];
        char name[MAX_NAME_LENGTH];
        char alternateNames[MAX_ALTERNATE_NAMES][MAX_NAME_LENGTH];
        int alternateNamesCount;
        char house[MAX_NAME_LENGTH];
        char ancestry[MAX_NAME_LENGTH];
        char species[MAX_NAME_LENGTH];
        char patronus[MAX_NAME_LENGTH];
        int hogwartsStaff;
        int hogwartsStudent;
        char actorName[MAX_NAME_LENGTH];
        int alive;
        char eyeColour[MAX_NAME_LENGTH];
        char gender;
        char hairColour[MAX_NAME_LENGTH];
        int wizard;

        // Parsing da linha
        char *token = strtok(line, ";");
        snprintf(id, sizeof(id), "%s", token);

        token = strtok(NULL, ";");
        snprintf(name, sizeof(name), "%s", token);

        token = strtok(NULL, ";");
        extrairNomesAlternativos(token, alternateNames, &alternateNamesCount);

        token = strtok(NULL, ";");
        snprintf(house, sizeof(house), "%s", token);

        token = strtok(NULL, ";");
        snprintf(ancestry, sizeof(ancestry), "%s", token);

        token = strtok(NULL, ";");
        snprintf(species, sizeof(species), "%s", token);

        token = strtok(NULL, ";");
        snprintf(patronus, sizeof(patronus), "%s", token);

        token = strtok(NULL, ";");
        hogwartsStaff = atoi(token);

        token = strtok(NULL, ";");
        hogwartsStudent = atoi(token);

        token = strtok(NULL, ";");
        snprintf(actorName, sizeof(actorName), "%s", token);

        token = strtok(NULL, ";");
        alive = atoi(token);

        token = strtok(NULL, ";");
        token = strtok(NULL, ";"); // Pular o campo "dateOfBirth"

        token = strtok(NULL, ";");
        snprintf(eyeColour, sizeof(eyeColour), "%s", token);

        token = strtok(NULL, ";");
        gender = token[0];

        token = strtok(NULL, ";");
        snprintf(hairColour, sizeof(hairColour), "%s", token);

        token = strtok(NULL, ";");
        wizard = atoi(token);

        // Atribuição dos dados à struct Personagem
        struct Personagem personagem = {0};
        snprintf(personagem.id, sizeof(personagem.id), "%s", id);
        snprintf(personagem.name, sizeof(personagem.name), "%s", name);
        for (int i = 0; i < alternateNamesCount; i++) {
            snprintf(personagem.alternateNames[i], sizeof(personagem.alternateNames[i]), "%s", alternateNames[i]);
        }
        personagem.alternateNamesCount = alternateNamesCount;
        snprintf(personagem.house, sizeof(personagem.house), "%s", house);
        snprintf(personagem.ancestry, sizeof(personagem.ancestry), "%s", ancestry);
        snprintf(personagem.species, sizeof(personagem.species), "%s", species);
        snprintf(personagem.patronus, sizeof(personagem.patronus), "%s", patronus);
        personagem.hogwartsStaff = hogwartsStaff;
        personagem.hogwartsStudent = hogwartsStudent;
        snprintf(personagem.actorName, sizeof(personagem.actorName), "%s", actorName);
        personagem.alive = alive;
        snprintf(personagem.eyeColour, sizeof(personagem.eyeColour), "%s", eyeColour);
        personagem.gender = gender;
        snprintf(personagem.hairColour, sizeof(personagem.hairColour), "%s", hairColour);
        personagem.wizard = wizard;

        personagens[*num_personagens] = personagem;
        (*num_personagens)++;
    }

    fclose(file);
}





void imprimirPersonagens(struct Personagem personagens[], int num_personagens) {
    for (int i = 0; i < num_personagens; i++) {
        printf("Personagem %d:\n", i+1);
        printf("ID: %s\n", personagens[i].id);
        printf("Nome: %s\n", personagens[i].name);
        printf("Nomes alternativos:\n");
        for (int j = 0; j < personagens[i].alternateNamesCount; j++) {
            printf("  %s\n", personagens[i].alternateNames[j]);
        }
        printf("Casa: %s\n", personagens[i].house);
        printf("Ancestralidade: %s\n", personagens[i].ancestry);
        printf("Espécie: %s\n", personagens[i].species);
        printf("Patronus: %s\n", personagens[i].patronus);
        printf("Staff de Hogwarts: %d\n", personagens[i].hogwartsStaff);
        printf("Estudante de Hogwarts: %d\n", personagens[i].hogwartsStudent);
        printf("Nome do ator: %s\n", personagens[i].actorName);
        printf("Vivo: %d\n", personagens[i].alive);
        printf("Cor dos olhos: %s\n", personagens[i].eyeColour);
        printf("Gênero: %c\n", personagens[i].gender);
        printf("Cor do cabelo: %s\n", personagens[i].hairColour);
        printf("Bruxo: %d\n", personagens[i].wizard);
        printf("\n");
    }
}

int main() {
    struct Personagem personagens[100]; // Ajuste o tamanho conforme necessário
    int num_personagens = 0;

    carregarDadosDoCSV("C:/Users/1448038/Desktop/teste/characters.csv", personagens, &num_personagens);
    printf("%s", personagens[2].name);

    return 0;
}
