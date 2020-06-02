package com.example.sampleproject.controller;//変更！！

import com.example.sampleproject.form.MovieForm;
import com.example.sampleproject.entity.DbUserDetails;
import com.example.sampleproject.entity.Image;
import com.example.sampleproject.entity.Movie;
import com.example.sampleproject.form.SearchForm;
import com.example.sampleproject.service.ImageService;
import com.example.sampleproject.service.MovieService;

// import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
// import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// 変更箇所(new)
// import com.example.sampleproject.mapper.RegisterMemberMapper;
// import com.example.sampleproject.entity.MemberRegistrationEntity;
// import com.example.sampleproject.service.RegisterMemberService;




@Controller
public class SampleController {

    private final MovieService movieService;
    private final ImageService imageService;
    //変更箇所(new)
    // @Autowired
    // private RegisterMemberService registerMemberService;

    @Autowired
    public SampleController(MovieService movieService, ImageService imageService){
        this.movieService = movieService;
        this.imageService = imageService;
    }


    @ModelAttribute
    public MovieForm setForm() {
        return new MovieForm();
    }
    
    @RequestMapping("/")
    public String top() {
        return "top";
    }
    @RequestMapping("/top")
    public String top2() {
        return "top";
    }

    @RequestMapping("/upload")
    public String upload(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if(authentication.getPrincipal() instanceof DbUserDetails){
            // DbUserDetails account = DbUserDetails.class.cast(authentication.getPrincipal());
            int userId = ((DbUserDetails)authentication.getPrincipal()).getUserId();

	    	// model.addAttribute("loginUser", account.getUserId());
	    	model.addAttribute("loginUser", userId);
	    }else{
	    	model.addAttribute("loginUser", "");
        }	

        return "upload";
    }

    @GetMapping("/index")
    public String index(Model model, RedirectAttributes redirectAttributes){
        //変更箇所(new)
        // List<MemberRegistrationEntity> memberRegistrationEntityList = registerMemberService.findAll();
        // model.addAttribute("find",memberRegistrationEntityList.get(0));
        
        List<Movie> list = movieService.getAll();
        // Collections.sort(list.getCreated(),Collections.reverseOrder());
        List<Object> list3 = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            // Collections.sort(list.get(i).getViews(),Collections.reverseOrder());

            StringBuffer data = new StringBuffer();
            String base64 = Base64.getEncoder().encodeToString(list.get(i).getMovie());
            // data.append("data:image/jpeg;base64,");
            //mp4にしか対応していない
            data.append("data:video/mp4;base64,");
            data.append(base64);

            //Object型のリストを作成し必要な要素のみをlistから取り出し詰め替える
            List<Object> list2 = new ArrayList<>();
            list2.add(list.get(i).getMovie());
            list2.add(list.get(i).getId());
            list2.add(list.get(i).getUserId());
            list2.add(list.get(i).getUser().getName());
            list2.add(list.get(i).getViews());
            list2.add(list.get(i).getTitle());
            list2.add(list.get(i).getUser().getAvatar());
            list2.add(list.get(i).getImage().getImage());
            
            //0にはString化される前のmovieが入っているため、それをString化したものに差し替える
            list2.set(0,data.toString());
            list3.add(list2);
        }
        // model.addAttribute("movieList", list);
        model.addAttribute("movieList3", list3);

        //再生回数順に上位5つを取り出す
        List<Movie> viewsList = movieService.getAll2();
        List<Object> viewsList3 = new ArrayList<>();
        for(int j = 0; j < viewsList.size(); j++) {
            StringBuffer data = new StringBuffer();
            String base64_2 = Base64.getEncoder().encodeToString(viewsList.get(j).getMovie());
            data.append("data:video/mp4;base64,");
            data.append(base64_2);

            List<Object> viewsList2 = new ArrayList<>();
            viewsList2.add(data.toString());
            viewsList2.add(viewsList.get(j).getId());
            viewsList2.add(viewsList.get(j).getTitle());
            viewsList2.add(viewsList.get(j).getImage().getImage());
            viewsList3.add(viewsList2);
        }
        model.addAttribute("viewsList3", viewsList3);

        //index.htmlでユーザー情報を取得したい場合に用いる(現在ログイン中のユーザー情報を取得)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if(authentication.getPrincipal() instanceof DbUserDetails){
            // DbUserDetails account = DbUserDetails.class.cast(authentication.getPrincipal());
            int userId = ((DbUserDetails)authentication.getPrincipal()).getUserId();

	    	// model.addAttribute("loginUser", account.getUserId());
	    	model.addAttribute("loginUser", userId);
	    }else{
	    	model.addAttribute("loginUser", "");
        }	
        //ここまで
        return "index";
    }

    @GetMapping("/video/{id}")
    public String video(Movie movie,@PathVariable int id, Model model) {
        Optional<Movie> movieOpt = movieService.getMovie(id);
        if(movieOpt.isPresent()) {
           movie = movieOpt.get();
        }
        //再生回数を+1
        int views = movie.getViews();
        views += 1; 
        // Movie viewss = (Movie) views;
        movieService.updateViews(views, id);
        StringBuffer data = new StringBuffer();
        String base64_3 = Base64.getEncoder().encodeToString(movie.getMovie());
        data.append("data:video/mp4;base64,");
        data.append(base64_3);
        // String convertMovie = data.toString();
        model.addAttribute("convert", data.toString());
        model.addAttribute("movie", movie);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if(authentication.getPrincipal() instanceof DbUserDetails){
            // DbUserDetails account = DbUserDetails.class.cast(authentication.getPrincipal());
            int userId = ((DbUserDetails)authentication.getPrincipal()).getUserId();

	    	// model.addAttribute("loginUser", account.getUserId());
	    	model.addAttribute("loginUser", userId);
	    }else{
	    	model.addAttribute("loginUser", "");
        }	

        return "detail";
    }
    
    @PostMapping("/upload")
    public String upload(@Validated MovieForm movieForm, BindingResult result, Model model ) throws Exception {

        if(result.hasErrors()) {
            return "upload";
        }

        Movie movie = new Movie();
        // 動画を複数投稿する場合
        // for(int i = 0; i < movieForm.getMovie().length; i++) {
            //ファイルが一つもない場合にエラー文を表示する
            // MultipartFile uploadFile = movieForm.getMovie()[i];
            // if (uploadFile.isEmpty()) {
            //     result.rejectValue("movie",null, "ファイルを選択してください");
            //     return "upload";
            // }

            // movie.setMovie(movieForm.getMovie()[i].getBytes());
            // movie.setCreated(LocalDateTime.now());
            // 変更箇所(ログインユーザーのID情報を渡す)
            // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            // if(authentication.getPrincipal() instanceof DbUserDetails){
            //     int userId = ((DbUserDetails)authentication.getPrincipal()).getUserId();
            //     movie.setUserId(userId);
            // }

            // movie.setViews(0);
            // movie.setTitle(movieForm.getTitle());
            //ここまで
            // movieService.save(movie);
        // }
        // 動画投稿を一つずつにする場合の分
        MultipartFile uploadFile = movieForm.getMovie();
        if (uploadFile.isEmpty()) {
            result.rejectValue("movie",null, "ファイルを選択してください");
            return "upload";
        }
        movie.setMovie(movieForm.getMovie().getBytes());
        movie.setCreated(LocalDateTime.now());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal() instanceof DbUserDetails){
            int userId = ((DbUserDetails)authentication.getPrincipal()).getUserId();
            movie.setUserId(userId);
        }
        movie.setViews(0);
        movie.setTitle(movieForm.getTitle());

        int lastId = movieService.save(movie);
        //ここからImage
        Image image = new Image();
        //SELECT LASTVAL();を用いて直前のmovie.idを取得しimageテーブルのmovie_idに入れる処理をここに挿入する
        // int lastId = movieService.getLastId();
        image.setMovie_id(lastId);

        StringBuffer data = new StringBuffer();
        String base64 = Base64.getEncoder().encodeToString(movieForm.getThumbnail().getBytes());
        data.append("data:image/;base64,");
        data.append(base64);
        image.setImage(data.toString());

        imageService.save(image);


        return "redirect:/index";
    }

    @PostMapping("/delete")
    public String delete (
        //hiddenのname属性をキーとしてvalueを受け取り変数idに格納している
        @RequestParam("movieId") int id,
        Model model) {
        //動画を一件削除しリダイレクト
        movieService.deleteById(id);
        return "redirect:/index";
    } 

    @PostMapping("/search")
    public String search (SearchForm searchForm, Model model) {
        if("".equals(searchForm.getSearchWord())) {
            return "index";
        } else {
            List<Movie> list = movieService.findBySearchWordLike("%" + searchForm.getSearchWord() + "%");
            List<Object> list3 = new ArrayList<>();
            for(int i = 0; i < list.size(); i++) {
                // Collections.sort(list.get(i).getViews(),Collections.reverseOrder());
    
                StringBuffer data = new StringBuffer();
                String base64 = Base64.getEncoder().encodeToString(list.get(i).getMovie());
                // data.append("data:image/jpeg;base64,");
                //mp4にしか対応していない
                data.append("data:video/mp4;base64,");
                data.append(base64);
    
                //Object型のリストを作成し必要な要素のみをlistから取り出し詰め替える
                List<Object> list2 = new ArrayList<>();
                list2.add(list.get(i).getMovie());
                list2.add(list.get(i).getId());
                list2.add(list.get(i).getUser().getName());
                list2.add(list.get(i).getViews());
                list2.add(list.get(i).getTitle());
                list2.add(list.get(i).getUserId());
                list2.add(list.get(i).getUser().getAvatar());
                list2.add(list.get(i).getImage().getImage());
                
                //0にはString化される前のmovieが入っているため、それをString化したものに差し替える
                list2.set(0,data.toString());
                list3.add(list2);
            }
            // model.addAttribute("movieList", list);
            model.addAttribute("list3", list3);
            model.addAttribute("listSize", list3.size());
        }
        return "search";
    }
    
    
}