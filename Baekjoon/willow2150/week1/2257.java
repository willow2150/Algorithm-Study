import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    /* 주어지는 문자열(화학식)의 최대 길이 **/
    private static final int MAX_CHEMICAL_FORMULA_LEN = 100;
    private static final int HYDROGEN_ATOMIC_MASS = 1;
    private static final int CARBON_ATOMIC_MASS = 12;
    private static final int OXYGEN_ATOMIC_MASS = 16;
    private static final char HYDROGEN = 'H';
    private static final char CARBON = 'C';
    private static final char OXYGEN = 'O';
    private static final char BRACKET_OPEN = '(';
    private static final char BRACKET_CLOSE = ')';
    /* 입력을 저장할 문자열 **/
    private static String chemicalFormula;
    
    public static void main(String[] args)  {
        /* 입력 처리 중 예외가 발생하면 프로그램 종료 **/
        if (!input()) return;
        /* 스택 생성 **/
        int[] stack = new int[MAX_CHEMICAL_FORMULA_LEN];
        /* 원자의 질량을 저장한 배열 **/
        int[] mass = new int['Z' + 1];
        /* 입력으로 주어진 문자열(화학식)의 길이 **/
        int chemicalFormulaLen = chemicalFormula.length();
        /* 스택의 top **/
        int top = -1;
        
        /* 원자의 질량을 저장 **/
        mass[HYDROGEN] = HYDROGEN_ATOMIC_MASS;
        mass[CARBON] = CARBON_ATOMIC_MASS;
        mass[OXYGEN] = OXYGEN_ATOMIC_MASS;

        for (int index = 0; index < chemicalFormulaLen; index++) {
            char character = chemicalFormula.charAt(index);
            if (character == BRACKET_OPEN) {
                stack[++top] = 0;
            } else if (character == BRACKET_CLOSE) {
                int sum = 0;
                while (stack[top] != 0)
                    sum += stack[top--];
                stack[top] = sum;
            } else if (character == CARBON || character == HYDROGEN || character == OXYGEN) {
                stack[++top] = mass[character];
            } else {
                stack[top] *= (character - '0');
            }
        }
        
        /* 스택에 저장된 값의 합(화학식량)을 계산 **/
        int formulaWeight = 0;
        for (int index = 0; index <= top; index++)
            formulaWeight += stack[index];
        
        /* 계산된 화학식량 출력 **/
        System.out.print(formulaWeight);
    }
    
    /**
     * 입력을 처리하는 메서드
     * @return : 입력이 정상 처리되면 true, 예외가 발생하면 false
     */
    public static boolean input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            chemicalFormula = reader.readLine();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
