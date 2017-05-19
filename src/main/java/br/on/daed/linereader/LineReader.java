/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.on.daed.linereader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author csiqueira
 */
public class LineReader {

	private static int countQuestion = 0;
	
	private static final int MAX_OUTPUT_LINES = 20 * 4;
	
	public static void main(String[] args) {

		List<String> output = new ArrayList<String>();
		
		String path = "entrada.txt";
		
		System.out.println("LENDO ARQUIVO DE ENTRADA: " + path);

		List<String> entrada = null;
		
		try {
			entrada = Files.readAllLines(Paths.get(path));
		} catch (IOException ex) {
			System.out.println("Arquivo entrada.txt nao encontrado");
			System.exit(0);
		}

		int i = entrada.size() - 1;
		int added = 0;
		while(output.size() != (MAX_OUTPUT_LINES + LineReader.countQuestion) && !entrada.isEmpty()) {
			
			if(!entrada.get(i).isEmpty()) {
			
				if(added++ % 4 == 0) {
					String separator = (++LineReader.countQuestion) + ")";
					output.add(separator);
				}

				String remove = "";
				int idx = -1;
				
				do {
					Double rng = Math.random();
					idx = (int) (rng * entrada.size());
					remove = entrada.get(idx);
				} while (remove.isEmpty());
				
				entrada.remove(idx);
				output.add(remove);
			} else {
				entrada.remove(i);
			}
			
			i--;
		}

		try {
			Files.write(Paths.get("saida.txt"), output);
		} catch (IOException ex) {
			System.out.println("Arquivo saida.txt nao pode ser criado");
			System.exit(0);
		}
	}

}
