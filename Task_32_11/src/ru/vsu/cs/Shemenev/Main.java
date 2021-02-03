package ru.vsu.cs.Shemenev;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    /**
     * Выбрать из текста самое длинное предложение. Если в тексте несколько предложений одинаковой длины – выбрать
     * самое первое.Длина предложения считается по количеству слов. Словом считается непрерывная последовательность
     * символов (строчных и прописных) А-Я, A-Z и цифр. Концом предложения считаются символы точка, '!' и '?'. Началом
     * предложения – любой символ, отличный от данных трех и пробела, первый после конца предыдущего предложения
     * (или вообще первый в тексте). 
     */
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Введите предложение:");
        String str = input.nextLine();
        Pattern pattern = Pattern.compile("\\S[^\\.\\!\\?]+[\\.\\!\\?]");
        Matcher matcher = pattern.matcher(str);
        int length = 0;
        String[] result = new String[length];
        while (matcher.find()) {
            length++;
            result = Arrays.copyOf(result, length);
            result[length - 1] = matcher.group();
        }
        int maxWord = 0;
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            int countWord = checkCountWord(result[i]);
            if (countWord > maxWord) {
                maxWord = countWord;
                index = i;
            }
        }
        System.out.println((length == 0) ? "Не найдено матчей." : result[index]);

    }

    private static int checkCountWord(String str) {
        int length = 0;
        String [] word = new String[length];
        Pattern pat = Pattern.compile("[\\w[А-Яа-я]]+");
        Matcher mat = pat.matcher(str);
        while (mat.find()){
            length++;
            word = Arrays.copyOf(word,length);
            word[length-1] = mat.group();
        }
        int rez = word.length;
        Pattern checkPat = Pattern.compile("[0-9][A-Za-zа-яА-Я]|[A-Za-zа-яА-Я][0-9]");
        for (int i = 0;i<word.length;i++){
            Matcher checkMat = checkPat.matcher(word[i]);
            if(checkMat.find()){
                rez--;
            }
        }
        return rez;
    }

}
