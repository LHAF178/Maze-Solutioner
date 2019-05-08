package PROGRAMA;

import java.io.*;

/**
 * Classe que faz a leitura do caminho informado pelo usuário até o arquivo, também
 * pegando informações das Linhas e Colunas presentes, checando a extensão, 
 * o tamanho e o conteúdo desse arquivo.
 * 
 * 
 * @author Lucas Faustino
 * @author Leonardo Belanga
 *
 */
public class Arquivo
{
   String conteudo;
   String caminho = null;
   int Linhas = 0;
   int Colunas = 0;
   
   BufferedReader leitor = null;
   
   /**
    * Construtor da classe Arquivo que usa a variável BufferedReader  leitor
    * para buscar o caminho digitado pelo usuário e passando os métodos 
    * checarExtensao() e isArquivoVazio(), que fazem validações referentes
    * ao tipo e o conteúdo do arquivo.
    * 
    * @author Lucas Faustino
    *@author Leonardo Belanga
    *
    */
   
   public Arquivo(String caminho) throws Exception
   {
	   try
	   {
		     if(this.leitor == null)
		     {
		    	 this.leitor = new BufferedReader(new FileReader(caminho));
		     }
	   }
     
     catch(Exception Error)              
     {
		 throw new Exception ("O caminho para o arquivo é inválido. Tente novamente");
     }
     
     	this.caminho = caminho;
    	 checarExtensao(caminho);
    	 isArquivoVazio();  
   }
   
   /**
    * Método que, passando a variável caminho (representa o caminho lido pelo BufferedReader)
    *  como parâmetro verifica a extensão do arquivo, 
    *  que deve ser .txt, e caso um arquivo
    * de outro tipo seja lido, uma excessão aponta o erro.
    * 
    * @author Lucas Faustino
    * @author Leonardo Belanga
    * @param caminho
    * @throws Exception
    */
   private void checarExtensao(String caminho) throws Exception
   {
	   if(!caminho.endsWith(".txt"))
	   {
		   throw new Exception ("O formato do arquivo deve ser .txt");
	   }
   }
   
   /**
    * Método que verifica o conteúdo do arquivo usando o leitor
    * e checa se o tamanho de linhas e colunas
    * confere com o que foi digitado na primeira linha do arquivo
    * 
    * @author Lucas Faustino
    * @author Leonardo Belanga
    * @throws Exception
    */
   private void tamanhoArquivo() throws Exception
   {  
      try
      {
    	  for(conteudo = null; (conteudo = this.leitor.readLine()) != null; this.Linhas++ )
    	  {
    		  if(this.Colunas < conteudo.length())
    			  this.Colunas = conteudo.length();
    	  }
      }
      catch(Exception error)
      {
        this.leitor.close();
        throw new Exception ("Arquivo inválido, ocorreu um erro ao tentar ler.");
      }
      this.leitor.close();
   }

   /**
    * Método getter usado para retornar as linhas lidas no arquivo, checando 
    * primeiramente se elas se igualam a 0 e lançando excessão
    * 
    * @author Lucas Faustino
    * @author Leonardo Belanga
    * @return
    * @throws Exception
    */
   public int getLinhas() throws Exception
   {
      if(this.Linhas == 0)
      {
          throw new Exception ("O arquivo está em branco");
      }
      
      else
      {
    	  return this.Linhas;
      }
   }
   
   /**
    * Método getter usado para retornar as colunas lidas no arquivo, checando 
    * primeiramente se elas se igualam a 0 e lançando excessão
    * 
    * @author Lucas Faustino
    * @author Leonardo Belanga
    * @return
    * @throws Exception
    */
   public int getColunas() throws Exception
   {
      if(Colunas == 0)
      {
        throw new Exception ("O arquivo está em branco");
      }
      
      else
      {
    	  return this.Colunas;
      }
   }
   
   /**
    * Método que verifica se o arquivo está vazio, usando a variável
    * leitor e usando o método tamanhoArquivo() para checar no final.
    * 
    * @author Lucas Faustino
    * @auhtor Leonardo Belanga
    * @throws Exception
    */
   private void isArquivoVazio() throws Exception
   {
     if(leitor.readLine() == null)
     {
       this.leitor.close();
       throw new Exception("O arquivo escolhido não pode ser vazio");
     }
     
     else
     {
    	 this.leitor.close();
    	 this.leitor = new BufferedReader(new FileReader(caminho));
    	 tamanhoArquivo();
     }
   }
   
   /**
    * Método "getter" usado para retornar o caminho do arquivo
    * digitado pelo usuário
    *  
    * @author Lucas Faustino
    * @author Leonardo Belanga
    * @throws Exception
    */
   public String getCaminhoArquivo() throws Exception
   {
      if(this.caminho != null) 
      {
    	  return this.caminho;
      }
      
      else
      {
    	  throw new Exception ("É necessário fornecer um caminho para começar");
      }
   }

   /**
    * Classe que usa o leitor para carregar o arquivo, atualizar a
    * String conteudo e passar as linhas e colunas (que dependem das Coordenadas)
    * para uma Matriz[linha][coluna] Object.
    * 
    * @author Lucas Faustino 
    * @author Leonardo Belanga
    * @return Matriz
    * @throws Exception
    */
   public Object[][] carregarArquivo() throws Exception
   {
	    int linha;
	    int coluna;
       Object Matriz[][] = null;
	   
	   try {
	   	  this.leitor = new BufferedReader(new FileReader(caminho));
     	  Matriz = new Object[this.Linhas][this.Colunas];
    	  
    	  for(linha = 0; linha < this.Linhas; linha++)
    	  {
    		  conteudo = this.leitor.readLine();
    		  
    		  for(coluna = 0; coluna < conteudo.length(); coluna++)
    		  {
    			  Matriz[linha][coluna] = conteudo.charAt(coluna);
    		  }
    	  }
	   }
	  
	   catch(Exception Error)
	   {
    	  this.leitor.close();
	   }
    	  return Matriz;
   }
}
