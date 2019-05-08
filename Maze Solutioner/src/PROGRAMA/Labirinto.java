package PROGRAMA;

import PROGRAMA.Pilha;

/**
 * Classe que tem o objetivo de carregar o labirinto usando uma matriz de Object,
 * valida alguns fatores para a legitimidade do labirinto e dando a solução final
 * 
 * @author Lucas Faustino
 * @author Leonardo Belanga
 *
 */
public class Labirinto
{
    Object[][] labirinto = null;
    int Linhas, Colunas;
    Coordenada Atual = null;
    Pilha<Pilha<Coordenada>> possibilidades = null;
    Pilha<Coordenada> caminho = null;
    Pilha<Coordenada> inverso = null;
   
    /**
     * Construtor da classe Labirinto, que inicializa as variáveis Linhas, Colunas,
     * labirinto e cria as Pilhas caminho e possibilidades.
     * 
     * @param labirinto
     * @param Linhas
     * @param Colunas
     * @throws Exception
     */
   public Labirinto(Object[][] labirinto, int Linhas, int Colunas) throws Exception
   {
	   try {
    	 this.Linhas = Linhas;
    	 this.Colunas = Colunas;
    	 this.labirinto = labirinto;
    	 this.caminho = new Pilha<Coordenada>();
    	 this.possibilidades = new Pilha<Pilha<Coordenada>>();
    	 this.inverso = new Pilha<Coordenada>();
	   }
	   
	   catch(Exception Error)
	   {
		   throw new Exception(Error);
	   }
    	
	   try
	   {
		   validarLabirinto();
	   }
	   
	   catch(Exception Error)
	   {
		   throw new Exception(Error);
	   }
   }
  
   /**
    * Método getter que retorna ao chamante todas as Coordenadas
    * 	que foram percorridas na leitura 
    * 
    * @return caminho
    * @throws Exception
    */
   public Pilha<Coordenada> getCaminho() throws Exception
   {
	   return this.caminho;
   }
   
   private Pilha<Coordenada> adjacentes = null;
   
   /**
    * Método que implementa o modo Progressivo e o modo Regressivo
    * checando se o labirinto é nulo e se o caminho do arquivo é válido. Retorna 
    * a variável que imprime o labirinto
    * 
    * @return getTexto()
    * @throws Exception
    */
   public String solucaoLabirinto() throws Exception
   {
	   if(this.labirinto == null)
	   {
		   throw new Exception ("\n O labirinto não foi carregado. Escreva o caminho para o arquivo \n");
	   }
	   
	   while(!encontrarCaminho(this.Atual))
	   {
		  /* if(this.adjacentes.isVazia())
		   {
			   throw new Exception ("\n O labirinto não possui caminho possível até a saída, portanto, não existe solução.\n"+getTexto());
		   }*/

			   Coordenada auxiliar = this.Atual;
		   
		   //MODO PROGRESSIVO
		   if(!this.adjacentes.isVazia())
		   {
			   this.Atual = this.adjacentes.exibeTopo(); //jogando um caminho adjacente na atual
			   
			   this.adjacentes.desempilhar(); //retirando o valor usado da Pilha Adjacentes
			   
			   this.labirinto[this.Atual.getX()][this.Atual.getY()] = "*"; //colocando o '*' no caminho em que passar
			   
			   this.caminho.empilhar(this.Atual); //empilhando a Coordenada atual onde ja tiver passado na Pilha caminho 
			   
			   this.possibilidades.empilhar(this.adjacentes); //empilhando para possível múltipla possibilidade de caminhos 
			   
		   } 
		   
		   //PARTE REGRESSIVA
		   else
		   {	   
			   this.caminho.desempilhar(); //retirando o valor já usado da Pilha caminho
			   
			   this.labirinto[this.Atual.getX()][this.Atual.getY()] = " "; //colocando um espaço em branco onde era anteriormente '*'
			   
			   if(this.caminho.isVazia())
				   throw new Exception("O labirinto não tem saída.");
			   
			   this.Atual = this.caminho.exibeTopo(); //voltando gradativamente até o último caminho possível
			   
			   this.adjacentes = (this.possibilidades.exibeTopo()); //voltando exatamente no último caminho possível
			   
			   this.possibilidades.desempilhar(); //retirando a possibilidade ja usada guardada na Pilha
		   }
	   } 
	   
	   return getTexto();
   }
   
   /**
    * Método usado para passar as Linhas e Colunas para variáveis
    * e imprimir o labirinto de forma correta, de forma a apresentar sua solução.
    * 
    * @return getTexto()
 * @throws Exception 
    */
   private String getTexto() throws Exception
   {
	   int linha = 1;
	   String getTexto = "";

	   while(linha < this.Linhas)
	   {
	    	  for(int coluna = 0; coluna < this.Colunas; coluna++)
	    	  {
	    		  getTexto = getTexto + this.labirinto[linha][coluna];
	    	  }
	    	  getTexto = getTexto+"\n";
	    	  linha++;
	   }
	   
	   while(!this.caminho.isVazia())
	   {
		  this.inverso.empilhar(this.caminho.exibeTopo());
		   this.caminho.desempilhar();
	   }
	   
	   while(!this.inverso.isVazia())
	   {
		   getTexto = getTexto + "(" + (((Coordenada)this.inverso.exibeTopo()).getX() - 1) + "," +  ((Coordenada)this.inverso.exibeTopo()).getY() + ") ";
		   this.inverso.desempilhar();
	   }
	   return getTexto;
   }
   
   /** 
    * Método utilizado para identificar as Coordenadas, colocando a inicial 
    * na Coordenada em que for encontrada o char 'E', e fazendo contas
    * para identificar as direções de movimento
    * 
    * @param atual
    * @return false (caso não entre em nenhuma validação anterior)
    * @throws Exception
    */
   private boolean encontrarCaminho(Coordenada atual) throws Exception
   {
	   int x = atual.getX();
	   int y = atual.getY();
	   Coordenada auxAdjacente = null;
	   this.adjacentes = new Pilha<Coordenada>();
	 
	  //verificando adjacente na coordenada acima da atual (x-1,y)
	   try
	   {
		   if(x-1 <= this.Linhas-1 && y <= this.Colunas && x-1 >= 1)
			   if(this.labirinto[x-1][y].equals('S'))
			   {
				   this.adjacentes.empilhar(auxAdjacente = new Coordenada(x-1,y));
				   return true;
			   }
			   else if(this.labirinto[x-1][y].equals(' '))
			   {
				   this.adjacentes.empilhar(auxAdjacente = new Coordenada(x-1,y));
			   }
	   }
	   
	   catch(Exception Error)
	   {
		   throw new Exception (Error);
	   }
	   
	    //verificando adjacente na coordenada abaixo da atual (x+1,y)
	   try
	   {
		   if(x+1 <= this.Linhas-1 && y <= this.Colunas)
			   if(this.labirinto[x+1][y].equals('S'))
			   {
				   this.adjacentes.empilhar(auxAdjacente = new Coordenada(x+1,y));
				   return true;
			   }
			   else if(this.labirinto[x+1][y].equals(' '))
			   {
				   this.adjacentes.empilhar(auxAdjacente = new Coordenada(x+1,y));
			   }
	   }
	   
	   catch(Exception Error)
	   {
		   throw new Exception(Error);
	   }
	
	    //verificando adjacente na coordenada a esquerda da atual (x,y-1)
	   try
	   {
		   if(x <= this.Linhas-1 && y-1 <= this.Colunas-1 && y-1 >=0)
			   if(this.labirinto[x][y-1].equals('S'))
			   {
				   this.adjacentes.empilhar(auxAdjacente = new Coordenada(x,y-1));
				   return true;
			   }
			   else if(this.labirinto[x][y-1].equals(' '))
			   {
				   this.adjacentes.empilhar(auxAdjacente = new Coordenada(x,y-1));
			   }
	   }
	   
	   catch(Exception Error)
	   {
		   throw new Exception(Error);
	   }
	   
	   //verificando adjacente na coordenada a direita da atual (x,y+1)
	   try {
		   if(x <= this.Linhas-1 && y+1 <= this.Colunas-1)
			   if(this.labirinto[x][y+1].equals('S'))
			   {
				   this.adjacentes.empilhar(auxAdjacente = new Coordenada(x,y+1));
				   return true;
			   }
			   else if(this.labirinto[x][y+1].equals(' '))
			   {
				   this.adjacentes.empilhar(auxAdjacente = new Coordenada(x,y+1));
			   }
	   }
	   
	   catch(Exception Error)
	   {
		   throw new Exception(Error);
	   }
	   
	   return false;  
   }

   /**
    * Método usado para validar o Labirinto, inicializando as variáveis cont
    * (para entrada, saída, paredes e espaços em branco), bem como a existência
    * de alguma invalidez quanto aos números de Linhas e Colunas presentes 
    * no labirinto
    * 
    * @throws Exception
    */
private void validarLabirinto() throws Exception
{
   String linhaaux = null;
   int contParede = 0;
   int contEspaco = 0;
   int i = 0;
   int contE = 0;
   int contS = 0;
   
   if(this.labirinto[0][0] == null)
   {
 	  throw new Exception ("\n Deve haver o número de linhas do labirinto indicado na primeira linha do arquivo. \n");
   }
   
   while(i < this.Colunas)
   {
 	  if(this.labirinto[0][i] == null)
 	  {
 		break;
 	  }
 	  
 	  if(linhaaux == null)
 	  {
 		  linhaaux = this.labirinto[0][i].toString();
 	  }
 	  
 	  else
 	  {
 		  linhaaux = linhaaux + (this.labirinto[0][i].toString());
 	  }
 	  
 	  try
 	  {
 		  Integer.parseInt(linhaaux);
 	  }
 	  
 	  catch(Exception Error)
 	  {
 		  throw new Exception ("O primeiro elemento deve ser um indicativo do número de linhas do labirinto");
 	  }
 	  i++;
   }
   
   for(int linha = 1; linha < this.Linhas; linha++)
   {
 	  for(int coluna = 0; coluna < this.Colunas; coluna++)
 	  {
 		  try
 		  {
 			  if(this.labirinto[linha][coluna].equals('S'))
 			  {	
 				  if(((linha == this.Linhas-1 || linha == 1) && (coluna == this.Colunas-1 || coluna == 0)))
 				  {
 					  throw new Exception ("\nA saída só pode estar nas bordas do labirinto \n");
 				  }
 				  contS++;
 			  }
 			  
 			  else if(this.labirinto[linha][coluna].equals('E'))
 			  {
 				  this.Atual = new Coordenada(linha,coluna);
 				  
 				  if(((linha == this.Linhas-1 || linha == 2) && (coluna == this.Colunas-1 || coluna == 1)))
 				  {
 					  throw new Exception ("\n A entrada não pode estar no meio do labirinto \n");
 				  }
 				  contE++;
 			  }
 			  
 			  else if(this.labirinto[linha][coluna].equals('#'))
 			  {
             	  contParede++;
 			  }
 			  
 			  else if(this.labirinto[linha][coluna].equals(' '))
 			  {
 				  if((linha == this.Linhas-1 || linha == 1) && (coluna == this.Colunas-1 || coluna == 1))
 				  {
     					  throw new Exception ("\n O labirinto não é fechado \n");
 				  }
 				  contEspaco++;
 			  }
 		    
 			  else
 				 throw new Exception ("\n O elemento "+this.labirinto[linha][coluna].toString()+" é inválido \n");
 		  }
 		  
 		    catch(Exception Error)
 		  {
 		    	throw new Exception(Error);
 		  }
 	  }
   }

   if(contParede == 0)
   {
 	throw new Exception ("\nO arquivo deve conter paredes, representadas por ('#'). \n");
   }
   
   if(contEspaco == 0)
   {
 	  throw new Exception("\nO arquivo deve conter pelo menos um caminho possível, representado por um espaço em branco. \n");
   }
   
   if(linhaaux != null)
   {
   int linhaauxiliar = Integer.parseInt(linhaaux);
   
   if(linhaauxiliar < (this.Linhas-1))
   {
		  throw new Exception ("\nO número de linhas indicado é menor do que o labirinto em si. \n");
   }
	  else if(linhaauxiliar > (this.Linhas-1))
	  {
		  throw new Exception ("\nO número de linhas indicado é maior do que o labirinto em si. \n");
	  } 
   
   if(contS > 1 || contE > 1)   
   {
 	  throw new Exception("\nO arquivo deve conter, no máximo, UMA entrada ('E') e UMA saída ('S') \n");
   }
   
   if(contS < 1 || contE < 1)  
   {
	 	  throw new Exception("\nO arquivo deve conter exatamente UMA entrada ('E') e UMA saída ('S') \n");
   }
   }
   
   else
   {
	   throw new Exception("A primeira linha do arquivo deve ser o numero do Linhas");
   }
}
}
