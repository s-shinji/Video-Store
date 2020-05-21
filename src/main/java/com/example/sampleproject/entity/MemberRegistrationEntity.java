package com.example.sampleproject.entity;//変更！！

// 変更箇所(new)
// import java.util.List;
// import javax.persistence.CascadeType;
// import javax.persistence.NamedQuery;
// import javax.persistence.OneToMany;
// import javax.persistence.Entity;
// import javax.persistence.Id;
/**
 * DBに入れる値を格納するクラス。
 */
//変更箇所(new)
// @NamedQuery(name="Movie.findAll", query="SELECT m FROM Movie m")
// @Entity
public class MemberRegistrationEntity {
	// 変更箇所
	// @Id
	private int id;

	private String name;

	private String password;
	// 変更箇所(new)
	// @OneToMany(mappedBy="movie",cascade = CascadeType.ALL)
	// private List<Movie> movies;
	// public MemberRegistrationEntity(){
	// }

	// 変更箇所
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	// 変更箇所(new)
	// public Movie addMovie(Movie movie) {
    //     getMovies().add(movie);
	// 	movie.setMemberRegistrationEntity(this);
	// 	return movie;

    // }
    // public Movie removeMovie(Movie movie) {
    //     getMovies().remove(movie);
	// 	movie.setMemberRegistrationEntity(null);
	// 	return movie;
	// }
	// public List<Movie> getMovies() {
	// 	return this.movies;
	// 	}
	// public void setMovies(List<Movie> movies) {
	// this.movies = movies;
	// }
}