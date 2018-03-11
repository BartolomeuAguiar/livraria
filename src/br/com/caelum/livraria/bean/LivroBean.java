package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean {  //inicio da classe
	

	private Livro livro = new Livro();
	private Integer autorId;
	
	public void setAutorId(Integer autorId) {	//inicio setAutorId
	        this.autorId = autorId;
	}	//fim setAutorId
	
	
	public Integer getAutorId() { //inicio getAutorId
        return autorId;
    } //fim getAutorId
	 
	
	public Livro getLivro() {//inicio getLivro
		return livro;
	}//fim getLivro

	
	public List<Livro> getLivros() {
		return new DAO<Livro>(Livro.class).listaTodos();
	}
	
	
	public List<Autor> getAutores() {//inicio getAutores
	        return new DAO<Autor>(Autor.class).listaTodos();
	}//fim getAutores

	public List<Autor> getAutoresDoLivro(){//inicio getAutoresDolivro
		return this.livro.getAutores();
	}//fim getAutoresDolivro
	
	public void gravarAutor() {//inicio gravarAutor
	        Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
	        this.livro.adicionaAutor(autor);
	}//fim gravarAutor
	
	public void gravar() {//inicio gravar
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			throw new RuntimeException("Livro deve ter pelo menos um Autor.");
		}

		new DAO<Livro>(Livro.class).adiciona(this.livro);
		
		this.livro = new Livro();
	}//fim gravar

}//fim da classe

