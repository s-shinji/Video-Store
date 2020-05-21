package com.example.sampleproject.controller;//変更！！

import com.example.sampleproject.ImageForm;
import com.example.sampleproject.entity.DbUserDetails;
import com.example.sampleproject.entity.Movie;
import com.example.sampleproject.service.MovieService;

// import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    //変更箇所(new)
    // @Autowired
    // private RegisterMemberService registerMemberService;

    @Autowired
    public SampleController(MovieService movieService){
        this.movieService = movieService;
    }


    @ModelAttribute
    public ImageForm setForm() {
        return new ImageForm();
    }
    
    @RequestMapping("/upload")
    public String upload() {
        return "upload";
    }

    @GetMapping("/index")
    public String index(Model model, RedirectAttributes redirectAttributes){
        //変更箇所(new)
        // List<MemberRegistrationEntity> memberRegistrationEntityList = registerMemberService.findAll();
        // model.addAttribute("find",memberRegistrationEntityList.get(0));
        
        List<Movie> list = movieService.getAll();
        List<Object> list3 = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
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
            //0にはString化される前のmovieが入っているため、それをString化したものに差し替える
            list2.set(0,data.toString());
            list3.add(list2);
        }
        // model.addAttribute("movieList", list);
        model.addAttribute("movieList3", list3);
        //index.htmlでユーザー情報を取得したい場合に用いる(現在ログイン中のユーザー情報を取得)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if(authentication.getPrincipal() instanceof DbUserDetails){
	    	DbUserDetails account = DbUserDetails.class.cast(authentication.getPrincipal());
	    	// model.addAttribute("userInfo", "現在ログインしているユーザ名：" + account.getUsername() + "をコントローラクラスから取得しました。");
	    	model.addAttribute("loginUser", account.getUserId());
	    }else{
	    	model.addAttribute("loginUser", "");
        }	
        //ここまで
        return "index";
    }
    
    @PostMapping("/upload")
    public String upload(@Validated ImageForm imageForm, BindingResult result, Model model ) throws Exception {

        // if(result.hasErrors()) {
        //     return "upload";
        // }

        Movie movie = new Movie();
        for(int i = 0; i < imageForm.getImage().length; i++) {
            //ファイルが一つもない場合にエラー文を表示する
            MultipartFile uploadFile = imageForm.getImage()[i];
            if (uploadFile.isEmpty()) {
                result.rejectValue("image",null, "ファイルを選択してください");
                return "upload";
            }

            // StringBuffer data = new StringBuffer();
            // String base64 = Base64.getEncoder().encodeToString(imageForm.getImage()[i].getBytes());
            // data.append("data:image/jpeg;base64,");
            // data.append(base64);
            movie.setMovie(imageForm.getImage()[i].getBytes());
            movie.setCreated(LocalDateTime.now());
            // 変更箇所(ログインユーザーのID情報を渡す)
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication.getPrincipal() instanceof DbUserDetails){
                int userId = ((DbUserDetails)authentication.getPrincipal()).getUserId();
                movie.setUserId(userId);
            }
            //ここまで
            movieService.save(movie);
        }
        // 動画投稿を一つずつにする場合の分
        // MultipartFile[] uploadFile = imageForm.getImage();
        // if (uploadFile.isEmpty()) {
        //     result.rejectValue("image",null, "ファイルを選択してください");
        //     return "upload";
        // }
        // StringBuffer data = new StringBuffer();
        // String base64 = Base64.getEncoder().encodeToString(imageForm.getImage().getBytes());
        // data.append("data:image/jpeg;base64,");
        // data.append(base64);
        // movie.setMovie(imageForm.getImage().getBytes());
        // movie.setCreated(LocalDateTime.now());
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // if(authentication.getPrincipal() instanceof DbUserDetails){
        //     int userId = ((DbUserDetails)authentication.getPrincipal()).getUserId();
        //     movie.setUserId(userId);
        // }
        // movieService.save(movie);
        //ここまで

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
    
    
}