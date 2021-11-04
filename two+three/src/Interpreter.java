import java.util.ArrayList;

public class Interpreter {
    ArrayList<Statement> statements;
    ArrayList<Variable> variables;

    Interpreter(Boolean step, Boolean print, String filePath) {
        ReadFile file = new ReadFile(filePath);

        file.validateCharacters();
        file.parseStatements();

        statements = file.getStatements();
    }

    public void nextStatement() {

    }
}
