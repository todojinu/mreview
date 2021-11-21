package org.zerock.mreview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.mreview.entity.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {  //<엔티티타입, @Id타입>

    //두 개의 엔티티가 1:N 관계를 가지며 JPQL로 객체를 조회할 때 N+1 문제가 발생할 수 있다
    // 1.EAGER 전략으로 데이터를 가져오는 경우
    // 2.LAZY 전략으로 데이터를 가져온 이후에 가져온 데이터에서 하위 엔티티를 다시 조회하는 경우
    // (해결방안)
    // - 자동으로 연결하도록 함
    // - 서브쿼리를 사용

    //inum가 가장 작은 MovieImage를 가져옴
//    @Query("select m, mi, avg(coalesce(r.grade, 0)), count(distinct r)" +
//            " from Movie m" +
//            " left outer join MovieImage mi on mi.movie = m" +
//            " left outer join Review r on r.movie = m" +
//            " group by m")

    //inum가 가장 큰 MovieImage를 가져옴
    @Query("select m, mi, avg(coalesce(r.grade, 0)), count(distinct r)" +
            " from Movie m" +
            " left outer join MovieImage mi on mi.movie = m" +
            " and mi.inum = (select max(mi2.inum) from MovieImage mi2 where mi2.movie = m)" +
            " left outer join Review r on r.movie = m" +
            " group by m")
    Page<Object[]> getListPage(Pageable pageable);  // 페이지 처리

    @Query("select m, mi, avg(coalesce(r.grade, 0)), count(r)" +
            " from Movie m" +
            " left outer join MovieImage mi on mi.movie = m" +
            " left outer join Review r on r.movie = m" +
            " where m.mno = :mno" +
            " group by m, mi"
    )
    List<Object[]> getMovieWithAll(Long mno);  // 특정 영화 조회

}
