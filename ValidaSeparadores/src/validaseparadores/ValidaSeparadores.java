/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validaseparadores;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Administrador
 */
public class ValidaSeparadores
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception
    {
        FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String file = dialog.getDirectory() + dialog.getFile();
        File entrada = new File(file);
        Scanner leitor = new Scanner(entrada);
        StringBuilder sb = new StringBuilder();
        String open = "{[(<", close = "}])>";
        Stack<Character> pilha = new Stack<Character>();

        while (leitor.hasNextLine())
        {
            boolean valido = true;
            for (char c : leitor.nextLine().toCharArray())
            {
                if (valido)
                {
                    if (open.indexOf(c) != -1)
                    {
                        pilha.push(c);

                    } else if (close.indexOf(c) != -1 && !pilha.isEmpty())
                    {
                        if (open.indexOf(pilha.peek()) == close.indexOf(c))
                        {
                            pilha.pop();
                        }
                    } else
                    {
                        valido = false;
                    }
                }
                sb.append(c);
            }
            if (valido)
                sb.append(" - Inválido" + System.lineSeparator());
            else
                sb.append(" - OK" + System.lineSeparator());
        }
        File saida = new File(dialog.getFile().substring(0, dialog.getFile().length() - 4) + "-check.txt");
        if (!saida.createNewFile())
        {
            saida.delete();
            saida.createNewFile();
        }
        try (FileWriter writer = new FileWriter(saida))
        {
            writer.write(sb.toString());
        }
        System.exit(0);
    }

}
