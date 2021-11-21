package org.zerock.mreview.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.MovieImage;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class MovieRepositoryTests {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieImageRepository movieImageRepository;

    @Commit  //테이스 케이스 @Transactional을 사용하면 테스트 이후 롤백 되므로 @Commit을 사용해 저장
    @Transactional
    @Test
    public void insertMovies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Movie movie = Movie.builder().title("Movie...." + i).build();

            System.out.println("----------------------------------");

            movieRepository.save(movie);

            int count = (int) (Math.random() * 5) + 1;  //1,2,3,4

            for (int j = 0; j < count; j++) {
                MovieImage movieImage = MovieImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .movie(movie)
                        .imgName("test" + j + ".jpg").build();

                movieImageRepository.save(movieImage);
            }

            System.out.println("======================================");
        });
    }

    @Test
    public void testListPage() {

        PageRequest pageRequest = PageRequest.of(1, 10, Sort.by(Sort.Direction.DESC, "mno"));

        Page<Object[]> result = movieRepository.getListPage(pageRequest);

        for (Object[] objects : result.getContent()) {
            System.out.println(Arrays.toString(objects));
            System.out.println("----------------------------------");
        }

    }

    @Test
    public void testMovieWithAll() {

        List<Object[]> result = movieRepository.getMovieWithAll(102L);

        System.out.println(result);
        System.out.println("===================================");

        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
