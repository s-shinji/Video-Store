package com.example.sampleproject.controller;

import com.example.sampleproject.form.MovieForm;
import com.example.sampleproject.entity.DbUserDetails;
import com.example.sampleproject.entity.Image;
import com.example.sampleproject.entity.Movie;
// import com.example.sampleproject.entity.Notification;
import com.example.sampleproject.entity.Review;
import com.example.sampleproject.form.SearchForm;
import com.example.sampleproject.service.FollowService;
import com.example.sampleproject.service.ImageService;
import com.example.sampleproject.service.MovieService;
// import com.example.sampleproject.service.NotificationService;
import com.example.sampleproject.service.ReviewService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


// @Controller
@RestController
//springとreactでurlがかぶらないようにするため追加
@RequestMapping("/KdiJ362")
public class SampleController {

    private final MovieService movieService;
    private final ImageService imageService;
    private final ReviewService reviewService;
    // private final FollowService followService;
    // private final NotificationService notificationService;
    private Movie movie;
    private Image image;

    @Autowired
    public SampleController(MovieService movieService, ImageService imageService, ReviewService reviewService, FollowService followService,/*NotificationService notificationService,*/ Movie movie, Image image){
        this.movieService        = movieService;
        this.imageService        = imageService;
        this.reviewService       = reviewService;
        // this.followService       = followService;
        // this.notificationService = notificationService;
        this.movie               = movie;
        this.image               = image;
    }


    @ModelAttribute
    public MovieForm setForm() {
        return new MovieForm();
    }
    
    // @RequestMapping("/")
    // public String top() {
    //     return "top";
    // }
    // @RequestMapping("/top")
    // public String top2() {
    //     return "top";
    // }

    // @RequestMapping("/upload")
    // // @ResponseBody
    // public String upload(Model model) {
    //     //ログインユーザーを取得
    //     int loginUserId = 0;
    //     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	//     if(authentication.getPrincipal() instanceof DbUserDetails){
    //         loginUserId = ((DbUserDetails)authentication.getPrincipal()).getUserId();
	//     	model.addAttribute("loginUser", loginUserId);
	//     }else{
	//     	model.addAttribute("loginUser", "");
    //     }	

    //     // return loginUserId;
    //     return "upload";
    // }
    // @CrossOrigin(origins = "http://localhost:3000")


    @GetMapping("/index")
    // @ResponseBody
    public List<Object> index(Model model,@RequestParam int loginUserId){
        List<Object> allItem = new ArrayList<>();


        List<Movie> movieGetAllLists    = movieService.getAll();
        model.addAttribute("movieList", movieGetAllLists);
        allItem.add(movieGetAllLists);

        // listを再生回数順に並び替える
        List<Movie> viewList = new  ArrayList<>(movieGetAllLists);
        // Collections.sort(movieGetAllLists,new Comparator<Movie>() {
        //     public int compare(Movie obj1, Movie obj2)
        //     {   //降順
        //         return ((Integer)obj2.getViews()).compareTo((Integer)obj1.getViews());
        //     }
        // // });
        
        //ラムダ式バージョン
        // Collections.sort(movieGetAllLists,(obj1, obj2) -> {
        //         //降順
        //         return ((Integer)obj2.getViews()).compareTo((Integer)obj1.getViews());
        // });
        
        //comparingメソッドバージョン
        Collections.sort(viewList,Comparator.comparing(Movie::getViews,Comparator.reverseOrder()));
        
        //streamのsortedバージョン(ラムダ式)(変数に代入する)
        // movieGetAllLists.stream()
        //         .sorted((obj1, obj2) -> obj2.getViews().compareTo(obj1.getViews()));

        //streamのsortedバージョン(comparing)(変数に代入する)
        // movieGetAllLists.stream()
        //         .sorted(Comparator.comparing(Movie::getViews,Comparator.reverseOrder()));
                
        if(viewList.size() >= 5) {
            //再生回数の上位5つを取り出す
            List<Object> top5Views = new ArrayList<>();
            for(int j = 0; j < 5; j++) {
                List<Object> refillableList2 = new ArrayList<>();
                refillableList2.add(viewList.get(j).getId());
                refillableList2.add(viewList.get(j).getImage().getImage());
                top5Views.add(refillableList2);
            }
            model.addAttribute("top5Views", top5Views);
            allItem.add(top5Views);
        }

        //index.htmlでユーザー情報を取得したい場合に用いる(現在ログイン中のユーザー情報を取得)
        //通知機能を省いたことで必要性は現時点ではない
        allItem.add(loginUserId);

        //フォローユーザーが新たな動画を投稿していた場合に通知する処理
        // if(loginUserId == 0) {
        // } else{
            // List<Integer> followingUserIdList  = followService.findFollowingById(loginUserId);
            // List<Movie> followingUserLatestMovieList = new ArrayList<>();
            // for(int followingUser : followingUserIdList) {
            //     //フォローユーザーに動画が存在する場合
            //     if(!(movieService.getFollowingUserLatestMovie(followingUser) == null)) {
            //         Movie followingUserLatestInfo = movieService.getFollowingUserLatestMovie(followingUser);

            //         //コードが長くなるため一度変数に格納(movieテーブルの最新情報)
            //         LocalDateTime followingUserLatestCreatedOnMovieTable = followingUserLatestInfo.getCreated();

            //         //一度もフォローユーザーの情報がnotificationに格納されていない場合はnullが返ってくるためその処理(以下の処理はnullじゃない場合に実行される)
            //         if((notificationService.getLatestCreated(followingUserLatestInfo.getUserId(), loginUserId)).isPresent()) {
            //             //コードが長くなるため一度変数に格納(notificationテーブルの最新情報)
            //             LocalDateTime followingUserLatestCreatedOnNotificationTable = notificationService.getLatestCreated(followingUserLatestInfo.getUserId(), loginUserId).get().getCreated();

            //             // 今回送られてきたmovieテーブルからの情報が以前notificationに保存されていた作成日よりも前または同じ場合処理を飛ばす（フォローユーザーの最新動画が削除された場合に起こる）
            //             //compareToメソッドで引数の日付(notificationのデータ)より後の場合は0より大きい値が帰ってくるため、それ以外の値が返ってきた時にcontinueしている（つまり、作成日よりも前または同じ場合）
            //             if(!(followingUserLatestCreatedOnMovieTable.compareTo(followingUserLatestCreatedOnNotificationTable) > 0)) {
            //                 continue;
            //             }
            //         }
            //         //Notificationのインスタンスを作成して詰め替える（for文の中のためDIできない）
            //         Notification notification = new Notification();
            //         notification.setMovie_id(followingUserLatestInfo.getId());
            //         notification.setFollowee_id(followingUserLatestInfo.getUserId());
            //         notification.setFollower_id(loginUserId);
            //         notification.setCreated(followingUserLatestCreatedOnMovieTable);
            //         //通知テーブルにおけるフォローユーザーの最新動画が更新されていない場合に更新した上でその情報をリストに追加する
            //         if(!(notificationService.searchLatestNotificationInfo(notification) == 1)) {
            //             followingUserLatestMovieList.add(movieService.getFollowingUserLatestMovie(followingUser));
            //         } 
            //     }
            // }

            // if(!(followingUserLatestMovieList.size() == 0)) {
            //     model.addAttribute("followingUserLatestMovieList", followingUserLatestMovieList);
            //     allItem.add(followingUserLatestMovieList);
            // }
        // }

        return allItem;
    }

    @GetMapping("/video/{id}")
    // @ResponseBody
    public List<Object> video(@PathVariable("id") int movieId, @RequestParam int loginUserId,Model model) {
        List<Object> detailVideoInfo = new ArrayList<>();
        //movieIdに紐づく動画を取得
        Optional<Movie> movieOpt = movieService.getMovie(movieId);
        if(movieOpt.isPresent()) {
           movie = movieOpt.get();
        }
        StringBuffer data = new StringBuffer();
        String base64_3   = Base64.getEncoder().encodeToString(movie.getMovie());
        data.append("data:video/mp4;base64,");
        data.append(base64_3);
        // model.addAttribute("convert", data.toString());
        // model.addAttribute("movie", movie);
        detailVideoInfo.add(data.toString());
        detailVideoInfo.add(movie);

        //ユーザーがログイン状態且つ動画投稿者じゃない場合に再生回数を+1
        if(movie.getUserId() == loginUserId || loginUserId == 0) {
        } else {
            //再生回数を+1
            int views = movie.getViews();
            views    += 1; 
            movieService.updateViews(views, movieId);
            
        }

        //既にReview済みの動画かどうかを判断するための処理
        String matchReview = "";
        if(reviewService.findMatchUserId(movieId,loginUserId) == null) {
        } else {
            matchReview = reviewService.findMatchUserId(movieId,loginUserId);
        }
        // model.addAttribute("matchReview", matchReview);
        detailVideoInfo.add(matchReview);

        //Review情報を取得
        List<Review> reviewLists      = reviewService.findReviewById(movieId);
        Map<String,Integer> reviewMap = new HashMap<String, Integer>();
        reviewMap.put("good", 0);
        reviewMap.put("normal", 0);
        reviewMap.put("bad", 0);
        for(Review reviewList : reviewLists) {
            if("good".equals(reviewList.getReview())) {
                reviewMap.put("good", reviewMap.get("good") + 1 );
            } else if("normal".equals(reviewList.getReview())) {
                reviewMap.put("normal", reviewMap.get("normal") + 1 );
            } else if("bad".equals(reviewList.getReview())) {
                reviewMap.put("bad", reviewMap.get("bad") + 1 );
            }
        }
        // model.addAttribute("reviewMap", reviewMap);
        detailVideoInfo.add(reviewMap);

        return detailVideoInfo;
    }
    
    @PostMapping(value="/upload")
    // @ResponseBody
    public String upload(
        @Validated MovieForm movieForm, BindingResult result, Model model,
                         @RequestParam("movie") MultipartFile movieParams,  
                         @RequestParam("thumbnail") MultipartFile thumbnailParams,  
                         @RequestParam("title") String titleParams,
                         @RequestParam("loginUserId") int idParams
                        //  @RequestBody MovieForm movieForm
                         ) throws Exception {

        // if(movieForm.getLoginUserId() == 0) {
            
        // }
        // List<Object> createMovie = new ArrayList<>();

        // if(result.hasErrors()) {
        //     return "upload";
        // }

        // Movie movie = new Movie();
        // 動画投稿の処理
        // MultipartFile uploadFile = movieForm.getMovie();
        // if (uploadFile.isEmpty()) {
        //     result.rejectValue("movie",null, "ファイルを選択してください");
        //     return "upload";
        // }
        movie.setMovie(movieForm.getMovie().getBytes());
        movie.setCreated(LocalDateTime.now());
        movie.setUserId(movieForm.getLoginUserId());
        //再生回数を0で設定
        movie.setViews(0);
        movie.setTitle(movieForm.getTitle());

        //movieテーブルに登録し、その際のmovie.idを戻り値として取得している
        int lastMovieId = movieService.save(movie);
        // createMovie.add(movie);

        //ここからImage
        // Image image = new Image();
        image.setMovie_id(lastMovieId);

        //サムネイル画像が投稿されているかどうかで処理を分岐
        if(movieForm.getThumbnail().getOriginalFilename().equals(movieForm.getMovie().getOriginalFilename())) {
            image.setImage("/images/noImage.jpg");
        } else {
            StringBuffer data = new StringBuffer();
            String base64     = Base64.getEncoder().encodeToString(movieForm.getThumbnail().getBytes());
            data.append("data:image/;base64,");
            data.append(base64);
            image.setImage(data.toString());
            }

        imageService.save(image);
        // createMovie.add(image);

        return "OK";
    }

    @PostMapping("/delete")
    // @ResponseBody
    public String delete (
        //hiddenのname属性をキーとしてvalueを受け取り変数movieIdに格納している
        @RequestParam("movieId") int movieId,
        @RequestParam("loginUserId") int loginUserId,
        Model model) {

        //hiddenで送られてきたmovie.idをもとに投稿者のuser_idを取得する
        Optional<Movie> movieOpt = movieService.getUserIdByMovieId(movieId);
        if(movieOpt.isPresent()) {
            movie = movieOpt.get();
        }

        if(movie.getUserId() != loginUserId) {
            // model.addAttribute("error1", "エラー：ログイン中のユーザーと動画の投稿者が一致しませんでした。");
            // return "errorMessage";
        }

        //動画を一件削除しリダイレクト
        movieService.deleteById(movieId);
        return "OK";
    } 
    
    @PostMapping("/search")
    // @ResponseBody
    //@RequestParamsで受け取れなかったため、@RequestBodyで代替
    public List<Object> search (@RequestParam("searchWord") String searchWord,SearchForm searchForm, Model model) {
        //不要なダブルクォーテーションをエスケープする
        // body = body.replaceAll("\"", "");
        List<Object> searchResultsListsInfo =  new ArrayList<>();
        if("".equals(searchForm.getSearchWord())) {
            // return "index";
        } else {
            List<Movie> searchResultsLists = movieService.findBySearchWordLike("%" + searchForm.getSearchWord() + "%");
            // List<Movie> searchResultsLists = movieService.findBySearchWordLike("%" + body + "%");
            model.addAttribute("searchResultList", searchResultsLists);
            model.addAttribute("searchResultListSize", searchResultsLists.size());
            searchResultsListsInfo.add(searchResultsLists);
            searchResultsListsInfo.add(searchResultsLists.size());
        }
        return searchResultsListsInfo;
    }

    //新規追加(ログインユーザーIDのみをかえす)
    @GetMapping("/auth")
    // @ResponseBody
    public int auth(@AuthenticationPrincipal DbUserDetails userDetails){
        return userDetails.getUserId();
    }    
}