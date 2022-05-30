import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		/*
		 * Indicando o caminho dos arquivos que serão coletados e salvos durante a execução ("Manual").
		 * Arquivos no formato .csv ou .txt com separador de campos ";".
		 */
		//System.out.println("Informe o caminho do arquivo de entrada: ");
		//String strPathIn = scanner.nextLine();
		//System.out.println("Informe o caminho do arquivo De x Para: ");
		//String strPathFromTo = scanner.nextLine();
		
		/*
		 * Indicando os caminhos dos arquivos que serão coletados e salvos ("Automático").
		 * Os arquivos "ctbFile" e "FromTo" precisam estar salvos no caminho indicado das String's.
		 * Arquivos no formato .csv ou .txt com separador de campos ";".
		 * Certifique-se que todos os caminhos informados nas Strings abaixo, existem no sistema, pois o código abaixo irá criar
		 * uma nova pasta e novos arquivos a partir do caminho indicado.
		 */
		String strPathIn = "C:\\Folder\\ctbFile.csv";
		String strPathFromTo = "C:\\Folder\\FromTo.csv";
		String strPathOut = "";
		String strPathOutNewAccount = "";
		
		try {
			File fl = new File(strPathIn);
			strPathOut = fl.getParent();
			Boolean pathOut = new File(strPathOut + "\\out").mkdir();
			strPathOutNewAccount = strPathOut + "\\out\\newAccountsFile.csv";
			strPathOut += "\\out\\newCtbFile.csv";
		} catch (Exception e) {
			strPathOut = "C:\\Folder\\newCtbFile.csv";
			strPathOutNewAccount = "C:\\Folder\\newAccountsFile.csv";
		}
		
		
		/*
		 * Lista para armazenar os dados dos arquivos de entrada.
		 */
		List<String> linesIn = new ArrayList<>();
		List<String> linesFromto = new ArrayList<>();
		List<String> newAccounts = new ArrayList<>();
		
		/*
		 * Leitura do arquivo de entrada "ctbFile" com os lançamentos contábeis.
		 * Atribuindo o conteúdo para o ArraysList "linesIn".
		 */
		try (BufferedReader br = new BufferedReader(new FileReader(strPathIn))) {
			String line = br.readLine();
			
			while(line != null) {
				linesIn.add(line);
				line = br.readLine();
			}					
		} catch (IOException IOe) {
			System.out.println("Error BufferedReader(In): " + IOe.getMessage());
		}
		
		/*
		 * Leitura do arquivo de base de dados "FromTo" das contas contábeis.
		 * Este arquivo tem a função de guardar o De x Para das Contas.
		 * Atribuindo o conteúdo para o ArraysList "linesFromto".
		 */
		try (BufferedReader brn = new BufferedReader(new FileReader(strPathFromTo))) {
			String lineFt = brn.readLine();
			
			while(lineFt != null) {
				linesFromto.add(lineFt);
				lineFt = brn.readLine();
			}
		} catch (IOException IOe) {
			System.out.println("Error BufferedReader(FromTo): " + IOe.getMessage());
		}
	
		/*
		 * Escrita/Geração do arquivo "newCtbFile" com o De x Para das contas contábeis.
		 */
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(strPathOut))) {
			//Add para o novo arquivo o mesmo cabeçalho do arquivo de entrada
			bw.write(linesIn.get(0));
			
			//Tratamento das linhas do arquivo de entrada. No caso de um campo vazio, não há tratamentos e o campo será incluído como vazio no novo arquivo.
			//Se a conta de Débito/Crédito não existir no arquivo "FromTo", a mesma será adicionada em um Array "newAccounts";
			for(int i = 1; i < linesIn.size(); i++) {
				bw.newLine();
				
				String[] vecIn = linesIn.get(i).split(";");
				String date = vecIn[0];
				
				String debit = "0";
				String credit = "0";
				
				String accountDeb = vecIn[1];
				String accountCred = vecIn[2];
				
				//De x Para de Contas.
				int contDeb = 0;
				int contCred = 0;
				for(String lineFt : linesFromto) {
					String[] accounts = lineFt.split(";");
					
					if(accounts[0].equals(accountDeb)) {
						debit = accounts[1];
					} else {
						if(!newAccounts.contains(accountDeb)) {
							newAccounts.add(accountDeb);
						}
					}
					
					if(accounts[0].equals(accountCred)) {
						credit = accounts[1];
					} else {
						if(!newAccounts.contains(accountCred)) {
							newAccounts.add(accountCred);
						}
					}
				}

				String value = vecIn[3];
				String code = vecIn[4];
				String comment = vecIn[5];
				String newLineFile = date + ";" + debit + ";" + credit + ";" + value + ";" + code + ";" + comment;
				
				bw.write(newLineFile);
			}
		} catch (IOException IOe) {
			System.out.println("Error BufferedWriter Out: " + IOe.getMessage());
		}
		
		//Retirar contas de "newAccounts" que já existem em "linesFromto".
		//Mantendo assim apenas as novas contas encontradas no arquivo "ctbFile".
		for(String ft : linesFromto) {
			String[] line = ft.split(";");
			if(newAccounts.contains(line[0])) {
				newAccounts.remove(newAccounts.lastIndexOf(line[0]));
			}
			
		}
		
		//Validação tamanho do vetor para newAccounts. Se maior que zero, possui contas novas.
		if(newAccounts.size() > 0) {
			/*
			 * Escrita/Geração do arquivo "newAccounts" com as contas nque fazem parte do arquivo "ctbFile" mas não estão presentes no arquivo "FromTo".
			 */
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(strPathOutNewAccount))) {
				bw.write(linesFromto.get(0));
				
				for(String account : newAccounts) {
					bw.newLine();
					bw.write(account);
				}
				
			} catch (IOException IOe) {
				System.out.println("Error BufferedWriter new Accounts: " + IOe.getMessage());
			}
		}

		scanner.close();
	}
}