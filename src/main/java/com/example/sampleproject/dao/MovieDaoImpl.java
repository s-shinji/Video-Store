package com.example.sampleproject.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;

import com.example.sampleproject.entity.Movie;

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

    @Override
    public void insertMovie(Movie movie) {
        jdbcTemplate.update("INSERT INTO movie(movie, created) VALUES(?, ?)", movie.getMovie(), movie.getCreated());

    }

    @Override
    public List<Movie> getAll() {
        final String sql = "SELECT id, movie, created FROM movie";
                        //queryForList(queryForMap)などでテーブルの1行分を取得する
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
            list.add(movie);
        }
        return list;
    }

}
