package org.zerock.mreview.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.mreview.entity.Member;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    /* @EntityGraph
     * -엔티티의 특정한 속성을 같이 로딩하도록 표시하는 어노테이션
     * -연관관계의 FETCH 속성이 LAZY로 되어있을때, 특정 기능을 수행할 때만 EAGER로딩을 하도록 지정할 수 있다.
     * -@EntityGraph의 속성
     *  > attributePaths: 로딩 설정을 변경하고 싶은 속성의 이름을 배열로 명시
     *  > type: @EntityGraph를 어떤 방식으로 적용할 것인지를 설정
     *  > FETCH 속성값은 attibutePaths에 명시한 속성은 EAGER로 처리하고 나머지는 LAZY로 처리함
     *  > LOAD 속성값은 attibutePaths에 명시한 속성은 EAGER로 처리하고 나머지는 엔티티 클래스에 명시되거나 기본 방식으로 처리함
     */
    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)  // Review를 처리할 떄 member도 같이 로딩된다
    List<Review> findByMovie(Movie movie);

    //기본쿼리로 member를 사용해 삭제하면, member에 대한 review의 개수만큼 삭제쿼리가 반복된다
    //->@Query를 사용해 where절을 지정하는 것이 낫다
    @Modifying
    @Query("delete from Review r where r.member = :member")
    void deleteByMember(Member member);

}
