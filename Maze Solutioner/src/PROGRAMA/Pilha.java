package PROGRAMA;

/**
 * Classe Pilha Genérica e Dinâmica que usa métodos para adicionar, remover e 
 * conferir itens dentro de uma Pilha criada. Classe faz isso através de Nós
 * 
 * @author Lucas Faustino
 * @author Leonardo Belanga
 *
 * @param <T>
 */
public class Pilha<T> 
{
    
    private class No 
    {
        private T item;
        private No proximo;

        public No(T item, No proximo) 
        {
            this.item = item;
            this.proximo = proximo;
        }

        public T getItem() 
        {
            return this.item;
        }

        public No getProximo() 
        {
            return this.proximo;
        }

        public void setItem(T item) 
        {
            this.item = item;
        }

        public void setProximo(No proximo) 
        {
            this.proximo = proximo;
        }
    } 

    //topo é o primeiro da pilha
    private No primeiro;
    private int tamanho = 0;
    
    public void Pilha() throws Exception
    {
        this.primeiro = null;
    }

    // push
    public void empilhar(T item) throws Exception
    {
        if (item == null)
            throw new Exception("Valor ausente");
        
        tamanho++;
        this.primeiro = new No (item, this.primeiro);
    }

    // pop
    public void desempilhar() throws Exception 
    {
        if (this.isVazia())
            throw new Exception("Nada guardado");
        
        tamanho--;
        this.primeiro = this.primeiro.getProximo();
    }
    
    /*public T retornaValorDesempilhado() throws Exception 
    {
    	
        if (this.isVazia())
            throw new Exception("Nada guardado");
        
        tamanho--;
        T valor = (T) this.primeiro;
        this.primeiro = this.primeiro.getProximo();
        
        return valor;
    }*/

    // peek
    public T exibeTopo() throws Exception 
    {
        if (this.isVazia())
            throw new Exception("Nada guardado");

        return this.primeiro.getItem();
    }
    
    //size
    public int getTamanho()
    {
    	return this.tamanho;
    }

    // isEmpty
    public boolean isVazia() 
    {
        return this.primeiro == null;
    }

    public String toString ()
    {
        if (this.primeiro==null)
            return "Vazia";

        return "Ultimo: "+this.primeiro.getItem();
    }

    // equals compara this e obj
    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        Pilha<T> pilha = (Pilha<T>)obj;

        No atualThis =this .primeiro,
           atualPilha=pilha.primeiro;

        while (atualThis!=null && atualPilha!=null)
            if (!atualThis.getItem().equals(atualPilha.getItem()))
                return false;

        if (atualThis!=null)
            return false;

        if (atualPilha!=null)
            return false;

        return true;
    }   

    public int hashCode ()
    {
        int ret = 10;

        No atual=this.primeiro;

        while (atual!=null)
        {
            ret = ret*7 + atual.getItem().hashCode();
            atual = atual.getProximo();
        }
        
        ret = ret*7 + new Integer(this.tamanho).hashCode();
        
        return ret;
    }
    
    /*public Pilha<T>.No desempilharP() throws Exception 
    {
        if (this.isVazia())
            throw new Exception("Nada guardado");
        
        tamanho--;
        this.primeiro = this.primeiro.getProximo();
        
        return this.primeiro;
    }*/
}