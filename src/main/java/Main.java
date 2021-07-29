import org.apache.commons.io.FilenameUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        String filePathIn = null;
        boolean filePathIsCorrect = false;
        BufferedReader bufferedReader = null;
        while (filePathIsCorrect == false) {
            System.out.println("Введите путь до файла с серийными номерами:");
            try {
                filePathIn = new BufferedReader(new InputStreamReader(System.in)).readLine().replaceAll("\"", "");
                File inSN = new File(filePathIn);
                bufferedReader = new BufferedReader(new FileReader(inSN));
                filePathIsCorrect = true;
            } catch (FileNotFoundException exception) {
                System.out.println("Нет такого файла или каталога.");
            }
        }

        String filePathOut = FilenameUtils.removeExtension(filePathIn) + "_result." + FilenameUtils.getExtension(filePathIn);
        FileWriter outSN = new FileWriter(filePathOut, false);

        String line = bufferedReader.readLine();
        int currentLine = 0;
        while (line != null) {
            currentLine++;
            StringBuffer stringBufferLine = new StringBuffer(line);
            if (line.length() == 20) {
                stringBufferLine.insert(16, '-');
                stringBufferLine.insert(13, '-');
                stringBufferLine.insert(8, '-');
                stringBufferLine.insert(2, '-');
                outSN.write(stringBufferLine.toString());
                outSN.append("\n");
            } else {
                System.out.println("Строка " + currentLine + " не похожа на серийный номер монитора DELL. Пропускается.");
            }
            line = bufferedReader.readLine();
        }
        outSN.flush();

        System.out.println("Измененные серийные номера сохранены в файле " + filePathOut);
    }
}
