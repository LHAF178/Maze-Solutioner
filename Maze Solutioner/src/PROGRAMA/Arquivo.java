package PROGRAMA;

import java.io.*;

/**
 * Classe que faz a leitura do caminho informado pelo usu�rio at� o arquivo, tamb�m
 * pegando informa��es das Linhas e Colunas presentes, checando a extens�o, 
 * o tamanho e o conte�do desse arquivo.
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
    * Construtor da classe Arquivo que usa a vari�vel BufferedReader  leitor
    * para buscar o caminho digitado pelo usu�rio e passando os m�todos 
    * checarExtensao() e isArquivoVazio(), que fazem valida��es referentes
    * ao tipo e o conte�do do arquivo.
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
		 throw new Exception ("O caminho para o arquivo � inv�lido. Tente novamente");
     }
     
     	this.caminho = caminho;
    	 checarExtensao(caminho);
    	 isArquivoVazio();  
   }
   
   /**
    * M�todo que, passando a vari�vel caminho (representa o caminho lido pelo BufferedReader)
    *  como par�metro verifica a extens�o do arquivo, 
    *  que deve ser .txt, e caso um arquivo
    * de outro tipo seja lido, uma excess�o aponta o erro.
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
    * M�todo que verifica o conte�do do arquivo usando o leitor
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
        throw new Exception ("Arquivo inv�lido, ocorreu um erro ao tentar ler.");
      }
      this.leitor.close();
   }

   /**
    * M�todo getter usado para retornar as linhas lidas no arquivo, checando 
    * primeiramente se elas se igualam a 0 e lan�ando excess�o
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
          throw new Exception ("O arquivo est� em branco");
      }
      
      else
      {
    	  return this.Linhas;
      }
   }
   
   /**
    * M�todo getter usado para retornar as colunas lidas no arquivo, checando 
    * primeiramente se elas se igualam a 0 e lan�ando excess�o
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
        throw new Exception ("O arquivo est� em branco");
      }
      
      else
      {
    	  return this.Colunas;
      }
   }
   
   /**
    * M�todo que verifica se o arquivo est� vazio, usando a vari�vel
    * leitor e usando o m�todo tamanhoArquivo() para checar no final.
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
       throw new Exception("O arquivo escolhido n�o pode ser vazio");
     }
     
     else
     {
    	 this.leitor.close();
    	 this.leitor = new BufferedReader(new FileReader(caminho));
    	 tamanhoArquivo();
     }
   }
   
   /**
    * M�todo "getter" usado para retornar o caminho do arquivo
    * digitado pelo usu�rio
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
    	  throw new Exception ("� necess�rio fornecer um caminho para come�ar");
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
