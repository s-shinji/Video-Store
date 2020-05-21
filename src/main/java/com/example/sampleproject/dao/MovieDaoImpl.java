package com.example.sampleproject.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;
//変更〜
import com.example.sampleproject.entity.MemberRegistrationEntity;

import com.example.sampleproject.entity.Movie;
// import com.example.sampleproject.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl implements MovieDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MovieDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // @Override
    // public void insertMovie(Movie movie) {
    //     jdbcTemplate.update("INSERT INTO movie(movie, created) VALUES(?, ?)", movie.getMovie(), movie.getCreated());

    // }
    @Override
    public void insertMovie(Movie movie) {
        jdbcTemplate.update("INSERT INTO movie(movie, created, user_id) VALUES(?, ?, ?)", movie.getMovie(), movie.getCreated(), movie.getUserId());

    }

    @Override
    public List<Movie> getAll() {
        // final String sql = "SELECT id, movie, created,user_id FROM movie";
        //変更〜
                          //movie.idにしていないと"id"が曖昧ですというエラーが発生する（users.idが存在することによる影響？）
        final String sql = "SELECT movie.id, movie, created, user_id,"
                        + "name FROM movie "
                        + "INNER JOIN users ON movie.user_id = users.id";
                        //queryForMapでテーブルの1行分を取得する(今回はそのMapをList化しているためDBの全てを取得していることになる？)
        final List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
        final List<Movie> list = new ArrayList<Movie>();
        //カラム名がStringに値がObjectに格納されている
        for(final Map<String, Object> result:resultList) {
            //entityをnewしている
            final Movie movie = new Movie();
            //Objectで受けているためキャストを用いて型変換を行う
            movie.setId((int) result.get("id"));
            // movie.setMovie((String) result.get("movie"));
            movie.setMovie((byte[]) result.get("movie"));
            //DBから取り出すとDateTime型からTimestamp型になる(さらにentityクラスのフィールドではLocalDateTime型であるためさらに変換)
            movie.setCreated(((Timestamp) result.get("created")).toLocalDateTime());
            //usersテーブルのid
            movie.setUserId((int) result.get("user_id"));

            //Userエンティティは別個で詰め替え
            //変更〜
            MemberRegistrationEntity user = new MemberRegistrationEntity();
            user.setId((int) result.get("user_id"));
            user.setName((String) result.get("name"));
            // MovieにUserをセット
            movie.setUser(user);

            list.add(movie);
        }
        return list;
    }
    
    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM movie WHERE id = ?", id);
    }

}
