package PROGRAMA;

import java.io.*;
import PROGRAMA.Labirinto;
import PROGRAMA.Arquivo;

/**
 * Classe Main, usada para chamar M�todos criados em outras classes,
 * assim chegando ao leitor do labirinto, resolvendo-o e o imprimindo no final.
 * Aqui tamb�m � instanciado o Labirinto, onde ele � carregado, trazendo os dados
 * do n�mero de Linhas e Colunas presentes nele
 * 
 * @author Lucas Faustino
 * @author Leonardo Belanga
 */
public class Main
{
   private static BufferedReader leitor = new BufferedReader (new InputStreamReader(System.in));

   public static void main (String[] args) throws Exception
   {
	     System.out.print("Digite o caminho para o arquivo do labirinto: ");
         Arquivo arquivo = null;
         Labirinto labirinto = null;
	     
	     try
	     {
    	 String caminho = leitor.readLine();
    	 	arquivo = new Arquivo(caminho);
	     }
	     
	     catch(Exception Error)              
	     {
			 System.err.println("\n Arquivo inv�lido - ERRO: "+ Error);
	     }
		
	     try
	     {
	     labirinto = new Labirinto(arquivo.carregarArquivo(),arquivo.getLinhas(), arquivo.getColunas());
	     }
	     
	     catch(Exception Error)
	     {
	    	 System.err.println("ERRO: " + Error);
	     }
	     
	     try
	     {
		 String solucao = labirinto.solucaoLabirinto();
		 System.out.println("\n");
    	 System.out.println(solucao);
		 System.out.println("\n!!RESOLU��O DO LABIRINTO ACIMA!! \n");
		 
		 System.out.println("Obrigado por usar o programa! Para resolver outro labirinto, execute novamente.");
	     }
	     
	     catch(Exception Error)
	     {
	    	 System.err.println("Falha ao tentar achar a solu��o - ERRO: "+ Error);
	     }
   }
}
