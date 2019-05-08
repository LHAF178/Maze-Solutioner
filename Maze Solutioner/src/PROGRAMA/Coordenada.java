package PROGRAMA;

public class Coordenada {
	private int x,y;
	
	/**
	 * Construtor da classe Coordenada que usa o "this" para igualar o x e o y coletados
	 * com as vari�veis criadas, passando como par�metro essas vari�veis. O valor de X
	 * equivale � vari�vel do eixo horizontal, enquanto o valor de Y equivale � vari�vel
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
	 * M�todo getter que retorna o valor de X coletado
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
	 * M�todo getter que retorna o valor de Y coletado
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
	 * M�todo setter usado para alterar o valor de X
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
	 * M�todo setter usado para alterar o valor de Y
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
