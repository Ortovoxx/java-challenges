public class Interpreter {
    Interpreter(Boolean step, Boolean print, String filePath) {
        ReadFile file = new ReadFile(filePath);

        file.validateCharacters();
        file.parseStatements();

        statements = file.getStatements();
    }
