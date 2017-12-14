package org.superbiz.moviefun.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.superbiz.moviefun.MoviesBean;
import org.superbiz.moviefun.Movie;
import java.util.Map;

@Controller
public class HomeController {

    private final MoviesBean moviesBean;

    public HomeController(MoviesBean bean)
    {
        this.moviesBean = bean;
    }

    @RequestMapping(value="/setup", method=RequestMethod.GET)

    private String setup(Map<String, Object> model)
    {

        moviesBean.addMovie(new Movie("Wedding Crashers", "David Dobkin", "Comedy", 7, 2005));
        moviesBean.addMovie(new Movie("Starsky & Hutch", "Todd Phillips", "Action", 6, 2004));
        moviesBean.addMovie(new Movie("Shanghai Knights", "David Dobkin", "Action", 6, 2003));
        moviesBean.addMovie(new Movie("I-Spy", "Betty Thomas", "Adventure", 5, 2002));
        moviesBean.addMovie(new Movie("The Royal Tenenbaums", "Wes Anderson", "Comedy", 8, 2001));
        moviesBean.addMovie(new Movie("Zoolander", "Ben Stiller", "Comedy", 6, 2001));
        moviesBean.addMovie(new Movie("Shanghai Noon", "Tom Dey", "Comedy", 7, 2000));

        model.put("movies", moviesBean.getMovies());

        return "setup";
    }

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String homePage()
    {
        return "index";
    }


    @RequestMapping(value="/moviefun", method=RequestMethod.GET)
    public String indexPage(Map<String, Object> model)
    {

        model.put("movies", moviesBean.getMovies());

        return "moviefun";

    }
}
