package PROGRAMA;

public class Coordenada {
	private int x,y;
	
	/**
	 * Construtor da classe Coordenada que usa o "this" para igualar o x e o y coletados
	 * com as variáveis criadas, passando como parâmetro essas variáveis. O valor de X
	 * equivale à variável do eixo horizontal, enquanto o valor de Y equivale à variável
	 * do eixo vertical.
	 * 
	 * @author Lucas Faustino
	 * @author Leonardo Belanga
	 * @param x
	 * @param y
	 */
	public Coordenada(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Método getter que retorna o valor de X coletado
	 * 
	 * 	@author Lucas Faustino
	 * @author Leonardo Belanga
	 * @return x
	 */
	public int getX()
	{
		return this.x;
	}
	
	/**
	 * Método getter que retorna o valor de Y coletado
	 * 
	 * 	@author Lucas Faustino
	 * @author Leonardo Belanga
	 * @return y
	 */
	public int getY()
	{
		return this.y;
	}
	
	/**
	 * Método setter usado para alterar o valor de X
	 * 
	 * 	@author Lucas Faustino
	 * @author Leonardo Belanga
	 * @param x (valor que deseja substituir)
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	
	/**
	 * Método setter usado para alterar o valor de Y
	 * 
	 * 	@author Lucas Faustino
	 * @author Leonardo Belanga
	 * @param y (valor que deseja substituir)
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	
    public int hashCode ()
    {
        int ret = 10;
        
        ret = ret*7 + new Integer(this.x).hashCode();
        ret = ret*7 + new Integer(this.y).hashCode();
        
        return ret;
    }
}
