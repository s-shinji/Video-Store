package com.example.sampleproject.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.sql.Timestamp;
import com.example.sampleproject.entity.Image;
import com.example.sampleproject.entity.MemberRegistrationEntity;
import com.example.sampleproject.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl implements MovieDao {

    private final JdbcTemplate jdbcTemplate;
    private Movie movie;
    private MemberRegistrationEntity entity;

    @Autowired
    public MovieDaoImpl(JdbcTemplate jdbcTemplate, MemberRegistrationEntity entity, Movie movie) {
        this.jdbcTemplate = jdbcTemplate;
        this.movie        = movie;
        this.entity       = entity;
    }

    
    @Override
    public int insertMovie(Movie movie) {
        Integer lastId = jdbcTemplate.queryForObject("INSERT INTO movie(movie, created, user_id, views, title) VALUES(?, ?, ?, ?, ?) RETURNING id", Integer.class,movie.getMovie(), movie.getCreated(), movie.getUserId(), movie.getViews(), movie.getTitle());
        return lastId;
    }


    @Override
    public List<Movie> getAll() {
                          //movie.idにしていないと"id"が曖昧ですというエラーが発生する（users.idが存在することによる影響？）
        String sql = "SELECT movie.id, movie, created, user_id, views, title,"
                        + "name, avatar, image FROM movie "
                        + "INNER JOIN users ON movie.user_id = users.id "
                        + "INNER JOIN image ON movie.id = image.movie_id "
                        + "ORDER BY created DESC";
                        //queryForMapでテーブルの1行分を取得する(今回はそのMapをList化しているためDBの全てを取得していることになる？)
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
        List<Movie> movieList                = new ArrayList<Movie>();
        //カラム名がStringに値がObjectに格納されている
        for(Map<String, Object> result:resultList) {
            //entityをnewしている
            Movie movie = new Movie();
            //Objectで受けているためキャストを用いて型変換を行う
            movie.setId     ((int) result.get("id"));
            // movie.setMovie((String) result.get("movie"));
            movie.setMovie  ((byte[]) result.get("movie"));
            //DBから取り出すとDateTime型からTimestamp型になる(さらにentityクラスのフィールドではLocalDateTime型であるためさらに変換)
            movie.setCreated(((Timestamp) result.get("created")).toLocalDateTime());
            //usersテーブルのid
            movie.setUserId ((int) result.get("user_id"));
            movie.setViews  ((int) result.get("views"));
            movie.setTitle  ((String) result.get("title"));
            //Userエンティティは別個で詰め替え
            MemberRegistrationEntity user = new MemberRegistrationEntity();
            user.setId      ((int) result.get("user_id"));
            user.setName    ((String) result.get("name"));
            user.setAvatar  ((String) result.get("avatar"));
            // MovieにUserをセット
            movie.setUser(user);

            Image image = new Image();
            image.setImage  ((String) result.get("image"));
            movie.setImage(image);

            movieList.add(movie);
        }
        return movieList;
    }


    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM movie WHERE id = ?", id);
    }


    @Override
    public Optional<Movie> getMovie(int id) {
        String sql = "SELECT movie.id, movie, created, user_id, views, title,"
                        + "name, avatar FROM movie "
                        + "INNER JOIN users ON movie.user_id = users.id "
                        + "WHERE movie.id = ?";
        Map<String, Object> result = jdbcTemplate.queryForMap(sql, id);
        //  Movie movie = new Movie();
        movie.setId     ((int) result.get("id"));
        movie.setMovie  ((byte[]) result.get("movie"));
        movie.setCreated(((Timestamp) result.get("created")).toLocalDateTime());
        movie.setUserId ((int) result.get("user_id"));
        movie.setViews  ((int) result.get("views"));
        movie.setTitle  ((String) result.get("title"));

        // MemberRegistrationEntity user = new MemberRegistrationEntity();
        entity.setId    ((int) result.get("user_id"));
        entity.setName  ((String) result.get("name"));
        entity.setAvatar((String) result.get("avatar"));
        movie.setUser(entity);

        Optional<Movie> movieOpt = Optional.ofNullable(movie);
        return movieOpt;
    }


    @Override
    public int updateViews(int views, int id) {
        return jdbcTemplate.update("UPDATE movie SET views = ? WHERE id = ?", views, id);
    }


    @Override
    public List<Movie> findBySearchWordLike(String searchWord) {
        String sql = "SELECT movie.id, movie, views, title, user_id, "
                        + "name, avatar, image FROM movie "
                        + "INNER JOIN users ON movie.user_id = users.id "
                        + "INNER JOIN image ON movie.id = image.movie_id "
                        + "WHERE title LIKE ? OR name LIKE ? "
                        + "ORDER BY created DESC";

        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, searchWord, searchWord);
        List<Movie> MovieList                = new ArrayList<Movie>();
        //カラム名がStringに値がObjectに格納されている
        for(Map<String, Object> result:resultList) {
            Movie movie = new Movie();
            movie.setId    ((int) result.get("id"));
            movie.setMovie ((byte[]) result.get("movie"));
            movie.setViews ((int) result.get("views"));
            movie.setTitle ((String) result.get("title"));
            movie.setUserId((int) result.get("user_id"));

            MemberRegistrationEntity user = new MemberRegistrationEntity();
            user.setName   ((String) result.get("name"));
            user.setAvatar ((String) result.get("avatar"));
            movie.setUser(user);

            Image image = new Image();
            image.setImage ((String) result.get("image"));
            movie.setImage(image);    

            MovieList.add(movie);
        }
        return MovieList;
    }


    @Override
    public Optional<Movie> getUserIdByMovieId(int id) {
        String sql = "SELECT user_id FROM movie "
                        + "WHERE id = ?";
        Map<String, Object> result = jdbcTemplate.queryForMap(sql, id);
        Movie movie                = new Movie();
        movie.setUserId((int) result.get("user_id"));
        Optional<Movie> movieOpt   = Optional.ofNullable(movie);
        return movieOpt;
    }
}
